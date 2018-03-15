package com.kbms.kb.Settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.kbms.kb.R;

/**
 * Author:XiYang on 2016/3/3.
 * Email:765849854@qq.com
 *
 * @class description
 */

public class Settings extends PreferenceActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Settings","onCreate");
        addPreferencesFromResource(R.xml.settings);
    }
}
