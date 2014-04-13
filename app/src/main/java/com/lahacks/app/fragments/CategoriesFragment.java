package com.lahacks.app.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import com.etsy.android.grid.StaggeredGridView;
import com.lahacks.app.R;
import com.lahacks.app.adapters.CategoriesAdapter;

public class CategoriesFragment extends Fragment {

    public CategoriesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        ListAdapter adapter = new CategoriesAdapter(getActivity());

        StaggeredGridView gridView = (StaggeredGridView) rootView.findViewById(R.id.categories_view);

        gridView.setAdapter(adapter);

        return rootView;
    }
}
