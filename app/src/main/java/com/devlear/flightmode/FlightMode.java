package com.devlear.flightmode;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

/**
 * Created by malakhv on 11.02.2018.
 * */
final class FlightMode {


    public static final IntentFilter MODE_CHANGED_FILTER = new IntentFilter();
    static {
        MODE_CHANGED_FILTER.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
    }

    public static boolean isFlightMode() {
        return false;
    }

    public static void setFlightMode(Context context, boolean enabled) {
        final ConnectivityManager mgr =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //mgr.setAirplaneMode(enabled);
    }
}