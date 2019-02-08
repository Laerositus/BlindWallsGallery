package com.example.blindwallsgallery;

import java.net.URL;

public class Mural {

    private int id;
    private String date;
    private int authorID;
    private String address;
    private int nimberOnMap;
    private URL videoUrl;
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
    private String CategoryEN;
    private String CategoryNL;
    private String[] images;

    /* Constructor */
    public Mural(int id, String date, int authorID, String address, int nimberOnMap, URL videoUrl, int year, String photographer, String videoAuthor, String author, double rating, String titleEN, String titleNL, String descEN, String authorDescEN, String descNL, String authorDescNL, String materialEN, String materialNL, String categoryEN, String categoryNL, String[] images) {
        this.id = id;
        this.date = date;
        this.authorID = authorID;
        this.address = address;
        this.nimberOnMap = nimberOnMap;
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
        CategoryEN = categoryEN;
        CategoryNL = categoryNL;
        this.images = images;
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

    public int getNimberOnMap() {
        return nimberOnMap;
    }

    public URL getVideoUrl() {
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
        return CategoryEN;
    }

    public String getCategoryNL() {
        return CategoryNL;
    }

    public String[] getImages() {
        return images;
    }
}
