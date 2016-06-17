package com.android.test.a1shippro.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.test.a1shippro.Adapter.RecyclerViewImageAdapter;
import com.android.test.a1shippro.R;
import com.android.test.a1shippro.Utils.Common;
import com.android.test.a1shippro.Utils.GetPathFromURI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateDHActivity extends AppCompatActivity {

    String[] s = {"Nhận hàng", "Giao hàng", "Kiểm tra hàng"};
    //toolbar
    Toolbar toolbar;
    //init controls
    View btnChangeNote, btnAddImage, btnDone, lnEdit;
    Spinner spWork;
    TextView tvNode;
    EditText etNode, etReason;
    Button btnOk, btnCancle;
    RadioGroup radioGroup;
    List<Bitmap> mListBitmap;
    RecyclerView recyclerView;
    RecyclerViewImageAdapter adapter;
    GetPathFromURI getPathFromURI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dh);
        init();
    }

    private void init() {
        initToolbar();
        btnAddImage = findViewById(R.id.btnAddImage);
        btnChangeNote = findViewById(R.id.btnChangeNote);
        btnDone = findViewById(R.id.btnDone);
        spWork = (Spinner) findViewById(R.id.spWork);
        tvNode = (TextView) findViewById(R.id.tvNote);
        etNode = (EditText) findViewById(R.id.etNote);
        etReason = (EditText) findViewById(R.id.etReason);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        lnEdit = findViewById(R.id.lnEdit);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancle = (Button) findViewById(R.id.btnCancle);
        setOnClickControls();
        setupSpinner();
        setupRecyclerView();
    }

    void setupRecyclerView(){
        mListBitmap = new ArrayList<>();
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewImageAdapter(this,mListBitmap);
        recyclerView.setAdapter(adapter);
    }

    void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.title_updatedonhang));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void removeItem(final int position){
        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(getString(R.string.message_remove_image));
        alertDialogBuilder.setPositiveButton(getString(R.string.btnOk),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListBitmap.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
        alertDialogBuilder.setNegativeButton(getString(R.string.btnCancle),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        android.support.v7.app.AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void setOnClickControls() {

        lnEdit.setVisibility(View.GONE);

        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pick = new Intent(Intent.ACTION_GET_CONTENT);
                pick.setType("image/*");
                Intent pho = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (Common.checkVersionAndroid()) {
                    pho.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/sdcard/tmp")));
                }
                Intent chosser = Intent.createChooser(pick, "Choose");
                chosser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pho});
                startActivityForResult(chosser, 999);
            }
        });
        btnChangeNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvNode.setVisibility(View.GONE);
                lnEdit.setVisibility(View.VISIBLE);
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvNode.setVisibility(View.VISIBLE);
                lnEdit.setVisibility(View.GONE);
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnEdit.setVisibility(View.GONE);
                tvNode.setVisibility(View.VISIBLE);
                tvNode.setText(etNode.getText().toString());
            }
        });
        //set Radio Group
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rbFinish:
                break;
            case R.id.rbUnfish:
                break;
        }
    }

    public ImageView drawImageView() {
        LinearLayout.LayoutParams lastTxtParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lastTxtParams.setMargins(10, 0, 0, 0);
        lastTxtParams.height = 300;
        lastTxtParams.width = 300;
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(lastTxtParams);
        return imageView;
    }

    private void setupSpinner() {
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item, //set layout choose
                s
        );
        //set layout for spinner
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spWork.setAdapter(adapter1);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == 999 && resultCode == RESULT_OK) {
                if (data.getExtras() != null) {
                    Uri uri = null;
                    if (Common.checkVersionAndroid()) {
                        File fi = new File("/sdcard/tmp");
                        try {
                            uri = Uri.parse(android.provider.MediaStore.Images.Media.insertImage(getContentResolver(), fi.getAbsolutePath(), null, null));
                            if (!fi.delete()) {
                                Log.i("logMarker", "Failed to delete " + fi);
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        uri = data.getData();
                    }
                    Bitmap c = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    getPathFromURI = new GetPathFromURI(this, uri);
                    Bitmap bitmap2 = Common.rotateImagePath(getPathFromURI.getRealPathFromURI(), c);
                   // Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    mListBitmap.add(bitmap2);
                    adapter.notifyDataSetChanged();
                } else {
                    Uri uri = data.getData();
                    try {
                        Bitmap c = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        getPathFromURI = new GetPathFromURI(this, uri);
                        Bitmap bitmap2 = Common.rotateImagePath(getPathFromURI.getRealPathFromURI(), c);
                        mListBitmap.add(bitmap2);
                        adapter.notifyDataSetChanged();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            //Toast.makeText(this,getResources().getString(R.string.connectError),Toast.LENGTH_LONG).show();
        }
    }
}
