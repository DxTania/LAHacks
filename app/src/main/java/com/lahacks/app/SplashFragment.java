package com.lahacks.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by seanzarrin on 4/12/14.
 */
public class SplashFragment extends android.support.v4.app.Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facebook,
                container, false);
        return view;
    }
}
