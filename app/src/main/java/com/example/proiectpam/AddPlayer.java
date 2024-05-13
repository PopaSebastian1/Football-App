package com.example.proiectpam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.proiectpam.Models.Player;

public class AddPlayer extends AppCompatActivity {
    private EditText playerNameEditText;
    private EditText playerPositionEditText;
    private EditText playerClubEditText;
    private EditText playerAgeEditText;
    private EditText playerImageUrlEditText;
    private EditText playerDescriptionEditText;
    private EditText playerLinkEditText;
    private Button addButton;
    private PlayerDatabase playerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_player);

        // Initialize views
        playerNameEditText = findViewById(R.id.playerNameEditText);
        playerPositionEditText = findViewById(R.id.playerPositionEditText);
        playerClubEditText = findViewById(R.id.playerClubEditText);
        playerAgeEditText = findViewById(R.id.playerAgeEditText);
        playerImageUrlEditText = findViewById(R.id.playerImageUrlEditText);
        //add a eddit text for the description with this id playerDescriptionEditText
        playerDescriptionEditText = findViewById(R.id.playerDescriptionEditText);
        playerLinkEditText = findViewById(R.id.playerLinkEditText);
        addButton = findViewById(R.id.addButton);

        // Initialize PlayerDatabase
        playerDatabase = PlayerDatabase.getDatabase(this);

        // Set click listener for the add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call a method to handle adding the player
                addPlayer();
            }
        });
    }

    private void addPlayer() {
        // Get the data entered by the user
        String playerName = playerNameEditText.getText().toString();
        String playerPosition = playerPositionEditText.getText().toString();
        String playerClub = playerClubEditText.getText().toString();
        int playerAge = Integer.parseInt(playerAgeEditText.getText().toString());
        String playerImage=playerImageUrlEditText.getText().toString();
        String playerDescription=playerDescriptionEditText.getText().toString();
        String playerLink=playerLinkEditText.getText().toString();
        // Create a new Player object
        //check if a field is empty

        Player newPlayer = new Player(playerName, playerPosition, playerClub, playerAge,playerImage);
        newPlayer.setDescription(playerDescription);
        newPlayer.setLink(playerLink);

        // Add the new player to the database
        //check if all fields are filled
        if(!playerName.isEmpty() && !playerPosition.isEmpty() && !playerClub.isEmpty() && !playerAgeEditText.getText().toString().isEmpty() && !playerImageUrlEditText.getText().toString().isEmpty() && !playerDescription.isEmpty() && !playerLink.isEmpty()) {
            PlayerDatabase.AddPlayer(this, newPlayer);
            Intent intent = new Intent(this, MainActivity.class);
            Toast.makeText(this, "Player added successfully", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }
        else {
            // Display an error message
            System.out.println("Please fill in all fields");

            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
        // Move back to the main activity


    }
}
