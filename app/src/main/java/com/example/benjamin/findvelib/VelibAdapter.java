package com.example.benjamin.findvelib;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.benjamin.findvelib.dbo.Field;
import com.example.benjamin.findvelib.dbo.Station;
import com.example.benjamin.findvelib.dbo.Velib;

import java.util.List;

public class VelibAdapter extends RecyclerView.Adapter<VelibAdapter.ViewHolder> {

    private List<String> velibList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.imageView);
        }
    }

    public VelibAdapter(List<String> velibList) {
        this.velibList = velibList;
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
    }

    @Override
    public int getItemCount() {
        return velibList.size();
    }
}
