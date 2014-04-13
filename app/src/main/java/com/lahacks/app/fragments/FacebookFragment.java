package com.lahacks.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lahacks.app.R;

/**
 * Created by seanzarrin on 4/12/14.
 */
public class FacebookFragment extends android.support.v4.app.Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facebook,
                container, false);
        return view;
    }
}
