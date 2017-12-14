package com.javpoblano.showcase.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.PolyUtil;
import com.javpoblano.showcase.R;
import com.javpoblano.showcase.utils.NormalService;

public class TrackerActivity extends AppCompatActivity implements OnMapReadyCallback{
    MapView mapView;
    GoogleMap googleMap;
    FloatingActionButton startButton,zoneButton;
    boolean isSelectionActivated = false;
    Polygon zone;
    Marker myLocation;
    PolygonOptions options;
    private static TrackerActivity inst;
    LatLng location;
    boolean isLocationActivated = false;

    public static TrackerActivity instance()
    {
        return inst;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        inst = this;
    }

    public void setLocation(LatLng location)
    {
        this.location = location;
        try
        {
            if(myLocation!=null)
            {
                myLocation.setPosition(location);
                if (!isSelectionActivated)
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,20));
            }
            else
            {
                myLocation = googleMap.addMarker(new MarkerOptions()
                                                    .position(location));
                if(!isSelectionActivated)
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,20));
            }

            if(zone!=null)
            {
                boolean isInZone = PolyUtil.containsLocation(location,zone.getPoints(),true);
                if(isInZone)
                    myLocation.setTitle("Estas en la zona");
                else
                    myLocation.setTitle("Ya te saliste");
            }
        }
        catch (Exception e)
        {
            Log.d("err map",e.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initMap(savedInstanceState);
        startButton = (FloatingActionButton) findViewById(R.id.start);
        zoneButton = (FloatingActionButton) findViewById(R.id.zona);
        zoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isSelectionActivated)
                {
                    startEditionMode();
                }
                else
                {
                    stopEditingMode();
                }
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isLocationActivated)
                {
                    startMyLocation();
                }
                else
                {
                    stopLocation();
                }
            }
        });
    }

    public void initMap(Bundle savedInstanceState)
    {
        mapView = (MapView)findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        try
        {
            MapsInitializer.initialize(getApplicationContext());
        }
        catch (Exception e)
        {
            Log.d("err map",e.toString());
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
    }

    public void startEditionMode()
    {
        isSelectionActivated = true;
        options = new PolygonOptions();
        options.strokeColor(Color.BLACK);
        options.fillColor(Color.BLUE);
        getSupportActionBar().setTitle("Editando");
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                try
                {
                    zone.remove();
                }
                catch (Exception e)
                {

                }
                finally
                {
                    options.add(latLng);
                    zone = googleMap.addPolygon(options);
                }
            }
        });
    }

    public void stopEditingMode()
    {
        isSelectionActivated = false;
        googleMap.setOnMapClickListener(null);
        getSupportActionBar().setTitle("Tracker");
    }

    public void startMyLocation()
    {
        isLocationActivated = true;
        startService(new Intent(this, NormalService.class));
    }

    public void stopLocation()
    {
        isLocationActivated = false;
        stopService(new Intent(this, NormalService.class));
    }
}
