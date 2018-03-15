package com.kbms.kb.streetsdkfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kbms.kb.R;
import com.tencent.tencentmap.streetviewsdk.StreetViewPanoramaView;

public class PanoramaFragment extends Fragment {
	
	private StreetViewPanoramaView mPanoramaView;	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.panarama_fragment, container, false);
		mPanoramaView = (StreetViewPanoramaView)view.findViewById(R.id.panorama_view);
		return view;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	public PanoramaFragmentController getPanoramaFragmentController() {
		return PanoramaFragmentController.getInstance(this);
	}
	
	public StreetViewPanoramaView getStreetView() {
		return mPanoramaView;
	}
}
