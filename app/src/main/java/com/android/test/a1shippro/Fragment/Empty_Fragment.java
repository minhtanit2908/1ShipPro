package com.android.test.a1shippro.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.test.a1shippro.R;


/**
 * Created by NamNgo on 09/03/2016.
 */
public class Empty_Fragment extends Fragment {
    public Empty_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_empty, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}//
