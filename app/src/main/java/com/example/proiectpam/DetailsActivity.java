package com.example.proiectpam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proiectpam.Models.Player;
import com.squareup.picasso.Picasso;


public class DetailsActivity extends AppCompatActivity {
    //declare textViews
    private TextView nameTF, descriptionTF;
    private ImageView imageTF;
    private Button webButton;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //wire object with widgets
        nameTF = findViewById(R.id.nameTextView);
        descriptionTF = findViewById(R.id.descriptionTextView);
        imageTF = findViewById(R.id.imageTextView);
        webButton = findViewById(R.id.button2);

        // get building data from the intent
        player = (Player)getIntent().getExtras().getSerializable("player");
        nameTF.setText(player.getName());
        descriptionTF.setText(player.getDescription());
        String linkForWeb=player.getLink();
        Picasso.get().load(player.getImage()).into(imageTF);
        //button action custom activity care sa contina un webview pe langa alte componente si noi sa facem render la un site
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start the web activity
                Intent intent = new Intent(DetailsActivity.this, WebActivity.class);
                intent.putExtra("player", player);
                startActivity(intent);
                finish();
            }
        });
    }
}