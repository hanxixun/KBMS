package com.kbms.kb.flipper.expandgroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.kbms.kb.R;

public class About{
	private Context mContext;
	private View mAbout;
	public About(Context context) {
		mContext = context;
		mAbout = LayoutInflater.from(context).inflate(R.layout.about, null);
		TextView textView = (TextView)mAbout.findViewById(R.id.chat_title);
		textView.setText("KB_知识库-V2.0.0");
	}
	public View getView() {
		return mAbout;
	}
}
