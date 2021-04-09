package com.vk.creations.firebase_data_insertion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;

public class AdminsActivity extends AppCompatActivity {
    ListView admin_listview;

    FirebaseListAdapter adapter;
    ArrayList<String> NameArray=new ArrayList<>();
    ArrayList<String> UsernameArray=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admins);
        admin_listview=findViewById(R.id.admin_list);

        final Query query= FirebaseDatabase.getInstance().getReference().child("Admin");
        FirebaseListOptions<UserHelperClass> options=new FirebaseListOptions.Builder<UserHelperClass>()
                .setLayout(R.layout.custom_ueser_design)
                .setQuery(query,UserHelperClass.class)
                .build();
        adapter=new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView txt_name=v.findViewById(R.id.lst_name);
                TextView txt_username=v.findViewById(R.id.lst_username);
                UserHelperClass user_Obj=(UserHelperClass) model;

                NameArray.add(String.valueOf(user_Obj.getName()));
                UsernameArray.add(String.valueOf(user_Obj.getUsername()));

                txt_name.setText(user_Obj.getName().toString());
                txt_username.setText(user_Obj.getUsername().toString());

            }
        };
        admin_listview.setAdapter(adapter);
        admin_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(AdminsActivity.this, NameArray.get(i), Toast.LENGTH_SHORT).show();

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