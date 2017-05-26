package com.example.benjamin.findvelib;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.benjamin.findvelib.dbo.Field;
import com.example.benjamin.findvelib.dbo.Station;
import com.example.benjamin.findvelib.dbo.Velib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class VelibFragment extends Fragment {
    private Station currentStation;
    private TextView stationName;
    private TextView stationStatus;
    private TextView stationBike;
    private TextView stationBikeAvailable;
    private TextView stationAddress;
    private TextView stationMajDate;
    private ImageView stationStatusImage;
    private int index = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.velib_page, container, false);
        //setHasOptionsMenu(true);
        stationName = (TextView) view.findViewById(R.id.station_name);
        stationStatus = (TextView) view.findViewById(R.id.station_status);
        stationAddress = (TextView) view.findViewById(R.id.station_address);
        stationBike = (TextView) view.findViewById(R.id.station_bike_stands);
        stationBikeAvailable = (TextView) view.findViewById(R.id.station_available_bike_stands);
        stationMajDate = (TextView) view.findViewById(R.id.station_maj_date);
        stationStatusImage = (ImageView) view.findViewById(R.id.imageViewStatus);
        index = getArguments().getInt("pageNum");

        handleMap(view);
        setStationInfo();
        return view;
    }

    private Field getStationField() {
        Object tmp = getActivity().getIntent().getExtras().get("stationName");
        assert tmp != null;
        String name = tmp.toString();
        Velib velib = RequestManager.getInstance().velibList;
        Station station = velib.records.get(index);
        currentStation = station;
        return station.fields;
    }

    private void setStationInfo() {
        Field fields = getStationField();
        if (fields.status.equals("CLOSED")) {
            stationStatusImage.setImageResource(R.drawable.station_close);
        }
        String strName = stationName.getText() + " " +  fields.name;
        stationName.setText(strName);
        String strStatus = stationStatus.getText() + " " + fields.status;
        stationStatus.setText(strStatus);
        String strBikes = stationBike.getText() + " " + fields.bike_stands;
        stationBike.setText(strBikes);
        String strAvailable = stationBikeAvailable.getText() + " " +  fields.available_bike_stands;
        stationBikeAvailable.setText(strAvailable);
        String strAddress = stationAddress.getText() + " " +  fields.address;
        stationAddress.setText(strAddress);

        String strDate = fields.last_update;
        String strMaj = stationMajDate.getText().toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.FRANCE);
        try {
            Date date = formatter.parse(strDate.replaceAll("Z$", "+0000"));
            strMaj = stationMajDate.getText() + " " + calculateMajDisplay(date);
        }
        catch (ParseException exception) {
            exception.printStackTrace();
        }
        stationMajDate.setText(strMaj);
    }

    private String calculateMajDisplay(Date majTime) {
        long difference = (new Date().getTime() - majTime.getTime()) / 1000;
        if (difference < 3600) {
            return (difference / 60) + " minutes";
        }
        else if (difference < 86400) {
            return (difference / 3600) + " heures";
        }
        else {
            return "il y a plus d'1 jour";
        }
    }

    private void handleMap(View view) {
        TableRow mapRow = (TableRow) view.findViewById(R.id.map_row);
        mapRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Field fields = getStationField();
                String lat = String.valueOf(fields.position[0]);
                String lon = String.valueOf(fields.position[1]);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" + lat + "," + lon));
                startActivity(intent);
            }
        });
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Intent intent = new Intent(Intent.ACTION_ALL_APPS);
                intent.putExtra(Intent.EXTRA_TEXT, currentStation.fields.address);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
