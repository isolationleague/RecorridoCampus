package com.example.admlab105.recorridocampus;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;


public class InfoTextsFragment extends Fragment {
    private static final String TAG = "InfoTextsFragment";
    MediaPlayer audioPlayer;
    ImageButton playButton;
    SeekBar seekBar;
    Handler handler;
    Runnable runnable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { View view = inflater.inflate(R.layout.info_texts_fragment,container,false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        ImagePagerAdapter adapter = new ImagePagerAdapter();
        viewPager.setAdapter(adapter);
        playButton = view.findViewById(R.id.playButton);
        seekBar = view.findViewById(R.id.seekBar);
        handler = new Handler();
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(v);
            }
        });
        return view;
    }

    public void play(View view) {
        if (audioPlayer == null) {
            audioPlayer = MediaPlayer.create(getActivity(), R.raw.lacus_somniorum);
            //audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            seekBar.setMax(audioPlayer.getDuration());
            playing();
            audioPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
            if (audioPlayer.isPlaying()) {
                if (audioPlayer != null) {
                    audioPlayer.pause();
                    playButton.setImageResource(R.drawable.sharp_play_arrow_black_18dp);
                }
            } else {
                if (audioPlayer != null) {
                    audioPlayer.start();
                    playButton.setImageResource(R.drawable.sharp_pause_black_18dp);
                }
            }
        }
        audioPlayer.start();
    }

    private void stopPlayer() {
        if (audioPlayer != null) {
            audioPlayer.release();
            audioPlayer = null;

        }
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onStop() {
        playButton.setImageResource(R.drawable.sharp_play_arrow_black_18dp);
        super.onStop();
        stopPlayer();
    }

    public void playing() {
            if (audioPlayer != null)
                handler.postDelayed(updateTime, 100);
    }

    private Runnable updateTime = new Runnable() {
        @Override
        public void run() {
            if (audioPlayer != null) {
                seekBar.setProgress(audioPlayer.getCurrentPosition());
                handler.postDelayed(this, 100);
            }
        }
    };

    private class ImagePagerAdapter extends PagerAdapter {
        private int[] mImages = new int[] {R.drawable.captura_intromenu,R.drawable.default0};


        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = getActivity();
            ImageView imageView = new ImageView(context);
            int padding = context.getResources().getDimensionPixelSize(
                    R.dimen.padding_medium);
            imageView.setPadding(padding, padding, padding, padding);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setImageResource(mImages[position]);
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }
}