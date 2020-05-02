package com.example.demoappb3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MemosAdapter extends RecyclerView.Adapter<MemosAdapter.MemosViewHolder>
{
    // Liste d'objets métier :
    private List<Memo> listMemos;

    // Constructeur :
    public MemosAdapter(List<Memo> listMemos, RecyclerViewActivity recyclerViewActivity)
    {
        this.listMemos = listMemos;
    }

    @NonNull
    @Override
    public MemosViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View viewMemo = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_memo, parent, false);
        return new MemosViewHolder(viewMemo);
    }

    @Override
    public void onBindViewHolder(MemosViewHolder holder, int position) {
        holder.textViewLibelleMemo.setText(listMemos.get(position).message + position);
    }

    @Override
    public int getItemCount()
    {
        return listMemos.size();
    }

    public void addMemo(Memo memo) {
        listMemos.add(listMemos.size(), memo);
        notifyItemInserted(0);
    }

    public class MemosViewHolder extends RecyclerView.ViewHolder
    {
        // TextView intitulé course :
        public TextView textViewLibelleMemo;

        // Constructeur :
        MemosViewHolder(@NonNull View itemView)
        {
            super(itemView);
            textViewLibelleMemo = itemView.findViewById(R.id.item_memo);

            // listener :
            textViewLibelleMemo.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Memo memo = listMemos.get(getAdapterPosition());
                    Toast.makeText(view.getContext(), "Mémo en position " + memo, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
