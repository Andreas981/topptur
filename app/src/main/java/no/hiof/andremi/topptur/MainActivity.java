package no.hiof.andremi.topptur;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "RusleTur";
    private int LOCATION_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: onCreate");

        if(!(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)){
            requestLocationPermission();
        }

    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main_menu, menu);
            getSupportActionBar().setTitle("RusleTur");
            return true;
        }

    @Override
        public boolean onOptionsItemSelected(MenuItem item) {

        // TODO 1.0: Add button action for actionbar

            switch(item.getItemId()) {
                //TODO 1.1: Add action for home button
                case R.id.action_home:
                    //Intent intent = new Intent(this, home.class);
                    //startActivity(intent);
                    //return true;

                    //TODO 1.2: Add action for settings button
                case R.id.action_settings:
                    //Intent intent = new Intent(this, settings.class);
                    //startActivity(intent);
                    //return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

        public void userMainMenuClicked(View view) {

            switch (view.getId()) {

                case R.id.btnLaunchMap:
                    //Start intent to launch map activity
                    startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                    makeDebugToast("Starting map activity");
                    break;
                case R.id.btnMakeAtrip:
                    //TODO 3.2 Make activity for setting up a new trip
                    makeDebugToast("Starting activity for a new trip");
                    break;
                case R.id.btnMyPosition:
                    //TODO 3.3 Re-purpose this button
                    makeDebugToast("Not sure what to do");
                    break;
                case R.id.btnSettings:
                    //TODO 3.4 Make settings menu
                    makeDebugToast("Imagine settings opening...");
                    break;
                default:
                    break;

            }
        }

        private void requestLocationPermission(){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
        }


        /*Remove me when done*/
        //Display debug message on device
        public void makeDebugToast(String msg){
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

        }
        /*Remove me when done*/

}
