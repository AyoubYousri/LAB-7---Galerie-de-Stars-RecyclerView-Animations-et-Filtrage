package com.example.starsgallery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.starsgallery.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.logo);
        logo.animate().rotation(360f).alpha(0f).setDuration(4000).withEndAction(() -> {
            startActivity(new Intent(this, ListActivity.class));
            finish();
        });
    }
}