package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.Review;

public class ReviewDTO {

    private long id;
    private int rate;
    private String commentary;
    private UserDTO user;

    public ReviewDTO(Review review){
        this.id = review.getId();
        this.rate = review.getRating();
        this.commentary = review.getCommentary();
        this.user = new UserDTO(review.getUser());
        this.user.setRoles(null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

}
