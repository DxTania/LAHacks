package com.lahacks.app.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.widget.ListAdapter;
import com.etsy.android.grid.StaggeredGridView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lahacks.app.HttpCallback;
import com.lahacks.app.HttpReceiver;
import com.lahacks.app.R;
import com.lahacks.app.adapters.FeedAdapter;
import com.lahacks.app.classes.Item;
import org.apache.http.client.methods.HttpPost;

import java.lang.reflect.Type;
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
        FeedAdapter adapter = new FeedAdapter(getActivity());
        adapter.setContents(items);
        StaggeredGridView gridView = (StaggeredGridView) rootView.findViewById(R.id.grid_view);
        gridView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Start async. download of feed items
        HttpPost post = new HttpPost("http://ec2-54-84-189-134.compute-1.amazonaws.com/api/user/feed");
        new HttpReceiver(this, post).execute();
        return rootView = inflater.inflate(R.layout.fragment_browse, container, false);
    }


}
