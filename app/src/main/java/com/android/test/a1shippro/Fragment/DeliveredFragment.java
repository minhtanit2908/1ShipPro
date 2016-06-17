package com.android.test.a1shippro.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.test.a1shippro.AbstractClass.BaseFragment;
import com.android.test.a1shippro.AbstractClass.HidingScrollListener;
import com.android.test.a1shippro.Adapter.RecyclerViewAdapter;
import com.android.test.a1shippro.Model.Item;
import com.android.test.a1shippro.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveredFragment extends BaseFragment {

    Spinner s1, s2;
    String[] ss1 = {"Đơn hàng mới nhất", "...", "...", "..."};
    String[] ss2 = {"Tất cả công việc", "...", "...", "..."};
    List<Item> mListItem;

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    RecyclerViewAdapter adpater;

    LinearLayoutManager linearLayoutManager;
    boolean loading = false;

    public DeliveredFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_da_hoan_thanh, container, false);

        init(view);

        return view;
    }

    void init(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        s1 = (Spinner) view.findViewById(R.id.spinner);
        s2 = (Spinner) view.findViewById(R.id.spinner1);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        setupSpinner();
        setupRecyclerView();
        setupSwipeRefresh();
    }



    @Override
    public void longClickItem() {
    }

    @Override
    public void showControls(boolean check) {

    }

    public void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        mListItem = new ArrayList<Item>();
        mListItem = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Item item = new Item(
                    "#12" + i,
                    "01/06/2016",
                    "11:00",
                    "Giao hàng",
                    "Q5, HCM -> Q.Bình Tân, HCM",
                    false
            );
            mListItem.add(item);
        }
        adpater = new RecyclerViewAdapter(getContext(), mListItem, this,false);
        recyclerView.setAdapter(adpater);
        recyclerView.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onUp() {
//                ((MainActivity)getActivity()).getToolbar().setVisibility(View.GONE);
            }

            @Override
            public void onDown() {
//                ((MainActivity)getActivity()).getToolbar().setVisibility(View.VISIBLE);
            }

            @Override
            public void loadMore() {
                if (!loading) {
                    loadListItem();
                    loading = false;
                }
            }
        });
    }


    public void setupSpinner() {
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_spinner_item, //set layout choose
                ss1
        );
        //set layout for spinner
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        s1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_spinner_item,
                ss2
        );
        adapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        s2.setAdapter(adapter2);
    }

    void setupSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshSwipe();
            }
        });
    }

    private void refreshSwipe() {
        mListItem.clear();
        recyclerView.setEnabled(false);
        loadListItem();

    }

    public void finishRefresh() {
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
        recyclerView.setEnabled(true);
    }

    private void loadListItem() {
        for (int i = 0; i < 10; i++) {
            Item item = new Item(
                    "#12" + i,
                    "01/06/2016",
                    "11:00",
                    "Giao hàng",
                    "Q5, HCM -> Q.Bình Tân, HCM",
                    false
            );
            mListItem.add(item);
        }
        adpater.notifyDataSetChanged();
        loading = false;
        finishRefresh();
    }

}
