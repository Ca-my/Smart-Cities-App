package com.example.abl.algeriancitiesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class aide extends AppCompatActivity {
    private LinearLayout ajout,consulter,profil,hist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aide);
        ajout=(LinearLayout) findViewById(R.id.Ajout);
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),aideAjout.class);
                startActivity(intent);
            }
        });
        consulter=(LinearLayout) findViewById(R.id.cons);
        consulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),aideConsulter.class);
                startActivity(intent);
            }
        });
        profil=(LinearLayout) findViewById(R.id.profil);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),aideConsulter.class);
                startActivity(intent);
            }
        });
        hist=(LinearLayout) findViewById(R.id.hist);
        hist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),aideHist.class);
                startActivity(intent);
            }
        });
    }
}
