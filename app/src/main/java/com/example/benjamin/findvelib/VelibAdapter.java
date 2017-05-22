package com.example.benjamin.findvelib;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class VelibAdapter extends RecyclerView.Adapter<VelibAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    private List<String> velibList;
    private final OnItemClickListener listener;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.imageView);
        }

        public void bind(final String item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public VelibAdapter(List<String> velibList) {
        this.velibList = velibList;
        this.listener = new OnItemClickListener() {
            @Override
            public void onItemClick(String item) {
                handleDetails(item);
            }
        };
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.velib_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(velibList.get(position));
        holder.imageView.setImageResource(R.drawable.station_close);
        holder.bind(velibList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return velibList.size();
    }

    private void handleDetails(String item) {
        Log.d("=============", item);
    }
}
