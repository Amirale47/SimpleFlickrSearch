package com.samilassed.gui.activity.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.samilassed.R;
import com.samilassed.flip.model.PhotoInfo;
import com.samilassed.flip.model.data.FlickerMap;
import com.samilassed.utils.FlickrManager;
import com.samilassed.utils.FlickrPhoto;
import com.samilassed.utils.ImagesDownloader;
import com.samilassed.utils.Utility;
import com.yalantis.flipviewpager.adapter.BaseFlipAdapter;
import com.yalantis.flipviewpager.utils.FlipSettings;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PhotosFragment extends Fragment/*Wont use ListFragment*/ {

	public static final int UPDATE = 1;
	
	private ListView imagesLV;
	private PhotosAdapter photosAdapter;
	private FlickerMap flickrMap;
	
	@SuppressLint("HandlerLeak")
	private Handler uiHandler = new Handler(){
		
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
				case UPDATE:
					photosAdapter.notifyDataSetChanged();
					break;
			}
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.photos_fragment, container, false);
		imagesLV = (ListView) root.findViewById(R.id.friends);
		return root;
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String searchTag = getArguments().getString(SearchFragment.SEARCH_TAG_KEY);
		new SearchAsync(searchTag).execute();
	}
	
	/*I wont use progress update for this exam due to time issue.*/
	private class SearchAsync extends AsyncTask<Void,Void,FlickerMap>{
		
		private final String searchTag;
		private ProgressDialog progressDialog;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(getActivity());
			progressDialog.setTitle(R.string.searching);
			progressDialog.show();
		}
		
		public SearchAsync(String searchTag) {
			this.searchTag = searchTag;
		}

		ImagesDownloader downloader;

		@Override
		protected FlickerMap doInBackground(Void... params) {
			
			List<FlickrPhoto> images = FlickrManager.NEW().searchImagesByTag(getActivity(), searchTag);
			
			if(images != null){
				flickrMap = FlickerMap.lazyLoad(images);
				//Run downloading images
				downloader = ImagesDownloader.getInstance(images, uiHandler);
			}else{
				return null;
			}
			
			return flickrMap;
		}
		
		@Override
		protected void onPostExecute(FlickerMap flickrMap) {
			super.onPostExecute(flickrMap);
			if(flickrMap != null){
				FlipSettings settings = new FlipSettings.Builder().defaultPage(1).build();
				photosAdapter = new PhotosAdapter(getActivity(), flickrMap, settings);
				imagesLV.setAdapter(photosAdapter);
		        imagesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		            @Override
		            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		                FlickrPhoto image = (FlickrPhoto) imagesLV.getAdapter().getItem(position);
		                Toast.makeText(getActivity(), image.getFarm(), Toast.LENGTH_SHORT).show();
		            }
		        });
				//Now start downloading images
				downloader.downloadAllImages();
			}else{
				//Raise a dialog for error
				/*************************/
				Toast.makeText(getActivity(), "Error loading data from server", Toast.LENGTH_LONG).show();
			}
			
			progressDialog.dismiss();
		}
		
	}
	
    class PhotosAdapter extends BaseFlipAdapter<FlickrPhoto> {

        private final int PAGES = 3;
        private int[] IDS_INTEREST = {R.id.interest_1, R.id.interest_2, R.id.interest_3, R.id.interest_4, R.id.interest_5};
        
        private FlickerMap flickerMap;

        public PhotosAdapter(Context context, FlickerMap flickerMap/*List<FlickrPhoto> items*/, FlipSettings settings) {
        	super(context, flickerMap.images(), settings);
        	this.flickerMap = flickerMap;
        }

        @Override
        public View getPage(int position, View convertView, ViewGroup parent, FlickrPhoto image1, FlickrPhoto image2) {
            final PhotosHolder holder;

            if (convertView == null) {
                holder = new PhotosHolder();
                convertView = getActivity().getLayoutInflater().inflate(R.layout.photo_item_page, parent, false);
                holder.leftImage = (ImageView) convertView.findViewById(R.id.first);
                holder.rightImage = (ImageView) convertView.findViewById(R.id.second);
                holder.infoPage = getActivity().getLayoutInflater().inflate(R.layout.photo_info, parent, false);
                holder.nickName = (TextView) holder.infoPage.findViewById(R.id.nickname);

                for (int id : IDS_INTEREST)
                    holder.imageTags.add((TextView) holder.infoPage.findViewById(id));

                convertView.setTag(holder);
            } else {
                holder = (PhotosHolder) convertView.getTag();
            }

            switch (position) {
                case 1:
                	fillImage(image1.getId(), holder.leftImage);
                    //holder.leftImage.setImageBitmap(image1.getThumb());
                    //Log.e("Thumb url", image1.getThumbURL());
                    if (image2 != null){
                    	fillImage(image2.getId(), holder.rightImage);
                    	//holder.rightImage.setImageBitmap(image2.getThumb());
                    	//Log.e("Thumb url", image2.getThumbURL());
                    }
                    	
                    break;
                default:
                    fillHolder(holder, position == 0 ? image1 : image2, position);
                    holder.infoPage.setTag(holder);
                    return holder.infoPage;
            }
            return convertView;
        }
        
        private void fillImage(String imageId,ImageView imageView){
        	File imageFile = Utility.getImageFile(imageId); 
        	if(imageFile.exists()){
        		imageView.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
        	}else{
        		imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.imageholder));
        	}
        }
        
        @Override
        public int getPagesCount() {
            return PAGES;
        }

        private void fillHolder(PhotosHolder holder, FlickrPhoto photo, int position) {
            if (photo == null)
                return;
            Iterator<TextView> iViews = holder.imageTags.iterator();
            /*Iterator<String> iInterests = friend.getInterests().iterator();
            while (iViews.hasNext() && iInterests.hasNext())
                iViews.next().setText(iInterests.next());*/
            holder.infoPage.setBackgroundColor(getResources().getColor(isEven(position)?R.color.saffron:R.color.sienna));
            PhotoInfo info = flickerMap.getPhotoInfo(photo.getId());
            if(info.getOwner() != null){
            	holder.nickName.setText(info.getOwner().getUserName() + " (" + info.getOwner().getRealName() + ")");
            }
        }

        class PhotosHolder {
            ImageView leftImage;
            ImageView rightImage;
            View infoPage;

            List<TextView> imageTags = new ArrayList<TextView>();
            TextView nickName;
        }
    }
	
    boolean isEven(int num){
    	return ((num & 1) == 0);
    }
    
}
