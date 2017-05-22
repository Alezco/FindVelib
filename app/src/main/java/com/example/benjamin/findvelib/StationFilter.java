package com.example.benjamin.findvelib;

import android.widget.Filter;

import com.example.benjamin.findvelib.dbo.Station;
import com.example.benjamin.findvelib.dbo.Velib;

import java.util.ArrayList;

/**
 * Created by Guillaume on 22/05/2017.
 */

public class StationFilter extends Filter {

    private final VelibAdapter velibAdapter;
    private final Velib originalList;
    private final Velib filteredList;

    public StationFilter(VelibAdapter velibAdapter, Velib originalList) {
        this.velibAdapter = velibAdapter;
        this.originalList = originalList;
        this.filteredList = new Velib(new ArrayList<Station>());
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filteredList.records.clear();
        final FilterResults results = new FilterResults();

        if (constraint.length() == 0) {
            filteredList.records.addAll(originalList.records);
        } else {
            final String filterPattern = constraint.toString().toLowerCase().trim();

            for (final Station station : originalList.records) {
                if (station.fields.name.contains(filterPattern)) {
                    filteredList.records.add(station);
                }
            }
        }
        results.values = filteredList;
        results.count = filteredList.records.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        velibAdapter.velibFiltered.records.clear();
        velibAdapter.velibFiltered.records.addAll((ArrayList<Station>) results.values);
        velibAdapter.notifyDataSetChanged();
    }
}
