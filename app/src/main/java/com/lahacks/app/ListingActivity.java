package com.lahacks.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.google.gson.Gson;
import com.lahacks.app.classes.Item;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class ListingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Item Listing");

        String sItem = getIntent().getStringExtra("item");
        Item item = (new Gson()).fromJson(sItem, Item.class);

        // Image
        ImageView imageView = (ImageView) findViewById(R.id.itemImage);
        imageView.setImageResource(android.R.drawable.ic_menu_help);

        // Title
        TextView description = (TextView) findViewById(R.id.itemDescription);
        description.setText(item.getDescription());

        TextView title = (TextView) findViewById(R.id.itemTitle);
        title.setText(item.getTitle() + " - $" + item.getPrice());

        // Preferred method spinner
        Spinner methodSpinner = (Spinner) findViewById(R.id.preferredMethod);
        boolean[] methods = item.getMethods();
        List<String> list = new ArrayList<String>();
        if (methods[Item.DELIVERY]) {
            list.add("Delivery");
        }
        if (methods[Item.MEETUP]) {
            list.add("Meetup");
        }
        if (methods[Item.PICKUP]) {
            list.add("Pickup");
        }
        ArrayAdapter<String> transAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        transAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        methodSpinner.setAdapter(transAdapter);

        // Button text
        Button action = (Button) findViewById(R.id.list);
        if (item.isObo()) {
            action.setText("Bid");
        } else {
            action.setText("Buy Now");
        }
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // TODO: Download full image size and on image click view it

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.listing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings not implemented", Toast.LENGTH_LONG).show();
                return true;
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
