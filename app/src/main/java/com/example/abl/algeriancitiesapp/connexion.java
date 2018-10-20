package com.example.abl.algeriancitiesapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class connexion extends AppCompatActivity {
    private EditText Email,Password;
    private Button con;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private sessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        Email=(EditText)findViewById(R.id.email);
        Password=(EditText)findViewById(R.id.password);
        con=(Button) findViewById(R.id.validerCon);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        sessionManager=new sessionManager(this);

        mAuth= FirebaseAuth.getInstance();
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email =Email.getText().toString();
                String password =Password.getText().toString();
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
                {
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                checkEmailVerification();
                            }else{
                                String error = task.getException().getMessage();
                                Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
                            }
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    });

                }

            }
        });
    }

    private void checkEmailVerification() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Boolean verify = user.isEmailVerified();
        if (!verify){
            Toast.makeText(getApplicationContext(), getString(R.string.msgConfirm), Toast.LENGTH_SHORT).show();

        }else{
            sessionManager.insertUser(Email.getText().toString());
            Intent intent= new Intent(connexion.this,accueil.class);
            startActivity(intent);

        }
    }
   /* @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            AccueilLog();
        } else {
            // No user is signed in
        }
    }

    private void AccueilLog() {
        Intent intent= new Intent(this,accueil.class);
        startActivity(intent);

    }*/

}
