package com.example.handyu_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button txtview_day;
    ImageView imgview_sliberal,imgview_snatural;

    long now = System.currentTimeMillis();
    Date mdate = new Date(now);

    SimpleDateFormat simpleDate = new SimpleDateFormat("yy-MM-dd E요일", Locale.KOREAN);
    String getTime = simpleDate.format(mdate);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtview_day = (Button) findViewById(R.id.txtview_day);
        imgview_sliberal = (ImageView) findViewById(R.id.imgview_sliberal);
        imgview_snatural = (ImageView) findViewById(R.id.imgview_snatural);

//        // Cloud Storage 설정
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        // storage 주소설정
//        StorageReference storageRef = storage.getReference();
//        //다운로드할 파일을 가르키는 참조 만들기
//        StorageReference pathReference = storageRef.child("natural/");
//        // Create a reference to a file from a Google Cloud Storage URI
//        StorageReference gsReference = storage.getReferenceFromUrl("gs://handyu1.appspot.com/natural");

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child("gs://handyu1.appspot.com");
        StorageReference pathRef = storageRef.child("natural/20201110055413_natural_staff.jpg");
        pathRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //이미지 로드 성공시

                Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
//                Glide.with(getApplicationContext())
//                        .load(uri)
//                        .into(imgview_snatural);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //이미지 로드 실패시
                Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
            }
        });


        txtview_day.setText(getTime);

        imgview_sliberal.setVisibility(View.VISIBLE);
        imgview_snatural.setVisibility(View.VISIBLE);


//                myRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        st_view = snapshot.getValue().toString();
//                        textView.setText(st_view);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        st_view = "Database Error";
//                    }
//                }

    }


}