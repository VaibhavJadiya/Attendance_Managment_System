package com.vk.creations.firebase_data_insertion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.transition.Hold;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.muddzdev.styleabletoast.StyleableToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AttendanceActivity extends AppCompatActivity {

    ListView attendance_listview;
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    ArrayList<String> presentSubject=new ArrayList<>();
    String db_username,dt_username_ict,dt_username_dbms,dt_username_iot,dt_username_java;
    int present_ict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        Intent intent = getIntent();
        final String db_username = intent.getStringExtra("username");
        final String db_date=intent.getStringExtra("date");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyy");
        final String currentDate = simpleDateFormat.format(calendar.getTime());
      //  Toast.makeText(this, db_username, Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, currentDate, Toast.LENGTH_SHORT).show();
        TextView display_date=findViewById(R.id.dis_date);
        display_date.setText(db_date);

        //Jugad For Showing Attendance
        Query checkUser=FirebaseDatabase.getInstance().getReference("Attendance");
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                   // StyleableToast.makeText(AttendanceActivity.this,"Attedance Loaded!",R.style.success).show();
                    //CHecking For ICT
                    dt_username_ict = snapshot.child(db_date).child("ICT").child(db_username).child("username").getValue(String.class);
                    if (db_username.equals(dt_username_ict)) {

                        insert_ict();

                    } else {
                        //Toast.makeText(AttendanceActivity.this, "Absent in ICT", Toast.LENGTH_SHORT).show();
                    }
                    //Checking For DBMS
                    dt_username_dbms = snapshot.child(db_date).child("DBMS").child(db_username).child("username").getValue(String.class);
                    if (db_username.equals(dt_username_dbms)) {

                        insert_dbms();
                    }
                    else{
                       // Toast.makeText(AttendanceActivity.this, "Absent In DBMS", Toast.LENGTH_SHORT).show();
                    }
                    //CHecking For Java
                    dt_username_java = snapshot.child(db_date).child("JAVA").child(db_username).child("username").getValue(String.class);
                    if (db_username.equals(dt_username_java)) {
                        //Toast.makeText(AttendanceActivity.this, db_date, Toast.LENGTH_SHORT).show();
                        insert_java();
                    }
                    else{
                       // Toast.makeText(AttendanceActivity.this, "Absent In Java", Toast.LENGTH_SHORT).show();
                    }
                    //Checking For IOT
                    dt_username_iot = snapshot.child(db_date).child("IOT").child(db_username).child("username").getValue(String.class);
                    if (db_username.equals(dt_username_iot)) {

                        insert_iot();
                    }
                    else{
                       // Toast.makeText(AttendanceActivity.this, "Absent In IOT", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    //Toast.makeText(AttendanceActivity.this, "Kal to chutti thi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



       // final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AttendanceActivity.this, android.R.layout.simple_list_item_1, presentSubject);
       // attendance_listview = findViewById(R.id.attendance_list);
        //attendance_listview.setAdapter(arrayAdapter);
       // presentSubject.add("DBMS");
       // presentSubject.add("ICT");

    }
    //Function to Add Data in List VIew as per Thier Attendance
    public void insert_ict(){
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AttendanceActivity.this, android.R.layout.simple_list_item_1, presentSubject);
        attendance_listview = findViewById(R.id.attendance_list);
        attendance_listview.setAdapter(arrayAdapter);
        presentSubject.add("ICT");
    }
    public void insert_dbms(){
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AttendanceActivity.this, android.R.layout.simple_list_item_1, presentSubject);
        attendance_listview = findViewById(R.id.attendance_list);
        attendance_listview.setAdapter(arrayAdapter);
        presentSubject.add("DBMS");
    }
    public void insert_java(){
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AttendanceActivity.this, android.R.layout.simple_list_item_1, presentSubject);
        attendance_listview = findViewById(R.id.attendance_list);
        attendance_listview.setAdapter(arrayAdapter);
        presentSubject.add("JAVA");
    }
    public void insert_iot(){
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AttendanceActivity.this, android.R.layout.simple_list_item_1, presentSubject);
        attendance_listview = findViewById(R.id.attendance_list);
        attendance_listview.setAdapter(arrayAdapter);
        presentSubject.add("IOT");
    }

}