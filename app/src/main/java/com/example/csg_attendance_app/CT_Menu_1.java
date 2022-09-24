package com.example.csg_attendance_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CT_Menu_1 extends AppCompatActivity {
    Button btn_morning_in,btn_morning_out,btn_afternoon_in,btn_afternoon_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ct_menu1);

        btn_morning_in = findViewById(R.id.btn_morning_in);
        btn_morning_out = findViewById(R.id.btn_morning_out);
        btn_afternoon_in = findViewById(R.id.btn_afternoon_in);
        btn_afternoon_out = findViewById(R.id.btn_afternoon_out);

        btn_morning_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CT_Menu_1.this,QR_Scanning.class);
                intent.putExtra("Time","COLLEGE/TERTIARY | Morning In");
                startActivity(intent);
            }
        });

        btn_morning_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CT_Menu_1.this,QR_Scanning.class);
                intent.putExtra("Time","COLLEGE/TERTIARY | Morning Out");
                startActivity(intent);
            }
        });

        btn_afternoon_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CT_Menu_1.this,QR_Scanning.class);
                intent.putExtra("Time","COLLEGE/TERTIARY | Afternoon In");
                startActivity(intent);
            }
        });

        btn_afternoon_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CT_Menu_1.this,QR_Scanning.class);
                intent.putExtra("Time","COLLEGE/TERTIARY | Afternoon Out");
                startActivity(intent);
            }
        });


    }
}