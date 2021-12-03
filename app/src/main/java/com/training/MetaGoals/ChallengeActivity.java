package com.training.MetaGoals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.training.MetaGoals.qr_code.QrCodeActicity;

public class ChallengeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_detail);

        ImageView imageView = (ImageView) findViewById(R.id.challengeHeroImg);
        TextView description_tv = (TextView) findViewById(R.id.challengeDescription);

        Bundle bundle = getIntent().getExtras();

        String mHeroImg = bundle.getString("imgUrl");
        String description = bundle.getString("description");

        Glide.with(this).load(mHeroImg).into(imageView);
        description_tv.setText(description);

        configureBackButton();
        configureQrButton();
    }

    private void configureBackButton() {
        ImageView backButton = (ImageView) findViewById(R.id.arrowBackDetail);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void  configureQrButton() {
        Button qrButton = findViewById(R.id.goToQrReader);
        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qrActivityIntent = new Intent(getApplicationContext(), QrCodeActicity.class);
                startActivity(qrActivityIntent);
            }
        });
    }
}
