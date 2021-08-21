package com.example.musicplayer;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<ModelAudio> userList;
MediaPlayer mediaPlayer;
    LinearLayoutManager layoutManager;
    BottomNavigationView bottomNavigationView;
public int y;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        userList = new ArrayList<>();
        mediaPlayer = new MediaPlayer();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        Intent k =getIntent();
        y=k.getIntExtra("Check",0);
//        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
//        {
//            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
//        }
//        if (ContextCompat.checkSelfPermission(MainActivity.this,
//                Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Permission is not granted
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
//                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//            } else {
//                // No explanation needed; request the permission
//                ActivityCompat.requestPermissions(MainActivity.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        1);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//        }
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
getAudioFiles();
bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.exit)
        {
            finishAffinity();
            System.exit(0);
        }
        return true;
    }
});

    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public  void getAudioFiles()
    {
        ContentResolver contentResolver = getContentResolver();
//String mediapath = Environment.getExternalStorageDirectory().getAbsolutePath();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String provider = "com.android.providers.media.MediaProvider";
        grantUriPermission(provider, uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        grantUriPermission(provider, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        grantUriPermission(provider, uri, Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        String[] STAR = {"*"};
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        Cursor cursor = contentResolver.query(uri,STAR,selection,null,null);
        if(cursor!=null && cursor.moveToFirst())
        {
            do {
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String duration = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                ModelAudio modelAudio = new ModelAudio();
                modelAudio.setaudioArtist(artist);
                modelAudio.setaudioTitle(title);
                modelAudio.setaudioDuration(duration);
                modelAudio.setaudioUri(Uri.parse(url));

                userList.add(modelAudio);

            }
            while (cursor.moveToNext());
        }
        Objects.requireNonNull(cursor).close();
        Adapter adapter = new Adapter(userList,this);
        recyclerView.setAdapter(adapter);

    }
}