package com.lahacks.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.etsy.android.grid.StaggeredGridView;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.ProfilePictureView;
import com.lahacks.app.http.HttpCallback;
import com.lahacks.app.http.HttpReceiver;
import com.lahacks.app.MainActivity;
import com.lahacks.app.R;
import com.lahacks.app.adapters.FeedAdapter;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by seanzarrin on 4/12/14.
 */
public class ProfileFragment extends android.support.v4.app.Fragment implements HttpCallback {
    private ProfilePictureView profilePictureView;
    private TextView userNameView;
    private UiLifecycleHelper uiHelper;
    private StaggeredGridView gridView;
    private static final int REAUTH_ACTIVITY_CODE = 100;
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(final Session session, final SessionState state, final Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_profile,
                container, false);
        // Find the user's profile picture custom view

        profilePictureView = (ProfilePictureView) view.findViewById(R.id.selection_profile_pic);
        profilePictureView.setCropped(true);

        // Find the user's name view
        userNameView = (TextView) view.findViewById(R.id.selection_user_name);

        ListAdapter adapter = new FeedAdapter(getActivity());
        gridView = (StaggeredGridView) view.findViewById(R.id.grid_view);
        gridView.setAdapter(adapter);

        // Check for an open session
        Session session = Session.getActiveSession();
        if (session != null && session.isOpened()) {
            // Get the user's data
            makeMeRequest(session);
        }

        return view;
    }

    public void httpCallback(String json) {
        System.out.println("Here's the response");
        System.out.println(json);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REAUTH_ACTIVITY_CODE) {
            uiHelper.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        uiHelper.onSaveInstanceState(bundle);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    private void makeMeRequest(final Session session) {
        // Make an API call to get user data and define a
        // new callback to handle the response.
        Request request = Request.newMeRequest(session,
                new Request.GraphUserCallback() {
                    @Override
                    public void onCompleted(GraphUser user, Response response) {
                        // If the response is successful
                        if (session == Session.getActiveSession()) {
                            if (user != null) {
                                // Set the id for the ProfilePictureView
                                // view that in turn displays the profile picture.
                                profilePictureView.setProfileId(user.getId());
                                // Set the Textview's text to the user's name.
                                userNameView.setText(user.getName());

                                // Display the parsed user info
                                HttpPost post = new HttpPost("http://ec2-54-84-189-134.compute-1.amazonaws.com/api/user/signup");

                                try {

                                    String userId = user.getId();
                                    String avatar = "http://graph.facebook.com/"+userId+"/picture";
                                    // Add your data
                                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                                    nameValuePairs.add(new BasicNameValuePair("firstName", user.getFirstName()));
                                    nameValuePairs.add(new BasicNameValuePair("lastName", user.getLastName()));
                                    nameValuePairs.add(new BasicNameValuePair("email", user.getProperty("email").toString()));
                                    nameValuePairs.add(new BasicNameValuePair("fbAuth", session.getAccessToken()));
                                    nameValuePairs.add(new BasicNameValuePair("fbUserId", user.getId()));
                                    nameValuePairs.add(new BasicNameValuePair("avatar", avatar));
                                    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                                    // Execute HTTP Post Request
                                    new HttpReceiver(ProfileFragment.this, post).execute();

                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                }


                            }
                        }
                        if (response.getError() != null) {
                            // Handle errors, will do so later.
                        }
                    }
                });
        request.executeAsync();
    }

    private void onSessionStateChange(final Session session, SessionState state, Exception exception) {
        if (session != null && session.isOpened()) {
            // Get the user's data.
            makeMeRequest(session);
        }
    }
}
