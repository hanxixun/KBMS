package com.kbms.kb.flipper.expandgroup;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kbms.kb.R;

import java.util.ArrayList;
import java.util.List;


public class Text3_1 {
	private Context mContext;
	private View mWikiMsg;
	private Button mEdit;
	ViewPager viewPager;

	private RadioButton notes;
	private RadioButton news;
	private RadioButton contacts;
	private RadioButton setting;

	private ViewPagerAdapter mAdapter;
	private List<View> mList = new ArrayList<View>();
	public Text3_1(Context context) {
		mContext = context;
		mWikiMsg = LayoutInflater.from(context).inflate(R.layout.message, null);
		mEdit = (Button) mWikiMsg.findViewById(R.id.message_edit);
		viewPager = (ViewPager) mWikiMsg.findViewById(R.id.message_pager);
		notes = (RadioButton) mWikiMsg.findViewById(R.id.rb_notes);
		news = (RadioButton) mWikiMsg.findViewById(R.id.rb_news);
		contacts = (RadioButton) mWikiMsg.findViewById(R.id.rb_contacts);
		setting = (RadioButton) mWikiMsg.findViewById(R.id.rb_setting);
		init();
		setListener();//设置监听
	}
	public View getView() {
		return mWikiMsg;
	}
	private void init() {
		View notes = LayoutInflater.from(mContext).inflate(R.layout.message_message, null);
		View news = LayoutInflater.from(mContext).inflate(R.layout.message_message, null);
		View contacts = LayoutInflater.from(mContext).inflate(R.layout.message_message, null);
		View me = LayoutInflater.from(mContext).inflate(R.layout.message_message, null);
		mList.add(notes);
		mList.add(news);
		mList.add(contacts);
		mList.add(me);
		mAdapter = new ViewPagerAdapter();
		viewPager.setAdapter(mAdapter);
		viewPager.setCurrentItem(0);
	}


	private void setListener() {
		mEdit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(mContext, "No editable content temporaril", Toast.LENGTH_SHORT).show();//暂时没有可编辑内容
			}
		});
		notes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(0);
			}
		});
		news.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(1);
			}
		});
		contacts.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(2);
			}
		});
		setting.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(3);
			}
		});
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			public void onPageSelected(int arg0) {

			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			public void onPageScrollStateChanged(int arg0) {
				if (arg0 == ViewPager.SCROLL_STATE_IDLE) {
					switch (viewPager.getCurrentItem()) {
						case 0:
							// mEdit.setVisibility(View.VISIBLE);
							notes.setChecked(true);
							break;

						case 1:
							// mEdit.setVisibility(View.VISIBLE);
							news.setChecked(true);
							break;

						case 2:
							contacts.setChecked(true);
							//  mEdit.setVisibility(View.GONE);
							break;
						case 3:
							setting.setChecked(true);
							break;
					}
				}
			}
		});
	}

	private class ViewPagerAdapter extends PagerAdapter {

		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mList.get(arg1));
		}

		public void finishUpdate(View arg0) {

		}

		public int getCount() {
			return mList.size();
		}

		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mList.get(arg1));
			return mList.get(arg1);
		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		public Parcelable saveState() {
			return null;
		}

		public void startUpdate(View arg0) {

		}
	}
}
