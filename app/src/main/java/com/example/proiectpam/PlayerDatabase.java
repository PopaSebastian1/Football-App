package com.example.proiectpam;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proiectpam.Models.Player;

@Database(entities = {Player.class}, version = 1)

public abstract class PlayerDatabase extends RoomDatabase {
public abstract PlayerDAO playerDAO();
private volatile static PlayerDatabase INSTANCE;
    public static PlayerDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PlayerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PlayerDatabase.class, "player-database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    public static void AddPlayer(final Context context, final Player player){
        new Thread(new Runnable() {
            @Override
            public void run() {
                PlayerDatabase db = getDatabase(context);
                db.playerDAO().insertPlayer(player);
            }
        }).start();
    }
    public static void UpdatePlayer(final Context context, final Player player){
        new Thread(new Runnable() {
            @Override
            public void run() {
                PlayerDatabase db = getDatabase(context);
                int updatedRows = db.playerDAO().updatePlayer(player);
                if (updatedRows == 0) {
                    // Player not found in the database
                    System.out.println("Player not found in the database");
                }
            }
        }).start();
    }
    public static void DeletePlayer(final Context context, final Player player){
        new Thread(new Runnable() {
            @Override
            public void run() {
                PlayerDatabase db = getDatabase(context);

                db.playerDAO().deletePlayer(player);
                System.out.println("Player deleted");
            }
        }).start();
    }


}
