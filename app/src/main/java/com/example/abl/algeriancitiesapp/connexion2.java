package com.example.abl.algeriancitiesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class connexion2 extends AppCompatActivity {
    private TextView Email,Email1,Email2;
    private EditText Password;
    private Button Con;
    private List<res> resList;
    private ListView listView;
    private DatabaseReference databaseReference;    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion2);
        databaseReference = FirebaseDatabase.getInstance().getReference("Responsable");

        Email = (TextView) findViewById(R.id.email);
        //Email1 = (TextView) findViewById(R.id.email1);
        Email2=(TextView) findViewById(R.id.email2);

        Password = (EditText) findViewById(R.id.password);
        Con = (Button) findViewById(R.id.con);
        resList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list);
        Email.setText(getIntent().getStringExtra("email"));
        String email = Email.getText().toString();
        String pass = Password.getText().toString();
        Query Search = databaseReference.orderByChild("eemail").equalTo(email);
       // String id = databaseReference.orderByChild("eemail").toString();//text matcher et equal to est le patern

        Search.addListenerForSingleValueEvent(value);
        mAuth = FirebaseAuth.getInstance();
        Con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailSah = Email2.getText().toString();
                String password= Password.getText().toString();
                if(resList.size()==0) {
                    Toast.makeText(getApplicationContext(),getString(R.string.msgEmail),Toast.LENGTH_LONG).show();;

                }

                if(!TextUtils.isEmpty(emailSah) && !TextUtils.isEmpty(password))
                {

                    mAuth.signInWithEmailAndPassword(emailSah,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent= new Intent(connexion2.this,accueil2.class);
                                startActivity(intent);
                            }else{
                                String error = task.getException().getMessage();
                                Toast.makeText(getApplicationContext(),"error:"+error, Toast.LENGTH_LONG).show();

                            }
                        }
                    });

                }
            }
        });



       /* Con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(connexion2.this,accueil.class);
                startActivity(intent);
                String emailSah = Email2.getText().toString();
                String password= Password.getText().toString();
                if(resList.size()==0) {
                    Toast.makeText(getApplicationContext(),getString(R.string.msgEmail),Toast.LENGTH_LONG).show();;

                }

                if(!TextUtils.isEmpty(emailSah) && !TextUtils.isEmpty(password))
                {

                    mAuth.signInWithEmailAndPassword(emailSah,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent= new Intent(connexion2.this,accueil.class);
                                startActivity(intent);
                            }else{
                                String error = task.getException().getMessage();
                                Toast.makeText(getApplicationContext(),"error:"+error, Toast.LENGTH_LONG).show();

                            }
                        }
                    });

                }
            }
        });





    }*/
    }
    ValueEventListener value =new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            resList.clear();
            for (DataSnapshot resSnapshot : dataSnapshot.getChildren()){
                res res =resSnapshot.getValue(res.class);
                resList.add(res);

            }
            resList adapter=new resList(connexion2.this,resList);
            listView.setAdapter(adapter);
            if(resList.size()==1){
                String email1=Email.getText().toString();
                Email2.setText(email1);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
