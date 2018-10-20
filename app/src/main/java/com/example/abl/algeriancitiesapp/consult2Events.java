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

public class consult2Events extends AppCompatActivity {
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
        setContentView(R.layout.activity_consult2_events);
        recyclerView=(ListView) findViewById(R.id.recycler_view);
        place=(TextView) findViewById(R.id.place);

        databaseReference=FirebaseDatabase.getInstance().getReference("Events");
        place.setText(getIntent().getStringExtra("place"));

        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        eventsList=new ArrayList<>();
        calendar= Calendar.getInstance();
        simpleDateFormat= new SimpleDateFormat(("dd-MM-yyyy HH:mm:ss"));
        final String  date = simpleDateFormat.format(calendar.getTime());

        //Date.setText(date);

        String p=place.getText().toString();
        Query Search = databaseReference.orderByChild("place").equalTo(p);
        Search.addListenerForSingleValueEvent(valueEventListener);
        recyclerView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final events event = eventsList.get(i);
                final String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseDatabase.getInstance().getReference("Responsable").child(id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        eventsList.clear();
                        res Res= dataSnapshot.getValue(res.class);
                        String commune =Res.getCommune().toString();
                        String adr =event.getPlace().toString();
                        Pattern pattern = Pattern.compile(commune);
                        Matcher matcher = pattern.matcher(adr);
                        if(matcher.find()) {
                            showUploadDialoge(event.getId(),event.getType(),event.getCategorie(),event.getDescription()
                                    ,event.getEtat(),event.getPlace(),event.getImageUrl(),event.getDate(),event.getObservation(),getString(R.string.mod)+date);
                        }else{
                            Toast.makeText(getApplicationContext(),getString(R.string.droits),Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



                return false;
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
            eventList adapter=new eventList(consult2Events.this,eventsList);
            recyclerView.setAdapter(adapter);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }

    };
    public void showUploadDialoge(final String id, final String type, final String cat, final String desc
            , final String etat, final String place , final String img, final String date, final String obs, final String date2){
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
    public Boolean updateEvent(String id,String type, String cat,String desc,String etat,
                               String place , String img,String date,String obs,String date2){

        DatabaseReference data = FirebaseDatabase.getInstance().getReference("Events").child(id);
        events event = new events(id,type,cat,desc,etat,place ,img,date,obs,date2);


        data.setValue(event);
        Toast.makeText(getApplicationContext(),getString(R.string.modification),Toast.LENGTH_LONG).show();
        return true;
    }

}