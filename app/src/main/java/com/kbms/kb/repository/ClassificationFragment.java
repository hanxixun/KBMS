package com.kbms.kb.repository;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kbms.kb.R;
import com.kbms.kb.repository.http.HttpContent;

/**
 * HomeFragment
 *
 * @author: hanxixun
 * @time: 2016/8/20 19:07
 */
public class ClassificationFragment extends Fragment {
    private WebView WebViewTab02;
    private WebSettings webSettings;
    private String url = HttpContent.URL + "webtype/view/Pub_mobile.do";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1: {
                    webViewGoBack();
                }
                break;
            }
        }
    };

    private void webViewGoBack() {
        WebViewTab02.goBack();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab02, null);
        WebViewTab02 = (WebView) view.findViewById(R.id.webviewTab02);
        webSettings = WebViewTab02.getSettings();
        WebViewFunction();
        //如果webView中需要用户手动输入用户名、密码或其他，则webview必须设置支持获取手势焦点。
        WebViewTab02.requestFocusFromTouch();
        WebViewTab02.loadUrl(url);
        WebViewTab02.setWebViewClient(new Tab02WebViewClient());
        WebViewTab02.setWebChromeClient(new WebChromeClient());
        WebViewTab02.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && WebViewTab02.canGoBack()) {
                    handler.sendEmptyMessage(1);
                    return true;
                }
                return false;
            }

        });
        return view;
    }

    private class Tab02WebViewClient extends WebViewClient {
        /**
         * 当加载的网页需要重定向的时候就会回调这个函数告知我们应用程序是否需要接管控制网页加载，
         * 如果应用程序接管，并且return true意味着主程序接管网页加载，如果返回false让webview自己处理。
         *
         * @param view
         * @param url
         * @return
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void WebViewFunction() {
        //支持js
        webSettings.setJavaScriptEnabled(true);
        //将图片调整到适合webview的大小和设置关键点
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        //适应屏幕，内容将自动缩放
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //加载图片
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //多窗口
        webSettings.supportMultipleWindows();
        //支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 允许访问文件
        webSettings.setAllowFileAccess(true);
        //设置 缓存模式
        webSettings.setCacheMode(webSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        webSettings.setDomStorageEnabled(true);
    }
}




