package com.example.benjamin.findvelib;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.benjamin.findvelib.dbo.Station;
import com.example.benjamin.findvelib.dbo.Velib;

import java.util.ArrayList;

class VelibAdapter extends RecyclerView.Adapter<VelibAdapter.ViewHolder> {
    private Velib velib;
    private Context context;
    private final OnItemClickListener listener;
    private Velib velibFiltered;

    private void copyStationsList() {
        velibFiltered = new Velib(new ArrayList<Station>());
        for (Station station : velib.records) {
            velibFiltered.records.add(station);
        }
    }

    public void filter(String queryText) {
        if (velibFiltered == null) {
            copyStationsList();
        }
        velib.records.clear();
        if (queryText.isEmpty()) {
            velib.records.addAll(velibFiltered.records);
        }
        else {
            queryText = queryText.toLowerCase();
            for (Station station : velibFiltered.records) {
                if (station.fields.name.toLowerCase().contains(queryText)) {
                    System.out.println(station.fields.name);
                    velib.records.add(station);
                }
            }
        }
        notifyDataSetChanged();
    }

    private interface OnItemClickListener {
        void onItemClick(String item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        private ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.imageView);
        }

        private void bind(final String item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public VelibAdapter(Velib velib, Context context) {
        this.velib = velib;
        this.context = context;
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
        holder.textView.setText(velib.records.get(position).fields.name);
        if (velib.records.get(position).fields.status.equals("CLOSED")) {
            holder.imageView.setImageResource(R.drawable.station_close);
        }
        else if (velib.records.get(position).fields.status.equals("OPEN")) {
            holder.imageView.setImageResource(R.drawable.station_open);
        }

        holder.bind(velib.records.get(position).fields.name, listener);
    }

    @Override
    public int getItemCount() {
        return velib.records.size();
    }

    private void handleDetails(String item) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("stationName", item);
        Velib velib = RequestManager.getInstance().velibList;
        Station station = null;
        for (Station s : velib.records) {
            if (s.fields.name.equals(item)) {
                station = s;
                break;
            }
        }
        int position = velib.records.indexOf(station);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }
}
