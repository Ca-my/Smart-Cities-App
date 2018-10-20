package com.example.abl.algeriancitiesapp;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by abl on 06/05/2018.
 */

public class resList extends ArrayAdapter<res> {
    private Activity context;
    private List<res> resList;
    public resList(Activity context,List<res> resList){
        super(context,R.layout.res_item,resList);
        this.context=context;
        this.resList=resList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.res_item,null,true);

        TextView Email=(TextView)listViewItem.findViewById(R.id.email1);

        res res = resList.get(position);
        Email.setText(res.getEmail());

        return listViewItem;
    }
}
