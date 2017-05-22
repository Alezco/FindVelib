package com.example.benjamin.findvelib;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benjamin.findvelib.dbo.Field;
import com.example.benjamin.findvelib.dbo.Station;
import com.example.benjamin.findvelib.dbo.Velib;

public class DetailsActivity extends AppCompatActivity {

    private Toast toast = null;

    private TextView stationName;
    private TextView stationStatus;
    private TextView stationBike;
    private TextView stationBikeAvailable;
    private TextView stationAddress;
    private TextView stationMajDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.velib_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        stationName = (TextView) findViewById(R.id.station_name);
        stationStatus = (TextView) findViewById(R.id.station_status);
        stationAddress = (TextView) findViewById(R.id.station_address);
        stationBike = (TextView) findViewById(R.id.station_bike_stands);
        stationBikeAvailable = (TextView) findViewById(R.id.station_available_bike_stands);
        stationMajDate = (TextView) findViewById(R.id.station_maj_date);

        setStationInfo();
    }

    private Field getStationField() {
        String name = getIntent().getExtras().get("stationName").toString();
        Velib velib = RequestManager.getInstance().velibList;
        Station station = null;
        for (Station s: velib.records) {
            if (s.fields.name.equals(name)) {
                station = s;
                break;
            }
        }
        assert station != null;
        return station.fields;
    }

    private void setStationInfo() {

        Field fields = getStationField();
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
        String strMaj = stationMajDate.getText() + " " +  fields.last_update;
        stationMajDate.setText(strMaj);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.about:
                if (toast != null)
                    toast.cancel();
                toast = Toast.makeText(DetailsActivity.this, getResources().getString(R.string.contributors), Toast.LENGTH_SHORT);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                if( v != null)
                    v.setGravity(Gravity.CENTER);
                toast.show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void openMap(View v) {
        Field fields = getStationField();
        String lat = String.valueOf(fields.position[0]);
        String lon = String.valueOf(fields.position[1]);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=" + lat + "," + lon));
        startActivity(intent);
    }
}
