package com.vk.creations.firebase_data_insertion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    Button btn_submit;
    Button txt_b_signin;
    EditText u_username,u_usernumber,u_useremail,u_userpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        btn_submit=findViewById(R.id.btn_submit);
        u_useremail=findViewById(R.id.user_email);
        u_username=findViewById(R.id.user_name);
        u_usernumber=findViewById(R.id.user_number);
        u_userpassword=findViewById(R.id.user_password);
        txt_b_signin=findViewById(R.id.txt_signin);


        txt_b_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SignInActivity.class));
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                addUsere();





            }
        });



    }
    private void addUsere(){

        String mail=u_useremail.getText().toString().trim();
        String name =u_username.getText().toString().trim();
        String number=u_usernumber.getText().toString().trim();
        String password=u_userpassword.getText().toString().trim();
        if(TextUtils.isEmpty(mail)||TextUtils.isEmpty(name)||TextUtils.isEmpty(number)||TextUtils.isEmpty(password)){

            Toast.makeText(MainActivity.this, "Fill the Complete Form", Toast.LENGTH_SHORT).show();
        }


        else {


            String ID = databaseReference.push().getKey();
            userInfo userInfo = new userInfo(ID, mail, name, number, password);
            databaseReference.child(ID).setValue(userInfo);
            Toast.makeText(MainActivity.this, "Record Succesfully Inserted", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,SignInActivity.class));
        }

    }

}