package com.example.proiectpam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectpam.Models.Player;
import com.squareup.picasso.Picasso;

public class PlayerActivity extends AppCompatActivity {

    private ImageView playerImageView;
    private TextView playerNameTextView;
    private TextView playerDetailsTextView;
    private Button playerDetailsButton;
    private Button editPlayerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // Initialize views
        playerImageView = findViewById(R.id.playerImageView);
        playerNameTextView = findViewById(R.id.playerNameTextView);
        playerDetailsTextView = findViewById(R.id.playerDetailsTextView);
        playerDetailsButton = findViewById(R.id.playerDetailsButton);
        editPlayerButton=findViewById(R.id.playerEditButton);

        // Get player data from intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Player player = (Player) bundle.getSerializable("player");

        // Set player details
        playerNameTextView.setText(player.getName());
        playerDetailsTextView.setText(player.getDescription());
        String imageName = player.getImage();
        Picasso.get().load(imageName).into(playerImageView); // Încărcați imaginea din URL



        // Set other player details accordingly
         playerDetailsButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                    Intent intent = new Intent(PlayerActivity.this, DetailsActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putSerializable("player", player);
                    intent.putExtras(bundle);
                    startActivity(intent);
             }
         });
         editPlayerButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(PlayerActivity.this, EditPlayer.class);
                     Bundle bundle = new Bundle();
                 bundle.putSerializable("player", player);
                 intent.putExtras(bundle);
                 startActivity(intent);
             }
         });
    }
}
