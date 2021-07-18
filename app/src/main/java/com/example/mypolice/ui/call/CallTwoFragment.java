package com.example.mypolice.ui.call;


import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypolice.R;
import com.example.mypolice.databinding.FragmentCallTwoBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.SinchError;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;
import com.sinch.android.rtc.calling.CallClientListener;
import com.sinch.android.rtc.calling.CallListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class CallTwoFragment extends Fragment {
    private FragmentCallTwoBinding binding;
    private static final String APP_KEY = "557a34c6-06ee-4980-9e5c-b1072ffb985b";
    private static final String APP_SECRET = "mg/uJUvUZUOZgzv/cVvSfA==";
    private static final String ENVIRONMENT = "sandbox.sinch.com";
private Boolean loud =false;
    private Call call;
    private SinchClient sinchClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCallTwoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sinchClient = Sinch.getSinchClientBuilder()
                .context(requireContext())
                .userId(FirebaseAuth.getInstance().getUid())
                .applicationKey(APP_KEY)
                .applicationSecret(APP_SECRET)
                .environmentHost(ENVIRONMENT)
                .build();

        sinchClient.setSupportCalling(true);
        sinchClient.startListeningOnActiveConnection();
        sinchClient.start();
        sinchClient.getCallClient().addCallClientListener(new SinchCallClientListener());
        String tujuan = getArguments().getString("key");
wait(1000);
call();

        binding.button.setOnClickListener(v -> {
            if (call != null) {
                call.hangup();

            }
        });
        binding.IDCallTwoBtnLoudspeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManager mAudioMgr = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
                if (loud==false){
                    getActivity().setVolumeControlStream(AudioManager.USE_DEFAULT_STREAM_TYPE);
                    loud=true;
                }else {



                }

            }
        });

    }


    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
    private void call() {
        if (sinchClient.isStarted()&&call == null) {

            call = sinchClient.getCallClient().callUser("admin");
            call.addCallListener(new SinchCallListener());
            binding.button.setText("Hang Up");
        }
    }

    private class SinchCallListener implements CallListener {
        @Override
        public void onCallEnded(Call endedCall) {
            call = null;
            SinchError a = endedCall.getDetails().getError();
            binding.button.setText("Call");
            binding.txtKet.setText("");
            Navigation.findNavController(requireView()).navigate(R.id.action_callTwoFragment_to_listContactFragment);
        }


        @Override
        public void onCallEstablished(Call establishedCall) {
            binding.txtKet.setText("connected");
            sinchClient.getAudioController().enableSpeaker();
        }

        @Override
        public void onCallProgressing(Call progressingCall) {
            binding.txtKet.setText("ringing");
        }

        @Override
        public void onShouldSendPushNotification(Call call, List<PushPair> pushPairs) {
        }
    }

    private class SinchCallClientListener implements CallClientListener {
        @Override
        public void onIncomingCall(CallClient callClient, Call incomingCall) {
            call = incomingCall;
            Toast.makeText(requireContext(), "incoming call", Toast.LENGTH_SHORT).show();
            call.answer();
            call.addCallListener(new SinchCallListener());
            binding.button.setText("Hang Up");

        }
    }
}