package com.example.proiectpam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectpam.Models.Player;

public class WebActivity extends AppCompatActivity {

    private Button backButton;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);

        // Get references to UI elements
        backButton = findViewById(R.id.backButton);
        webView = findViewById(R.id.webView);
        Player player = (Player) getIntent().getSerializableExtra("player");
        webView.getSettings().setJavaScriptEnabled(true);
        // Load the URL
        if (player != null) {
            String newUrl = player.getLink();

            // Load the URL (check for empty string)
            if (!newUrl.isEmpty()) {
                webView.loadUrl(newUrl);
            }
        }
        // Set back button click listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebActivity.this, DetailsActivity.class);
                intent.putExtra("player", player);
                startActivity(intent);
                finish();
        }
        });
    }
}
