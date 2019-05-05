package id.web.bitocode.mrizqizeinazisapps;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

/*
 *
 * Tanggal Pengerjaan :
 * NIM   : 10116073
 * Nama  : Muhammad Rizqi Zein Azis
 * Kelas : AKB-2 / IF-2
 *
 *
 */

public class SplashActivity extends Activity
{
  Handler handler;
  @Override
  protected  void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    
    MediaPlayer music = MediaPlayer.create(SplashActivity.this, R.raw.splash_sound);
    music.start();
    
    
    handler = new Handler();
    handler.postDelayed(new Runnable()
    {
      @Override
      public void run()
      {
        Intent start = new Intent(SplashActivity.this, SliderActivity.class);
        startActivity(start);
        finish();
      }
    },4000);
  }
}
