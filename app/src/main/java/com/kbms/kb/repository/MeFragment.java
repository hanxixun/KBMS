package com.kbms.kb.repository;

import android.content.Context;
import android.content.Intent;
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
import com.kbms.kb.repository.toanother.MyActivity;


/**
 * HomeFragment
 *
 * @author: hanxixun
 * @time: 2016/8/20 19:07
 */
public class MeFragment extends Fragment {
    private WebView WebViewTab05;
    private WebSettings webSettings;
    private String url = HttpContent.URL+"webuser/PubHome_mobile.do";

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
        WebViewTab05.goBack();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab05, null);
        WebViewTab05 = (WebView) view.findViewById(R.id.webviewTab05);
        webSettings = WebViewTab05.getSettings();
        WebViewFunction();
        //如果webView中需要用户手动输入用户名、密码或其他，则webview必须设置支持获取手势焦点。
        WebViewTab05.requestFocusFromTouch();
        WebViewTab05.loadUrl(url);
        WebViewTab05.setWebViewClient(new Tab03WebViewClient());
        WebViewTab05.setWebChromeClient(new WebChromeClient());
        WebViewTab05.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && WebViewTab05.canGoBack()) {
                    handler.sendEmptyMessage(1);
                    return true;
                }
                return false;
            }

        });
        return view;
    }

    private class Tab03WebViewClient extends WebViewClient {
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
            if (url != null && url.contains("/webuser/PubHome_mobile.do?type=know")) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyActivity.class);//从一个activity跳转到另一个activity
                intent.putExtra("url", HttpContent.URL+"webuser/PubHome_mobile.do?type=know");//给intent添加额外数据，key为“url”,key值为"HttpContent.URL+"webuser/PubHome_mobile.do"
                startActivity(intent);
                return true;
            } else if (url != null && url.contains("/webuser/PubHome_mobile.do?type=file")) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyActivity.class);//从一个activity跳转到另一个activity
                intent.putExtra("url", HttpContent.URL+"webuser/PubHome_mobile.do?type=file");
                startActivity(intent);
                return true;
            }else if (url != null && url.contains("/webuser/PubHome_mobile.do?type=joy")) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyActivity.class);//从一个activity跳转到另一个activity
                intent.putExtra("url", HttpContent.URL+"webuser/PubHome_mobile.do?type=joy");
                startActivity(intent);
                return true;
            }else if (url != null && url.contains("/webuser/PubHome_mobile.do?type=group")) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyActivity.class);//从一个activity跳转到另一个activity
                intent.putExtra("url", HttpContent.URL+"webuser/PubHome_mobile.do?type=group");
                startActivity(intent);
                return true;
            }else if (url != null && url.contains("/webuser/PubHome_mobile.do?type=usermessage")) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyActivity.class);//从一个activity跳转到另一个activity
                intent.putExtra("url", HttpContent.URL+"webuser/PubHome_mobile.do?type=usermessage");
                startActivity(intent);
                return true;
            }else if (url != null && url.contains("/webuser/PubHome_mobile.do?type=audito")) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyActivity.class);//从一个activity跳转到另一个activity
                intent.putExtra("url", HttpContent.URL+"webuser/PubHome_mobile.do?type=audito");
                startActivity(intent);
                return true;
            }else if (url != null && url.contains("/webuser/PubHome_mobile.do?type=audith")) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyActivity.class);//从一个activity跳转到另一个activity
                intent.putExtra("url", HttpContent.URL+"webuser/PubHome_mobile.do?type=audith");
                startActivity(intent);
                return true;
            }else if (url != null && url.contains("/webuser/PubHome_mobile.do?type=audit")) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyActivity.class);
                intent.putExtra("url", HttpContent.URL+"webuser/PubHome_mobile.do?type=audit");
                startActivity(intent);
                return true;
            }else if (url != null && url.contains("/webuser/edit_mobile.do")) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyActivity.class);//从一个activity跳转到另一个activity
                intent.putExtra("url", HttpContent.URL+"webuser/edit_mobile.do");
                startActivity(intent);
                return true;
            } else if (url != null && url.contains("/webuser/editCurrentUserPwd_mobile.do")) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyActivity.class);//从一个activity跳转到另一个activity
                intent.putExtra("url", HttpContent.URL+"webuser/editCurrentUserPwd_mobile.do");
                startActivity(intent);
                return true;
            }else if (url != null && url.contains("/resume/view_mobile.do")) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyActivity.class);//从一个activity跳转到另一个activity
                intent.putExtra("url", HttpContent.URL+"resume/view_mobile.do");
                startActivity(intent);
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);
        }
      /*  @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }*/
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