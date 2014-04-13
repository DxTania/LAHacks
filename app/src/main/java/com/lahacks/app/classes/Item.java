package com.lahacks.app.classes;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by rawrtan on 4/12/14.
 */
public class Item {

    private String title;
    private String description;
    private String category;
    private String user;

    private List<String> meetTypes;
    private double price;
    private boolean obo;
//    private Bitmap image;

    public Item(String title, String description, String category, String user, List<String> meetTypes, double price,
                boolean obo) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.user = user;
        this.meetTypes = meetTypes;
        this.price = price;
        this.obo = obo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMeetTypes(List<String> meetTypes) {
        this.meetTypes = meetTypes;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setObo(boolean obo) {
        this.obo = obo;
    }

    public String getTitle() {

        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getMeetTypes() {
        return meetTypes;
    }

    public double getPrice() {
        return price;
    }

    public boolean isObo() {
        return obo;
    }
}
