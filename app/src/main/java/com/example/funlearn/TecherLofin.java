package com.example.funlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TecherLofin extends AppCompatActivity {
    private EditText email, password;
    private Button login;
    private DatabaseReference MAdmindatabase;
    private String emailtext, passwordtext;
    private String EmailText;
    private String PasswordText = "";
    private DatabaseReference Mpost;
    private String emailget, Passwordget;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_techer_lofin);


        progressDialog = new ProgressDialog(TecherLofin.this);
        MAdmindatabase = FirebaseDatabase.getInstance().getReference().child("Admin");

        Mpost = FirebaseDatabase.getInstance().getReference().child("Post");
        Mpost.child("value").setValue("value");

        MAdmindatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    if(dataSnapshot.hasChild("email")){
                        emailget = dataSnapshot.child("email").getValue().toString();
                    }
                    if(dataSnapshot.hasChild("Password")){
                        Passwordget = dataSnapshot.child("Password").getValue().toString();
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

                String emailstring = email.getText().toString();
                String passwordstring = password.getText().toString();

                if(emailstring.isEmpty()){
                    email.setError("email require");
                }
                else if(passwordstring.isEmpty()){
                    password.setError("password require");
                }
                else {
                    progressDialog.setMessage("please wait");
                    progressDialog.show();
                    progressDialog.setCanceledOnTouchOutside(false);
                    if(emailstring.equals(emailget) || passwordstring.equals(Passwordget)){
                        Intent intent = new Intent(getApplicationContext(), AdminHomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        progressDialog.dismiss();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });



    }
}
