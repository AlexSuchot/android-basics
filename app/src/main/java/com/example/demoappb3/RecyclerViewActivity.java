package com.example.demoappb3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    // Vues :
    private RecyclerView recyclerView = null;

    // Adapter :
    private MemosAdapter memosAdapter;

    private EditText editTextMemo;
    private List<Memo> listMemos;

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


        // Database :
        MemoDatabaseHelper dbHelper = new MemoDatabaseHelper(getBaseContext(), null, null, 1);

        MemoDAO memoDAO = new MemoDAO();
        List<Memo> listMemos = memoDAO.getListMemo(this);

        // adapter :
        memosAdapter = new MemosAdapter(listMemos, this);
        recyclerView.setAdapter(memosAdapter);

        Toast.makeText(this, "mon message : " + listMemos.size(), Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    public void addMemo(View view) {

        editTextMemo = findViewById(R.id.add_memo);
        String insertedText = editTextMemo.getText().toString();
        memosAdapter.addMemo(new Memo(insertedText));
        editTextMemo.setText("");
    }
}
