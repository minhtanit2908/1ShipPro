package com.android.test.a1shippro.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.test.a1shippro.Activity.LoginActivity;
import com.android.test.a1shippro.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    EditText                etName, etPhone, etInviteCode;
    Button                  btNext;
    Toolbar                 toolbar;
    TextView                tvTitle;
    LoginButton             loginButton;
    CallbackManager         callbackManager;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);
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

        etName = (EditText) v.findViewById(R.id.etName);
        etPhone = (EditText) v.findViewById(R.id.etPhone);
        etInviteCode = (EditText) v.findViewById(R.id.etInviteCode);
        etName.requestFocus();

        btNext = (Button) v.findViewById(R.id.btNext);
        loginButton = (LoginButton) v.findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_about_me"));
        loginButton.setFragment(RegisterFragment.this);
    }

    public void control()
    {
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                checkInformation();
                ((LoginActivity) getActivity()).loadFragment("addInfo");
            }

        });

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getActivity(), "onSuccess", Toast.LENGTH_SHORT).show();
                Log.d("loi","getAccessToken: " + loginResult.getAccessToken().getToken().toString());
                Log.d("loi","getAccessToken: " + loginResult.getAccessToken().getPermissions().toString());
                Log.d("loi","getAccessToken: " + loginResult.getAccessToken().getUserId().toString());

           //app code
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.d("loi", "response: " + response.toString());

                                // Application code
                                try {
                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    String birthday = object.getString("birthday"); // 01/31/1980 format
                                    Log.d("loi","object: " + name + email + birthday);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity(), "onCancel", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getActivity(), "onError", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void clearEditText()
    {
        etName.setText("");
        etPhone.setText("");
        etInviteCode.setText("");
        etName.requestFocus();
    }

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
}//END
