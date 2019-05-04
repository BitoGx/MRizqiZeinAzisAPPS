package id.web.bitocode.mrizqizeinazisapps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Vector;

import id.web.bitocode.mrizqizeinazisapps.adapter.VideoRecyclerAdapter;
import id.web.bitocode.mrizqizeinazisapps.model.YoutubeModel;

public class VideoActivity extends AppCompatActivity
{
  
  private DrawerLayout dl;
  private ActionBarDrawerToggle dt;
  private NavigationView nv;
  private Intent start;
  
  RecyclerView recyclerView;
  Vector<YoutubeModel> youtubeVideo = new Vector<YoutubeModel>();
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_video);
  
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle("Video");
  
    dl = findViewById(R.id.activity_video);
    dt = new ActionBarDrawerToggle(this, dl,  R.string.Open, R.string.Close);
  
    dl.addDrawerListener(dt);
    dt.syncState();
  
    nv = findViewById(R.id.nvVideo);
    nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
    {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
      {
        int id = menuItem.getItemId();
        switch (id)
        {
          case R.id.navhome:
            start = new Intent(VideoActivity.this, HomeActivity.class);
            startActivity(start);
            break;
        
          case R.id.navdaily:
            start = new Intent(VideoActivity.this, DailyActivity.class);
            startActivity(start);
            break;
        
          case R.id.navfriend:
            Toast.makeText(VideoActivity.this, "Friend",Toast.LENGTH_SHORT).show();
            break;
        
          case R.id.navgallery:
            start = new Intent(VideoActivity.this, GalleryActivity.class);
            startActivity(start);
            break;
        
          case R.id.navvideo:
            Toast.makeText(VideoActivity.this, "Video",Toast.LENGTH_SHORT).show();
            break;
        
          case R.id.navmusic:
            Toast.makeText(VideoActivity.this, "Music",Toast.LENGTH_SHORT).show();
        
          case R.id.navprofile:
            Toast.makeText(VideoActivity.this, "Profile",Toast.LENGTH_SHORT).show();
            break;
        
          default:
            return true;
        }
        return true;
      }
    });
  
    recyclerView = (RecyclerView) findViewById(R.id.recycler_view_video);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager( new LinearLayoutManager(this));
  
    youtubeVideo.add( new YoutubeModel("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/3MrZCtlIzrQ\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>") );
    youtubeVideo.add( new YoutubeModel("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/U5OH7cF9M8A\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>") );
    youtubeVideo.add( new YoutubeModel("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/h3Mczd0cWcM\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>") );
    youtubeVideo.add( new YoutubeModel("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/jWmBwMmnTdo\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>") );
  
    VideoRecyclerAdapter videoAdapter = new VideoRecyclerAdapter(youtubeVideo);
  
    recyclerView.setAdapter(videoAdapter);
    
  }
  @Override
  public boolean onOptionsItemSelected(MenuItem menuItem)
  {
    if(dt.onOptionsItemSelected(menuItem))
      return true;
    return super.onOptionsItemSelected(menuItem);
  }
}
