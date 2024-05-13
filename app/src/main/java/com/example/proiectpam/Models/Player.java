package com.example.proiectpam.Models;

import java.io.Serializable;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "players")
public class Player implements Serializable
{

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    public String name;
    @ColumnInfo(name = "position")
    public String position;
    @ColumnInfo(name = "club")
    public String club;
    @ColumnInfo(name = "image")
    public String image;
    @ColumnInfo(name="description")
    public String description;
    @ColumnInfo(name="link")
    public String link;
    @ColumnInfo(name = "age")
    public int age;
    public Player(@NonNull String name, String position, String club, int age, String image) {
        this.name = name;
        this.position = position;
        this.club = club;
        this.age = age;
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getLink() {
        return link;
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
    public void setName(@NonNull String name) {
        this.name = name;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getClub() {
        return club;
    }
    public void setClub(String club) {
        this.club = club;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
