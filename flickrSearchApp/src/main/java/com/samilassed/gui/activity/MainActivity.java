package com.samilassed.gui.activity;

import com.samilassed.R;
import com.samilassed.gui.activity.fragment.PhotosFragment;
import com.samilassed.gui.activity.fragment.SearchFragment;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {

	private static final String SEARCH_FRAGMENT_TAG = "SEARCH_FRAGMENT";
	private static final String PHOTOS_FRAGMENT_TAG = "PHOTOS_FRAGMENT";
	private static final String LOADING_FRAGMENT_TAG = "LOADING_FRAGMENT";
	
	private static final int SEARCH_FRAGMENT = 1;
	private static final int PHOTOS_FRAGMENT = 2;
	private static final int LOADING_FRAGMENT = 3;
	
	private static FragmentManager fragmentManager;
	private static Fragment currentFragment;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Add search fragment
        moveToFragment(this, SEARCH_FRAGMENT, null);
    }
    
    protected static synchronized void moveToFragment(Activity mainActivity, int id, Bundle bundle){
    	
    	fragmentManager = mainActivity.getFragmentManager();
    	/*if(fragmentManager == null){
    		fragmentManager = mainActivity.getFragmentManager();
    	}*/
    	String tag;
    	switch (id){
    		case SEARCH_FRAGMENT:
    			currentFragment = fragmentManager.findFragmentByTag(SEARCH_FRAGMENT_TAG);
    			if(currentFragment == null){
    				currentFragment = new SearchFragment();
    			}
    			tag = SEARCH_FRAGMENT_TAG;
    			break;
    		case PHOTOS_FRAGMENT:
    			currentFragment = fragmentManager.findFragmentByTag(PHOTOS_FRAGMENT_TAG);
    			if(currentFragment == null){
    				currentFragment = new PhotosFragment();
    			}
    			tag = PHOTOS_FRAGMENT_TAG;
    			break;
    		case LOADING_FRAGMENT:
    			currentFragment = fragmentManager.findFragmentByTag(LOADING_FRAGMENT_TAG);
    			if(currentFragment == null){
    				//currentFragment = new LoadingFragment();
    			}
    			tag = LOADING_FRAGMENT_TAG;
    			break;
			default:
				currentFragment = new SearchFragment();
				tag = SEARCH_FRAGMENT_TAG;
				break;
    	}
    	if( bundle!= null ){
    		currentFragment.setArguments(bundle);
    	}
    	fragmentManager.beginTransaction().replace(R.id.mainContainer, currentFragment, tag).commit();
    }
    
    public void moveToPhotosFragment(Bundle bundle){
    	moveToFragment(this, PHOTOS_FRAGMENT, bundle);
    }
    
    public void moveToSearchFragment(Bundle bundle){
    	moveToFragment(this, SEARCH_FRAGMENT, bundle);
    }
	
    /*public void moveToLoadingFragment(){
    	moveToFragment(this, LOADING_FRAGMENT);
    }*/
    
}
