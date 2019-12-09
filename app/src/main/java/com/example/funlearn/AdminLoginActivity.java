package com.example.funlearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login;
    private DatabaseReference MAdmindatabase;
    private String emailtext, passwordtext;
    private String EmailText;
    private String PasswordText = "";
    private DatabaseReference Mpost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        MAdmindatabase = FirebaseDatabase.getInstance().getReference().child("Admin");

        Mpost = FirebaseDatabase.getInstance().getReference().child("Post");

        MAdmindatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    if(dataSnapshot.hasChild("email")){
                        String email = dataSnapshot.child("email").getValue().toString();
                        Log.i("email", email);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        email = findViewById(R.id.AdminEmailID);
        password = findViewById(R.id.AdminPaswordIFD);
        login  =findViewById(R.id.LLgoinID);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Mpost.child("users").child("users_").setValue("sss")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();

                                }
                            }
                        });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailstring = email.getText().toString();
                String passwordstring = password.getText().toString();

                if(emailstring.isEmpty()){
                    email.setError("email require");
                }
                else if(passwordstring.isEmpty()){
                    password.setError("password require");
                }
                else {

                    if(email.getText().equals(emailstring)){

                    }
                }
            }
        });



    }
}
