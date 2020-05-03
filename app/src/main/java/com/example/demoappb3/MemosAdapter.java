package com.example.demoappb3;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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
        holder.textViewLibelleMemo.setText(listMemos.get(position).message + (position + 1));
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
                public void onClick(final View view)
                {
                    // client HTTP :
                    AsyncHttpClient client = new AsyncHttpClient();
                    // paramètres :
                    RequestParams requestParams = new RequestParams();
                    final Memo memo = listMemos.get(getAdapterPosition());
                    requestParams.put("1234", (memo.message + " " + (getAdapterPosition() + 1)));
                    // appel :
                    client.post("http://httpbin.org/post", requestParams, new AsyncHttpResponseHandler()
                    {

                        @SuppressLint("ResourceType")
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                            String retour = null;
                            try {
                                retour = new String(responseBody, "UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            Log.i("TAG", retour);

                            Toast.makeText(view.getContext(), retour, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("TAG", error.toString());
                        }
                    });
                }
            });

        }
    }
}
