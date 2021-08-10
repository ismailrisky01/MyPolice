package com.example.mypolice;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.SinchError;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;
import com.sinch.android.rtc.calling.CallClientListener;
import com.sinch.android.rtc.calling.CallListener;

import java.util.List;

public class Sinch extends AppCompatActivity {
    private static final String APP_KEY = "557a34c6-06ee-4980-9e5c-b1072ffb985b";
    private static final String APP_SECRET = "mg/uJUvUZUOZgzv/cVvSfA==";
    private static final String ENVIRONMENT = "sandbox.sinch.com";
    private Boolean loud = false;
    private Call call;
    private SinchClient sinchClient;

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private void endCall() {
        if (call != null) {
            call.hangup();
//            Navigation.findNavController(requireView()).popBackStack();
        } else {
//            Navigation.findNavController(requireView()).popBackStack();
        }
    }


}
