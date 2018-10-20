package com.example.abl.algeriancitiesapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener{

    private GoogleMap mMap;
    private Button ok,loc,ser;
    private View v;
    private TextView type, cat;
    private AutoCompleteTextView mSearchText;
    private PlaceAutocompleteAdapter mPlaceAutoCompleteAdapter;
    private GoogleApiClient mGoogleApiClient;
    private LocationManager locationManager;
    //private String lati,longi;
    private String place;
    private static  final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(new LatLng(-41,-168),
            new LatLng(71,136));

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        type = (TextView) findViewById(R.id.type);
        cat = (TextView) findViewById(R.id.cat);
        type.setText(getIntent().getStringExtra("evt2"));
        cat.setText(getIntent().getStringExtra("cat"));
        mSearchText=(AutoCompleteTextView) findViewById(R.id.input_search) ;
       ok=(Button) findViewById(R.id.ok);
       loc=(Button) findViewById(R.id.loc);
       ser=(Button) findViewById(R.id.ser);
       ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Type= type.getText().toString();
                String Cat = cat.getText().toString();
                Intent intent=new Intent(getApplicationContext(),evenement.class);
                intent.putExtra("type",Type);
                intent.putExtra("categorie",Cat);
               // intent.putExtra("latitude",lati);
               // intent.putExtra("longitude",longi);
                intent.putExtra("place",place);
                startActivity(intent);
            }
        });
       init();
    }
    private void init(){

        mGoogleApiClient = new GoogleApiClient
                .Builder(this).addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this,this)
                .build();
        mPlaceAutoCompleteAdapter = new PlaceAutocompleteAdapter(this,mGoogleApiClient ,
                LAT_LNG_BOUNDS,null);
        mSearchText.setAdapter(mPlaceAutoCompleteAdapter);
        ser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                geoLocate();
            }
        });
        /*mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == keyEvent.ACTION_DOWN
                        || keyEvent.getAction() == keyEvent.KEYCODE_ENTER){


                }
                    return false;
            }


        });*/
    }

    @SuppressLint("WrongConstant")
    private void geoLocate() {
String searchString = mSearchText.getText().toString();
        Geocoder geocoder = new Geocoder(MapsActivity.this);
        List<Address> addressList=new ArrayList<>();
        int visibility=1;
        try {
            addressList =geocoder.getFromLocationName(searchString,1);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),getString(R.string.ajouter),Toast.LENGTH_LONG).show();
        }
        if(addressList.size()>0){
            Address adress= addressList.get(0);
            //Toast.makeText(getApplicationContext(),adress.toString(),Toast.LENGTH_LONG).show();
            LatLng pos = new LatLng(adress.getLatitude(),adress.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos,10));
            MarkerOptions options = new MarkerOptions().position(new LatLng(adress.getLatitude(),adress.getLongitude())).title(adress.getAddressLine(0));
            mMap.addMarker(options);
            Double latitude = adress.getLatitude();
            Double longitude = adress.getLongitude();
            //lati= String.valueOf(latitude);
            //longi= String.valueOf(longitude);
            place= adress.getAddressLine(0);


            ok.setVisibility(visibility);
        }

    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            final Location location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            loc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
                    List<Address> addressList;
                    double lati=location.getLatitude();
                    double longi=location.getLongitude();
                    try {
                        addressList= geocoder.getFromLocation(lati,longi,1);
                         place=addressList.get(0).getAddressLine(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    LatLng loc =new LatLng(lati,longi);
                    final MarkerOptions options = new MarkerOptions().position(loc);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc,10));

                    mMap.addMarker(options);
                   ok.setVisibility(View.VISIBLE);

                }
            });

        }
        //init();

    }


}
