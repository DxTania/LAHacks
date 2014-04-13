package com.lahacks.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;


public class FiltersActivity extends Activity {

    private SeekBar distanceBar;
    private TextView distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayout subcategory = (LinearLayout) findViewById(R.id.subcategory);

        int fragment = getIntent().getIntExtra("fragment", 0);
        if (fragment == ForSaleActivity.WINDOW_SHOP) {
            setTitle("");
            subcategory.setVisibility(View.INVISIBLE);
        } else if (fragment == ForSaleActivity.CATEGORIES) {
            // setTitle("")
            setTitle(getIntent().getStringExtra("category"));
        }

        distanceBar = (SeekBar) findViewById(R.id.distanceBar);
        distance = (TextView) findViewById(R.id.distance);

        distanceBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                distance.setText("Distance: " + progress + " miles");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.filters, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
