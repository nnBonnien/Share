package com.jianda.liwenjie.music;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.List;

/**
 * Mediaplayer
 * 播放有两种途径
 */
public class MainActivity extends AppCompatActivity implements Handler.Callback, SeekBar.OnSeekBarChangeListener {

    private MediaPlayer mediaPlayer;
    private List<File> list;
    private TextView currentTime;
    private TextView totalTime;
    private Handler mHandler;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = FileUtil.getList(new File("storage/emulated/0/"));
        currentTime = (TextView) findViewById(R.id.player_current_time);
        totalTime = (TextView) findViewById(R.id.player_total_time);
        mHandler = new Handler(this);
        seekBar = (SeekBar) findViewById(R.id.player_progress);
        seekBar.setOnSeekBarChangeListener(this);
    }

    public static final int PROGRESS = 1;
    private void play(String path){
        if (mediaPlayer == null){
            //创建一个MediaPlayer
            mediaPlayer = MediaPlayer.create(this, Uri.parse(path));
        }
        mediaPlayer.start();
        //当前播放的进度
        int currentPosition = mediaPlayer.getCurrentPosition();
        Message message = Message.obtain();
        message.what = PROGRESS;
        message.arg1 = currentPosition;
        mHandler.sendMessage(message);
    }

    public void play(View view) {
        play("storage/emulated/0/goldfallen.mp3");
        Log.d("0000", "play() returned: " + list.size());
       /* if (list != null && list.size() > 0){
            Log.d("0000", "play() returned: " + list.get(0));
            play(list.get(0).getAbsolutePath());
        }*/
    }

    public void stop(View view) {
        if (mediaPlayer != null){
            mediaPlayer.pause();
            mHandler.removeMessages(PROGRESS);
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case PROGRESS:
                currentTime.setText(DateFormat.format("mm:ss", msg.arg1));
                Message message = Message.obtain();
                message.what = PROGRESS;
                message.arg1 = mediaPlayer.getCurrentPosition();
                mHandler.sendMessage(message);
                break;
        }
        return false;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
