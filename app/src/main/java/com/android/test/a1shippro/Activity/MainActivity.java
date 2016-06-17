package com.android.test.a1shippro.Activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.test.a1shippro.Adapter.ViewPagerAdapter;
import com.android.test.a1shippro.Fragment.DeliveredFragment;
import com.android.test.a1shippro.Fragment.ReceivedOrdersFragment;
import com.android.test.a1shippro.Fragment.NewOrdersFragment;
import com.android.test.a1shippro.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    ViewPagerAdapter adapter;
    public Toolbar toolbar;
    public AppBarLayout appbar;
    String[] titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public Toolbar getToolbar(){
        return toolbar;
    }

    void init(){
        titles = new String[]{
                getResources().getString(R.string.donhang_tab1),
                getResources().getString(R.string.donhang_tab2),
                getResources().getString(R.string.donhang_tab3)
        };
        initToolbar();
        initViewPager();
        initDrawerMenu();
    }

    void initToolbar(){
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.title_donhang));
        setSupportActionBar(toolbar);
    }

    void initViewPager(){
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),MainActivity.this,getListFragment(),titles);
        mViewPager = (ViewPager) findViewById(R.id.container);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);
    }

    void initDrawerMenu(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private List<Fragment> getListFragment() {
        final List<Fragment> list = new ArrayList<>();
        NewOrdersFragment dhm = new NewOrdersFragment();
        ReceivedOrdersFragment dn = new ReceivedOrdersFragment();
        DeliveredFragment dht = new DeliveredFragment();
        //Collections.addAll(list,home,competition,user);
        list.add(dhm);
        list.add(dn);
        list.add(dht);
        return list;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.logout) {
            Toast.makeText(this,"Log Out",Toast.LENGTH_LONG).show();
            finish();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
