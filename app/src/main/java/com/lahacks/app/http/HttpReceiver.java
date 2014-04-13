package com.lahacks.app.http;

/**
 * HttpReceiver creates an asynchronous task in order to
 * complete an HTTP GET or POST request with a callback
 * which is passed the string result of the HTTP request
 */

import android.os.AsyncTask;
import com.lahacks.app.classes.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        if (callback != null) {
            callback.httpCallback(result);
        }
    }

    public User getUser(String id) {
        return null;
    }

}
