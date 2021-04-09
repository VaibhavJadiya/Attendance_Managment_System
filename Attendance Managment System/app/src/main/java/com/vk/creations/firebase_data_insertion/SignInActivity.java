package com.vk.creations.firebase_data_insertion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.muddzdev.styleabletoast.StyleableToast;

import java.util.EventListener;
import java.util.HashMap;

public class SignInActivity extends AppCompatActivity  {
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    TextInputEditText l_username,l_password;
    Button butnSignin;
    TextInputLayout nameLayout,passwordLayout;
    String db_username,db_password;
   public String system_username,system_name;
    String session_username,session_password;
    public static final String NAME="name";
    public static final String USERNAME="username";
    CheckBox rememberme;
    ProgressBar l_progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        l_username=findViewById(R.id.username);
        l_password=findViewById(R.id.password);
        butnSignin=findViewById(R.id.btn_signin);
      //  txt_b_signup=findViewById(R.id.txt_signup);
        rememberme=findViewById(R.id.ck_rememberme);
        l_progressbar=findViewById(R.id.login_progressbar);
        Sprite DoubleBounce = new DoubleBounce();
        l_progressbar.setIndeterminateDrawable(DoubleBounce);
        nameLayout=findViewById(R.id.name_layout);
        passwordLayout=findViewById(R.id.password_layout);

        //Using Sesion to Re-Store Data if Present in Session
        SessionManager sessionManager=new SessionManager(SignInActivity.this,SessionManager.SESSION_Rememberme);
        if (sessionManager.CheckLRememberMe()){
            HashMap<String,String> rememberMeDeatils =sessionManager.getRememberMeDetailsFromSession();
            l_username.setText(rememberMeDeatils.get(SessionManager.RememberMe_USERNAME));
            session_username=rememberMeDeatils.get(SessionManager.RememberMe_USERNAME);
            l_password.setText(rememberMeDeatils.get(SessionManager.RememberMe_password));
            session_password=rememberMeDeatils.get(SessionManager.RememberMe_password);
           // StyleableToast.makeText(SignInActivity.this,"Rembered",R.style.success).show();
            Intent intent=new Intent(getApplicationContext(),DashboardActivity.class);
            intent.putExtra("username",session_username);

            startActivity(intent);

        }


        butnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!valid_name()|| !valid_password())
                {
                    return;
                }



               db_username=l_username.getText().toString().trim();
               db_password=l_password.getText().toString().trim();
                l_progressbar.setVisibility(View.VISIBLE);
                //Using Shared Prefrences
                if (rememberme.isChecked()){
                    SessionManager sessionManager=new SessionManager(SignInActivity.this,SessionManager.SESSION_Rememberme);
                    sessionManager.createRemebermeSesion(db_username,db_password);
                }
                //Database retriving
                Query checkUser=FirebaseDatabase.getInstance().getReference("Users").orderByChild("username").equalTo(db_username);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists())
                        {
                            String system_name=snapshot.child(db_username).child("name").getValue(String.class);
                            String system_username=snapshot.child(db_username).child("username").getValue(String.class);
                            String sysytem_number=snapshot.child(db_username).child("number").getValue(String.class);
                            String sysytem_mail=snapshot.child(db_username).child("email").getValue(String.class);

                            //retriving password
                            String system_password=snapshot.child(db_username).child("password").getValue(String.class);
                            if (system_password.equals(db_password)){
                                //Toast.makeText(SignInActivity.this, "Succefully Login", Toast.LENGTH_SHORT).show();
                                StyleableToast.makeText(SignInActivity.this,"Succesfully Login",R.style.success).show();
                                l_progressbar.setVisibility(View.INVISIBLE);
                                //Making Session for the first time Login
                                SessionManager sessionManager=new SessionManager(SignInActivity.this,SessionManager.SESSION_USER);
                                sessionManager.createLoginSesion(db_username,db_password);




                                Intent intent=new Intent(getApplicationContext(),DashboardActivity.class);
                                intent.putExtra("username",system_username);
                               // intent.putExtra("name",system_name);
                                startActivity(intent);
                                //startActivity(new Intent(SignInActivity.this,DashboardActivity.class));
                            }
                            else {
                               // Toast.makeText(SignInActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                StyleableToast.makeText(SignInActivity.this,"Wrong Password",R.style.error).show();
                                l_progressbar.setVisibility(View.INVISIBLE);
                            }
                            //Passing Values To Profile Activity
                           /* Intent intent=new Intent(getApplicationContext(),ProfileActivity.class);
                            intent.putExtra("name",system_name);
                            intent.putExtra("username",system_username);
                            intent.putExtra("email",sysytem_mail);
                            intent.putExtra("number",sysytem_number);
                            intent.putExtra("password",system_password);
                            startActivity(intent);*/

                        }
                        else{
                            //Toast.makeText(SignInActivity.this, "User Dose'nt exists", Toast.LENGTH_SHORT).show();
                            StyleableToast.makeText(SignInActivity.this,"Invalid Username",R.style.error).show();
                            l_progressbar.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                       // Toast.makeText(SignInActivity.this, "Invalid Username Or Password", Toast.LENGTH_SHORT).show();
                        StyleableToast.makeText(SignInActivity.this,"Invalid Username Or Password",R.style.error).show();
                        l_progressbar.setVisibility(View.INVISIBLE);
                    }
                });

            }
        });


    }

    private Boolean valid_name(){
        String val=l_username.getText().toString().trim();
        if(val.isEmpty()){
            nameLayout.setError("Name Can't be Empty");
            return false;
        }
        else{
            nameLayout.setError(null);
            return true;
        }

    }
    private Boolean valid_password(){
        String val=l_password.getText().toString().trim();
        if(val.isEmpty()){
            passwordLayout.setError("Password Can't be Empty");
            return false;
        }
        else{
            passwordLayout.setError(null);
            return true;
        }

    }


}