package com.example.blindwallsgallery.data;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Object for a mural to be created
 */
public class Mural {

    private int id;
    private double latitude;
    private double longitude;
    private String date;
    private int authorID;
    private String address;
    private int numberOnMap;
    private String videoUrl;
    private int year;
    private String photographer;
    private String videoAuthor;
    private String author;
    private String rating;
    private String titleEN;
    private String titleNL;
    private String descEN;
    private String descNL;
    private String materialEN;
    private String materialNL;
    private String categoryEN;
    private String categoryNL;
    private List<String> imageUrls;

    /* Main Constructor for full object*/
    public Mural(int id, double latitude, double longitude, String date, int authorID, String address, int numberOnMap, String videoUrl, int year, String photographer, String videoAuthor, String author, String rating, String titleEN, String titleNL, String descEN, String descNL, String materialEN, String materialNL, String categoryEN, String categoryNL, List<String> imageUrls) {
        this.id = id;
        this.latitude=latitude;
        this.longitude=longitude;
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
        this.descNL = descNL;
        this.materialEN = materialEN;
        this.materialNL = materialNL;
        this.categoryEN = categoryEN;
        this.categoryNL = categoryNL;
        this.imageUrls = imageUrls;
    }

    /* Secondary constructor for the detailed view to decrease the information needed for the item*/
    public Mural(int id, double latitude, double longitude, String address, int numberOnMap, String photographer, String titleEN, String titleNL, String descEN,String descNL, String materialEN,String materialNL,List<String> imageUrls){
        this.id=id;
        this.latitude=latitude;
        this.longitude=longitude;
        this.address=address;
        this.numberOnMap=numberOnMap;
        this.photographer=photographer;
        this.titleEN=titleEN;
        this.titleNL=titleNL;
        this.descEN=descEN;
        this.descNL=descNL;
        this.materialEN=materialEN;
        this.materialNL=materialNL;
        this.imageUrls=imageUrls;
    }


    /**
     * Basic toString method
     * @return String
     */
    @NonNull
    @Override
    public String toString() {
        return "Mural{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", authorID=" + authorID +
                ", address='" + address + '\'' +
                ", numberOnMap=" + numberOnMap +
                ", videoUrl='" + videoUrl + '\'' +
                ", year=" + year +
                ", photographer='" + photographer + '\'' +
                ", videoAuthor='" + videoAuthor + '\'' +
                ", author='" + author + '\'' +
                ", rating=" + rating +
                ", titleEN='" + titleEN + '\'' +
                ", titleNL='" + titleNL + '\'' +
                ", descEN='" + descEN + '\'' +
//                ", authorDescEN='" + authorDescEN + '\'' +
                ", descNL='" + descNL + '\'' +
//                ", authorDescNL='" + authorDescNL + '\'' +
                ", materialEN='" + materialEN + '\'' +
                ", materialNL='" + materialNL + '\'' +
                ", categoryEN='" + categoryEN + '\'' +
                ", categoryNL='" + categoryNL + '\'' +
                ", imageUrls=" + imageUrls +
                '}';
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

    public String getRating() {
        return rating;
    }

    public String getTitleEN() {return titleEN;}

    public String getTitleNL() {
        return titleNL;
    }

    public String getDescEN() {
        return descEN;
    }

    public String getDescNL() {
        return descNL;
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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
