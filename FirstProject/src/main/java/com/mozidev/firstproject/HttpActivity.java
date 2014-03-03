package com.mozidev.firstproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_http_fragment, new HttpFragment())
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.http, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class HttpFragment extends Fragment {

        private EditText et_fragment_http;

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootview = inflater.inflate(R.layout.fragment_http, container, false);
            et_fragment_http = (EditText) rootview.findViewById(R.id.et_fragment_http);
            return rootview;
        }

        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            HttpAcyncTask task = new HttpAcyncTask();
            task.execute();
        }


        class HttpAcyncTask extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... params) {
                String data = "";
                InputStream inputstream = null;

                try {
                    URL url = new URL("http://developer.alexanderklimov.ru/android");

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //connection.setDoInput(true);

                    int respcode = connection.getResponseCode();

                    if (respcode == HttpURLConnection.HTTP_OK) {
                        inputstream = connection.getInputStream();
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();

                        int read = 0;
                        byte[] buff = new byte[24];
                        while ((read = inputstream.read(buff)) != -1) {
                            bos.write(buff);
                        }
                        byte[] result = bos.toByteArray();

                        bos.close();
                        inputstream.close();

                        data = new String(result);

                        return data;
                    } else {
                        data = connection.getResponseMessage() + " . Error Code : " + respcode;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inputstream != null) {

                        try {
                            inputstream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return data;
            }

            @Override
            protected void onPostExecute(String result) {
                et_fragment_http.setText(result);
            }
        }
    }
}
