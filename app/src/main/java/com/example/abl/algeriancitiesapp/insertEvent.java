package com.example.abl.algeriancitiesapp;

import android.content.Context;

/**
 * Created by abl on 04/05/2018.
 */

public class insertEvent {
    private String mId;
    private String mType;
    private String mCategorie;
    private String mDescription;
    private String mEtat;
    private String mPlace;
    private String mImageUrl;
    private String mDate;
    private String mDate2;
    private String mObs;

public insertEvent(){

    }

    public insertEvent(String Id,String Type,String Categorie,String Description,String Etat,String Place,
                       String ImageUrl,String Date,String Obs,String Date2, Context context){
        /*if(Obs.trim().equals("")){
            Obs = context.getString(R.string.aucune);
        }*/
        if(Description.trim().equals("")){
            Description = context.getString(R.string.aucune);
        }
        mId=Id;
        mType=Type;
        mCategorie=Categorie;
        mDescription=Description;
        mEtat=Etat;
        mPlace=Place;
        mDate=Date;
        mDate2=Date2;
        mImageUrl=ImageUrl;
        mObs=Obs;
    }
    public String getType(){
        return mType;
    }
    public void setType(String type){
        mType=type;
    }
    public String getCategorie(){
        return mCategorie;
    }
    public void setCategorie(String cat){
        mCategorie=cat;
    }
    public String getDescription(){
        return mDescription;
    }
    public void setDescription(String des){
        mDescription=des;
    }
    public String getEtat(){
        return mEtat;
    }
    public void setEtat(String etat){
        mEtat=etat;
    }
    public String getPlace(){
        return mPlace;
    }
    public void setPlace(String lat){
        mPlace=lat;
    }

    public String getImageUrl(){
        return mImageUrl;
    }
    public void setImageUrl(String img){
        mImageUrl=img;
    }


    public String getId() {
        return mId;
    }

    public void setId(String Id) {
        this.mId = Id;
    }
    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public String getObservation() {
        return mObs;
    }

    public void setObservation(String observation) {
        mObs = observation;
    }

    public String getmDate2() {
        return mDate2;
    }

    public void setmDate2(String date2) {
        this.mDate2 = date2;
    }
}
