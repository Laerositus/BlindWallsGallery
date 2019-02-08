package com.example.blindwallsgallery;

import java.net.URL;
import java.util.List;

public class Mural {

    private int id;
    private String date;
    private int authorID;
    private String address;
    private int numberOnMap;
    private String videoUrl;
    private int year;
    private String photographer;
    private String videoAuthor;
    private String author;
    private double rating;
    private String titleEN;
    private String titleNL;
    private String descEN;
    private String authorDescEN;
    private String descNL;
    private String authorDescNL;
    private String materialEN;
    private String materialNL;
    private String categoryEN;
    private String categoryNL;
    private List<String> imageUrls;

    /* Constructor */
    Mural(int id, String date, int authorID, String address, int numberOnMap, String videoUrl, int year, String photographer, String videoAuthor, String author, double rating, String titleEN, String titleNL, String descEN, String authorDescEN, String descNL, String authorDescNL, String materialEN, String materialNL, String categoryEN, String categoryNL, List<String> imageUrls) {
        this.id = id;
        this.date = date;
        this.authorID = authorID;
        this.address = address;
        this.numberOnMap = numberOnMap;
        this.videoUrl = videoUrl;
        this.year = year;
        this.photographer = photographer;
        this.videoAuthor = videoAuthor;
        this.author = author;
        this.rating = rating;
        this.titleEN = titleEN;
        this.titleNL = titleNL;
        this.descEN = descEN;
        this.authorDescEN = authorDescEN;
        this.descNL = descNL;
        this.authorDescNL = authorDescNL;
        this.materialEN = materialEN;
        this.materialNL = materialNL;
        this.categoryEN = categoryEN;
        this.categoryNL = categoryNL;
        this.imageUrls = imageUrls;
    }



    /*
    Getters for all attributes
     */
    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getAuthorID() {
        return authorID;
    }

    public String getAddress() {
        return address;
    }

    public int getNumberOnMap() {
        return numberOnMap;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public int getYear() {
        return year;
    }

    public String getPhotographer() {
        return photographer;
    }

    public String getVideoAuthor() {
        return videoAuthor;
    }

    public String getAuthor() {
        return author;
    }

    public double getRating() {
        return rating;
    }

    public String getTitleEN() {
        return titleEN;
    }

    public String getTitleNL() {
        return titleNL;
    }

    public String getDescEN() {
        return descEN;
    }

    public String getAuthorDescEN() {
        return authorDescEN;
    }

    public String getDescNL() {
        return descNL;
    }

    public String getAuthorDescNL() {
        return authorDescNL;
    }

    public String getMaterialEN() {
        return materialEN;
    }

    public String getMaterialNL() {
        return materialNL;
    }

    public String getCategoryEN() {
        return categoryEN;
    }

    public String getCategoryNL() {
        return categoryNL;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }
}
