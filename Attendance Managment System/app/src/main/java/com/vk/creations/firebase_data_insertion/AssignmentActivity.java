package com.vk.creations.firebase_data_insertion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.muddzdev.styleabletoast.StyleableToast;

import java.util.ArrayList;
import java.util.Map;

public class AssignmentActivity extends AppCompatActivity {
    ListView assignment_listview;
    FirebaseListAdapter adapter;
    ArrayList<String> AssrignentNameArray=new ArrayList<>();
    ArrayList<String> SubjectNameArray=new ArrayList<>();
    ArrayList<String> LinkArray=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        assignment_listview=findViewById(R.id.assignment_list);
        Query query=FirebaseDatabase.getInstance().getReference().child("Assignments");
        FirebaseListOptions<AssignmentHelperClass> options=new FirebaseListOptions.Builder<AssignmentHelperClass>()
                .setLayout(R.layout.custom_assignment_view)
                .setQuery(query,AssignmentHelperClass.class)
                .build();

        adapter=new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView Topic=v.findViewById(R.id.ast_topic);
                TextView Subject=v.findViewById(R.id.sbj_name);
                AssignmentHelperClass assignmenObj=(AssignmentHelperClass) model;

                AssrignentNameArray.add(String.valueOf(assignmenObj.getName()));
                SubjectNameArray.add(String.valueOf(assignmenObj.getSubject()));
                LinkArray.add(String.valueOf(assignmenObj.getLink()));
                Topic.setText(assignmenObj.getName().toString());
                Subject.setText(assignmenObj.getSubject().toString());


            }
        };


        assignment_listview.setAdapter(adapter);
        assignment_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(AssignmentActivity.this, AssrignentNameArray.get(i), Toast.LENGTH_SHORT).show();
               // Toast.makeText(AssignmentActivity.this, LinkArray.get(i), Toast.LENGTH_SHORT).show();
                gotlink(LinkArray.get(i));
            }
        });


    }
    private void gotlink(String s) {

        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
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