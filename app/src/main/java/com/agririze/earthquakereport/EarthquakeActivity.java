package com.agririze.earthquakereport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        //Create a fake list of earthquake locations.
        ArrayList<EarthQuake> earthquakes = QueryUtils.extractEarthquakes();


        //Find a reference to the ListView in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        //custom adapter
        final EarthquakeAdapter adapter = new EarthquakeAdapter(this,earthquakes);


        //Set the adapter on the ListView
        //so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EarthQuake currentEarthquake = adapter.getItem(position);

                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW,earthquakeUri);
                startActivity(websiteIntent);
            }
        });
    }
}