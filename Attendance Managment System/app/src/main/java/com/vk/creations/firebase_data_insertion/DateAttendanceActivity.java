package com.vk.creations.firebase_data_insertion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.muddzdev.styleabletoast.StyleableToast;

import java.util.ArrayList;

public class DateAttendanceActivity extends AppCompatActivity {

    ListView datelist;
    FirebaseListAdapter adapter;
    ArrayList<String> DateArray=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_attendance);

        Intent intent=getIntent();
        final String db_username=intent.getStringExtra("username");


        datelist=findViewById(R.id.date_list);
        Query query= FirebaseDatabase.getInstance().getReference().child("Attendance");
        FirebaseListOptions<DateHelperClass>options=new FirebaseListOptions.Builder<DateHelperClass>()
                .setLayout(R.layout.customdatelist)
                .setQuery(query,DateHelperClass.class)
                .build();
        adapter=new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView date_txt=v.findViewById(R.id.lst_date);
                DateHelperClass DateObj=(DateHelperClass) model;
                DateArray.add(String.valueOf(DateObj.getDate()));
                date_txt.setText(DateObj.getDate().toString());

            }
        };
        datelist.setAdapter(adapter);
        datelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),AttendanceActivity.class);
                intent.putExtra("username",db_username);
                intent.putExtra("date",DateArray.get(i));

                startActivity(intent);
               // Toast.makeText(DateAttendanceActivity.this, DateArray.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}