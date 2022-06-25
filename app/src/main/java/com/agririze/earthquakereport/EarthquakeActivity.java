package com.agririze.earthquakereport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    private static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    private EarthquakeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);



        //Find a reference to the ListView in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

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

        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);
    }

    private class EarthquakeAsyncTask extends AsyncTask<String,Void, List<EarthQuake>>{
        @Override
        protected List<EarthQuake> doInBackground(String... urls) {
            if(urls.length < 1 || urls[0]==null){
                return null;
            }

            List<EarthQuake> result = QueryUtils.fetchEarthquakeData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<EarthQuake> data) {
            mAdapter.clear();
            if(data!=null && !data.isEmpty()){
                mAdapter.addAll(data);
            }
        }
    }
}