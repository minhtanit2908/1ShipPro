package com.android.test.a1shippro.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.test.a1shippro.Activity.LoginActivity;
import com.android.test.a1shippro.Activity.MainActivity;
import com.android.test.a1shippro.Model.Login.LoginState;
import com.android.test.a1shippro.R;
import com.android.test.a1shippro.Utils.Common;
import com.android.test.a1shippro.Utils.Database;

import java.util.ArrayList;

/**
 * Created by NamNgo on 10/04/2016.
 */
public class LoginFragment extends Fragment {

    EditText                            etPhone;
    Button                              btLogin;
    TextView                            btRegister;
    View                                rltLogin;
    Database                            db;
    Common                              cm;

    public static LoginState loginState = new LoginState();
    public static ArrayList<LoginState> lstLoginStates;
    public static ArrayList<String> lstUser;

    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new Database(getActivity());
        cm = new Common(getActivity());
        // Create DATABASE
        try {
//            db.createDataBase();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            cm.log("ERROR: db.createDataBase() " + e);
        }
        // END DATABASE

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        init(v);
//        checkAccountInDatabase();
        return v;
    }//END onCreateView

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        cm.log("resume");

    }

    public void init(View v) {

        etPhone = (EditText) v.findViewById(R.id.etPhone);
        btLogin = (Button) v.findViewById(R.id.btLogin);
        btRegister = (TextView) v.findViewById(R.id.btRegister);
        rltLogin = v.findViewById(R.id.rltLogin);

//        cm.setupUI(rltLogin);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etPhone.getText().length() > 0) {
                    //setClickableButton(false);
//                    processLogin();
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(getActivity(),
                            getActivity().getResources().getString(R.string.inputNotComplete),
                            Toast.LENGTH_SHORT).show();
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginActivity) getActivity()).loadFragment("register");
            }
        });

        // HidesoftKeyboard
        // End hidesoftKeyboard
    }

//    public void checkAccountInDatabase() {
//        lstUser = new ArrayList<>();
//        lstLoginStates = new ArrayList<>();
//        if (!LoginActivity.bLogOut) {
//            try {
//
//                Cursor c = db.GetAllDataFromTable("tbLogin");
//                c.moveToFirst();
//                do {
//                    //state == 1 login saved
//                    //state == 0 not save
//                    lstLoginStates.add(new LoginState(c.getString(1), c.getString(2), c.getString(3)));
//                    lstUser.add(c.getString(1));
////                    cm.log(c.getString(1) + c.getString(2) + "--" + c.getString(3));
//                } while (c.moveToNext());
//                c.close();
//                ArrayAdapter<LoginState> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, lstLoginStates);
//                etUser.setAdapter(adapter);
//                etUser.setText("");
//                etPass.setText("");
//                for (LoginState l : lstLoginStates
//                        ) {
//                    if (l.getState().equals("1")) {
//                        etUser.setText(l.getUsername());
//                        etPass.setText(l.getPassword());
//                        processLogin();
//                        break;
//                    }
//                }
//
//            } catch (Exception e) {
//
//                cm.log("ERROR LoginFragment checkAccountInDatabase: " + e);
//            }
//        } else {
//            etUser.setText("");
//            etPass.setText("");
//        }
//    }
//
//
//    public void processLogin() {
//        try {
//            db.updateAllRowOfColumn("tbLogin", "state", "0");
//        } catch (Exception e) {
//            cm.log("ERROR LoginFragment processLogin updateAllRowOfColumn: " + e);
//        }
//        Login lg = new Login(etUser.getText().toString(),
//                etPass.getText().toString());
//        String json = new Gson().toJson(lg);
//        ConnectAPI connectAPI = new ConnectAPI(getActivity());
//        connectAPI.connect(Util.LOGIN,
//                null,
//                json,
//                false, LoginFragment.this);
//    }
//
//    public void loginSuccess() {
//        try {
//            loginState.setUsername(etUser.getText().toString());
//            loginState.setPassword(etPass.getText().toString());
//            loginState.setState("1");
//            db.insertOrUpdate_Object(loginState, "tbLogin");
//        } catch (Exception e) {
//            cm.log("ERROR LoginFragment loginSuccess insertOrUpdate_Object: " + e);
//        }
//        LoginActivity.bLogOut = false;
//        setClickableButton(true);
//        ((LoginActivity)getActivity()).startMainActivity();
//        }

    public void setClickableButton(boolean click)
    {
        btLogin.setClickable(click);
    }
}//END
