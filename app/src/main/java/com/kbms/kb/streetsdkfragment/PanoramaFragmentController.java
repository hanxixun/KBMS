package com.kbms.kb.streetsdkfragment;

import com.tencent.tencentmap.streetviewsdk.StreetViewPanorama;

public class PanoramaFragmentController {

	private static PanoramaFragmentController mController;
	private StreetViewPanorama mStreetViewPanorama;

	private PanoramaFragmentController (PanoramaFragment panoramaFragment) {
		mStreetViewPanorama = panoramaFragment.getStreetView().getStreetViewPanorama();
	}
	
	protected static PanoramaFragmentController getInstance(PanoramaFragment panoramaFragment) {
		if (mController == null ) {
			mController = new PanoramaFragmentController(panoramaFragment);
		}
		return mController;
	}
	
	public void setPosition(double latitude, double longitude) {
		mStreetViewPanorama.setPosition(latitude, longitude);
	}
	
	public void setPosition(String panoramaid) {
		mStreetViewPanorama.setPosition(panoramaid);
	}
	
	public void setPosition(double latitude, double longitude, int radius) {
		mStreetViewPanorama.setPosition(latitude, longitude, radius);
	}
	
	public float getPanoramaTilt() {
		return mStreetViewPanorama.getPanoramaTilt();
	}
	
	public void setPanoramaTilt(float tilt) {
		mStreetViewPanorama.setPanoramaTilt(tilt);
	}
	
	public float getPanoramaBearing() {
		return mStreetViewPanorama.getPanoramaBearing();
	}
	
	public void setPanoramaBearing(float bearing) {
		mStreetViewPanorama.setPanoramaBearing(bearing);
	}
}
