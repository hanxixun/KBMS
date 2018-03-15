package com.kbms.kb.streetsdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.kbms.kb.R;
import com.kbms.kb.flipper.activity_test.Main;

/**
 * WellComeActivity
 *
 * @author: Yun Zhenhuan
 * @time: 2016/2/25
 */
public class WellComeActivity extends Activity {
    private static String TAG = "WellComeActivity";
	@SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
        setContentView(R.layout.wellcome);
       //利用线程延迟时间跳转主界面
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent mainIntent = new Intent(WellComeActivity.this, Main.class);
                WellComeActivity.this.startActivity(mainIntent);
                WellComeActivity.this.finish();
            }
        }, 2900);
    }
}
