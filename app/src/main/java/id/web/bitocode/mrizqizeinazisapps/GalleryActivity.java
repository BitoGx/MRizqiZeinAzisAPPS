package id.web.bitocode.mrizqizeinazisapps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import id.web.bitocode.mrizqizeinazisapps.adapter.GalleryRecyclerAdapter;

/*
 *
 * Tanggal Pengerjaan : April 23, 2019 - May 5,2019
 * NIM   : 10116073
 * Nama  : Muhammad Rizqi Zein Azis
 * Kelas : AKB-2 / IF-2
 *
 *
 */

public class GalleryActivity extends AppCompatActivity
{
  
 
  private DrawerLayout dl;
  private ActionBarDrawerToggle dt;
  private NavigationView nv;
  private Intent start;
  private RecyclerView recyclerView;
  private int[] images =
          {
            R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,
            R.drawable.image5,R.drawable.image6,R.drawable.image7
          };
  private String[] title=
          {
                  "War Thunder 1","War Thunder 2","War Thunder 3","Programmer Joke","OwO","Patch 1","Patch 2"
          };
  private RecyclerView.LayoutManager layoutManager;
  private GalleryRecyclerAdapter adapter;
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gallery);
  
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle("Gallery");
  
    dl = findViewById(R.id.activity_gallery);
    dt = new ActionBarDrawerToggle(this, dl,  R.string.Open, R.string.Close);
  
    dl.addDrawerListener(dt);
    dt.syncState();
  
    nv = findViewById(R.id.nvGallery);
    nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
    {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
      {
        int id = menuItem.getItemId();
        switch (id)
        {
          case R.id.navhome:
            start = new Intent(GalleryActivity.this, HomeActivity.class);
            startActivity(start);
            break;
        
          case R.id.navdaily:
            start = new Intent(GalleryActivity.this, DailyActivity.class);
            startActivity(start);
            break;
        
          case R.id.navfriend:
            start = new Intent(GalleryActivity.this, FriendActivity.class);
            startActivity(start);
            break;
        
          case R.id.navgallery:
            Toast.makeText(GalleryActivity.this, "Gallery",Toast.LENGTH_SHORT).show();
            break;
        
          case R.id.navvideo:
            start = new Intent(GalleryActivity.this, VideoActivity.class);
            startActivity(start);
            break;
        
          case R.id.navmusic:
            start = new Intent(GalleryActivity.this, MusicActivity.class);
            startActivity(start);
            break;
        
          case R.id.navprofile:
            start = new Intent(GalleryActivity.this, ProfileActivity.class);
            startActivity(start);
            break;
        
          default:
            return true;
        }
        return true;
      }
    });
    
    recyclerView = findViewById(R.id.recycler_view_gallery);
    layoutManager = new GridLayoutManager(this, 2);
    recyclerView.setHasFixedSize(false);
    recyclerView.setLayoutManager(layoutManager);
    adapter = new GalleryRecyclerAdapter(images,title);
    adapter.setHasStableIds(true);
    recyclerView.setAdapter(adapter);
    
  }
  @Override
  public boolean onOptionsItemSelected(MenuItem menuItem)
  {
    if(dt.onOptionsItemSelected(menuItem))
      return true;
    return super.onOptionsItemSelected(menuItem);
  }
}
