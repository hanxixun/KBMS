package com.kbms.kb.online_chat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kbms.kb.R;
import com.kbms.kb.online_chat.Chat;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobRealTimeData;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.ValueEventListener;


public class OnLineChat {
    private Context mContext;
    private View mWikiMsg;
    private TextView tv_time;
    ListView lv_data;
    Button btn_send;
    EditText et_name, et_content;

    MyAdapter myAdapter;
    //消息列表
    List<Chat> messages = new ArrayList<Chat>();
    //实时数据
    BmobRealTimeData data = new BmobRealTimeData();

    public OnLineChat(Context context) {
        mContext = context;
        mWikiMsg = LayoutInflater.from(context).inflate(R.layout.online_chat, null);
        init();
        et_name = (EditText) mWikiMsg.findViewById(R.id.et_name);
        et_content = (EditText) mWikiMsg.findViewById(R.id.et_content);
        lv_data = (ListView) mWikiMsg.findViewById(R.id.lv_data);

        btn_send = (Button) mWikiMsg.findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String content = et_content.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(content)) {
                    Toast.makeText(mContext, "The user name and content cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    sendMsg(name, content,new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                }
            }
        });
        myAdapter = new MyAdapter();
        lv_data.setAdapter(myAdapter);
        //item长按事件
        lv_data.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //添加是否确认删除警告框
                Dialog dialog = new AlertDialog.Builder(mContext)
                        .setTitle("删除信息").setMessage("您确定要删除吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            /**
                             *确定点击事件响应
                             */
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            /**
                             *取消点击事件响应
                             */
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();//关闭窗口
                            }
                        }).create();
                dialog.show();//显示窗口
                //refreshNotesListView();
                return true;
            }
        });
    }

    public View getView() {
        return mWikiMsg;
    }

    private void init() {
        Bmob.initialize(mContext, "664bb96b68739bb3d731f2f49e6b6453");
        data.start(mContext, new ValueEventListener() {
            @Override
            public void onDataChange(JSONObject arg0) {
                if (BmobRealTimeData.ACTION_UPDATETABLE.equals(arg0.optString("action"))) {
                    JSONObject data = arg0.optJSONObject("data");
                    messages.add(new Chat(data.optString("name"), data.optString("content"),data.optString("send_time")));
                    myAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onConnectCompleted() {
                if (data.isConnected()) {
                    data.subTableUpdate("Chat");
                }
            }
        });
    }

    /**
     * 发送消息
     *
     * @param name
     * @param msg
     */
    private void sendMsg(String name, String msg,String send_time) {
        Chat chat = new Chat(name + "：", msg,send_time);
        chat.save(mContext, new SaveListener() {
            @Override
            public void onSuccess() {
                et_content.setText("");
            }
            @Override
            public void onFailure(int arg0, String arg1) {
            }
        });
    }

    private class MyAdapter extends BaseAdapter {
        ViewHolder holder;

        @Override
        public int getCount() {
            return messages.size();
        }

        @Override
        public Object getItem(int position) {
            return messages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.list_item, null);
                holder = new ViewHolder();
                holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
                holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Chat chat = messages.get(position);
            holder.tv_name.setText(chat.getName());
            holder.tv_content.setText(chat.getContent());
            holder.tv_time.setText(chat.getSend_time());
            holder.tv_time.setBackgroundColor(Color.GRAY);
            return convertView;
        }

        class ViewHolder {
            TextView tv_name;
            TextView tv_content;
            TextView tv_time;
        }
    }
}
