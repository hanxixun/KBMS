package com.kbms.kb.streetsdk;

import android.content.Context;
import android.widget.Toast;

/**
 * ToastUtil
 *
 * @author: Yun Zhenhuan
 * @time: 2016/2/25
 */
public class ToastUtil {
	public static void showLongToast(Context context, String text) {
		Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
		toast.show();
	}
}
