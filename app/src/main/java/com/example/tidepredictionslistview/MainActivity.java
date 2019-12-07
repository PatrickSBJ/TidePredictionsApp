package com.example.tidepredictionslistview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.SimpleAdapter;


import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener {

    ArrayList<TideItem> tideItems;
    public static final String DATE = "Date";
    public static final String DAY = "Day";
    public static final String TIME = "Time";
    public static final String HIGH_LOW = "HighLow";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dal dal = new Dal(this);
            tideItems = dal.parseXmlFile("Florence_2019_tide_predictions.xml");

        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        for(TideItem t : tideItems)
        {
            map.put(DATE, t.getDate());
            map.put(DAY, t.getDay());
            map.put(TIME, t.getTime());
            map.put(HIGH_LOW, t.getHighlow());
            data.add(map);
            map = new HashMap<String, String>();
        }

        SimpleAdapter adapter = new SimpleAdapter(this,
                data,
                R.layout.tide_item_layout,
                new String[]{DATE, DAY, TIME, HIGH_LOW},
                new int[] {
                        R.id.dateTextView,
                        R.id.dayTextView,
                        R.id.timeTextView,
                        R.id.highLowTextView
                } );


        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
        getListView().setFastScrollEnabled(true);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, tideItems.get(i).getPredInCm(), Toast.LENGTH_SHORT).show();

    }
}
