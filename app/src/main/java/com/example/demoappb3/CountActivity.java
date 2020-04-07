package com.example.demoappb3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    public void startCount(View view) {
        EditText count = findViewById(R.id.count);

        int countNumber = Integer.parseInt(count.getText().toString());

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("count", countNumber);

        startActivity(intent);
    }
}
