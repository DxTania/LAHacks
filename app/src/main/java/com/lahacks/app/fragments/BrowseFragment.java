package com.lahacks.app.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.widget.ListAdapter;
import com.etsy.android.grid.StaggeredGridView;
import com.lahacks.app.R;
import com.lahacks.app.adapters.FeedAdapter;

public class BrowseFragment extends android.support.v4.app.Fragment {

    public BrowseFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_browse, container, false);
        ListAdapter adapter = new FeedAdapter(getActivity());
        StaggeredGridView gridView = (StaggeredGridView) rootView.findViewById(R.id.grid_view);
        gridView.setAdapter(adapter);

        return rootView;
    }


}
