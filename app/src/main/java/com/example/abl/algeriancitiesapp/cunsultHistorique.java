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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class cunsultHistorique extends AppCompatActivity {
    private ListView recyclerView;

    private TextView place;
    private DatabaseReference databaseReference,ref;
    private Button btn;
    private List<events> eventsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cunsult_historique);
        recyclerView=(ListView) findViewById(R.id.recycler_view);
        place=(TextView) findViewById(R.id.place);
        String IdUser= FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference("UsersEvents").child(IdUser);
        ref= FirebaseDatabase.getInstance().getReference("Events");
        place.setText(getString(R.string.hist));
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        eventsList=new ArrayList<>();
        recyclerView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                events event = eventsList.get(i);

                showUploadDialoge(event.getId(),event.getType(),event.getCategorie(),event.getDescription()
                        ,event.getEtat(),event.getPlace(),event.getImageUrl(),event.getDate(),event.getObservation(),event.getDate2());

                return false;
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventsList.clear();
                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()){
                    events event = eventSnapshot.getValue(events.class);
                    String id =event.getId().toString();
                    Query Search = ref.orderByChild("id").equalTo(id);
                    Search.addListenerForSingleValueEvent(valueEventListener);
                    //eventsList.add(event);

                }
                //eventList adapter=new eventList(cunsultHistorique.this,eventsList);
                //recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            //eventsList.clear();
            for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()){
                events event2 = eventSnapshot.getValue(events.class);
                eventsList.add(event2);

            }
            eventList adapter=new eventList(cunsultHistorique.this,eventsList);
            recyclerView.setAdapter(adapter);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }

    };
    public void showUploadDialoge(final String id, final String type, final String cat, final String desc
            , final String etat, final String place , final String img, final String date,final String obs,final String date2) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflat = getLayoutInflater();
        final View dialogView = inflat.inflate(R.layout.upsupp_dialog, null);
        dialog.setView(dialogView);
        final EditText Desc = (EditText) dialogView.findViewById(R.id.nvDesc);
        final Button btn = (Button) dialogView.findViewById(R.id.mod);
        final Button btn1 = (Button) dialogView.findViewById(R.id.supp);
        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deesc = Desc.getText().toString().trim();
                //events event = new events();
                updateEvent(id, type, cat, deesc, etat, place, img, date,obs,date2);
                alertDialog.dismiss();



            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(id).removeValue();
                ref.child(id).removeValue();
                Toast.makeText(getApplicationContext(),getString(R.string.supprimerEvt2),Toast.LENGTH_LONG).show();
                alertDialog.dismiss();



            }
        });

    }
    public Boolean updateEvent(String id,String type, String cat,String desc,String etat,String place , String img,String date,String obs,String date2){
        String IdUser= FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference data = FirebaseDatabase.getInstance().getReference("Events").child(id);
        DatabaseReference data2=FirebaseDatabase.getInstance().getReference("UsersEvents").child(IdUser).child(id);
        events event = new events(id,type,cat,desc,etat,place ,img,date,obs,date2);
        data.setValue(event);
        data2.setValue(event);
        Toast.makeText(getApplicationContext(),getString(R.string.modification),Toast.LENGTH_LONG).show();
        return true;
    }




}