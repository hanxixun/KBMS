package com.kbms.kb.repository;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kbms.kb.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragment;

    private LinearLayout mTabHome;
    private LinearLayout mTabClassification;
    private LinearLayout mTabMe;
    private LinearLayout mTabGroup;
    private LinearLayout mTabHonor;

    private ImageButton mHomeImg;
    private ImageButton mClassificationImgs;
    private ImageButton mGroupImg;
    private ImageButton mHonorImg;
    private ImageButton mMeImgs;
    private ImageButton mSearchBack;
    private ImageButton mAdd;

    private TextView mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
        setSelect(0);
    }

    private void initEvent() {
        mTabHome.setOnClickListener(this);
        mTabClassification.setOnClickListener(this);
        mTabGroup.setOnClickListener(this);
        mTabHonor.setOnClickListener(this);
        mTabMe.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mAdd.setOnClickListener(this);
        mSearchBack.setOnClickListener(this);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mSearch = (TextView) findViewById(R.id.search_input);
        //tabs
        mTabHome = (LinearLayout) findViewById(R.id.id_tab_home);
        mTabClassification = (LinearLayout) findViewById(R.id.id_tab_classification);
        mTabGroup = (LinearLayout) findViewById(R.id.id_tab_group);
        mTabHonor = (LinearLayout) findViewById(R.id.id_tab_honor);
        mTabMe = (LinearLayout) findViewById(R.id.id_tab_me);

        //imageButton
        mHomeImg = (ImageButton) findViewById(R.id.id_tab_home_img);
        mClassificationImgs = (ImageButton) findViewById(R.id.id_tab_classification_img);
        mGroupImg = (ImageButton) findViewById(R.id.id_tab_group_img);
        mHonorImg = (ImageButton) findViewById(R.id.id_tab_honor_img);
        mMeImgs = (ImageButton) findViewById(R.id.id_tab_me_img);
        mAdd = (ImageButton) findViewById(R.id.add_btn);
        mSearchBack = (ImageButton) findViewById(R.id.search_btn_back);


        //四个数据源Fragment初始化
        mFragment = new ArrayList<Fragment>();
        Fragment mTab01 = new HomeFragment();
        Fragment mTab02 = new ClassificationFragment();
        Fragment mTab03 = new GroupFragment();
        Fragment mTab04 = new HonorFragment();
        Fragment mTab05 = new MeFragment();
        mFragment.add(mTab01);
        mFragment.add(mTab02);
        mFragment.add(mTab03);
        mFragment.add(mTab04);
        mFragment.add(mTab05);

        //增加viewpager的缓存页
        mViewPager.setOffscreenPageLimit(5);
        //Adapter初始化
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }
        };
        //为ViewPager设置适配器
        mViewPager.setAdapter(mAdapter);
        //实现ViewPager的联动效果，监听下ViewPager的变化
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = mViewPager.getCurrentItem();
                setTab(currentItem);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 调用setTab方法
     * 获取当前的Tab页面，用来切换内容
     *
     * @param i
     */
    private void setSelect(int i) {
        setTab(i);
        mViewPager.setCurrentItem(i);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.id_tab_home:
                setSelect(0);
                break;
            case R.id.id_tab_classification:
                setSelect(1);
                break;
            case R.id.id_tab_group:
                setSelect(2);
                break;
            case R.id.id_tab_honor:
                setSelect(3);
                break;
            case R.id.id_tab_me:
                setSelect(4);
                break;
            case R.id.search_btn_back:
                finish();
                break;
            case R.id.search_input:
                Intent intent1 = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent1);
                break;
            case R.id.add_btn:
                Intent intent2 = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent2);
                break;
        }
    }

    /**
     * 设置图片亮色
     * 设置内容区域
     *
     * @param i
     */
    private void setTab(int i) {
        resetImg();
        switch (i) {
            case 0:
                mHomeImg.setImageResource(R.drawable.tab_home_pressed);
                break;
            case 1:
                mClassificationImgs.setImageResource(R.drawable.tab_classification_pressed);
                break;
            case 2:
                mGroupImg.setImageResource(R.drawable.tab_group_pressed);
                break;
            case 3:
                mHonorImg.setImageResource(R.drawable.tab_honor_pressed);
                break;
            case 4:
                mMeImgs.setImageResource(R.drawable.tab_me_pressed);
                break;
        }
    }

    /**
     * 将所有图片切换为暗色
     */
    private void resetImg() {
        mHomeImg.setImageResource(R.drawable.tab_home_normal);
        mClassificationImgs.setImageResource(R.drawable.tab_classification_normal);
        mGroupImg.setImageResource(R.drawable.tab_group_normal);
        mHonorImg.setImageResource(R.drawable.tab_honor_normal);
        mMeImgs.setImageResource(R.drawable.tab_me_normal);
    }
}