package com.vk.creations.firebase_data_insertion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClassroomActivity extends AppCompatActivity {
    Button bt_user,bt_admin,btn_assignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);
       bt_user=findViewById(R.id.btn_user);
        bt_admin=findViewById(R.id.btn_admin);
        btn_assignment=findViewById(R.id.bt_assignment);
        btn_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClassroomActivity.this,AssignmentActivity.class));
            }
        });
        bt_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClassroomActivity.this,UsersActivity.class));
            }
        });

        bt_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClassroomActivity.this,AdminsActivity.class));
            }
        });
    }
}