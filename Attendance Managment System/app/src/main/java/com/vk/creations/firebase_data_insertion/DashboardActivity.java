package com.vk.creations.firebase_data_insertion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.muddzdev.styleabletoast.StyleableToast;

public class DashboardActivity extends AppCompatActivity {

   //private DrawerLayout drawerLayout;
  //  private NavigationView navigationView;
   // private ActionBarDrawerToggle toggle;


    Button bt_classes,bt_profile,bt_logout,bt_attendance,bt_classroom;
    TextView dislpayname;
    String data_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //creating navigation
      //  Toolbar toolbar;
      //  toolbar = findViewById(R.id.tooolbar);
     //   navigationView=findViewById(R.id.navigation);
      //  drawerLayout=findViewById(R.id.drawer_layout);
       //// setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // toggle=new ActionBarDrawerToggle(DashboardActivity.this,drawerLayout,R.string.open,R.string.close);
       // drawerLayout.addDrawerListener(toggle);
       // toggle.syncState();


        Intent intent=getIntent();
       final String db_username=intent.getStringExtra("username");

        bt_classes=findViewById(R.id.btn_classes);
        bt_logout=findViewById(R.id.btn_logout);
        bt_classroom=findViewById(R.id.btn_classroom);
        dislpayname=findViewById(R.id.username_display_text);

        bt_profile=findViewById(R.id.btn_profile);
        bt_attendance=findViewById(R.id.btn_Attendance);

        Query checkUser= FirebaseDatabase.getInstance().getReference("Users").orderByChild("username").equalTo(db_username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_name=snapshot.child(db_username).child("name").getValue(String.class);
                dislpayname.setText("Welcome "+data_name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        bt_classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                intent.putExtra("username",db_username);
                intent.putExtra("name",data_name);
                startActivity(intent);

            }
        });
        bt_classroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this,ClassroomActivity.class));
            }
        });
        bt_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(DashboardActivity.this,ProfileActivity.class));

                Intent intent=new Intent(getApplicationContext(),ProfileActivity.class);
                intent.putExtra("username",db_username);


                startActivity(intent);
            }
        });
        bt_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),DateAttendanceActivity.class);
                intent.putExtra("username",db_username);
                startActivity(intent);

            }
        });

        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(DashboardActivity.this, "LOG OUT SUCCEFULLY!", Toast.LENGTH_SHORT).show();
                SessionManager sessionManager=new SessionManager(DashboardActivity.this,SessionManager.SESSION_Rememberme);
                sessionManager.LogoutUserFromSession();
                StyleableToast.makeText(DashboardActivity.this,"LOG OUT Succesfully!",R.style.success).show();
                startActivity(new Intent(DashboardActivity.this,SignInActivity.class));
            }
        });
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }


}