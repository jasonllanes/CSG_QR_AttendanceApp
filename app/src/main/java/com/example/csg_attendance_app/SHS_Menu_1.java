package com.example.csg_attendance_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SHS_Menu_1 extends AppCompatActivity {
    Button btn_morning_in,btn_morning_out,btn_afternoon_in,btn_afternoon_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shs_menu1);

        btn_morning_in = findViewById(R.id.btn_morning_in);
        btn_morning_out = findViewById(R.id.btn_morning_out);
        btn_afternoon_in = findViewById(R.id.btn_afternoon_in);
        btn_afternoon_out = findViewById(R.id.btn_afternoon_out);

        btn_morning_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SHS_Menu_1.this,QR_Scanning.class);
                intent.putExtra("Time","Morning In");
                startActivity(intent);
            }
        });

        btn_morning_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SHS_Menu_1.this,QR_Scanning.class);
                startActivity(intent);
            }
        });


        btn_afternoon_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SHS_Menu_1.this,QR_Scanning.class);
                startActivity(intent);
            }
        });

        btn_afternoon_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SHS_Menu_1.this,QR_Scanning.class);
                startActivity(intent);
            }
        });



    }
}