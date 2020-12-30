package org.academiadecodigo.offstring.prankguru.models;

public class Review {
    private Integer id;
    private String username;
    private String title;
    private String stars;
    private String content;

    public Review(Integer id, String username, String title, String stars, String content) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.stars = stars;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }
}
