package com.Twitter.App.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tweets")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tweetId;

    @Column(name = "content")
    private String content;

    @Column(name = "time_posted")
    private Date timePosted;

    @Transient
    private Integer userId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Integer getTweetId() {
        return tweetId;
    }

    public void setTweetId(Integer tweetId) {
        this.tweetId = tweetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(Date timePosted) {
        this.timePosted = timePosted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    @PostLoad
    public void postLoad() {
        if(user != null)
            userId = user.getUserId();
    }

}
