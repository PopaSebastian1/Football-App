package com.example.proiectpam;
import  androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proiectpam.Models.Player;

import java.util.List;

@Dao
public interface PlayerDAO {
    @Insert
    void insertPlayer(Player player);
    @Update
    int updatePlayer(Player player);
    @Query("SELECT * FROM players")
    List<Player> getAllPlayers();
    @Delete
    void deletePlayer(Player player);
}
