package com.example.abl.algeriancitiesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class accueil2 extends AppCompatActivity {
    private LinearLayout con,Aide,com;
    private TextView propre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil2);
        con = (LinearLayout) findViewById(R.id.Con);
        propre = (TextView) findViewById(R.id.propreCom);
        con.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(
                        accueil2.this, consult2Maps.class

                );
                startActivity(intent);

            }
        });
        Aide = (LinearLayout) findViewById(R.id.aide);
        Aide.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(
                        accueil2.this, aideRes.class

                );
                startActivity(intent);

            }
        });
        com = (LinearLayout) findViewById(R.id.com);
        com.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(
                        accueil2.this, Commune.class

                );
                startActivity(intent);

            }
        });
        final String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference("Responsable").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                res Res = dataSnapshot.getValue(res.class);
                String commune = Res.getCommune().toString();
                propre.setText(commune);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
