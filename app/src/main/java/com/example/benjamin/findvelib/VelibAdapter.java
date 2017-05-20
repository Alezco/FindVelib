package com.example.benjamin.findvelib;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class VelibAdapter extends RecyclerView.Adapter<VelibAdapter.ViewHolder> {

    private String[] velibList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textView);
        }
    }

    public VelibAdapter(String[] velibList) {
        this.velibList = velibList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.velib_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(velibList[position]);
    }

    @Override
    public int getItemCount() {
        return velibList.length;
    }
}
