package no.hiof.andremi.topptur;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;

    private LocationManager locationManager;

    private LocationListener locationListener;

    private int userOption; //Default = 0 Display user location
                            //Display path = 1
                            //Start record mode = 2


    //Zoom in to requested location
    public void centerMapOnLocation(Location location){
        //Fetch coordinates from passed location
        LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());

        //Refresh map layout
        mMap.clear();

        mMap.addMarker(new MarkerOptions().position(userLocation).title("Du er her"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,10));

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //TODO 1.1 SETUP Statements to check if intents contain variables
        userOption = 0;
        makeDebugToast("Starting option: " + userOption);
    }

    //TODO 2.1 Make method for receving a "trip" object, and display it on map

    //TODO 2.2 Make method for storing tracked location (Save trip)


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(userOption == 0){
            makeDebugToast("Starting retrieving of location");

            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

            //Seperate into own method?
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    //centerMapOnLocation(location);
                    mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())));

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };

        }
        else {
            makeDebugToast("Missing function");
        }

        //iF user's device is older than v23 (< Marshmallow)
        if(Build.VERSION.SDK_INT < 23){
            if(ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            }else{
                makeDebugToast("Mangler permission");
            }
        }else{
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

                Location lastKnownLocation = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);

                centerMapOnLocation(lastKnownLocation);
            }
        }


    }


    @Override
    public void onMapClick(LatLng latLng) {
        //TODO 3.1 Interaction on map
        //Make user set a P.O.I
    }

    /*Remove me when done*/
    //Display debug message on device
    public void makeDebugToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

    }
    /*Remove me when done*/
}
