package id.web.bitocode.mrizqizeinazisapps;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.web.bitocode.mrizqizeinazisapps.adapter.SplashPagerAdapter;

/*
 *
 * Tanggal Pengerjaan :
 * NIM   : 10116073
 * Nama  : Muhammad Rizqi Zein Azis
 * Kelas : AKB-2 / IF-2
 *
 *
 */

public class SliderActivity extends AppCompatActivity
{
  Handler handler;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.slider_activity);
    
    final ViewPager viewPager = findViewById(R.id.viewpager);
    viewPager.setAdapter(new SplashPagerAdapter(this));
    
    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
    {
      @Override
      public void onPageScrolled(int i, float v, int i1)
      {
    
      }
  
      @Override
      public void onPageSelected(int i)
      {
        if(i == viewPager.getAdapter().getCount()-1)
        {
          handler = new Handler();
          handler.postDelayed(new Runnable()
          {
            @Override
            public void run()
            {
              Intent start = new Intent(SliderActivity.this, HomeActivity.class);
              startActivity(start);
              finish();
            }
          },3000);
        }
      }
  
      @Override
      public void onPageScrollStateChanged(int i)
      {
    
      }
    });
  }
}
