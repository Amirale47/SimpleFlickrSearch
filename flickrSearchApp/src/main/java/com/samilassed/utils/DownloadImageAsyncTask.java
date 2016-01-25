package com.samilassed.utils;

import java.io.File;
import java.util.ArrayList;

import com.samilassed.R;
import com.samilassed.utils.gui.RoundedImage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class DownloadImageAsyncTask extends AsyncTask<FlickrPhoto,Void,Bitmap>{

	private static final ArrayList<Integer> DOWNLOADING_LIST = new ArrayList<Integer>();

	private ImageView imageView;
	private Context cnt;
	private boolean rounded;

	public DownloadImageAsyncTask(Context cnt,ImageView imageView,boolean rounded) {
		this.cnt = cnt;
		this.imageView = imageView;
		this.rounded = rounded;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if(rounded){
			imageView.setImageDrawable( new RoundedImage(BitmapFactory.decodeResource(cnt.getResources(), R.drawable.imageholder)));
		}else{
			imageView.setImageBitmap( BitmapFactory.decodeResource(cnt.getResources(), R.drawable.imageholder));
		}
	}

	@Override
	protected Bitmap doInBackground(final FlickrPhoto... params) {
		final String imageId =  params[0].getId();

		String filePath = Utility.getImagePath(imageId);
		File file = new File(filePath);
		Bitmap userBitmap;
		if(file.exists()){
			userBitmap = BitmapFactory.decodeFile(filePath);
			return userBitmap;
		}else{
			new Thread(new Runnable() {
				@Override
				public void run() {
					Helper.downloadImage(params[0]);
				}
			}).start();
			return null;
		}
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);

		if(this.imageView != null && result != null){
			if (rounded) {
				imageView.setImageDrawable( new RoundedImage(result));
			} else {
				imageView.setImageBitmap(result);
			}
		}

	}

	public static synchronized ArrayList<Integer> getDownloadingList() {
		return DOWNLOADING_LIST;
	}

}
