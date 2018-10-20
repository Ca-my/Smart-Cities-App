package com.example.abl.algeriancitiesapp;

/**
 * Created by abl on 28/04/2018.
 */

public class User {
    String id;
    String name;
    String pname;
    String numCarte;
    String email;
    String tlfn;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password;




    public String getNumCarte() {
        return numCarte;
    }

    public void setNumCarte(String numCarte) {
        this.numCarte = numCarte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTlfn() {
        return tlfn;
    }

    public void setTlfn(String tlfn) {
        this.tlfn = tlfn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public User(){

    }

    public User(String id, String name, String pname, String numCarte, String email, String tlfn, String password) {
        this.id = id;
        this.name = name;
        this.pname = pname;
        this.numCarte = numCarte;
        this.email = email;
        this.tlfn = tlfn;
        this.password = password;
    }
}
