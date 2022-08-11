package com.example.mockproject.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class Broadcast extends BroadcastReceiver {
    private static final String TAG = "Broadcast";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentService = new Intent(context, PlayMediaService.class);
        Log.i(TAG, "onReceive: "+intent.getAction());
        intentService.setAction(intent.getAction());
        context.startService(intentService);
    }
}

