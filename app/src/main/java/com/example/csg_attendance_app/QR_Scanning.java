package com.example.csg_attendance_app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.zxing.Result;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class QR_Scanning extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    StorageReference storageRef;
    final long ONE_MEGABYTE = 1024 * 1024 *5;

    private CodeScanner mCodeScanner;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    TextView tv_time,tv_timee,tv_details,tv_name;
    ImageView iv_profile;
    Button btn_submit;

    String year,department,course,uid,fullname,currentTime = " ";

    LinearLayout linearLayout;

    TextClock tc_time;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanning);

        Dialog dialog = new Dialog(QR_Scanning.this);
        dialog.setContentView(R.layout.result_popup);
        dialog.setCancelable(true);

        linearLayout = (LinearLayout) findViewById(R.id.mainLayout);

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        String time = getIntent().getStringExtra("Time");


        tv_time = findViewById(R.id.tv_time);
        tc_time = findViewById(R.id.digitalClock);

        btn_submit = dialog.findViewById(R.id.btn_submit);
        tv_details = dialog.findViewById(R.id.tv_details);
        iv_profile = dialog.findViewById(R.id.ivProfile);
        tv_name = dialog.findViewById(R.id.tv_name);
        tv_time.setText(time);




        DatabaseReference myRef = database.getReference(tv_time.getText().toString());






        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentTime = tc_time.getText().toString();

                try{
                    String details = tv_details.getText().toString();
                    String[] details_split = details.split("\n");
                    for (int i=0; i < details_split.length; i++){
                        fullname = details_split[0];
                        course = details_split[1];
                        year = details_split[2];

                    }

                    Accounts acc = new Accounts(fullname,course,year,currentTime);
                    myRef.child(fullname).setValue(acc).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            @SuppressLint("ResourceAsColor") Snackbar snackbar = Snackbar
                                    .make(linearLayout, "Nice ka one " + fullname.toUpperCase() + "!", Snackbar.LENGTH_LONG).setTextColor(getResources().getColor(R.color.black)).setBackgroundTint(getResources().getColor(R.color.green));
                            snackbar.show();
                            dialog.cancel();
                        }
                    });
                }catch (ArrayIndexOutOfBoundsException e){
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Dili mana mao na QR Code chuy", Snackbar.LENGTH_LONG).setTextColor(getResources().getColor(R.color.white)).setBackgroundTint(getResources().getColor(R.color.red));
                    snackbar.show();
                }



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

                        try{
                            String details = result.getText();
                            String[] details_split = details.split("\n");
                            for (int i=0; i < details_split.length; i++){
                                fullname = details_split[0];
                                course = details_split[1];
                                year = details_split[2];
                                uid = details_split[3];
                            }
                            tv_name.setText("Hi " + fullname + "♥");
                            tv_details.setText(fullname+"\n"+course+"\n"+year+"\n");
                        } catch (ArrayIndexOutOfBoundsException e){
                            Snackbar snackbar = Snackbar
                                    .make(linearLayout, "Dili mana mao na QR Code chuy", Snackbar.LENGTH_LONG).setTextColor(getResources().getColor(R.color.white)).setBackgroundTint(getResources().getColor(R.color.red));
                            snackbar.show();
                        }
                        System.out.println(uid);
                        storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://csg-attendance.appspot.com/Profile/"+uid.trim().toString());
                        storageRef.getBytes(ONE_MEGABYTE)
                                        .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                            @Override
                                            public void onSuccess(byte[] bytes) {
                                                Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                                DisplayMetrics dm = new DisplayMetrics();
                                                getWindowManager().getDefaultDisplay().getMetrics(dm);


                                                iv_profile.setMinimumHeight(dm.heightPixels);
                                                iv_profile.setMinimumWidth(dm.widthPixels);
                                                iv_profile.setImageBitmap(bm);
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        System.out.println(e);
                                        Toast.makeText(QR_Scanning.this, "Something went wrong!", Toast.LENGTH_LONG).show();

                                    }
                                });
                        Toast.makeText(QR_Scanning.this, fullname+"\n"+course+"\n"+year, Toast.LENGTH_SHORT).show();

                        dialog.show();
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