package com.androidjson.one_signal;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

public class one_signal extends Application {

    private static one_signal mInstance;
    SharedPreferences.Editor prefEditor;


    public  one_signal(){
        mInstance = this;
    }


    @Override
    public  void onCreate() {
        super.onCreate();
        mInstance = this;

        prefEditor = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit();
        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new NotificationHandler())
                .init();

    }


    class NotificationHandler implements  OneSignal.NotificationOpenedHandler{


        @Override
        public void notificationOpened(OSNotificationOpenResult result) {

            JSONObject data = result.notification.payload.additionalData;

            if (data != null && data.has("name") ){

                // DO what ever you want

                prefEditor.putString("nameRecieved", data.optString("name"));
                prefEditor.putString("phoneRecieved", data.optString("phone"));
                prefEditor.putString("countryRecieved", data.optString("country"));
                prefEditor.apply();


            }



        }
    }




    public static  synchronized  one_signal getmInstance(){

        return mInstance;
    }

}
