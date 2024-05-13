package com.example.proiectpam.Models;

import android.content.Context;
import android.os.AsyncTask;

import com.example.proiectpam.PlayerDAO;
import com.example.proiectpam.PlayerDatabase;
import com.example.proiectpam.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kotlin.collections.ArrayDeque;

public class Players {
    private Context context;
    private List<Player> playerData = new ArrayList<>();
    private RecyclerViewInterface listener;

    public Players(Context context, RecyclerViewInterface listener){
        this.context = context;
        this.listener = listener;
        new GetAllPlayers().execute();
    }
    private class GetAllPlayers extends AsyncTask<Void, Void, List<Player>> {
        @Override
        protected List<Player> doInBackground(Void... voids) {
            PlayerDatabase db = PlayerDatabase.getDatabase(context);
            return db.playerDAO().getAllPlayers();
        }

        @Override
        protected void onPostExecute(List<Player> players) {
            playerData = players;
            if (listener != null) {
                listener.onDataLoaded();
            }
        }
    }
    public int getCount(){return playerData.size();}
    public Player getPlayer(int index){return playerData.get(index);}
    public String[] getPlayerNames() {
        if (playerData != null) {
            String[] names = new String[playerData.size()];
            for (int i = 0; i < playerData.size(); i++) {
                names[i] = playerData.get(i).getName();
            }
            return names;
        } else {
            return new String[0]; // sau poți arunca o excepție sau trata altfel cazul în care playerData este null
        }
    }

    public String [] getPlayerPositions(){
        String [] positions = new String[this.getCount()];

        for(int i=0;i<getCount();i++){
            positions[i] = playerData.get(i).getPosition();
        }
        return  positions;
    }
    public String [] getPlayerClubs(){
        String [] clubs = new String[this.getCount()];

        for(int i=0;i<getCount();i++){
            clubs[i] = playerData.get(i).getClub();
        }
        return  clubs;
    }
    public int [] getPlayerAges(){
        int [] ages = new int[this.getCount()];

        for(int i=0;i<getCount();i++){
            ages[i] = playerData.get(i).getAge();
        }
        return  ages;
    }
    public void setPlayerData(List<Player> playerData) {
        this.playerData = playerData;
    }
    public String [] getPlayerImages(){
        String [] images = new String[this.getCount()];

        for(int i=0;i<getCount();i++){
            images[i] = playerData.get(i).getImage();
        }
        return  images;
    }
}
