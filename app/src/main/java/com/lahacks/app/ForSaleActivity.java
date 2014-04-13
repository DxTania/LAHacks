package com.lahacks.app;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.lahacks.app.fragments.BrowseFragment;
import com.lahacks.app.fragments.CategoriesFragment;
import com.lahacks.app.fragments.SellFragment;

public class ForSaleActivity extends Activity {
    public static final int WINDOW_SHOP = 0;
    public static final int LIST_ITEM = 1;
    public static final int CATEGORIES = 2;
    public static final int PROFILE = 3;

    private DrawerLayout sidebar;
    private ListView navigation;
    private ActionBarDrawerToggle toggle;
    private String[] navigationItems;
    private boolean searchButton = true;
    private int curPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationItems = getResources().getStringArray(R.array.navigation_items);
        sidebar = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigation = (ListView) findViewById(R.id.sidebar);

        // set up the drawer's list view with items and click listener
        navigation.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, navigationItems));
        navigation.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        toggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                sidebar,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }
        };
        sidebar.setDrawerListener(toggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {
        if(!searchButton) {
            menu.findItem(R.id.action_search).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings not implemented", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_search:
                Intent intent = new Intent(ForSaleActivity.this, FiltersActivity.class);
                intent.putExtra("fragment", curPosition);
                startActivityForResult(intent, 0);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == 0) {
            Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show();
            // refresh grid with query results
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            curPosition = position;
            selectItem(position);
        }
    }

    private void selectItem(int position) {

        String navItem = navigationItems[position];
        Fragment fragment;

        if (navItem.equals(getString(R.string.windowshop))) {
            fragment = new BrowseFragment();
            searchButton = true;
        } else if (navItem.equals(getString(R.string.list))) {
            fragment = new SellFragment();
            searchButton = false;
        } else if (navItem.equals(getString(R.string.categories))) {
            fragment = new CategoriesFragment();
            searchButton = false;
        } else {
            Toast.makeText(this, "Profiles not implemented", Toast.LENGTH_LONG).show();
            return;
        }

        invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        navigation.setItemChecked(position, true);
        getActionBar().setTitle(navigationItems[position]);
        sidebar.closeDrawer(navigation);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        toggle.onConfigurationChanged(newConfig);
    }
}