package com.agririze.earthquakereport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderCallbacks<List<EarthQuake>> {

    private static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    private static final int EARTHQUAKE_LOADER_ID = 1;
    private EarthquakeAdapter mAdapter;
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);



        //Find a reference to the ListView in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView)findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(mEmptyStateTextView);

        //custom adapter
        mAdapter = new EarthquakeAdapter(this,new ArrayList<EarthQuake>());


        //Set the adapter on the ListView
        //so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EarthQuake currentEarthquake = mAdapter.getItem(position);

                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW,earthquakeUri);
                startActivity(websiteIntent);
            }
        });

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID,null,this);
    }

    @Override
    public Loader<List<EarthQuake>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeLoader(this,USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<EarthQuake>> loader, List<EarthQuake> data) {
        mEmptyStateTextView.setText(R.string.no_earthquakes);
        mAdapter.clear();

        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<EarthQuake>> loader) {
        mAdapter.clear();
    }
}