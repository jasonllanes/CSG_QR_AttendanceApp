package com.example.csg_attendance_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;

public class LogIn extends AppCompatActivity {

    EditText et_email,et_password;
    Button btn_login;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        et_email = findViewById(R.id.et_email);
//        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        linearLayout = (LinearLayout) findViewById(R.id.logInLayout);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_email.getText().toString();
                if(name.isEmpty()){
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Butngi sad ug ngalan", Snackbar.LENGTH_LONG).setTextColor(getResources().getColor(R.color.white)).setBackgroundTint(getResources().getColor(R.color.red));
                    snackbar.show();
                }else if(!name.trim().isEmpty()){

                    Intent i = new Intent(LogIn.this,Home_Screen.class);
                    i.putExtra("Name",name);
                    startActivity(i);
                }

            }
        });



    }
}