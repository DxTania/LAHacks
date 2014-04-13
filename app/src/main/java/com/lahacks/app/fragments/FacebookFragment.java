package com.lahacks.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lahacks.app.R;

import java.util.Arrays;

/**
 * Created by seanzarrin on 4/12/14.
 */
public class FacebookFragment extends android.support.v4.app.Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facebook,
                container, false);

        com.facebook.widget.LoginButton authButton = (com.facebook.widget.LoginButton) view.findViewById(R.id.login_button);
        //authButton.setFragment(this);
        authButton.setReadPermissions(Arrays.asList("basic_info", "email"));
        System.out.println("This made it here!!\n");

        return view;
    }
}
