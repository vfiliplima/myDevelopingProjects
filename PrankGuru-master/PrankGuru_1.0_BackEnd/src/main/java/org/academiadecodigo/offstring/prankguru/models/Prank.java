package org.academiadecodigo.offstring.prankguru.models;

import java.util.ArrayList;
import java.util.List;

public class Prank {

    private Integer id;
    private String prankName;
    private String description;
    private String participants;
    private String environment;
    private String difficulty;
    private String url;
    private List<Review> reviews;

    public Prank(Integer id, String prankName, String description, String participants, String environment, String difficulty, String url) {
        this.id = id;
        this.prankName = prankName;
        this.description = description;
        this.participants = participants;
        this.environment = environment;
        this.difficulty = difficulty;
        this.url = url;
        reviews = new ArrayList<>();
    }
/*
    public void createReview(String username, String title, String stars, String content) {
        Integer reviewId = reviews.size() + 1;
        Review newReview = new Review(reviewId, username, title, stars, content);
        reviews.add(newReview);
    }*/

    public void createReview(Integer id, String username, String title, String stars, String content) {

        Review newReview = new Review(id, username, title, stars, content);
        reviews.add(newReview);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrankName() {
        return prankName;
    }

    public void setPrankName(String prankName) {
        this.prankName = prankName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}