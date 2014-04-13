package com.lahacks.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.Toast;
import com.etsy.android.grid.StaggeredGridView;
import com.lahacks.app.adapters.FeedAdapter;


public class CategoriesActivity extends Activity {

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        setSearchTitle(getIntent().getStringExtra(getString(R.string.categories)));
        setTitle(title);

        ListAdapter adapter = new FeedAdapter(this);

        StaggeredGridView gridView = (StaggeredGridView) findViewById(R.id.grid_view);

        gridView.setAdapter(adapter);

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setSearchTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.categories, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action buttons
        switch(item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings not implemented", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_search:
                Intent intent = new Intent(CategoriesActivity.this, FiltersActivity.class);
                intent.putExtra("fragment", ForSaleActivity.CATEGORIES);
                intent.putExtra("category", title);
                startActivityForResult(intent, 0);
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
