package com.example.abl.algeriancitiesapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by abl on 05/05/2018.
 */

public class eventList extends ArrayAdapter<events> {
    private Activity context;
    private List<events> eventsList;
    public eventList( Activity context,List<events> eventsList){
        super(context,R.layout.event_item,eventsList);
        this.context=context;
        this.eventsList=eventsList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.event_item,null,true);
        TextView obs=(TextView)listViewItem.findViewById(R.id.obsConsult);

        TextView type=(TextView)listViewItem.findViewById(R.id.typeConsult);
        TextView cat=(TextView)listViewItem.findViewById(R.id.catConsult);
        TextView desc=(TextView)listViewItem.findViewById(R.id.DescConsult);
        TextView etat=(TextView)listViewItem.findViewById(R.id.etatConsult);
        TextView date=(TextView)listViewItem.findViewById(R.id.date);
        TextView date2=(TextView)listViewItem.findViewById(R.id.date2);
        ImageView image=(ImageView)listViewItem.findViewById(R.id.imageConsult);
        events event = eventsList.get(position);
        type.setText(event.getType());
        cat.setText(context.getString(R.string.cat) +event.getCategorie());
        desc.setText(context.getString(R.string.description) + event.getDescription());
        etat.setText(context.getString(R.string.etat) +event.getEtat());
        obs.setText(context.getString(R.string.obs) +event.getObservation());
        date.setText(event.getDate());
        date2.setText(event.getDate2());

        Glide.with(context).load(event.getImageUrl()).into(image);
        return listViewItem;
    }
}
