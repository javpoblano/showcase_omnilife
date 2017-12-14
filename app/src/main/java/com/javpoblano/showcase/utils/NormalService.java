package com.javpoblano.showcase.utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.javpoblano.showcase.activities.TrackerActivity;

public class NormalService extends Service implements LocationListener{

    LocationManager locationManager;
    LocationListener locationListener;

    public NormalService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent i,int flags,int startId)
    {
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationListener = this;
        try
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15*1000,0,locationListener);
        }
        catch (SecurityException ex)
        {
            Log.d("err - gps",ex.getMessage());
        }
        return START_STICKY;
    }


    @Override
    public void onLocationChanged(Location location) {
        String url = "http://maps.google.com/maps?q="+location.getLatitude()+","+location.getLongitude();
        //SmsManager smsManager = SmsManager.getDefault();
        //smsManager.sendTextMessage("2226857591",null,url,null,null);
        LatLng newLocation = new LatLng(location.getLatitude(),location.getLongitude());
        try{
            TrackerActivity.instance().setLocation(newLocation);
        }catch (Exception e){

        }
        Log.d("onLocationChanged",url);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        locationManager.removeUpdates(locationListener);
    }

}
