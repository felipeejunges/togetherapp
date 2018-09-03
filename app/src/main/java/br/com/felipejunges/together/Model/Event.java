package br.com.felipejunges.together.Model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event implements Serializable {

    private String id;
    private String name;
    private String profileImage;
    private String description;
    private boolean unactive;
    private int minAge;
    private int maxAge;
    private int minParticipation;
    private int maxParticipation;
    private String primaryCategory;
    private double price;
    private String location;

    private List<Category> categories = new ArrayList<>();

    public Event() {}

    public Event(String name, String description, boolean unactive, double price, int minAge, int maxAge,
                 int minParticipation, int maxParticipation, String primaryCategory, String location, String profileImage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unactive = unactive;
        this.price = price;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minParticipation = minParticipation;
        this.maxParticipation = maxParticipation;
        this.primaryCategory = primaryCategory;
        this.location = location;
        this.profileImage = profileImage;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isUnactive() {
        return unactive;
    }

    public void setUnactive(boolean unactive) {
        this.unactive = unactive;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getMinParticipation() {
        return minParticipation;
    }

    public void setMinParticipation(int minParticipation) {
        this.minParticipation = minParticipation;
    }

    public int getMaxParticipation() {
        return maxParticipation;
    }

    public void setMaxParticipation(int maxParticipation) {
        this.maxParticipation = maxParticipation;
    }

    public String getPrimaryCategory() {
        return primaryCategory;
    }

    public void setPrimaryCategory(String primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }


    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
