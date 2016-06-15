package com.android.test.a1shippro.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.test.a1shippro.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DaHoanThanhFragment extends Fragment {


    public DaHoanThanhFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_da_hoan_thanh, container, false);
    }

}
