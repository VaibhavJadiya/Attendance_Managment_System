package com.vk.creations.firebase_data_insertion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.muddzdev.styleabletoast.StyleableToast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG="HomeActivity";
    Button subject_iot;
    Button subject_java;
    Button subject_dbms;
    Button subject_ict;
    FirebaseDatabase rootnode;
    DatabaseReference reference,childrefrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.i(TAG, "onCreate: ");
        subject_iot=findViewById(R.id.btn_iot);
        subject_java=findViewById(R.id.btn_java);
        subject_dbms=findViewById(R.id.btn_dbms);
        subject_ict=findViewById(R.id.btn_ict);
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMM-yyy");
        final String currentDate=simpleDateFormat.format(calendar.getTime());
        Intent intent=getIntent();
        //Getting Name and username
        final String db_username=intent.getStringExtra("username");
        final String db_name=intent.getStringExtra("name");
      // final String at_username= null,at_name = null;



        subject_iot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootnode=FirebaseDatabase.getInstance();
                reference=rootnode.getReference("Attendance");
                childrefrence=rootnode.getReference("Attendance").child(currentDate).child("date");
                childrefrence.setValue(currentDate);
                //Putting Name and Uaernme in Attendance database

                AttendanceHelper attendanceHelper=new AttendanceHelper(db_username,db_name,currentDate);
                reference.child(currentDate).child("IOT").child(db_username).setValue(attendanceHelper);
               // StyleableToast.makeText(HomeActivity.this,"Attendance Registered!",R.style.success).show();

                gotourl("https://meet.google.com/lookup/bqqbhrb4q6");
            }
        });
        subject_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootnode=FirebaseDatabase.getInstance();
                reference=rootnode.getReference("Attendance");
                childrefrence=rootnode.getReference("Attendance").child(currentDate).child("date");
                childrefrence.setValue(currentDate);
                //Putting Name and Uaernme in Attendance database

                AttendanceHelper attendanceHelper=new AttendanceHelper(db_username,db_name,currentDate);
                reference.child(currentDate).child("JAVA").child(db_username).setValue(attendanceHelper);
              //  StyleableToast.makeText(HomeActivity.this,"Attendance Registered!",R.style.success).show();

                gotourl("https://meet.google.com/lookup/bz6jy6xmn4");
            }
        });
        subject_dbms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootnode=FirebaseDatabase.getInstance();
                reference=rootnode.getReference("Attendance");
                childrefrence=rootnode.getReference("Attendance").child(currentDate).child("date");
                childrefrence.setValue(currentDate);


                //Putting Name and Uaernme in Attendance database
                AttendanceHelper attendanceHelper=new AttendanceHelper(db_username,db_name,currentDate);
                reference.child(currentDate).child("DBMS").child(db_username).setValue(attendanceHelper);
                //Toast.makeText(HomeActivity.this, db_name, Toast.LENGTH_SHORT).show();
              //  StyleableToast.makeText(HomeActivity.this,"Attendance Registered!",R.style.success).show();
                //Redirecting to meet
                 gotourl("https://meet.google.com/lookup/bz6jy6xmn4");
            }
        });
        subject_ict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootnode=FirebaseDatabase.getInstance();
                reference=rootnode.getReference("Attendance");
                childrefrence=rootnode.getReference("Attendance").child(currentDate).child("date");
                childrefrence.setValue(currentDate);

                //Putting Name and Uaernme in Attendance database
                AttendanceHelper attendanceHelper=new AttendanceHelper(db_username,db_name,currentDate);
                reference.child(currentDate).child("ICT").child(db_username).setValue(attendanceHelper);
               // StyleableToast.makeText(HomeActivity.this,"Attendance Registered!",R.style.success).show();

                gotourl("https://meet.google.com/lookup/azg3qwcm42");

            }
        });


    }

    private void gotourl(String s) {

        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
        //Toast.makeText(HomeActivity.this, "App has been closed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }
}