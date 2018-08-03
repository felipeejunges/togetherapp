package br.com.felipejunges.together.Model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event implements Serializable {

    private int id;
    private String name;
    private int profileImage;
    private String description;
    private boolean unactive;
    private int minAge;
    private int maxAge;
    private int minParticipation;
    private int maxParticipation;
    private String primaryCategory;
    private List<Category> categories = new ArrayList<>();

    public Event(int id, String name, int profileImage, String primaryCategory) {
        this.id = id;
        this.name = name;
        this.profileImage = profileImage;
        this.primaryCategory = primaryCategory;
    }

    public Event(int id, String name, String description, boolean unactive,
                 int minAge, int maxAge, int minParticipation, int maxParticipation, String primaryCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unactive = unactive;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minParticipation = minParticipation;
        this.maxParticipation = maxParticipation;
        this.primaryCategory = primaryCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }


    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
