package com.android.test.a1shippro.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.test.a1shippro.R;

public class ConfirmCodeActivity extends Activity {
    EditText                    etConfirmCode;
    Button                      btActived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_code);
        init();
        control();
    }

    public  void init()
    {
        etConfirmCode = (EditText) findViewById(R.id.etConfirmCode);
        btActived = (Button) findViewById(R.id.btActived);
    }

    public void control()
    {
        btActived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfirmCodeActivity.this, MainActivity.class);
                startActivity(i);
            }

        });


    }
}//END
