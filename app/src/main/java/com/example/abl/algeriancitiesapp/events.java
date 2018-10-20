package com.example.abl.algeriancitiesapp;

/**
 * Created by abl on 05/05/2018.
 */


public class events {
    private String imageUrl, type,categorie,description,etat,mObs;
    private String Date ,Date2;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    private String Id;
    private String Place;

    public events(){

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getObservation() {
        return mObs;
    }

    public void setObservation(String observation) {
        mObs = observation;
    }

    public String getDate2() {
        return Date2;
    }

    public void setDate2(String date2) {
        Date2 = date2;
    }

    public events(String Id, String type, String categorie, String description,
                  String etat, String Place, String imageUrl, String Date, String mObs, String Date2) {
        this.imageUrl = imageUrl;
        this.type = type;
        this.categorie = categorie;
        this.description = description;
        this.etat = etat;
        this.mObs = mObs;
        this.Date = Date;
        this.Date2 = Date2;
        this.Id = Id;
        this.Place = Place;
    }
}
