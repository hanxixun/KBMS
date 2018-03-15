package com.kbms.kb.notes;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Author:XiYang on 2016/2/18.
 * Email:765849854@qq.com
 * <p>
 * 显示视频的Activity
 */

public class AtyVideoViewer extends Activity {

    private VideoView vv;

    public static final String EXTRA_PATH = "path";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vv = new VideoView(this);
        vv.setMediaController(new MediaController(this));
        setContentView(vv);

        String path = getIntent().getStringExtra(EXTRA_PATH);
        if (path != null) {
            vv.setVideoPath(path);
        } else {
            finish();
        }
    }
}
