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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class signaler extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    Button cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10,cat11,cat12;
    private sessionManager sessionManager;
    private TextView evt,cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signaler);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerAcc);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView= (NavigationView) findViewById(R.id.navigationAcc);
        navigationView.setNavigationItemSelectedListener(this);
        sessionManager=new sessionManager(this);
        evt = (TextView) findViewById(R.id.sign);
        evt.setText(getIntent().getStringExtra("Evenement"));
        cat1 = (Button)findViewById(R.id.cat1);
        cat1.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String evtt = evt.getText().toString();
                String cat=cat1.getText().toString();
                Intent intent = new Intent(
                        signaler.this,MapsActivity.class

                );
                intent.putExtra("evt2",evtt);
                intent.putExtra("cat",cat);
                startActivity(intent);


            }
        });
        cat2 = (Button)findViewById(R.id.cat2);
        cat2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String evtt = evt.getText().toString();
                String cat=cat2.getText().toString();
                Intent intent = new Intent(
                        signaler.this,MapsActivity.class

                );
                intent.putExtra("evt2",evtt);
                intent.putExtra("cat",cat);
                startActivity(intent);

            }
        });
        cat3 = (Button)findViewById(R.id.cat3);
        cat3.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String evtt = evt.getText().toString();
                String cat=cat3.getText().toString();
                Intent intent = new Intent(
                        signaler.this,MapsActivity.class

                );
                intent.putExtra("evt2",evtt);
                intent.putExtra("cat",cat);
                startActivity(intent);

            }
        });
        cat4 = (Button)findViewById(R.id.cat4);
        cat4.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String evtt = evt.getText().toString();
                String cat=cat4.getText().toString();
                Intent intent = new Intent(
                        signaler.this,MapsActivity.class

                );
                intent.putExtra("evt2",evtt);
                intent.putExtra("cat",cat);
                startActivity(intent);

            }
        });
        cat5 = (Button)findViewById(R.id.cat5);
        cat5.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String evtt = evt.getText().toString();
                String cat=cat5.getText().toString();
                Intent intent = new Intent(
                        signaler.this,MapsActivity.class

                );
                intent.putExtra("evt2",evtt);
                intent.putExtra("cat",cat);
                startActivity(intent);

            }
        });
        cat6 = (Button)findViewById(R.id.cat6);
        cat6.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String evtt = evt.getText().toString();
                String cat=cat6.getText().toString();
                Intent intent = new Intent(
                        signaler.this,MapsActivity.class

                );
                intent.putExtra("evt2",evtt);
                intent.putExtra("cat",cat);
                startActivity(intent);

            }
        });

        cat7 = (Button)findViewById(R.id.cat7);
        cat7.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String evtt = evt.getText().toString();
                String cat=cat7.getText().toString();
                Intent intent = new Intent(
                        signaler.this,MapsActivity.class

                );
                intent.putExtra("evt2",evtt);
                intent.putExtra("cat",cat);
                startActivity(intent);

            }
        });
        cat8 = (Button)findViewById(R.id.cat8);
        cat8.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String evtt = evt.getText().toString();
                String cat=cat8.getText().toString();
                Intent intent = new Intent(
                        signaler.this,MapsActivity.class

                );
                intent.putExtra("evt2",evtt);
                intent.putExtra("cat",cat);
                startActivity(intent);

            }
        });
        cat9 = (Button)findViewById(R.id.cat9);
        cat9.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String evtt = evt.getText().toString();
                String cat=cat9.getText().toString();
                Intent intent = new Intent(
                        signaler.this,MapsActivity.class

                );
                intent.putExtra("evt2",evtt);
                intent.putExtra("cat",cat);
                startActivity(intent);

            }
        });
        cat10 = (Button)findViewById(R.id.cat10);
        cat10.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String evtt = evt.getText().toString();
                String cat=cat10.getText().toString();
                Intent intent = new Intent(
                        signaler.this,MapsActivity.class

                );
                intent.putExtra("evt2",evtt);
                intent.putExtra("cat",cat);
                startActivity(intent);

            }
        });
        cat11 = (Button)findViewById(R.id.cat11);
        cat11.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String evtt = evt.getText().toString();
                String cat=cat11.getText().toString();
                Intent intent = new Intent(
                        signaler.this,MapsActivity.class

                );
                intent.putExtra("evt2",evtt);
                intent.putExtra("cat",cat);
                startActivity(intent);

            }
        });
        cat12 = (Button)findViewById(R.id.cat12);
        cat12.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String evtt = evt.getText().toString();
                String cat=cat12.getText().toString();
                Intent intent = new Intent(
                        signaler.this,MapsActivity.class

                );
                intent.putExtra("evt2",evtt);
                intent.putExtra("cat",cat);
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
                    signaler.this,accueil.class

            );
            // intent.putExtra("message",textInput)
            startActivity(intent);
            Toast.makeText(this,getString(R.string.acc),Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.dec){
            sessionManager.logout();
            Intent intent = new Intent(
                    signaler.this,MainActivity.class

            );
            // intent.putExtra("message",textInput)
            startActivity(intent);
            // Toast.makeText(this,"--Accueil--",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.Cons){

            Intent intent = new Intent(
                    signaler.this,consultMaps.class

            );
            // intent.putExtra("message",textInput)
            startActivity(intent);
            // Toast.makeText(this,"--Accueil--",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.historique){

            Intent intent = new Intent(
                    signaler.this,cunsultHistorique.class

            );
            // intent.putExtra("message",textInput)
            startActivity(intent);
            // Toast.makeText(this,"--Accueil--",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.profile){

            Intent intent = new Intent(
                    signaler.this,monProfil.class

            );
            // intent.putExtra("message",textInput)
            startActivity(intent);
            // Toast.makeText(this,"--Accueil--",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.help){

            Intent intent = new Intent(
                    signaler.this,aide.class

            );
            // intent.putExtra("message",textInput)
            startActivity(intent);
            // Toast.makeText(this,"--Accueil--",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
