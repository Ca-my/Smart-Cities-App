package com.example.abl.algeriancitiesapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class monProfil extends AppCompatActivity {
    private ListView listView;
    DatabaseReference databaseReference;
    private List<User> userList;
    ProgressDialog progressDialog;
    Button mod,sup;
    sessionManager sessionManager;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_profil);
        listView = (ListView) findViewById(R.id.list);
        sessionManager=new sessionManager(this);
        mod= (Button) findViewById(R.id.mod);
        sup= (Button) findViewById(R.id.supp);
        mAuth= FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Supprimer(view);
            }
        });

        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        monProfil.this,modPassword.class

                );
                // intent.putExtra("message",textInput)
                startActivity(intent);

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        userList = new ArrayList<>();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        Query Search = databaseReference.orderByChild("email").equalTo(email);
        Search.addListenerForSingleValueEvent(valueEventListener);
    }

    private void Supprimer(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String id =user.getUid();

        if (user!=null){
            progressDialog.setMessage(getString(R.string.suppEncours));
            progressDialog.show();
            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(id);
                        ref.removeValue();
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),getString(R.string.suppCmp),Toast.LENGTH_LONG).show();
                        sessionManager.logout();
                        finish();
                        Intent intent = new Intent(
                                monProfil.this,MainActivity.class

                        );
                        // intent.putExtra("message",textInput)
                        startActivity(intent);
                    }else{
                        progressDialog.dismiss();

                        Toast.makeText(getApplicationContext(),getString(R.string.suppImpo),Toast.LENGTH_LONG).show();

                    }

                }
            });

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser()!=null){

        }
    }


    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            userList.clear();
            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                User user = userSnapshot.getValue(User.class);
                userList.add(user);

            }
            userList adapter = new userList(monProfil.this, userList);
            listView.setAdapter(adapter);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
  /*  public void showUploadDialoge(final String id,String username, String numCarte,String email,String numTlfn,String password ) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflat = getLayoutInflater();
        final View dialogView = inflat.inflate(R.layout.upload_dialog, null);
        dialog.setView(dialogView);
        final Button btn = (Button) dialogView.findViewById(R.id.button);
        final EditText Username = (EditText) dialogView.findViewById(R.id.username);
        final EditText NumCarte = (EditText) dialogView.findViewById(R.id.numCarte);
        final EditText Email = (EditText) dialogView.findViewById(R.id.email);
        final EditText NumTlfn = (EditText) dialogView.findViewById(R.id.numTlfn);
        final EditText Password = (EditText) dialogView.findViewById(R.id.password);
        final EditText Password1 = (EditText) dialogView.findViewById(R.id.password1);
        dialog.setTitle("Modification des informations :");
        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user= new User();
                String username = Username.getText().toString().trim();
                String numCarte = NumCarte.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String numTlfn = NumTlfn.getText().toString().trim();
                //String password = Password.getText().toString().trim();
                String password1 = Password1.getText().toString().trim();
                updateUser(id,username,numCarte,email,numTlfn,password1);
                mAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"waaaaaaaay",Toast.LENGTH_LONG).show();

                        }
                    }
                });
                alertDialog.dismiss();



                //DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Events").child(id);
                //ref.removeValue();

            }
        });
    }
    public Boolean updateUser(String id,String username, String numCarte,String email,String numTlfn,String password ){
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("users").child(id);
        User user = new User(id,username,numCarte,email,numTlfn,password);
        data.setValue(user);
        return true;
    }
*/
}