package com.kbms.kb.streetsdk;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kbms.kb.R;
import com.kbms.kb.streetsdkfragment.PanoramaFragment;
import com.kbms.kb.streetsdkfragment.PanoramaFragmentController;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.mapsdk.raster.model.BitmapDescriptorFactory;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.TencentMap;
import com.tencent.tencentmap.streetviewsdk.Marker;
import com.tencent.tencentmap.streetviewsdk.StreetViewPanorama;
import com.tencent.tencentmap.streetviewsdk.StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener;
import com.tencent.tencentmap.streetviewsdk.StreetViewPanorama.OnStreetViewPanoramaChangeListener;
import com.tencent.tencentmap.streetviewsdk.StreetViewPanorama.OnStreetViewPanoramaFinishListner;
import com.tencent.tencentmap.streetviewsdk.StreetViewPanoramaCamera;
import com.tencent.tencentmap.streetviewsdk.StreetViewPanoramaView;

/**
 * PanoramaActivity
 *
 * @author: Yun Zhenhuan
 * @time: 2016/2/25
 */
public class PanoramaActivity extends Activity implements OnStreetViewPanoramaChangeListener, OnStreetViewPanoramaFinishListner, OnStreetViewPanoramaCameraChangeListener {
    // private PanoramaFragment mPanoramaFragment;
    // private PanoramaFragmentController mPanoramaFragmentController;

    private StreetViewPanoramaView mPanoramaView;
    private StreetViewPanorama mPanorama;

    private RelativeLayout mControlPanel;
    private CheckBox cbGuidance;
    private CheckBox cbGestures;
    private CheckBox cbStreet;
    private CheckBox cbZoom;
    private CheckBox cbNavigation;
    private CheckBox cbGallery;
    private CheckBox cbScenceName;
    private CheckBox cbIndoorLink;
    private Button auto_locate;

    private com.tencent.mapsdk.raster.model.Marker location;

    private TencentLocationManager tLocationMan;
    private TencentLocationRequest tLocationReq;
    private TencentLocationListener tLocationListener;

    private Animation controlAppear;
    private Animation controlVanish;

    private boolean streetViewPanoramaState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panarama_activity);
        init();
    }

    private void init() {

        //ActivityManager fragemtManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);fragemtManager.getL
        //mPanoramaFragment = (PanoramaFragment) fragemtManager.findAcById(R.id.panorama_fragment);
        //mPanoramaFragmentController = mPanoramaFragment.getPanoramaFragmentController();

        mPanoramaView = (StreetViewPanoramaView) findViewById(R.id.panorama_view);
        mPanorama = mPanoramaView.getStreetViewPanorama();

        //初始化地点为中北大学
        mPanorama.setPosition(38.0142792507, 112.4540698528);
        mPanorama.setOnStreetViewPanoramaChangeListener(this);
        mPanorama.setOnStreetViewPanoramaFinishListener(this);
        mPanorama.setOnStreetViewPanoramaCameraChangeListener(this);
        mControlPanel = (RelativeLayout) findViewById(R.id.layout_orientation_panel);
        mControlPanel.setAnimationCacheEnabled(true);
        mControlPanel.setPersistentDrawingCache(ViewGroup.PERSISTENT_ANIMATION_CACHE);
        cbGestures = (CheckBox) findViewById(R.id.cb_gestures);
        cbGuidance = (CheckBox) findViewById(R.id.cb_guidance);
        cbStreet = (CheckBox) findViewById(R.id.cb_streetname);
        cbZoom = (CheckBox) findViewById(R.id.cb_zoom);
        cbNavigation = (CheckBox) findViewById(R.id.cb_navigation);
        cbGallery = (CheckBox) findViewById(R.id.cb_gallery);
        cbScenceName = (CheckBox) findViewById(R.id.cb_scence_name);
        cbIndoorLink = (CheckBox) findViewById(R.id.cb_indoor_link);
        auto_locate = (Button) findViewById(R.id.auto_locate);

        cbGestures.setChecked(mPanorama.isPanningGesturesEnabled());
        cbGuidance.setChecked(mPanorama.isIndoorGuidanceEnabled());
        cbStreet.setChecked(mPanorama.isStreetNamesEnabled());
        cbZoom.setChecked(mPanorama.isZoomGesturesEnabled());
        cbNavigation.setChecked(mPanorama.isUserNavigationEnabled());
        cbGallery.setChecked(mPanorama.isStreetGalleryEnabled());
        cbScenceName.setChecked(mPanorama.isScenceNameEnabled());
        cbIndoorLink.setChecked(mPanorama.isIndoorLinkPoiEnabled());

        addMarker();
        bindPanleListener();
        bindCheckBoxListener();
        setAnimation();

        //位置监听
        tLocationListener = new TencentLocationListener() {
            @Override
            public void onStatusUpdate(String arg0, int arg1, String arg2) {
            }

            @Override
            public void onLocationChanged(TencentLocation arg0, int arg1, String arg2) {
                if (arg1 == TencentLocation.ERROR_OK) {
                    LatLng latLng = new LatLng(arg0.getLatitude(), arg0.getLongitude());//根据经纬度获取坐标
                     setPanoramaPosition(latLng);//按坐标设置全景位置
                   // tLocationMan.removeUpdates(tLocationListener);
                }
            }
        };
        tLocationMan = TencentLocationManager.getInstance(this);//获取位置管理
        tLocationReq = TencentLocationRequest.create().setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_GEO);
        //自动定位
        auto_locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int res;
                if (auto_locate.getText().toString().equals("开启自动定位")) {
                    res = tLocationMan.requestLocationUpdates(tLocationReq, tLocationListener);
                    if (res == 0) {
                        auto_locate.setText("重新自动定位");
                        ToastUtil.showLongToast(PanoramaActivity.this, "开始定位");
                    } else if (res == 1) {
                        ToastUtil.showLongToast(PanoramaActivity.this, "定位条件不满足或系统不允许定位");
                    } else if (res == 2) {
                        ToastUtil.showLongToast(PanoramaActivity.this, "key错误");
                    }
                } else  if (auto_locate.getText().toString().equals("重新自动定位")) {
                    res = tLocationMan.requestLocationUpdates(tLocationReq, tLocationListener);
                    if (res == 0) {
                        auto_locate.setText("重新自动定位");
                        ToastUtil.showLongToast(PanoramaActivity.this, "开始定位");
                    } else if (res == 1) {
                        ToastUtil.showLongToast(PanoramaActivity.this, "定位条件不满足或系统不允许定位");
                    } else if (res == 2) {
                        ToastUtil.showLongToast(PanoramaActivity.this, "key错误");
                    }
                }/*else{
                    tLocationMan.removeUpdates(tLocationListener);
                    auto_locate.setText("开启自动定位");
                }*/
            }
        });
    }

    //添加标记
    private void addMarker() {
        final Marker marker1 = new Marker(38.0142031755, 112.4494349957, convertViewToBitmap(getMarkerLayout("知行广场"))) {
            @Override
            public void onClick(float arg0, float arg1) {
                // TODO Auto-generated method stub
                mPanorama.setPosition(38.0142031755, 112.4494349957);
                ToastUtil.showLongToast(PanoramaActivity.this, "到达知行广场");
                super.onClick(arg0, arg1);
            }
        };
        mPanorama.addMarker(marker1);
        Marker marker2 = new Marker(38.0153020313, 112.4477505684, convertViewToBitmap(getMarkerLayout("图书馆"))) {
            @Override
            public void onClick(float arg0, float arg1) {
                // TODO Auto-generated method stub
                mPanorama.setPosition(38.0153020313, 112.4477505684);
                //使用街景id进入街景
                //mPanorama.setPosition("10011009120328110539700");
                //marker1.updateIcon(convertViewToBitmap(getMarkerLayout("点我到知行广场")));
                ToastUtil.showLongToast(PanoramaActivity.this, "到达图书馆");
                super.onClick(arg0, arg1);
            }

            @Override
            public float onGetItemScale(double arg0, float arg1) {
                // TODO Auto-generated method stub
                //Log.i("marker", "distance:" + arg0 +"; angleScale:" + arg1);
                return super.onGetItemScale(0.2 * arg0, arg1);
            }
        };
        mPanorama.addMarker(marker2);
        final Marker marker3 = new Marker(38.0093511074, 112.4426972866, convertViewToBitmap(getMarkerLayout("一道门"))) {
            @Override
            public void onClick(float arg0, float arg1) {
                // TODO Auto-generated method stub
                mPanorama.setPosition(38.0093511074, 112.4426972866);
                ToastUtil.showLongToast(PanoramaActivity.this, "到达一道门");
                super.onClick(arg0, arg1);
            }
        };
        mPanorama.addMarker(marker3);

        final Marker marker4 = new Marker(38.0141862699, 112.4560546875, convertViewToBitmap(getMarkerLayout("田园"))) {
            @Override
            public void onClick(float arg0, float arg1) {
                // TODO Auto-generated method stub
                mPanorama.setPosition(38.0141862699, 112.4560546875);
                ToastUtil.showLongToast(PanoramaActivity.this, "到达田园");
                super.onClick(arg0, arg1);
            }
        };
        mPanorama.addMarker(marker4);
        final Marker marker5 = new Marker(38.0122167405, 112.4435234070, convertViewToBitmap(getMarkerLayout("二道门"))) {
            @Override
            public void onClick(float arg0, float arg1) {
                // TODO Auto-generated method stub
                mPanorama.setPosition(38.0122167405, 112.4435234070);
                ToastUtil.showLongToast(PanoramaActivity.this, "到达二道门");
                super.onClick(arg0, arg1);
            }
        };
        mPanorama.addMarker(marker5);
        final Marker marker6 = new Marker(38.0148920755, 112.4449396133, convertViewToBitmap(getMarkerLayout("工商银行"))) {
            @Override
            public void onClick(float arg0, float arg1) {
                // TODO Auto-generated method stub
                mPanorama.setPosition(38.0148920755, 112.4449396133);
                ToastUtil.showLongToast(PanoramaActivity.this, "到达工商银行");
                super.onClick(arg0, arg1);
            }
        };
        mPanorama.addMarker(marker6);
    }

    private void bindPanleListener() {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlVanish.reset();
                controlVanish.start();
            }
        };
    }

    //绑定复选框监听
    private void bindCheckBoxListener() {
        OnCheckedChangeListener listener = new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                switch (buttonView.getId()) {
                    case R.id.cb_gestures:
                        mPanorama.setPanningGesturesEnabled(isChecked);
                        break;
                    case R.id.cb_guidance:
                        mPanorama.setIndoorGuidanceEnabled(isChecked);
                        break;
                    case R.id.cb_streetname:
                        mPanorama.setStreetNamesEnabled(isChecked);
                        break;
                    case R.id.cb_zoom:
                        mPanorama.setZoomGesturesEnabled(isChecked);
                        break;
                    case R.id.cb_navigation:
                        mPanorama.setUserNavigationEnabled(isChecked);
                        break;
                    case R.id.cb_gallery:
                        mPanorama.setStreetGalleryEnabled(isChecked);
                        break;
                    case R.id.cb_scence_name:
                        mPanorama.setScenceNameEnabled(isChecked);
                        break;
                    case R.id.cb_indoor_link:
                        mPanorama.setIndoorLinkPoiEnabled(isChecked);
                        break;
                    default:
                        break;
                }
            }
        };
        cbGestures.setOnCheckedChangeListener(listener);
        cbGuidance.setOnCheckedChangeListener(listener);
        cbStreet.setOnCheckedChangeListener(listener);
        cbZoom.setOnCheckedChangeListener(listener);
        cbNavigation.setOnCheckedChangeListener(listener);
        cbGallery.setOnCheckedChangeListener(listener);
        cbScenceName.setOnCheckedChangeListener(listener);
        cbIndoorLink.setOnCheckedChangeListener(listener);
    }

    //设置动画
    private void setAnimation() {
        controlAppear = AnimationUtils.loadAnimation(this, R.anim.control_appear);
        controlAppear.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                mControlPanel.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isStreetViewPanoramaFinish()) {
                    mControlPanel.setAnimation(controlVanish);
                }
            }
        });

        controlVanish = AnimationUtils.loadAnimation(PanoramaActivity.this, R.anim.control_vanish);
        controlVanish.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mControlPanel.setVisibility(View.GONE);
            }
        });

    }

    //获取标记布局
    private LinearLayout getMarkerLayout(String title) {
        LayoutInflater layInflater = getLayoutInflater();
        LinearLayout markerLayout = (LinearLayout) layInflater.inflate(R.layout.marker, null);
        TextView tvMarkerTitle = (TextView) markerLayout.findViewById(R.id.marker_title);
        tvMarkerTitle.setText(title);
        return markerLayout;
    }

    private Bitmap convertViewToBitmap(View view) {
        view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    //设置全景位置
    protected void setPanoramaPosition(LatLng latLng) {
      //  if (location == null) {
            //location = mTencentMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.location)));
      //  } else {
           // location.setPosition(latLng);
      //  }
        mPanorama.setPosition(latLng.getLatitude(), latLng.getLongitude());
    }

    private void setStreetViewPanoramaState(boolean state) {
        streetViewPanoramaState = state;
    }

    private boolean isStreetViewPanoramaFinish() {
        return streetViewPanoramaState;
    }

    @Override
    public void OnStreetViewPanoramaFinish(boolean arg0) {
        // TODO Auto-generated method stub
        Log.i("change", "panorama finish");
        setStreetViewPanoramaState(true);
        mControlPanel.startAnimation(controlVanish);
    }

    @Override
    public void onStreetViewPanoramaChange(String arg0) {
        // TODO Auto-generated method stub
        setStreetViewPanoramaState(false);
        if (mControlPanel.getVisibility() != View.GONE) {
            return;
        }
        mControlPanel.startAnimation(controlAppear);
    }

    @Override
    public void onStreetViewPanoramaCameraChange(final StreetViewPanoramaCamera arg0) {
        // TODO Auto-generated method stub
//		Log.i("camera", "camera zoom:" + arg0.zoom + 
//				"\ncamera tilt:" + arg0.tilt + "\ncamera bearing:" + arg0.bearing);
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (mControlPanel.getVisibility() == View.GONE) {
                    mControlPanel.startAnimation(controlAppear);
                } else {
                    controlVanish.reset();
                    controlVanish.start();
                }
            }
        });
    }
}
