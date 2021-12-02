package com.example.groccery_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class FindNearbyStores extends AppCompatActivity implements LocationListener{

    Button btn;
    public static TextView storeList;
    private LocationManager locationManager;
    private long minTime = 500;
    private float minDistance = 1;
    private static final int MY_PERMISSION_GPS = 1;
    float latitude;
    float longitude;
    double lat = 53.3546668;
    double lon = -6.279672;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_nearby_stores);

        btn = (Button) findViewById(R.id.findStoresBtn);
        storeList = (TextView) findViewById(R.id.storeList);
        setUpLocation();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 fetchData process = new fetchData(lat,lon);
                 process.execute();
            }
        });

    }

    private void setUpLocation(){
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(FindNearbyStores.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(FindNearbyStores.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSION_GPS);
        } else { // permission granted

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, (LocationListener) this);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {


        Log.d("GPSLOCATION", "LOCATION CHANGED!");

        if (location != null) {

            latitude = Math.round(location.getLatitude());
            longitude = Math.round(location.getLongitude());

//            latestLocation = String.format(
//                    "Current Location: Latitude %1$s Longitude : %2$s",
//                    Math.round(location.getLatitude()), Math.round(location.getLongitude()));
            Log.d("GPSLOCATION", "LOCATION longitude:" + longitude + "Latitude"+ latitude );

        }


    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {

        Log.d("GPSLOCATION", "LOCATION longitude:" + longitude + "Latitude"+ latitude );

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_GPS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // All good!
                } else {
                    Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onFlushComplete(int requestCode) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}

