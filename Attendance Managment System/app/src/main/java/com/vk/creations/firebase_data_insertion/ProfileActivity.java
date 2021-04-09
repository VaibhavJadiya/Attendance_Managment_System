package com.vk.creations.firebase_data_insertion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    TextView p_name,p_username,p_email,p_number,p_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final ProgressBar pg_profile;
        FirebaseDatabase rootnode;
        DatabaseReference reference;

        p_name=findViewById(R.id.profile_name);
        p_username=findViewById(R.id.profile_username);
        p_email=findViewById(R.id.profile_email);
        p_number=findViewById(R.id.profile_number);
        p_password=findViewById(R.id.profile_password);
        pg_profile=findViewById(R.id.progreesbar_profile);

        Intent intent=getIntent();
        final String dis_username=intent.getStringExtra("username");

        p_username.setText(dis_username);
        //Retriving Data From LoginActivity
        Query checkUser=FirebaseDatabase.getInstance().getReference("Users").orderByChild("username").equalTo(dis_username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data_name=snapshot.child(dis_username).child("name").getValue(String.class);
                String data_email=snapshot.child(dis_username).child("email").getValue(String.class);
                String data_number=snapshot.child(dis_username).child("number").getValue(String.class);
                String data_password=snapshot.child(dis_username).child("password").getValue(String.class);
                p_name.setText(data_name);
                p_email.setText(data_email);
                p_number.setText(data_number);
                p_password.setText(data_password);
                pg_profile.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

}