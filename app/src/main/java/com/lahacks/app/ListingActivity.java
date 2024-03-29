package com.lahacks.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.google.gson.Gson;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.lahacks.app.classes.Item;

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
        imageView.setImageResource(android.R.drawable.progress_indeterminate_horizontal);
        UrlImageViewHelper.setUrlDrawable(imageView, item.getImageUrl());

        // Title
        TextView description = (TextView) findViewById(R.id.itemDescription);
        description.setText(item.getDescription());

        TextView title = (TextView) findViewById(R.id.itemTitle);
        title.setText(item.getTitle() + " - $" + item.getPrice());

        // Preferred method spinner
        Spinner methodSpinner = (Spinner) findViewById(R.id.preferredMethod);
        ArrayAdapter<String> transAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, item.getMeetTypes());
        transAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        methodSpinner.setAdapter(transAdapter);

        // Button bid / buy text
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

        // View seller profile
        Button viewSellerProfile = (Button) findViewById(R.id.viewSellerProfile);
        viewSellerProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListingActivity.this, SellerActivity.class);
                startActivity(intent);
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
