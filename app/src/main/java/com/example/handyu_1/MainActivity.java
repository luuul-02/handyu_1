package com.example.handyu_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import com.bumptech.glide.module.AppGlideModule;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
    ImageView imgview_snatural,imgview_stfnatural;//imgview_sliberal,imgview_stfliberal;

    long now = System.currentTimeMillis();
    Date mdate = new Date(now);

    SimpleDateFormat simpleDate = new SimpleDateFormat("yy-MM-dd E요일", Locale.KOREAN);
    String getTime = simpleDate.format(mdate);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtview_day = (Button) findViewById(R.id.txtview_day);
        imgview_snatural = (ImageView) findViewById(R.id.imgview_snatural);
        imgview_stfnatural = (ImageView) findViewById(R.id.imgview_stfnatural);
//        imgview_sliberal = (ImageView) findViewById(R.id.imgview_sliberal);
//        imgview_stfliberal = (ImageView) findViewById(R.id.imgview_stfliberal);


        //FirebaseStorage 인스턴스를 생성
        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference storageRef_studentN = storage.getReference("natural/20201110055413_natural_student.jpg");
        StorageReference storageRef_staffN = storage.getReference("natural/20201110055413_natural_staff.jpg");

        //StorageReference에서 파일 다운로드 URL 가져옴
        storageRef_studentN.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    // Glide 이용하여 이미지뷰에 로딩
                    Glide.with(MainActivity.this)
                            .load(task.getResult())
                            .into(imgview_snatural);
                }else{
                    // URL을 가져오지 못하면 토스트 메세지
                    Toast.makeText(MainActivity.this, "오류", Toast.LENGTH_SHORT).show();  }
            }
        });

//        StorageReference storageRef_studentN = storage.getReference("natural/20201110055413_natural_student.jpg");
//        StorageReference storageRef_staffN = storage.getReference("natural/20201110055413_natural_staff.jpg");

//        StorageReference storageRef_studentL = storage.getReference("liberal/20201116021728_liberal_student.jpg");
//        StorageReference storageRef_staffL = storage.getReference("liberal/20201116021728_liberal_staff.jpg");



        txtview_day.setText(getTime);

        imgview_snatural.setVisibility(View.VISIBLE);
        imgview_stfnatural.setVisibility(View.VISIBLE);
//        imgview_sliberal.setVisibility(View.INVISIBLE);
//        imgview_stfliberal.setVisibility(View.INVISIBLE);

    }
}