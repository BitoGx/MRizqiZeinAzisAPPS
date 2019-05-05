package id.web.bitocode.mrizqizeinazisapps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.web.bitocode.mrizqizeinazisapps.adapter.DailyRecyclerAdapter;
import id.web.bitocode.mrizqizeinazisapps.model.DailyModel;
import id.web.bitocode.mrizqizeinazisapps.presenter.DailyPresenter;
import id.web.bitocode.mrizqizeinazisapps.presenter.DailyPresenterImpl;
import id.web.bitocode.mrizqizeinazisapps.view.DailyView;

/*
 *
 * Tanggal Pengerjaan : April 23, 2019 - May 5,2019
 * NIM   : 10116073
 * Nama  : Muhammad Rizqi Zein Azis
 * Kelas : AKB-2 / IF-2
 *
 *
 */

public class DailyActivity extends AppCompatActivity implements DailyView
{
  
  private RecyclerView recyclerView;
  private Button buttonAdd;
  private DailyRecyclerAdapter adapter;
  private List<DailyModel> dailyModels = new ArrayList<>();
  private AppCompatDialog dialog;
  private DailyPresenter presenter;
  private DrawerLayout dl;
  private ActionBarDrawerToggle dt;
  private NavigationView nv;
  private Intent start;
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_daily);
  
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle("Daily Activities");
  
    dl = findViewById(R.id.activity_daily);
    dt = new ActionBarDrawerToggle(this, dl,  R.string.Open, R.string.Close);
  
    dl.addDrawerListener(dt);
    dt.syncState();
  
    nv = findViewById(R.id.nvDaily);
    nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
    {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
      {
        int id = menuItem.getItemId();
        switch (id)
        {
          case R.id.navhome:
            start = new Intent(DailyActivity.this, HomeActivity.class);
            startActivity(start);
            break;
        
          case R.id.navdaily:
            Toast.makeText(DailyActivity.this, "Daily Activity",Toast.LENGTH_SHORT).show();
            break;
        
          case R.id.navgallery:
            start = new Intent(DailyActivity.this, GalleryActivity.class);
            startActivity(start);
            break;
            
          case R.id.navfriend:
            start = new Intent(DailyActivity.this, FriendActivity.class);
            startActivity(start);
            break;
        
          case R.id.navvideo:
            start = new Intent(DailyActivity.this, VideoActivity.class);
            startActivity(start);
            break;
  
          case R.id.navmusic:
            start = new Intent(DailyActivity.this, MusicActivity.class);
            startActivity(start);
            break;
        
          case R.id.navprofile:
            start = new Intent(DailyActivity.this, ProfileActivity.class);
            startActivity(start);
            break;
        
          default:
            return true;
        }
        return true;
      }
    });
  
    presenter = new DailyPresenterImpl(this);
  
    recyclerView = findViewById(R.id.recycler_view_daily);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
  
    adapter = new DailyRecyclerAdapter(dailyModels);
    recyclerView.setAdapter(adapter);
  
    presenter.load();
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem menuItem)
  {
    if(dt.onOptionsItemSelected(menuItem))
      return true;
    return super.onOptionsItemSelected(menuItem);
  }
  
  @Override
  public void onLoad(List<DailyModel> dailyModel)
  {
    dailyModels.clear();
    dailyModels.addAll(dailyModel);
    
    adapter.notifyDataSetChanged();
  }
  
  public static class YoutubeVideo
  {
    private String url;
    
    public String getUrl()
    {
      return url;
    }
    
    public void setUrl(String url)
    {
      this.url = url;
    }
    
    public YoutubeVideo(String url)
    {
      this.url = url;
    }
    
  }
}
