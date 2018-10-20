package com.example.abl.algeriancitiesapp;


/**
 * Created by abl on 06/05/2018.
 */

public class res {
    String eemail;

    public String getId() {
        return id;
    }

    String id;

    public res(String eemail, String id, String commune) {
        this.eemail = eemail;
        this.id = id;
        this.commune = commune;
    }

    String commune;

    public String getCommune() {
        return commune;
    }



    public String getEmail() {
        return eemail;
    }
    public void setEmail(String email) {
        eemail = email;
    }
    public res(){

    }
}
