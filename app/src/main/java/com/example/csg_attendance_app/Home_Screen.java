package com.example.csg_attendance_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;

public class Home_Screen extends AppCompatActivity {
    Button btn_shs,btn_ct;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        btn_shs = findViewById(R.id.btn_shs);
        btn_ct = findViewById(R.id.btn_ct);
        linearLayout = (LinearLayout) findViewById(R.id.homeLayout);

        String name = getIntent().getStringExtra("Name");

        Snackbar snackbar = Snackbar
                .make(linearLayout, "Nice ka " + name + "!", Snackbar.LENGTH_LONG).setTextColor(getResources().getColor(R.color.black)).setBackgroundTint(getResources().getColor(R.color.green));
        snackbar.show();

        btn_shs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home_Screen.this,SHS_Menu_1.class);
                startActivity(intent);
            }
        });

        btn_ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home_Screen.this,CT_Menu_1.class);
                startActivity(intent);
            }
        });

    }
}