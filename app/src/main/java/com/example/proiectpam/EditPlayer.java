package com.example.proiectpam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectpam.Models.Player;

public class EditPlayer extends AppCompatActivity {
    Player firstPlayer;
    private EditText editPlayerNameEditText;
    private EditText editPlayerPositionEditText;
    private EditText editPlayerClubEditText;
    private EditText editPlayerAgeEditText;
    private EditText editPlayerImageUrlEditText;
    private EditText editPlayerDescriptionEditText;
    private EditText editPlayerLinkEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_player);

        // Initialize views
        editPlayerNameEditText = findViewById(R.id.editPlayerNameEditText);
        editPlayerPositionEditText = findViewById(R.id.editPlayerPositionEditText);
        editPlayerClubEditText = findViewById(R.id.editPlayerClubEditText);
        editPlayerAgeEditText = findViewById(R.id.editPlayerAgeEditText);
        editPlayerImageUrlEditText = findViewById(R.id.editPlayerImageUrlEditText);
        editPlayerLinkEditText = findViewById(R.id.editPlayerLinkEditText);
        editPlayerDescriptionEditText = findViewById(R.id.editPlayerDescriptionEditText);
        saveButton = findViewById(R.id.saveButton);

        // Get player data from intent
        Player player = (Player) getIntent().getSerializableExtra("player");
        firstPlayer=player;
        // Populate EditText fields with player data
        editPlayerNameEditText.setText(player.getName());
        editPlayerPositionEditText.setText(player.getPosition());
        editPlayerClubEditText.setText(player.getClub());
        editPlayerImageUrlEditText.setText(player.getImage());
        editPlayerAgeEditText.setText(String.valueOf(player.getAge()));
        editPlayerDescriptionEditText.setText(player.getDescription());
        editPlayerLinkEditText.setText(player.getLink());
        // Set click listener for the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the updated data entered by the user
                String updatedName = editPlayerNameEditText.getText().toString();
                String updatedPosition = editPlayerPositionEditText.getText().toString();
                String updatedClub = editPlayerClubEditText.getText().toString();
                String updatedImageUrl=editPlayerImageUrlEditText.getText().toString();
                String updatedDescription=editPlayerDescriptionEditText.getText().toString();
                int updatedAge = Integer.parseInt(editPlayerAgeEditText.getText().toString());

                // Update the player object with the new data
                player.setName(updatedName);
                player.setPosition(updatedPosition);
                player.setClub(updatedClub);
                player.setAge(updatedAge);
                player.setImage(updatedImageUrl);
                player.setDescription(updatedDescription);
                player.setLink(editPlayerLinkEditText.getText().toString());
                // Optionally, you can update the player in the database here
                // Display a toast message to indicate successful update
                editPlayer(firstPlayer);
                // Optionally, you can finish the activity to go back to the previous screen
                // finish();
            }
        });
    }
    public void editPlayer(Player player){
        //check if any of the fields are empty
        if(!player.getName().isEmpty() && !player.getPosition().isEmpty() && !player.getClub().isEmpty() && !String.valueOf(player.getAge()).isEmpty() && !player.getImage().isEmpty() && !player.getDescription().isEmpty() && !player.getLink().isEmpty())
        {
            PlayerDatabase.UpdatePlayer(this, player);
            Intent intent = new Intent(this, MainActivity.class);
            Toast.makeText(EditPlayer.this, "Player updated successfully", Toast.LENGTH_SHORT).show();
            startActivity(intent);

            finish();
        }
        else {
            // Display an error message
            System.out.println("Please fill in all fields");
            Toast.makeText(EditPlayer.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }

    }
}
