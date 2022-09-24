package com.example.csg_attendance_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home_Screen extends AppCompatActivity {
    Button btn_shs,btn_ct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        btn_shs = findViewById(R.id.btn_shs);
        btn_ct = findViewById(R.id.btn_ct);

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