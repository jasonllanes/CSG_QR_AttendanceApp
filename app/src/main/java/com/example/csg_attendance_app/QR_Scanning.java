package com.example.csg_attendance_app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class QR_Scanning extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private CodeScanner mCodeScanner;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    TextView tv_time,tv_timee,tv_details;

    Button btn_submit;

    String year,department,course,fullname,currentTime = " ";

    LinearLayout linearLayout;

    TextClock tc_time;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanning);



        linearLayout = (LinearLayout) findViewById(R.id.mainLayout);

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        String time = getIntent().getStringExtra("Time");

        btn_submit = findViewById(R.id.btn_submit);
        tv_time = findViewById(R.id.tv_time);
        tc_time = findViewById(R.id.digitalClock);
        tv_details = findViewById(R.id.tv_details);

        tv_time.setText(time);




        DatabaseReference myRef = database.getReference(tv_time.getText().toString());

        String details = tv_details.getText().toString();




        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTime = tc_time.getText().toString();
                String[] details_split = details.split("\n");
                for (int i=0; i < details_split.length; i++){
                    fullname = details_split[0];
                    department = details_split[1];
                    course = details_split[2];
                    year = details_split[3];
                }
                Accounts acc = new Accounts(fullname,department,year,course,currentTime);
                myRef.child(fullname).setValue(acc).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        @SuppressLint("ResourceAsColor") Snackbar snackbar = Snackbar
                                .make(linearLayout, "Success!", Snackbar.LENGTH_LONG).setTextColor(0xffffff).setBackgroundTint(0xffd633);
                        snackbar.show();
                    }
                });

            }
        });

        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_details.setText(result.getText());
                        Toast.makeText(QR_Scanning.this, result.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }




    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}