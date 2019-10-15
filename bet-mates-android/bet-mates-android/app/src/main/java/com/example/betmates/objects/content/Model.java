package com.example.betmates.objects.content;

import android.view.View;


public class Model {

    private int id, likes, propic, postpic;
    private String name,time,status;
    private Bet bet;

    public Model() {
        this.id = -1;
        this.likes = 0;
        this.bet = null;
        this.propic = 0;
        this.postpic = 0;
        this.name = "";
        this.time = "";
        this.status = "";
    }

    public Model(int id, int likes, int propic, int postpic, String name, String time, String status, Bet bet) {
        this.id = id;
        this.likes = likes;
        this.bet = bet;
        this.propic = propic;
        this.postpic = postpic;
        this.name = name;
        this.time = time;
        this.status = status;
    }

    // GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public int getLikes() {
        return likes;
    }
    public Bet getBet() {
        return bet;
    }
    public int getPropic() {
        return propic;
    }
    public int getPostpic() {
        return postpic;
    }
    public String getName() {
        return name;
    }
    public String getTime() {
        return time;
    }
    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void incLike(View view){
        likes++;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
    public void setPropic(int propic) { this.propic = propic; }
    public void setPostpic(int postpic) { this.postpic = postpic; }
    public void setName(String name) { this.name = name; }
    public void setTime(String time) { this.time = time; }
    public void setStatus(String status) { this.status = status; }
}
