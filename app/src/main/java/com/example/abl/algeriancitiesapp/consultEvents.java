package com.example.abl.algeriancitiesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class consultEvents extends AppCompatActivity {
    private ListView recyclerView;

    private TextView place;
    private DatabaseReference databaseReference;
    private Button btn;
    private List<events> eventsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_events);
        recyclerView=(ListView) findViewById(R.id.recycler_view);
        place=(TextView) findViewById(R.id.place);

        databaseReference=FirebaseDatabase.getInstance().getReference("Events");
        place.setText(getIntent().getStringExtra("place"));


       eventsList=new ArrayList<>();

        String p=place.getText().toString();
        Query Search = databaseReference.orderByChild("place").equalTo(p);
        Search.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventsList.clear();
                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()){
                    events event = eventSnapshot.getValue(events.class);
                    eventsList.add(event);

                }
                eventList adapter=new eventList(consultEvents.this,eventsList);
                recyclerView.setAdapter(adapter);

    }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

    };


}
