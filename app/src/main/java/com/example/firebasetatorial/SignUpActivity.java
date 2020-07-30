package com.example.firebasetatorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebasetatorial.Modal.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    private EditText username ,password,phonenumer,firstname,lastname;
    private Button signup;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private HashMap<String ,User> mUserHashMap;
    private DatabaseReference mDatabaseReference= FirebaseDatabase.getInstance().getReference("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        connection();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em,pass;
                em=username.getText().toString();
                pass=password.getText().toString();
                if (em!="" && pass != "")
                {
                    final String fn,ln,pn,pic = "";
                    fn=firstname.getText().toString();
                    ln=lastname.getText().toString();
                    pn=phonenumer.getText().toString();
                    final User user = new User(fn,ln,em,pass,pic,pn);

                 mFirebaseAuth.createUserWithEmailAndPassword(em,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful())
                         {
                             FirebaseUser firebaseUser =mFirebaseAuth.getCurrentUser();
                                  // mUserHashMap.put("information",user);
                                   mDatabaseReference.child(firebaseUser.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {
                                           Toast.makeText(getApplicationContext(),"Information added Succssfully",Toast.LENGTH_SHORT).show();
                                       }
                                   });

                                   startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                                   finish();
                         }

                     }
                 });
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Fields are Empty",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void connection() {
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        phonenumer=findViewById(R.id.phonenumber);
        signup=findViewById(R.id.btn_signup);
        mFirebaseAuth=FirebaseAuth.getInstance();
        mFirebaseUser=mFirebaseAuth.getCurrentUser();
        mUserHashMap=new HashMap<>();
    }


}