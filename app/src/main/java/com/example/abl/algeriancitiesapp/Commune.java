package com.example.abl.algeriancitiesapp;


import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commune extends AppCompatActivity {
    private ListView recyclerView;

    private TextView place;
    private DatabaseReference databaseReference;
    private Button btn;
    private List<events> eventsList;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commune);
        recyclerView = (ListView) findViewById(R.id.recycler_view);
       // place = (TextView) findViewById(R.id.place);

        databaseReference = FirebaseDatabase.getInstance().getReference("Events");
        //place.setText(getIntent().getStringExtra("place"));

        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        eventsList = new ArrayList<>();
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat(("dd-MM-yyyy HH:mm:ss"));
        final String date = simpleDateFormat.format(calendar.getTime());

        //Date.setText(date);

       /* String p = place.getText().toString();
        Query Search = databaseReference.orderByChild("place").equalTo(p);
        Search.addListenerForSingleValueEvent(valueEventListener);*/
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot data2Snapshot) {

                eventsList.clear();
                for (DataSnapshot event2Snapshot : data2Snapshot.getChildren()) {
                    final events event = event2Snapshot.getValue(events.class);
                    final String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    FirebaseDatabase.getInstance().getReference("Responsable").child(id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            res Res = dataSnapshot.getValue(res.class);
                            String commune = Res.getCommune().toString();
                            String adr = event.getPlace().toString();
                            Pattern pattern = Pattern.compile(commune);
                            Matcher matcher = pattern.matcher(adr);
                            if (matcher.find()) {
                                eventsList.add(event);

                            }
                            eventList2 adapter=new eventList2(Commune.this,eventsList);
                            recyclerView.setAdapter(adapter);
                            int w1 = commune.compareTo("Adrar");
                            int w2 = commune.compareTo("Chlef");
                            int w3 = commune.compareTo("Laghouat");
                            int w4 = commune.compareTo("Oum El Bouagi");
                            int w5 = commune.compareTo("Batna");
                            int w6 = commune.compareTo("Béjaia");
                            int w7 = commune.compareTo("Biskra");
                            int w8 = commune.compareTo("Béchar");
                            int w9 = commune.compareTo("Blida");
                            int w10 = commune.compareTo("Bouira");
                            int w11 = commune.compareTo("Tamanrasset");
                            int w12 = commune.compareTo("Tébessa");
                            int w13 = commune.compareTo("Tlemcen");
                            int w14 = commune.compareTo("Tiaret");
                            int w15 = commune.compareTo("Tizi Ouzou");
                            int w16 = commune.compareTo("Alger");
                            int w17 = commune.compareTo("Djelfa");
                            int w18 = commune.compareTo("Jijel");
                            int w19 = commune.compareTo("Sétif");
                            int w20 = commune.compareTo("Saida");
                            int w21 = commune.compareTo("Skikda");
                            int w22 = commune.compareTo("Sidi Bel Abbès");
                            int w23 = commune.compareTo("Annaba");
                            int w24 = commune.compareTo("Guelma");
                            int w25 = commune.compareTo("Constantine");
                            int w26 = commune.compareTo("Médéa");
                            int w27 = commune.compareTo("Mostaganem");
                            int w28 = commune.compareTo("M'sila");
                            int w29 = commune.compareTo("Mascara");
                            int w30 = commune.compareTo("Ouergla");
                            int w31 = commune.compareTo("Oran");
                            int w32 = commune.compareTo("El Bayadh");
                            int w33 = commune.compareTo("Ilizi");
                            int w34 = commune.compareTo("Bordj Bou Arreridj");
                            int w35 = commune.compareTo("Boumerdess");
                            int w36 = commune.compareTo("Tarf");
                            int w37 = commune.compareTo("Tindouf");
                            int w38 = commune.compareTo("Tissemsilt");
                            int w39 = commune.compareTo("El Oued");
                            int w40 = commune.compareTo("Khenchela");
                            int w41= commune.compareTo("Souk Ahras");
                            int w42= commune.compareTo("Tipaza");
                            int w43 = commune.compareTo("Mila");
                            int w44= commune.compareTo("Ain Defla");
                            int w45= commune.compareTo("Naama");
                            int w46= commune.compareTo("Ain Témouchent");
                            int w47= commune.compareTo("Ghardaia");
                            int w48 = commune.compareTo("Relizane");
                            if(w1!=0){
                                recyclerView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                    @Override
                                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        final events event1 = eventsList.get(i);
                                        showUploadDialoge(event1.getId(),event1.getType(),event1.getCategorie(),event1.getDescription()
                                                ,event1.getEtat(),event1.getPlace(),event1.getImageUrl(), event1.getDate(),event1.getObservation(),getString(R.string.mod)+date);

                                        return false;

                                    }
                                });
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            eventsList.clear();
            for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()){
                events event = eventSnapshot.getValue(events.class);
                eventsList.add(event);

            }
            eventList2 adapter=new eventList2(Commune.this,eventsList);
            recyclerView.setAdapter(adapter);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }

    };
    public void showUploadDialoge(final String id, final String type, final String cat, final String desc
            , final String etat, final String place , final String img, final String date, final String obs,final String date2){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflat = getLayoutInflater();
        final View dialogView= inflat.inflate(R.layout.upload_dialog,null);
        dialog.setView(dialogView);
        final Spinner Etat = (Spinner) dialogView.findViewById(R.id.spinner);
        final EditText Obs = (EditText) dialogView.findViewById(R.id.obs);
        final Button btn=(Button) dialogView.findViewById(R.id.button);
        dialog.setTitle(getString(R.string.dialog));
        final AlertDialog alertDialog= dialog.create();
        alertDialog.show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etaat = Etat.getSelectedItem().toString().trim();
                String obbs = Obs.getText().toString().trim();
                //events event = new events();
                updateEvent(id,type,cat,desc,etaat,place ,img,date,obbs,date2);
                alertDialog.dismiss();

                Query Search = databaseReference.orderByChild("place").equalTo(place);
                Search.addListenerForSingleValueEvent(valueEventListener);

                //DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Events").child(id);
                //ref.removeValue();

            }
        });


    }
    public Boolean updateEvent(String id,String type, String cat,String desc,String etat,String place , String img,String date,String obs,String date2){

        DatabaseReference data = FirebaseDatabase.getInstance().getReference("Events").child(id);
        events event = new events(id,type,cat,desc,etat,place ,img,date,obs,date2);


        data.setValue(event);
        Toast.makeText(getApplicationContext(),getString(R.string.modification),Toast.LENGTH_LONG).show();
        return true;
    }


}
