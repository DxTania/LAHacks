package com.lahacks.app;

/**
 * Created by rawrtan on 4/13/14.
 */
import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lahacks.app.classes.Item;
import com.lahacks.app.classes.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class HttpReceiver extends AsyncTask<Void, Void, String> {
    HttpPost post;
    HttpCallback callback;

    public HttpReceiver(HttpCallback callback, HttpPost post) {
        this.callback = callback;
        this.post = post;
    }

    @Override
    public String doInBackground(Void... params) {
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        String json = "";

        try {
            response = client.execute(post);
            StringBuilder builder = new StringBuilder();

            BufferedReader rd = new BufferedReader
                    (new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                builder.append(line);
            }
            json = builder.toString();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return null;
        }

        return json;
    }

    @Override
    public void onPostExecute(String result) {
        callback.httpCallback(result);
    }

    public User getUser(String id) {
        return null;
    }

}
