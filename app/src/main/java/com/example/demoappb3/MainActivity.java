package com.example.demoappb3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int resultCount;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent getCountIntent = getIntent();
        if (getCountIntent.getExtras() != null) {
            resultCount = getCountIntent.getIntExtra("count", 0);

            final TextView displayText = findViewById(R.id.displayText);
            displayText.setText(resultCount + "");

            new CountDownTimer(resultCount * 1000, 1000) {

                public void onTick(long millisUntilFinished) {
                    displayText.setText("Compteur: " + resultCount --);
                }

                public void onFinish() {
                    displayText.setText("Créer un décompte");
                }
            }.start();
        }
    }

    public void createCount(View view) {
        Intent intent = new Intent(this, CountActivity.class);
        startActivity(intent);
    }
}
