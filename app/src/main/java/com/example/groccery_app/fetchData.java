package com.example.groccery_app;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void,Void,Void> {

    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    long latitude;
    long longitude;


    fetchData(long lat, long lon){
        this.latitude = lat;
        this.longitude = lon;

    }
    @Override
    protected Void doInBackground(Void... voids) {

        try {
            String lat;
            URL url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?keyword=cruise&location=" + latitude + "%"+ longitude + "&radius=1500&type=restaurant&key=AIzaSyCqMBZfoHTtvFgSLAtgglyvNOSMNV8Ge1o");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONArray JA = new JSONArray(data);
            for(int i=0; i < 5;i++){
                JSONObject JO = (JSONObject) JA.getJSONObject(i);
                singleParsed = "Name" + JO.getJSONObject("name") + "\n";
//                                "Address" + JO.get("vicinity")+ "\n";
                Log.d("data", singleParsed);
                dataParsed = dataParsed + singleParsed + "\n";

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);

        FindNearbyStores.storeList.setText(dataParsed);

    }
}
