package com.example.musicplayer;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {
//List<ModelAudio> userList;
//List<ModelAudio> userList;
    Bundle songs;
    ArrayList<ModelAudio> userList;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    ImageButton imageButton4;
    ImageButton imageButton;
    ImageButton imageButton2;
    ImageButton imageButton3;
    SeekBar seekBar;
    MediaPlayer mediaPlayer;
    int temp;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        userList = new ArrayList<>();
        ModelAudio modelAudio = new ModelAudio();

        mediaPlayer = new MediaPlayer();
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        imageButton4 = findViewById(R.id.imageButton4);
imageButton4.setImageResource(R.drawable.quit);
        imageButton = findViewById(R.id.imageButton);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);
        imageButton.setImageResource(R.drawable.play);
        imageButton2.setImageResource(R.drawable.next);
        imageButton3.setImageResource(R.drawable.prev1);
        seekBar = findViewById(R.id.seekBar);
//        ContentResolver contentResolver = getContentResolver();
////String mediapath = Environment.getExternalStorageDirectory().getAbsolutePath();
//
//        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        String provider = "com.android.providers.media.MediaProvider";
//        grantUriPermission(provider, uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        grantUriPermission(provider, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//        grantUriPermission(provider, uri, Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
//        String[] STAR = {"*"};
//        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
//        Cursor cursor = contentResolver.query(uri,STAR,selection,null,null);
//        if(cursor!=null && cursor.moveToFirst())
//        {
//            do {
//                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
//                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
//                String duration = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
//                String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
//                ModelAudio modelAudio = new ModelAudio();
//                modelAudio.setaudioArtist(artist);
//                modelAudio.setaudioTitle(title);
//                modelAudio.setaudioDuration(duration);
//                modelAudio.setaudioUri(Uri.parse(url));
//                Log.d("Disp",title);
//                textView4.setText(artist);
//                textView5.setText(title);
//int f = Integer.parseInt(duration);
//int s = f/1000;
//int h =s/3600;
//int lefthr = s%3600;
//int min =lefthr/60;
//int sec = lefthr%60;
//String time = h+":"+min+":"+sec;
//                textView7.setText(time);
//                mediaPlayer = MediaPlayer.create(this,uri);
////                userList.add(modelAudio);
//
//            }
//            while (cursor.moveToNext());
//        }
//        Objects.requireNonNull(cursor).close();
//        ArrayList<? extends ModelAudio> userList = getIntent().getParcelableArrayListExtra("musiclist");
Intent k =getIntent();
songs = k.getExtras();
userList =   songs.getParcelableArrayList("musiclist");
int position = k.getIntExtra("position",0);
 temp = position;
//Bundle args = k.getBundleExtra("bundle");
//        List<ModelAudio> userList = (List<ModelAudio>) args.getSerializable("musiclist");

//        List<ModelAudio> userList = (ArrayList<ModelAudio>) getIntent().getSerializableExtra("musiclist");


Log.d("Position","Pos="+position);
Log.d("See",userList.get(position).getaudioTitle());
initMediaPlayer(position);
//
//String stringuri = k.getStringExtra("uri");
//String title = k.getStringExtra("title");
//textView5.setText(title);
//String artist = k.getStringExtra("artist");
//textView4.setText(artist);
//String duration = k.getStringExtra("duration");
//int f = Integer.parseInt(duration);
//int s = f/1000;
//int h =s/3600;
//int lefthr = s%3600;
//int min =lefthr/60;
//int sec = lefthr%60;
//String time = h+":"+min+":"+sec;
//                textView7.setText(time);
//mediaPlayer = MediaPlayer.create(this,Uri.parse(stringuri));
//        if(mediaPlayer.isPlaying())
//        {
//
//            button2.setText("Pause");
//        }
//        else {
//
//            button2.setText("Play");
//        }
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if(mediaPlayer.isPlaying())
{
//    int x = mediaPlayer.getCurrentPosition();
//    String t = currentTimerTable(x);
//    textView6.setText(t);
    mediaPlayer.pause();
    imageButton.setImageResource(R.drawable.play);
}
else {
    mediaPlayer.start();
    imageButton.setImageResource(R.drawable.pause);
}
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Log.d("Next",userList.get(position).getaudioTitle());
               if(temp<(userList.size()-1))
               {
                    temp++;
               }
               else
               {
                   temp =0;
               }
               initMediaPlayer(temp);
/////Intent i =new Intent(MainActivity2.this,MainActivity.class);
//////i.putExtra("Check",2);
//////
//////startActivity(i);
            }
       });
       imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(temp>0)
                {
                    temp--;
                }
                else
                {
                    temp =userList.size()-1;
                }
                initMediaPlayer(temp);
            }
        });
       mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
           @Override
           public void onCompletion(MediaPlayer mp) {
               if(temp<userList.size()-1)
               {
                   temp++;
               }
               else
               {
                   temp =0;
               }
               initMediaPlayer(temp);
           }
       });

//        getAudioFiles();
        Handler mHandler = new Handler();
//Make sure you update Seekbar on UI thread
        MainActivity2.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if(mediaPlayer != null){
                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
//textView6.setText(currentTimerTable(mediaPlayer.getCurrentPosition()));
                    seekBar.setProgress(mCurrentPosition);
//                    textView6.setText(currentTimerTable(mCurrentPosition*1000));
                }
                mHandler.postDelayed(this, 1000);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
if(fromUser)
{
    mediaPlayer.seekTo(progress*1000);
    seekBar.setProgress(progress);
}

                int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                textView6.setText(""+progress);
                textView6.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
//                textView6.setText(currentTimerTable((int) (seekBar.getX() + val + seekBar.getThumbOffset() / 2)));
//                textView6.setText(currentTimerTable(progress*1000));
//                textView6.setText(currentTimerTable(val));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(mediaPlayer!=null)
//                {
//
//                        if(mediaPlayer.isPlaying())
//                        {
//                            Message message = new Message();
//                            message.what = mediaPlayer.getCurrentPosition();
//                            handler.sendMessage(message);
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                }
//            }
//        }).start();

    }

public String currentTimerTable(int duration)
{

    int f= duration/1000;
    int s = f/1000;
    int h =s/3600;
    String hr = Integer.toString(h);
    int lefthr = s%3600;
    int min =lefthr/60;
    String mn = Integer.toString(min);
    int sec = lefthr%60;
    String ss = Integer.toString(sec);
    return hr+":"+mn+":"+ss;
}



    public void initMediaPlayer(int position) {
        if(mediaPlayer != null && mediaPlayer.isPlaying())
        {
            mediaPlayer.reset();
        }

        String artist = userList.get(position).getaudioArtist();
        textView4.setText(artist);
        String title = userList.get(position).getaudioTitle();
        textView5.setText(title);
        String duration = userList.get(position).getaudioDuration();
        int f = Integer.parseInt(duration);
int s = f/1000;
int h =s/3600;
int lefthr = s%3600;
int min =lefthr/60;
int sec = lefthr%60;
String time = h+":"+min+":"+sec;

                textView7.setText(time);
                seekBar.setMax(s);
                Uri uri =userList.get(position).getaudioUri();
               mediaPlayer =MediaPlayer.create(getApplicationContext(),uri);
    }
//    @RequiresApi(api = Build.VERSION_CODES.R)
//    public  void getAudioFiles()
//    {
//        ContentResolver contentResolver = getContentResolver();
////String mediapath = Environment.getExternalStorageDirectory().getAbsolutePath();
//
//        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        String provider = "com.android.providers.media.MediaProvider";
//        grantUriPermission(provider, uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        grantUriPermission(provider, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//        grantUriPermission(provider, uri, Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
//        String[] STAR = {"*"};
//        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
//        Cursor cursor = contentResolver.query(uri,STAR,selection,null,null);
//        if(cursor!=null && cursor.moveToFirst())
//        {
//            do {
//                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
//                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
//                String duration = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
//                String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
//                ModelAudio modelAudio = new ModelAudio();
//                modelAudio.setaudioArtist(artist);
//                modelAudio.setaudioTitle(title);
//                modelAudio.setaudioDuration(duration);
//                modelAudio.setaudioUri(Uri.parse(url));
//                textView4.setText(modelAudio.getaudioArtist());
//                textView5.setText(modelAudio.getaudioTitle());
//
//                textView7.setText(modelAudio.getaudioDuration());
//                userList.add(modelAudio);
//
//            }
//            while (cursor.moveToNext());
//        }
//        Objects.requireNonNull(cursor).close();
//
////        Adapter adapter = new Adapter(userList,this);
////        recyclerView.setAdapter(adapter);
//
//    }
}