package com.android.test.a1shippro.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;

import java.util.List;

/**
 * Created by Zanty on 13/04/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    Context context;
    private List<Fragment> mFragmentList;
    int tag;
    String[] titles;

    public ViewPagerAdapter(FragmentManager fm, Context context, List<Fragment> mFragmentList, String[] titles) {
        super(fm);
        this.context = context;
        this.mFragmentList = mFragmentList;
        this.titles = titles;
    }

    public ViewPagerAdapter(FragmentManager fm, Context context, List<Fragment> mFragmentList) {
        super(fm);
        this.context = context;
        this.mFragmentList = mFragmentList;
        titles = new String[]{"Đơn hàng mới","Đã nhận", "Đã hoàn thành"};
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        SpannableString sb = new SpannableString(titles[position]);
        return sb;
    }
}
