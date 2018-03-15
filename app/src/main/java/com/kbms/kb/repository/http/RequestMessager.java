package com.kbms.kb.repository.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 *
 * @author Yun Zhenhuan
 * @Description
 * @Time 2016年5月11日 下午9:44:49
 */
public class RequestMessager extends HttpUtils implements HttpContent {

    private String url;
    private Map<String, Object> params = new HashMap<String, Object>();
    private String result;
    private HttpModel model;
    private List<BasicNameValuePair> json;
    private Handler handler;
    private int handlerDate;
    private String filePath;

    public RequestMessager(Context act, HttpModel model, String url, Map<String, Object> params, Handler handler, int handlerDate, List<BasicNameValuePair> json) {
        super(act);
        this.model = model;
        this.url = url;
        this.params = params;
        this.handler = handler;
        this.handlerDate = handlerDate;
        this.json = json;
    }

    public RequestMessager(Activity act, String url, HttpModel model, Handler handler, int handlerDate, String filePath) {
        super(act);
        this.url = url;
        this.model = model;
        this.handler = handler;
        this.handlerDate = handlerDate;
        this.filePath = filePath;
    }



    public void doStart() {
        RequestSumbit request = new RequestSumbit();
        request.execute();
    }

    class RequestSumbit extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... param) {
            switch (model) {
                case GET:
                    result = doGet(url, params);
                    break;
                case POST:
                    Log.d("sss", "提交的数据为:" + json + ",采用的URL为" + url);
                    result = doPost(url, json);
                    break;
                case FILE:
                    result = uploadImage(url, filePath);
                    break;
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("ZZZ", "result-->" + result);
            Message msg = handler.obtainMessage();
            msg.what = handlerDate;
            msg.obj = result;
            handler.sendMessage(msg);
        }
    }
}

