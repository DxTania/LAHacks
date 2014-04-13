package com.lahacks.app.classes;

import android.graphics.Bitmap;

/**
 * Created by rawrtan on 4/12/14.
 */
public class Item {
    public final static int DELIVERY = 0;
    public final static int PICKUP = 1;
    public final static int MEETUP = 2;

    private String title;
    private String description;
    private String category;

    private boolean[] methods;
    private double price;
    private boolean obo;
    private Bitmap image;

    public Item(String title, String description, String category, boolean[] methods, double price, boolean obo,
                Bitmap image) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.methods = methods;
        this.price = price;
        this.obo = obo;
        this.image = image;
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

    public void setMethods(boolean[] methods) {
        this.methods = methods;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setObo(boolean obo) {
        this.obo = obo;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {

        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean[] getMethods() {
        return methods;
    }

    public double getPrice() {
        return price;
    }

    public boolean isObo() {
        return obo;
    }

    public Bitmap getImage() {
        return image;
    }
}
