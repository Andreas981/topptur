package no.hiof.andremi.topptur;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "RusleTur";
    private int LOCATION_PERMISSION_CODE = 1;
    private ArrayList<String> mItem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: onCreate");

        if(!(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)){
            requestLocationPermission();
        }

        initArraysForRecyclerView();

    }

    private void initArraysForRecyclerView(){
        mItem.add("Kart"); //Se på kartet
        mItem.add("Planlegg tur"); //Gå til activity for å planlegge, videre til kart
        mItem.add("Finn en tur"); //Gå til activity for å finne tur, videre til kart
        mItem.add("Toppliste"); //Hvem har gått mest tur
        mItem.add("Venner"); //Dine venner
        mItem.add("Innstillinger"); //Gå til innstillinger


        initRecyclerView();

    }
    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        MainActivityRecyclerViewAdapter adapter = new MainActivityRecyclerViewAdapter(mItem, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                case R.id.action_profile:
                    //Intent intent = new Intent(this, settings.class);
                    //startActivity(intent);
                    //return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

    public void userMainMenuClicked(View view) {

            Button b = (Button)view;

            switch (b.getText().toString()) {

                case "Kart":
                    //Start intent to launch map activity
                    startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                    makeDebugToast("Starting map activity...");
                    break;
                case "Start egen tur":
                    //TODO 3.2 Make activity for setting up a new trip
                    makeDebugToast("Starting activity for a new trip");
                    break;
                case "Min posisjon":
                    //TODO 3.3 Re-purpose this button
                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    intent.putExtra("action", "1");
                    startActivity(intent);
                    makeDebugToast("Min posisjon...");
                    break;
                case "Innstillinger":
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
