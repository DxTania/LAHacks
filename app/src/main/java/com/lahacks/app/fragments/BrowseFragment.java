package com.lahacks.app.fragments;

import android.os.Bundle;
import android.view.*;
import com.etsy.android.grid.StaggeredGridView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lahacks.app.http.HttpCallback;
import com.lahacks.app.http.HttpReceiver;
import com.lahacks.app.R;
import com.lahacks.app.adapters.FeedAdapter;
import com.lahacks.app.classes.Item;
import org.apache.http.client.methods.HttpPost;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrowseFragment extends android.support.v4.app.Fragment implements HttpCallback {
    private View rootView;

    public BrowseFragment() {}

    /**
     * Sets the adapter for the feed grid
     *
     * @param json The json string response from the HTTP request
     */
    public void httpCallback(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Item>>() {}.getType();
        List<Item> items = gson.fromJson(json, listType);

        /////
        String[] ms = {"pickup", "delivery"};
        String[] cs = {"Category 1", "Categry 2"};
        Item i = new Item("Title", "Description", Arrays.asList(cs), "User",
                Arrays.asList(ms),
                10.00, false, "http://www.pressrecord.com.au/wp-content/uploads/2013/02/example.png", "UserName");
        items = new ArrayList<Item>();
        items.add(i);
        items.add(i);
        items.add(i);
        //////

        if (items != null) {
            FeedAdapter adapter = new FeedAdapter(getActivity());
            adapter.setContents(items);
            StaggeredGridView gridView = (StaggeredGridView) rootView.findViewById(R.id.grid_view);
            gridView.setAdapter(adapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Start async. download of feed items
//        HttpPost post = new HttpPost("http://ec2-54-84-189-134.compute-1.amazonaws.com/api/user/feed");
//        new HttpReceiver(this, post).execute();
        rootView = inflater.inflate(R.layout.fragment_browse, container, false);
        httpCallback("");
        return rootView;
    }


}
