package com.example.chen.myapplication.test3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.test3.fragment.FriendsFragment;
import com.example.chen.myapplication.test3.fragment.GroupFragment;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

/**
 * Created by chen on 2018-03-07.
 */

public class ContactsActivity extends AppCompatActivity {
    private String[] mTitle = {"好友","群","多人聊天","设备","通讯录","公众号"};
    private PtrFrameLayout mPtrFrame;
    private TabLayout mTablayout;
    private FragmentManager mFragmentManager;
    private FriendsFragment mFriendsFragment;
    private GroupFragment mGroupFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_layout);
        mPtrFrame = (PtrFrameLayout) findViewById(R.id.ptr_contacts);
        mTablayout = (TabLayout) findViewById(R.id.tab_ayout);
        mFragmentManager = getSupportFragmentManager();
        initFF();
        intitTabLayout();
    }

    private void initFF() {
        mPtrFrame.setResistance(1.7f); //阻尼系数 默认: 1.7f，越大，感觉下拉时越吃力。
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f); //触发刷新时移动的位置比例 默认，1.2f，移动达到头部高度1.2倍时可触发刷新操作。
        mPtrFrame.setDurationToClose(200);//回弹延时 默认 200ms，回弹到刷新高度所用时间
        mPtrFrame.setDurationToCloseHeader(1000);//头部回弹时间 默认1000ms
        mPtrFrame.setPullToRefresh(false);// 刷新是保持头部 默认值 true.
        mPtrFrame.setKeepHeaderWhenRefresh(true);//下拉刷新 / 释放刷新 默认为释放刷新

        final PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(this);
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);

        mPtrFrame.setHeaderView(header);
        // mPtrFrame.setPinContent(true);//刷新时，保持内容不动，仅头部下移,默认,false
        mPtrFrame.addPtrUIHandler(header);
        //mPtrFrame.setKeepHeaderWhenRefresh(true);//刷新时保持头部的显示，默认为true
        //mPtrFrame.disableWhenHorizontalMove(true);//如果是ViewPager，设置为true，会解决ViewPager滑动冲突问题。
        mPtrFrame.setPtrHandler(new PtrHandler() {

            //需要加载数据时触发
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                System.out.println("MainActivity.onRefreshBegin");
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                        //mPtrFrame.autoRefresh();//自动刷新
                    }
                }, 1800);

            }

            /**
             * 检查是否可以执行下来刷新，比如列表为空或者列表第一项在最上面时。
             */
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                System.out.println("MainActivity.checkCanDoRefresh");
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                // return true;
            }
        });
    }

    private void intitTabLayout() {
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                showFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                hidenFragment(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < mTitle.length; i++){
            mTablayout.addTab(mTablayout.newTab().setText(mTitle[i]),i == 0  ? true : false);
        }
    }

    private void hidenFragment(int i) {
        Fragment fragmentByTag = mFragmentManager.findFragmentByTag(mTitle[i]);
        if (fragmentByTag != null) {
            mFragmentManager.beginTransaction().hide(fragmentByTag).commit();
        }
    }

    private void showFragment(int i) {
        Fragment fragmentByTag = mFragmentManager.findFragmentByTag(mTitle[i]);
        if (fragmentByTag == null) {
            fragmentByTag = getFragment(i);
            mFragmentManager.beginTransaction().add(R.id.fl_content, fragmentByTag,mTitle[i]).commit();
        }
        mFragmentManager.beginTransaction().show(fragmentByTag).commit();
    }

    private Fragment getFragment(int i) {
        Fragment fragment = null;
        switch (i){
            case 0:
                if (mFriendsFragment == null){
                    mFriendsFragment = new FriendsFragment();
                }
                fragment = mFriendsFragment;
                break;
            case 1:
                if (mGroupFragment == null){
                    mGroupFragment = new GroupFragment();
                }
                fragment = mGroupFragment;
                break;
        }
        return fragment;
    }
}
