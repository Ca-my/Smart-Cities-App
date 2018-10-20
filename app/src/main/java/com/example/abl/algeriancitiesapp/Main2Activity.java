package com.example.abl.algeriancitiesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {
   // private List<res> resList;
   // private ListView listView;
    private EditText Email, Password;
    private Button con;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Email = (EditText) findViewById(R.id.email);
        Password=(EditText)findViewById(R.id.pass);
       con = (Button) findViewById(R.id.Con);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);

        mAuth= FirebaseAuth.getInstance();



         con.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String email=Email.getText().toString();
                String password=Password.getText().toString();
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
                {
                    progressBar.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {

                                final String id =mAuth.getCurrentUser().getUid().toString();
                                FirebaseDatabase.getInstance().getReference("Responsable").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.hasChild(id)) {

                                            Intent intent = new Intent(getApplicationContext(), accueil2.class);
                                            intent.putExtra("email", email);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(getApplicationContext(),getString(R.string.respExistPas),Toast.LENGTH_LONG).show();
                                        }

                                        progressBar.setVisibility(View.INVISIBLE);


                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }else{
                                String error = task.getException().getMessage();
                                Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);

                            }

                        }
                    });

                }
            }
        });




    }
}
