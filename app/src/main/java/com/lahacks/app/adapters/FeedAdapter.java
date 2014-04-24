package com.lahacks.app.adapters;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.lahacks.app.R;
import com.lahacks.app.classes.Item;
import com.lahacks.app.ListingActivity;
import com.google.gson.Gson;
import android.content.res.Configuration;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Adapter for the user's feed, handles views for each item in the grid
 */
public class FeedAdapter implements ListAdapter {

    private Context context;
    private List<Item> contents;

    public FeedAdapter(Context c) {
        context = c;
        contents = new ArrayList<Item>();
    }

    public void setContents(List<Item> items) {
        contents = items;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return contents.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO: Retrieve a bunch of items from server, use those values and content
        final Item item = contents.get(position);

        // Item Layout
        LinearLayout itemLayout = new LinearLayout(context);
        itemLayout.setBackgroundColor(Color.WHITE);
        itemLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        itemLayout.setLayoutParams(params);

        // Download and set image
        ImageView imageView = new ImageView(context);
        imageView.setAdjustViewBounds(true);
        UrlImageViewHelper.setUrlDrawable(imageView, item.getImageUrl());

        // Text Layout
        LinearLayout textLayout = new LinearLayout(context);
        textLayout.setBackgroundColor(Color.WHITE);
        textLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 0.3f);
        LinearLayout.LayoutParams textParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 0.7f);
        textLayout.setLayoutParams(params);

        // Title
        TextView textTitle = new TextView(context);
        textTitle.setText(item.getTitle());
        textTitle.setPadding(10, 10, 0, 10);
        textTitle.setBackgroundColor(Color.WHITE);
        textTitle.setLayoutParams(textParams);
        textLayout.addView(textTitle);

        // Price
        // TODO: Set price color based on their price filter setting. Red high, etc.
        TextView textPrice = new TextView(context);
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        textPrice.setText(nf.format(item.getPrice()));
        textPrice.setPadding(0, 10, 10, 10);
        textPrice.setLayoutParams(textParams2);
        textPrice.setGravity(Gravity.RIGHT);
        textLayout.addView(textPrice);

        // Finish adding views
        itemLayout.addView(imageView);
        itemLayout.addView(textLayout);

        // Click
        itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListingActivity.class);
                String sItem = (new Gson()).toJson(item);
                intent.putExtra("item", sItem);
                context.startActivity(intent);
            }
        });

        return itemLayout;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
