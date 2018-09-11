package com.example.abhin_000.bountyhunt;

public class UserInformation {
    private String id;
    private int score = 0;
    private String name;

    public UserInformation() {
    }

    public UserInformation(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


