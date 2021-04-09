package com.vk.creations.firebase_data_insertion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.muddzdev.styleabletoast.StyleableToast;

public class SignupActivity extends AppCompatActivity {


    TextInputEditText u_name,u_email,u_username,u_number,u_password;
    TextInputLayout nameLayout;
    Button signup,existing_user;
    String s_name,s_email,s_username,s_number,s_password,val;
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        u_name=findViewById(R.id.input_name);
        u_email=findViewById(R.id.input_email);
        u_username=findViewById(R.id.input_username);
        u_number=findViewById(R.id.input_number);
        u_password=findViewById(R.id.input_password);
        signup=findViewById(R.id.btn_signup);
        existing_user=findViewById(R.id.btn_existing_user);
        bar=findViewById(R.id.progreesbar);
        nameLayout=findViewById(R.id.name_layout);



        existing_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this,SignInActivity.class));
            }
        });






        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootnode=FirebaseDatabase.getInstance();
                reference=rootnode.getReference("Users");

                s_name=u_name.getText().toString().trim();


                s_email=u_email.getText().toString().trim();
                s_username=u_username.getText().toString().trim();
                s_number=u_number.getText().toString().trim();
                s_password=u_password.getText().toString().trim();

                //Checking is Username alrady exists



                //Validating Form

                    //Direct Signup ho jaega
                    bar.setVisibility(View.VISIBLE);
                    UserHelperClass helperClass=new UserHelperClass(s_name,s_email,s_username,s_number,s_password);
                    reference.child(s_username).setValue(helperClass);
                    bar.setVisibility(View.INVISIBLE);
                   // Toast.makeText(SignupActivity.this, "Registered Sucessful!", Toast.LENGTH_SHORT).show();
                StyleableToast.makeText(SignupActivity.this,"Registration Succefull",R.style.success).show();
                    startActivity(new Intent(SignupActivity.this,SignInActivity.class));



            }
        });


    }

    private Boolean valid_name(){
        String val=u_name.getText().toString().trim();
        if(val.isEmpty()){
            nameLayout.setError("Name Can't be Empty");
            return false;
        }
        else{
            nameLayout.setError(null);
            return true;
        }

    }

}