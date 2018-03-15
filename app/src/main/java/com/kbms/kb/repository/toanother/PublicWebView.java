package com.kbms.kb.repository.toanother;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kbms.kb.R;


/**
 * 通用的webview，只需要传入2个参数即可， param1:title 标题 param2:url 网站
 */
/*
public class PublicWebView extends Activity {
    private WebView webview;
    private ImageButton back;
    private LinearLayout public_webview_top;
    private boolean showTitle;
    private ProgressBar progressBar;

    @SuppressLint(JavascriptInterface)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.public_webview);
        Intent intent = getIntent();
        String url = intent.getStringExtra(url);
        String title = intent.getStringExtra(title);
        showTitle = intent.getBooleanExtra(show_title, true);

        public_webview_top = (LinearLayout) findViewById(R.id.public_webview_top);
        if (showTitle) {
            public_webview_top.setVisibility(View.VISIBLE);
        } else {
            public_webview_top.setVisibility(View.GONE);
        }
        TextView titletv = (TextView) findViewById(R.id.public_webview_title);
        titletv.setText(title);
        progressBar = (ProgressBar) findViewById(R.id.public_webview_progressbar);

        webview = (WebView) findViewById(R.id.public_webview_webview);
        // 加载需要显示的网页
        webview.addJavascriptInterface(new JavascriptInterface(PublicWebView.this), Phoenix_Android);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);
        // 加载需要显示的网页
        webview.loadUrl(url);
        // 设置Web视图
        webview.setWebViewClient(new MyWebViewClient());
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        back = (ImageButton) findViewById(R.id.public_webview_back);
        back.setOnClickListener(new BackListener(PublicWebView.this, null, 1));
    }
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            // webview.goBack();// 返回前一个页面
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //// 监听 所有点击的链接，如果拦截到我们需要的，就跳转到相对应的页面。
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            System.out.println("-------------shouldOverrideUrlLoading" + url);
            if (url != null && url.contains(/m/phoneRegiste.do)) {
                Intent intent = new Intent(PublicWebView.this, RegisterByPhone.class);
                PublicWebView.this.startActivity(intent);
                finish();
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);
        }
    }

}
@SuppressLint(NewApi)
public class JavascriptInterface {
    @SuppressWarnings(unused)
    private Context context;

    public JavascriptInterface(Context context) {
        this.context = context;
    }

    public void back(String module, int status, String place) {
        reFreshBlance();
        if (status == -1) { // 用户取消了
            return;
        } else if (status == 0) {
            Toast.makeText(PublicWebView.this, "充值请求失败,请稍后重试...,", Toast.LENGTH_SHORT).show();
            return;
        } else if (status == 1) {
            // 充值请求成功
            reFreshBlance();
            // 1.用户中心进来的有两个参数
            // a.bet参数是返回购彩界面
            // b.userCenter返回用户中心
        }
    }
}
*/
