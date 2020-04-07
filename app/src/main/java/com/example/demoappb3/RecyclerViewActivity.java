package com.example.demoappb3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    // Vues :
    private RecyclerView recyclerView = null;

    // Adapter :
    private MemosAdapter memosAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView = findViewById(R.id.list_memos);
        // à ajouter pour de meilleures performances :
        recyclerView.setHasFixedSize(true);
        // layout manager, décrivant comment les items sont disposés :
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // contenu d'exemple :
        List<Memo> listMemos = new ArrayList<>();

        for (int i = 1; i < 16; i++) {
            listMemos.add(new Memo(i, "Mémo N°" + Integer.toString(i)));
        }
        // adapter :
        MemosAdapter memosAdapter = new MemosAdapter(listMemos, this);
        recyclerView.setAdapter(memosAdapter);

        Toast.makeText(this, "mon message : " + listMemos.size(), Toast.LENGTH_SHORT).show();
    }
}