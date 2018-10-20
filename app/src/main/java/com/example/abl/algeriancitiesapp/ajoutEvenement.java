package com.example.abl.algeriancitiesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ajoutEvenement extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private LinearLayout button,button1,button2;
    private sessionManager sessionManager;
    private TextView btn,btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_evenement);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerAcc);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView= (NavigationView) findViewById(R.id.navigationAcc);
        navigationView.setNavigationItemSelectedListener(this);
        sessionManager=new sessionManager(this);
        button = (LinearLayout) findViewById(R.id.signaler);
        btn = (TextView) findViewById(R.id.sign);
        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String evt =btn.getText().toString();
               // Intent intent2 = new Intent (ajoutEvenement.this,evenement.class);

                Intent intent = new Intent(
                        ajoutEvenement.this,signaler.class

                );
                intent.putExtra("Evenement",evt);
                startActivity(intent);





            }
        });
        button1 = (LinearLayout) findViewById(R.id.suggerer);
        btn1 = (TextView) findViewById(R.id.sug);
        button1.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String evt =btn1.getText().toString();
                Intent intent = new Intent(
                        ajoutEvenement.this,suggerer.class

                );
                intent.putExtra("Evenement",evt);
                // intent.putExtra("message",textInput)
                startActivity(intent);

            }
        });
        button2 = (LinearLayout) findViewById(R.id.feliciter);
        btn2 = (TextView) findViewById(R.id.fel);
        button2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String evt =btn2.getText().toString();
                Intent intent = new Intent(
                       ajoutEvenement.this,feliciter.class

                );
                intent.putExtra("Evenement",evt);
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
                    ajoutEvenement.this,accueil.class

            );
            // intent.putExtra("message",textInput)
            startActivity(intent);
            Toast.makeText(this,getString(R.string.acc),Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.dec){
            sessionManager.logout();
            Intent intent = new Intent(
                    ajoutEvenement.this,MainActivity.class

            );
            // intent.putExtra("message",textInput)
            startActivity(intent);
            // Toast.makeText(this,"--Accueil--",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.Cons){

            Intent intent = new Intent(
                    ajoutEvenement.this,consultMaps.class

            );
            // intent.putExtra("message",textInput)
            startActivity(intent);
            // Toast.makeText(this,"--Accueil--",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.historique){

            Intent intent = new Intent(
                    ajoutEvenement.this,cunsultHistorique.class

            );
            // intent.putExtra("message",textInput)
            startActivity(intent);
            // Toast.makeText(this,"--Accueil--",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.profile){

            Intent intent = new Intent(
                    ajoutEvenement.this,monProfil.class

            );
            // intent.putExtra("message",textInput)
            startActivity(intent);
            // Toast.makeText(this,"--Accueil--",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.help){

            Intent intent = new Intent(
                    ajoutEvenement.this,aide.class

            );
            // intent.putExtra("message",textInput)
            startActivity(intent);
            // Toast.makeText(this,"--Accueil--",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
