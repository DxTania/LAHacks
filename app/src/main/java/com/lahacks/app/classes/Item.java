package com.lahacks.app.classes;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Class that represents an item for sale
 */
public class Item {

    private String title;
    private String description;
    private List<String> categories;
    private String user;
    private String userName;
    private String imageUrl;

    private List<String> meetTypes;
    private double price;
    private boolean obo;


    public Item(String title, String description, List<String> categories, String user, List<String> meetTypes,
                double price,
                boolean obo, String url, String userName) {
        this.title = title;
        this.description = description;
        this.categories = categories;
        this.user = user;
        this.meetTypes = meetTypes;
        this.price = price;
        this.obo = obo;
        this.imageUrl = url;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
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
