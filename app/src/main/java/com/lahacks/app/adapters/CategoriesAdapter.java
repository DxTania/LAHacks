package com.lahacks.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.lahacks.app.CategoriesActivity;
import com.lahacks.app.R;

/**
 * Created by rawrtan on 4/12/14.
 */
public class CategoriesAdapter implements ListAdapter {

    Context context;

    public CategoriesAdapter(Context c) {
        context = c;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Layout
        LinearLayout itemLayout = new LinearLayout(context);
        itemLayout.setBackgroundColor(Color.WHITE);
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        itemLayout.setLayoutParams(params);

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 0.9f);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 0.1f);

//        Bitmap img = BitmapFactory.decodeResource(context.getResources(), R.drawable.example);
//        Bitmap crop = Bitmap.createScaledBitmap(img, 400, 200, false);

        ImageView image = new ImageView(context);
//        image.setImageBitmap(crop);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        image.setBackgroundColor(Color.WHITE);
        image.setLayoutParams(imageParams);

        TextView ex = new TextView(context);
        ex.setText(">");
        ex.setBackgroundColor(Color.WHITE);
        ex.setGravity(Gravity.CENTER);
        ex.setHeight(200);
        ex.setLayoutParams(textParams);

        itemLayout.addView(image);
        itemLayout.addView(ex);

        itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CategoriesActivity.class);
                intent.putExtra(context.getString(R.string.categories), String.valueOf(position));
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
