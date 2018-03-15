package com.kbms.kb.flipper.expandgroup;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kbms.kb.R;


public class Text1_3 {
    private static String TAG = "Text1_3";
    private Context mContext;
    private View mWikiMsg;
    private ImageView flip;

    public Text1_3(Context context) {
        mContext = context;
        mWikiMsg = LayoutInflater.from(context).inflate(R.layout.chat, null);
        TextView textView = (TextView) mWikiMsg.findViewById(R.id.text);
        flip = (ImageView) mWikiMsg.findViewById(R.id.message_flip);
        flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "dianji ---", Toast.LENGTH_SHORT).show();//暂时没有可编辑内容

            }
        });
        textView.setText("测试1_35");
    }

    public View getView() {
        return mWikiMsg;
    }
}
