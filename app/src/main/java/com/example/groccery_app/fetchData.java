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
    float latitude;
    float longitude;
    double testLat;
    double tesLong;


    fetchData(double lat, double lon){
        this.testLat = lat;
        this.tesLong = lon;

    }
    @Override
    protected Void doInBackground(Void... voids) {

        try {

            URL url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?keyword=tesco%2Clidl%2CAldi&location="+ testLat +"%2C" + tesLong + "&radius=1500&type=superstore&key=AIzaSyCqMBZfoHTtvFgSLAtgglyvNOSMNV8Ge1o");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONObject obj = new JSONObject(data);
            JSONArray JA = obj.getJSONArray("results");

            for(int i=0; i < 5;i++){
                JSONObject JO = (JSONObject) JA.getJSONObject(i);
                singleParsed = "Name:  " + JO.getString("name") + "\n";
//                                "Address" + JO.get("vicinity")+ "\n";
                Log.d("location_data", singleParsed);
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
