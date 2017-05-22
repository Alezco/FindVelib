package com.example.benjamin.findvelib;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.benjamin.findvelib.dbo.Velib;

class VelibAdapter extends RecyclerView.Adapter<VelibAdapter.ViewHolder> {

    private interface OnItemClickListener {
        void onItemClick(String[] items);
    }

    private Velib velib;
    private static Context context;
    private final OnItemClickListener listener;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        private ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            context = v.getContext();
        }

        private void bind(final String[] items, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(items);
                }
            });
        }
    }

    public VelibAdapter(Velib velib) {
        this.velib = velib;
        this.listener = new OnItemClickListener() {
            @Override
            public void onItemClick(String[] items) {
                handleDetails(items);
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

        //holder.bind(velib.records.get(position).fields.name, listener);
        holder.bind(getDetailsData(position), listener);
    }

    public String[] getDetailsData(int position) {
        String name =  velib.records.get(position).fields.name;
        String status = velib.records.get(position).fields.status;
        String bikes = String.valueOf(velib.records.get(position).fields.bike_stands);
        String available = String.valueOf(velib.records.get(position).fields.available_bike_stands);
        String address = velib.records.get(position).fields.address;
        String majDate = velib.records.get(position).fields.last_update;
        String[] items = new String[] {
                name, status, bikes, available, address, majDate,
        };
        return items;
    }

    @Override
    public int getItemCount() {
        return velib.records.size();
    }

    private void handleDetails(String[] items) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("stationName", items[0]);
        intent.putExtra("stationStatus", items[1]);
        intent.putExtra("stationBikes", items[2]);
        intent.putExtra("stationBikesAvailable", items[3]);
        intent.putExtra("stationAddress", items[4]);
        intent.putExtra("stationMajDate", items[5]);
        context.startActivity(intent);
    }
}
