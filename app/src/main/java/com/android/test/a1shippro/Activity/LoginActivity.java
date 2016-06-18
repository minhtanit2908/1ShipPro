package com.android.test.a1shippro.Activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.FrameLayout;

import com.android.test.a1shippro.Fragment.AddInformationFragment;
import com.android.test.a1shippro.Fragment.Empty_Fragment;
import com.android.test.a1shippro.Fragment.LoginFragment;
import com.android.test.a1shippro.Fragment.RegisterFragment;
import com.android.test.a1shippro.R;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private SectionsPagerAdapter        adapter;
    FrameLayout                         flContainer;
    boolean                             loginShowing = true, bLogOut = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize the facebook SDK
        FacebookSdk.sdkInitialize(LoginActivity.this);
        AppEventsLogger.activateApp(LoginActivity.this);
        setContentView(R.layout.activity_login);
        flContainer = (FrameLayout) findViewById(R.id.container);
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.d("loi", "ANdroid ID: " + android_id);
        setupAdapter();
    }

    private void setupAdapter() {
        adapter = new SectionsPagerAdapter(getSupportFragmentManager());

        LoginFragment login = new LoginFragment();
//        Bundle bCN = new Bundle();
//        bCN.putString("role", "cn");
//        main.setArguments(bCN);
        adapter.addFragment(login, "login");

        RegisterFragment register = new RegisterFragment();
        adapter.addFragment(register, "register");

        AddInformationFragment addInfo = new AddInformationFragment();
        adapter.addFragment(addInfo, "addInfo");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, adapter.getItem(0))
                        .commit();
                loginShowing = true;
            }
        }, 1000);

    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {

            try {
                return mFragmentList.get(position);
            } catch (Exception e) {
                return new Empty_Fragment();
            }

        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }


    public void loadFragment(String request) {
        ///if (loginShowing)
        {
            switch (request) {
                case "login":
                    getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations
                                    (R.anim.slide_in_right, R.anim.slide_out_left,
                                            R.anim.slide_in_left, R.anim.slide_out_right)
                            .replace(R.id.container, adapter.getItem(0), "login")
                            .addToBackStack(null)
                            .commit();
                    loginShowing = true;

                    break;
                case "register":
                    getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations
                                    (R.anim.slide_in_right, R.anim.slide_out_left,
                                            R.anim.slide_in_left, R.anim.slide_out_right)
                            .replace(R.id.container, adapter.getItem(1),"register")
                            .addToBackStack(null)
                            .commit();
                    loginShowing = false;
                    break;
                case "addInfo":
                    getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations
                                    (R.anim.slide_in_right, R.anim.slide_out_left,
                                            R.anim.slide_in_left, R.anim.slide_out_right)
                            .replace(R.id.container, adapter.getItem(2), "addInfo")
                            .addToBackStack(null)
                            .commit();
                    loginShowing = false;
                    break;
                case "home":
                    getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations
                                    (R.anim.slide_in_right, R.anim.slide_out_left,
                                            R.anim.slide_in_left, R.anim.slide_out_right)
                            .replace(R.id.container, adapter.getItem(3))
//                            .replace(R.id.container, new HomeFragment())
                            .addToBackStack(null)
                            .commit();
                    loginShowing = false;
                    break;
            }
        }
//        else {
//            getSupportFragmentManager().popBackStack();
//        }
    }


}//END
