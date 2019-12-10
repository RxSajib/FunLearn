package com.example.funlearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UsersProfileActivity extends AppCompatActivity {

    private Button submit;
    private EditText name, age, gender;
    private DatabaseReference profiledatabase;
    private ProgressBar progressBar;
    private FirebaseAuth mauth;
    private String CurrentUserID;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String RadioString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_profile);
        progressBar = findViewById(R.id.pp);
        progressBar.setVisibility(View.INVISIBLE);
        profiledatabase = FirebaseDatabase.getInstance().getReference().child("users");
        name = findViewById(R.id.name);
        age = findViewById(R.id.Age);
        mauth = FirebaseAuth.getInstance();
        gender = findViewById(R.id.GenderID);
        submit = findViewById(R.id.SubmitID);
        CurrentUserID = mauth.getCurrentUser().getUid();
        radioGroup = findViewById(R.id.RgroupID);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(id);
                RadioString = radioButton.getText().toString();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String n = name.getText().toString();
                String A = age.getText().toString();
                String G = gender.getText().toString();

                if (n.isEmpty()) {

                } else if (A.isEmpty()) {

                } else if (G.isEmpty()) {

                }

                else if(RadioString.equals("")){

                }
                else {
                    progressBar.setEnabled(true);
                    Map usermap = new HashMap();
                    usermap.put("name", n);
                    usermap.put("gender", G);
                    usermap.put("age", A);
                    usermap.put("value", RadioString);

                    profiledatabase.child(CurrentUserID).updateChildren(usermap)
                            .addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
                                        name.setText("");
                                        age.setText("");
                                        gender.setText("");
                                        progressBar.setEnabled(false);
                                        Intent intent = new Intent(getApplicationContext(), UsersHomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    } else {
                                        progressBar.setEnabled(false);
                                    }
                                }
                            });
                }
            }
        });
    }
}
