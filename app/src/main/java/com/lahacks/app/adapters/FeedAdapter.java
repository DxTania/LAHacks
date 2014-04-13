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
import com.lahacks.app.R;
import com.lahacks.app.classes.Item;
import com.lahacks.app.ListingActivity;
import com.google.gson.Gson;
import android.content.res.Configuration;


/**
 * Created by rawrtan on 4/12/14.
 */
public class FeedAdapter implements ListAdapter {

    Context context;
    Bitmap example;

    public FeedAdapter(Context c) {
        context = c;
        example = BitmapFactory.decodeResource(context.getResources(), R.drawable.example);
    }

    public void setContents() {

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
        return 10;
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
        // Item Layout
        LinearLayout itemLayout = new LinearLayout(context);
        itemLayout.setBackgroundColor(Color.WHITE);
        itemLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        itemLayout.setLayoutParams(params);

        // Image
        ImageView ex = new ImageView(context);
        ex.setScaleType(ImageView.ScaleType.FIT_XY);
        double ratio = example.getWidth() / example.getHeight();
        ex.setImageBitmap(Bitmap.createScaledBitmap(example, 300, (int) (300/ratio), false));

        // Text Layout
        LinearLayout textLayout = new LinearLayout(context);
        textLayout.setBackgroundColor(Color.WHITE);
        textLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 0.3f);
        LinearLayout.LayoutParams textParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 0.7f);
        textLayout.setLayoutParams(params);

        TextView textTitle = new TextView(context);
        textTitle.setText("Necklace");
        textTitle.setPadding(10, 10, 0, 10);
        textTitle.setBackgroundColor(Color.WHITE);
        textTitle.setLayoutParams(textParams);

        TextView textPrice = new TextView(context);
        textPrice.setText("$5");
        textPrice.setPadding(0, 10, 10, 10);
        textPrice.setLayoutParams(textParams2);
        textPrice.setGravity(Gravity.RIGHT);

        textLayout.addView(textTitle);
        textLayout.addView(textPrice);

        // Finish
        itemLayout.addView(ex);
        itemLayout.addView(textLayout);

        // Click
        itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListingActivity.class);
                // TODO: Put item information in intent
                boolean[] methods = {true, true, false};
                Item item = new Item("Necklace", "Blah blah jewelry", "jewelry", methods, 1.1, false, null);
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
