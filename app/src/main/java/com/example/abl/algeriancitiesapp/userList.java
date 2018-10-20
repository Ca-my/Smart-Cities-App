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
 * Created by abl on 09/05/2018.
 */

public class userList extends ArrayAdapter<User> {
    private Activity context;
    private List<User> userList;

    public userList(Activity context,List<User> userList){
        super(context,R.layout.user_item,userList);
        this.context=context;
        this.userList=userList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.user_item,null,true);
        TextView username=(TextView)listViewItem.findViewById(R.id.username);
        TextView name=(TextView)listViewItem.findViewById(R.id.name);
        TextView numCarte=(TextView)listViewItem.findViewById(R.id.numCarte);
        TextView email=(TextView)listViewItem.findViewById(R.id.email);
        TextView numTlfn=(TextView)listViewItem.findViewById(R.id.numTlfn);
        TextView pass=(TextView)listViewItem.findViewById(R.id.mdp);
        User user= userList.get(position);
        name.setText(context.getString(R.string.nom)+"  : "+user.getName());
        username.setText(context.getString(R.string.prénom)+"  : "+user.getPname());
        numCarte.setText(context.getString(R.string.NumCarte)+"  : " +user.getNumCarte());
        email.setText(context.getString(R.string.email)+"  : "+ user.getEmail());
        numTlfn.setText(context.getString(R.string.telephone)+"  : " +user.getTlfn());
        pass.setText(context.getString(R.string.mdp)+"  : " +user.getPassword());

        return listViewItem;
    }
}
