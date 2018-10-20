package com.example.abl.algeriancitiesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class modPassword extends AppCompatActivity {
    private EditText nvMdp,Mdp ;
    private Button valider;
    sessionManager sessionManager;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_password);
        nvMdp=(EditText) findViewById(R.id.editText);
        Mdp=(EditText) findViewById(R.id.pass);
        valider=(Button) findViewById(R.id.valider);
        sessionManager= new sessionManager(this);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Changer(view);
            }
        });
    }

    private void Changer(View v) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String id = user.getUid().toString();

        FirebaseDatabase.getInstance().getReference("users").child(id).
                addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final User user = dataSnapshot.getValue(User.class);
                String password=user.getPassword().toString();
                final String pass=Mdp.getText().toString();
                int comparaison = password.compareTo(pass);
                if(comparaison==0){
                    FirebaseUser user2 = FirebaseAuth.getInstance().getCurrentUser();
                    if (user2!=null){
                        user2.updatePassword(nvMdp.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            updateUser(id,user.getName(),user.getPname(),user.getNumCarte(),user.getEmail(),user.getTlfn(),nvMdp.getText().toString());
                                            finish();
                                            sessionManager.logout();
                                            finish();
                                            Intent intent = new Intent(
                                                    modPassword.this,connexion.class

                                            );
                                            // intent.putExtra("message",textInput)
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(getApplicationContext(),getString(R.string.motPassErreur),Toast.LENGTH_LONG).show();

                                        }

                                    }
                                });

                    }
                }else{
                    Toast.makeText(getApplicationContext(),getString(R.string.mdpActuel),Toast.LENGTH_LONG).show();
                    Mdp.setText("");
                    nvMdp.setText("");

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
    public Boolean updateUser(String id, String name, String pname, String numCarte, String email, String tlfn, String password){
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("users").child(id);
        User user=new User(id,name,pname,numCarte,email, tlfn, password);
        data.setValue(user);
        Toast.makeText(getApplicationContext(),getString(R.string.modification),Toast.LENGTH_LONG).show();
        return true;
    }

}
