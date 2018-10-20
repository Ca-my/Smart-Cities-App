package com.example.abl.algeriancitiesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class inscription extends AppCompatActivity implements View.OnClickListener{
    private EditText Name,Pname,NumCarte,Email,Tlfn,Password,Password2;
 // private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        Name=(EditText) findViewById(R.id.username);
        Pname=(EditText) findViewById(R.id.pname);
        NumCarte=(EditText) findViewById(R.id.numCarte);
        Email=(EditText) findViewById(R.id.email);
        Tlfn=(EditText) findViewById(R.id.tlfn);
        Password=(EditText) findViewById(R.id.password);
        Password2=(EditText) findViewById(R.id.password2);
        //progressBar = (ProgressBar) findViewById(R.id.progressBar) ;

        mAuth= FirebaseAuth.getInstance();
        findViewById(R.id.validerInsc).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser()!=null){

        }
    }

    private void registerUser(){

        final String name = Name.getText().toString().trim();
        final String pname = Pname.getText().toString().trim();
        final String numCarte = NumCarte.getText().toString().trim();
        final String email= Email.getText().toString().trim();
        final String tlfn= Tlfn.getText().toString().trim();
        final String password = Password.getText().toString().trim();
        String password2 = Password2.getText().toString().trim();

        if(name.isEmpty()){
            Name.setError(getString(R.string.verifName));
            Name.requestFocus();
            return;
        }
        if(pname.isEmpty()){
            Pname.setError(getString(R.string.verifPname));
           Pname.requestFocus();
            return;
        }
        if(numCarte.isEmpty()){
            NumCarte.setError(getString(R.string.verifnumcart));
            NumCarte.requestFocus();
            return;
        }

        if(email.isEmpty()){
            Email.setError(getString(R.string.verifemail));
            Email.requestFocus();
            return;
        }
        if(tlfn.isEmpty()){
            Tlfn.setError(getString(R.string.veriftlfn));
            Tlfn.requestFocus();
            return;
        }

        if(password.isEmpty()){
            Password.setError(getString(R.string.verifMotpass));
            Password.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError(getString(R.string.invalidEmail));
            Email.requestFocus();
            return;
        }
        if(password.length()<6){
            Password.setError(getString(R.string.verifpass));
            Password.requestFocus();
            return;
        }
        if(tlfn.length()!=10){
            Tlfn.setError(getString(R.string.veriflong));
            Tlfn.requestFocus();
            return;
        }
        int comparaison = password.compareTo(password2);
        if(comparaison!=0){
            Password.setError(getString(R.string.different));
            Password.requestFocus();
            Password.setText("");
            Password2.setText("");
            return;

        }

     // progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    User user= new User(id ,name,pname,numCarte,email,tlfn,password);
                    FirebaseDatabase.getInstance().getReference("users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                          // progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()){
                                sendEmailVerification();
                                Intent intent= new Intent(getApplicationContext(),connexion.class);
                                startActivity(intent);

                                //Toast.makeText(inscription.this,"registration successfully",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(inscription.this,getString(R.string.verifConx),Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                }else{
                    Toast.makeText(inscription.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void sendEmailVerification() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), getString(R.string.msgConfirmation), Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                    }

                }
            });
        }
    }
    private void checkEmailVerification() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Boolean verify = user.isEmailVerified();
        if (!verify){
            Toast.makeText(getApplicationContext(), getString(R.string.verifMail), Toast.LENGTH_SHORT).show();

        }else{
            Intent intent= new Intent(getApplicationContext(),accueil.class);
            startActivity(intent);

        }
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.validerInsc:
                registerUser();
                break;
        }

    }
}
