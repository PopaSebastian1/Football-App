package com.example.proiectpam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.proiectpam.Models.Player;
import com.example.proiectpam.Models.Players;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {
    private Players data;
    private String[] playersName;
    private String[] playersPosition;
    private String[] playersClub;
    private int[] playersAge;
    private int[] imageIds;
    private String[] playersImage;
    private RecyclerView list;
    ImageRecyclerAdapter adapter;
    private int selectedItemPosition;
    private FloatingActionButton fabDelete, fabNext;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //generate data
        data = new Players(this, this);
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabDelete = findViewById(R.id.fabDelete);
        fabNext = findViewById(R.id.fabArrow);
        updateFabVisibility();
        selectedItemPosition=0;

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClick(); // Calling the onButtonClick method when FloatingActionButton is clicked
            }
        });
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePlayer(player); // Calling the onButtonClick method when FloatingActionButton is clicked
                //refresh the list
                onDataLoaded();
            }
        });
        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("player", player);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

    public void onDataLoaded() {
        playersName = data.getPlayerNames();
        playersPosition = data.getPlayerPositions();
        playersClub = data.getPlayerClubs();
        playersAge = data.getPlayerAges();
        playersImage = data.getPlayerImages();

        list = findViewById(R.id.recyclerView);
        list.setLayoutManager(new LinearLayoutManager(this));

        //make adapter
        adapter = new ImageRecyclerAdapter(this, R.layout.item_layout, playersName, playersPosition, playersClub, playersAge, imageIds, playersImage, this);
        list.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {

        adapter.notifyDataSetChanged();


        // get the data to push to Activity 2
         player = data.getPlayer(position);
        // make an intent and a bundle
        Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("player", player);
        intent.putExtras(bundle);
        // start activity 2
        //startActivity(intent);
        updateFabVisibility();
    }

    public void onButtonClick() {
        Intent intent = new Intent(MainActivity.this, AddPlayer.class);
        startActivity(intent);
    }

    private void updateFabVisibility() {
        if (selectedItemPosition != 0) {
            // Dacă există un element selectat, afișăm butonul fabDelete și resetăm selectedItemPosition
            fabDelete.show();
            fabNext.show();
            selectedItemPosition = 0;
        } else {
            // Dacă nu există un element selectat, ascundem butonul fabDelete
            fabDelete.hide();
            fabNext.hide();
            selectedItemPosition=1;
        }
    }
    private void deletePlayer(Player player ) {
        // Ștergeți jucătorul selectat
        PlayerDatabase.DeletePlayer(this, player);
        Toast.makeText(this, "Player deleted", Toast.LENGTH_SHORT).show();
        onDataLoaded();
        adapter.notifyDataSetChanged();
        data=new Players(this,this);
        // Actualizați vizibilitatea butoanelor fab
        updateFabVisibility();
    }



}
