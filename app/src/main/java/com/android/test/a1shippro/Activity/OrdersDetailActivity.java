package com.android.test.a1shippro.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.test.a1shippro.Adapter.ViewPagerAdapter;
import com.android.test.a1shippro.Fragment.CostOrdersFragment;
import com.android.test.a1shippro.Fragment.DeliveryOrdersFragment;
import com.android.test.a1shippro.Fragment.InfoOrdersFragment;
import com.android.test.a1shippro.R;

import java.util.ArrayList;
import java.util.List;

public class OrdersDetailActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    ViewPagerAdapter adapter;
    public Toolbar toolbar;
    String[] titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_detail);
        init();
    }

    private void init() {
        titles = new String[]{
                getResources().getString(R.string.orderdetail_tab1),
                getResources().getString(R.string.orderdetail_tab2),
                getResources().getString(R.string.orderdetail_tab3)
        };
        initToolbar();
        initViewPager();
    }
    void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ĐƠN HÀNG");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void initViewPager(){
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),OrdersDetailActivity.this,getListFragment(),titles);
        mViewPager = (ViewPager) findViewById(R.id.container);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);
    }
    private List<Fragment> getListFragment() {
        final List<Fragment> list = new ArrayList<>();
        DeliveryOrdersFragment delevery = new DeliveryOrdersFragment();
        InfoOrdersFragment info = new InfoOrdersFragment();
        CostOrdersFragment cost = new CostOrdersFragment();
        list.add(delevery);
        list.add(info);
        list.add(cost);
        return list;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
