package com.android.test.a1shippro.Fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.test.a1shippro.Activity.ConfirmCodeActivity;
import com.android.test.a1shippro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddInformationFragment extends Fragment {

    Toolbar                     toolbar;
    TextView                    tvTitle;
    LinearLayout                lnFinish;
    ImageView                   img11, img12, img21, img22, img31, img32, img41, img42;

    public AddInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_information, container, false);
        init(v);
        control();
        return  v;

    }

    public  void init(View v)
    {
        tvTitle = (TextView) v.findViewById(R.id.toolbarTitle);
        toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

        lnFinish = (LinearLayout) v.findViewById(R.id.lnFinish);
        img11 = (ImageView) v.findViewById(R.id.img11);
        img12 = (ImageView) v.findViewById(R.id.img12);
        img21 = (ImageView) v.findViewById(R.id.img21);
        img22 = (ImageView) v.findViewById(R.id.img22);
        img31 = (ImageView) v.findViewById(R.id.img31);
        img32 = (ImageView) v.findViewById(R.id.img32);
        img41 = (ImageView) v.findViewById(R.id.img41);
        img42 = (ImageView) v.findViewById(R.id.img42);

        img11.setOnClickListener(clickListener);
        img12.setOnClickListener(clickListener);
        img21.setOnClickListener(clickListener);
        img22.setOnClickListener(clickListener);
        img31.setOnClickListener(clickListener);
        img32.setOnClickListener(clickListener);
        img41.setOnClickListener(clickListener);
        img42.setOnClickListener(clickListener);

    }

    public void control()
    {
        lnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ConfirmCodeActivity.class);
                startActivity(i);
            }

        });


    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent pho = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Log.d("loi", "v.id: " + v.getId());
            switch (v.getId())
            {
                case R.id.img11:
                    startActivityForResult(pho, 11);
                    break;
                case R.id.img12:
                    startActivityForResult(pho, 12);
                    break;
                case R.id.img21:
                   startActivityForResult(pho, 21);
                    break;
                case R.id.img22:
                    startActivityForResult(pho, 22);
                    break;
                case R.id.img31:
                    startActivityForResult(pho, 31);
                    break;
                case R.id.img32:
                    startActivityForResult(pho, 32);
                    break;
                case R.id.img41:
                    startActivityForResult(pho, 41);
                    break;
                case R.id.img42:
                    startActivityForResult(pho, 42);
                    break;
            }

        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_CANCELED) {

            Log.d("loi", "requestCode: " + requestCode + "-resultCode: " + resultCode + data.getDataString());
            if(resultCode == getActivity().RESULT_OK) {
                if (requestCode == 11)
                    setImageViewSRC(data, img11);
                else if (requestCode == 12)
                    setImageViewSRC(data, img12);
                else if (requestCode == 21)
                    setImageViewSRC(data, img21);
                else if (requestCode == 22)
                    setImageViewSRC(data, img22);
                else if (requestCode == 31)
                    setImageViewSRC(data, img31);
                else if (requestCode == 32 )
                    setImageViewSRC(data, img32);
                else if (requestCode == 41 )
                    setImageViewSRC(data, img41);
                else if (requestCode == 42 )
                    setImageViewSRC(data, img42);
            }
        }
        else
            super.onActivityResult(requestCode, resultCode, data);
    }

    public void setImageViewSRC(Intent data, ImageView img)
    {
        try {
            if(data.getExtras()!=null)
        {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            img.setImageBitmap(imageBitmap);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
            else {
                Uri uri = data.getData();
                img.setImageURI(uri);
                img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        } catch (Exception e) {
        }
    }

}//END
