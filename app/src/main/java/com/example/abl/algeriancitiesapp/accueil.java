package com.example.abl.algeriancitiesapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class accueil extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Button button;
    private sessionManager sessionManager;
    private TextView textView;
    private LinearLayout linearLayout,button1,button2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerAcc);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView= (NavigationView) findViewById(R.id.navigationAcc);
        navigationView.setNavigationItemSelectedListener(this);
        textView=(TextView) findViewById(R.id.tv_username);
        linearLayout=(LinearLayout) findViewById(R.id.ajoutEvent);

        sessionManager=new sessionManager(this);
        if(sessionManager.islogged()){
            //String email=sessionManager.getEmail();

            //textView.setText(email+",");
        }
       // button = (Button)findViewById(R.id.ajoutEvent);
        linearLayout.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent = new Intent(
                        accueil.this,ajoutEvenement.class

                );
                // intent.putExtra("message",textInput)
                startActivity(intent);

            }
        });
        button1 = (LinearLayout)findViewById(R.id.consulter);
        button1.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent = new Intent(
                        accueil.this,consultMaps.class

                );
                // intent.putExtra("message",textInput)
                startActivity(intent);

            }
        });
        button2 = (LinearLayout)findViewById(R.id.aide);
        button2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent = new Intent(
                        accueil.this,aide.class

                );
                // intent.putExtra("message",textInput)
                startActivity(intent);

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.accueil){
            Intent intent = new Intent(
                    this,accueil.class

            );
            // intent.putExtra("message",textInput)
            startActivity(intent);
            Toast.makeText(this,getString(R.string.acc),Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.dec){
            sessionManager.logout();
            Intent intent = new Intent(
                   accueil.this,AlgerianCities.class

            );
            // intent.putExtra("message",textInput)
           startActivity(intent);
            // Toast.makeText(this,"--Accueil--",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.Cons){

            Intent intent = new Intent(
                    accueil.this,consultMaps.class

            );
            // intent.putExtra("message",textInput)
            startActivity(intent);
            // Toast.makeText(this,"--Accueil--",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.historique){

            Intent intent = new Intent(
                    accueil.this,cunsultHistorique.class

            );
            // intent.putExtra("message",textInput)
            startActivity(intent);
            // Toast.makeText(this,"--Accueil--",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.profile){

            Intent intent = new Intent(
                    accueil.this,monProfil.class

            );
            // intent.putExtra("message",textInput)
            startActivity(intent);
            // Toast.makeText(this,"--Accueil--",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.help){

            Intent intent = new Intent(
                    accueil.this,aide.class

            );
            // intent.putExtra("message",textInput)
            startActivity(intent);
            // Toast.makeText(this,"--Accueil--",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}

