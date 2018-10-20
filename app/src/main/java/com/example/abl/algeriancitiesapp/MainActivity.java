package com.example.abl.algeriancitiesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button con,insc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con=(Button)findViewById(R.id.con);
        insc=(Button)findViewById(R.id.insc);

        con.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent = new Intent(
                        MainActivity.this,connexion.class

                );
                // intent.putExtra("message",textInput)
                startActivity(intent);

            }
        });
        insc.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent = new Intent(
                        MainActivity.this,inscription.class

                );
                // intent.putExtra("message",textInput)
                startActivity(intent);

            }
        });
    }
}
