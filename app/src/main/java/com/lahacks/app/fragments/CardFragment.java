package com.lahacks.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.lahacks.app.R;
import com.lahacks.app.adapters.FeedAdapter;

import com.stripe.android.*;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.stripe.android.model.Token;

import org.apache.http.auth.AuthenticationException;

/**
 * Created by seanzarrin on 4/12/14.
 */
public class CardFragment extends android.support.v4.app.Fragment {
    private EditText cardNumberField;
    private EditText monthField;
    private EditText yearField;
    private EditText cvcField;
    public CardFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_card, container, false);
        monthField = (EditText) rootView.findViewById(R.id.month);
        yearField = (EditText) rootView.findViewById(R.id.year);

        return rootView;
    }
}
