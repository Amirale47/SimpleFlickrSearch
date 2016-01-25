package com.samilassed.gui.activity.fragment;

import com.samilassed.R;
import com.samilassed.gui.activity.MainActivity;
import android.app.Fragment;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class SearchFragment extends Fragment implements OnClickListener{

	public static final String SEARCH_TAG_KEY = "SEARCH_TAG_KEY";
	
	private ImageView searchBarIV, iconIV;
    private TextView text;
    private AnimatedVectorDrawable searchToBar;
    private AnimatedVectorDrawable barToSearch;
    private float offset;
    private Interpolator interp;
    private int duration;
    private boolean expanded = false;
	
	public SearchFragment() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.search_fragment, container, false);
		
        searchBarIV = (ImageView) rootView.findViewById(R.id.search);
        searchBarIV.setOnClickListener(this);
        iconIV = (ImageView) rootView.findViewById(R.id.searchIcon);
        iconIV.setOnClickListener(this);
        text = (TextView) rootView.findViewById(R.id.text);
        text.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Bundle searchTag = new Bundle();
                    searchTag.putString(SEARCH_TAG_KEY, text.getText().toString().trim());
                	((MainActivity)getActivity()).moveToPhotosFragment(searchTag);
                	//startActivity(new Intent(getActivity(), FriendsActivity.class));
                }    
                return false;
            }
        });
        searchToBar = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_search_to_bar);
        barToSearch = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_bar_to_search);
        interp = AnimationUtils.loadInterpolator(this.getActivity(), android.R.interpolator.linear_out_slow_in);
        duration = getResources().getInteger(R.integer.duration_bar);
        // iv is sized to hold the search+bar so when only showing the search icon, translate the
        // whole view left by half the difference to keep it centered
        offset = -71f * (int) getResources().getDisplayMetrics().scaledDensity;
        searchBarIV.setTranslationX(offset);
        
        rootView.setOnClickListener(this);
        
        return rootView;
    }
	
    public void animate() {

        if (!expanded) {
            searchBarIV.setImageDrawable(searchToBar);
            searchToBar.start();
            text.setVisibility(View.VISIBLE);
            iconIV.setVisibility(View.GONE);
            searchBarIV.setVisibility(View.VISIBLE);
            searchBarIV.animate().translationX(0f).setDuration(duration).setInterpolator(interp);
            text.animate().alpha(1f).setStartDelay(duration - 100).setDuration(100).setInterpolator(interp);
        } else {
            searchBarIV.setImageDrawable(barToSearch);
            barToSearch.start();
            text.setVisibility(View.GONE);
            searchBarIV.animate().translationX(offset).setDuration(duration).setInterpolator(interp);
            text.setAlpha(0f);
        }
        expanded = !expanded;
    }

	@Override
	public void onClick(View v) {
		animate();
	}
	
}
