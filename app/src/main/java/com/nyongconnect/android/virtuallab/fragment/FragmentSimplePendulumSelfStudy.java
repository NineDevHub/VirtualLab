package com.nyongconnect.android.virtuallab.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyongconnect.android.virtuallab.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;

/**
 * Created by enyason on 9/10/18.
 */

public class FragmentSimplePendulumSelfStudy extends Fragment {




    public FragmentSimplePendulumSelfStudy() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.pendulum_layout, container, false);
    }

    YouTubePlayerView youTubePlayerView;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         youTubePlayerView = view.findViewById(R.id.youtube_player_view);
        youTubePlayerView.initialize(
                new YouTubePlayerInitListener() {

                    @Override
                    public void onInitSuccess(
                            final YouTubePlayer initializedYouTubePlayer) {

                        initializedYouTubePlayer.addListener(
                                new AbstractYouTubePlayerListener() {
                                    @Override
                                    public void onReady() {
                                        initializedYouTubePlayer.loadVideo("vX074Q4kP9k", 0);
                                    }
                                });
                    }
                }, true);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }
}
