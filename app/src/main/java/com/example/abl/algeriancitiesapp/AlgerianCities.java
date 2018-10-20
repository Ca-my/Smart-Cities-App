package com.example.abl.algeriancitiesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AlgerianCities extends AppCompatActivity {
    private Button Citoyen, Responsable;
    private sessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algerian_cities);
        Citoyen=(Button)findViewById(R.id.cit);
        Citoyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cit = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(cit);
            }
        });
        Responsable=(Button)findViewById(R.id.res);
        Responsable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent res = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(res);

            }
        });
        sessionManager=new sessionManager(this);
        if(sessionManager.islogged()){
            Intent intent = new Intent(
                    AlgerianCities.this,accueil.class

            );

            startActivity(intent);
        }
    }
}
