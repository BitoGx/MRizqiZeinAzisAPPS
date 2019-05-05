package id.web.bitocode.mrizqizeinazisapps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import id.web.bitocode.mrizqizeinazisapps.adapter.FriendRecyclerAdapter;
import id.web.bitocode.mrizqizeinazisapps.adapter.GalleryRecyclerAdapter;

public class FriendActivity extends AppCompatActivity
{
  
  private DrawerLayout dl;
  private ActionBarDrawerToggle dt;
  private NavigationView nv;
  private Intent start;
  private RecyclerView recyclerView;
  private int[] img_friend =
          {
                  R.drawable.friend1,R.drawable.friend2,R.drawable.friend3,R.drawable.friend4,
                  R.drawable.friend5,R.drawable.friend6,R.drawable.friend7
          };
  private String[] friend_name =
          {
                  "Rizky Fauzan","Fikih Zaman","Muhammad Idris Kurniawan","Aditya Maulana Rajak",
                  "Nialdi tanawara","Fritson Agung Ayomi ","Bobby Rafika Chandra"
          };
  private RecyclerView.LayoutManager layoutManager;
  private FriendRecyclerAdapter adapter;
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_friend);
  
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle("Friend");
  
    dl = findViewById(R.id.activity_friend);
    dt = new ActionBarDrawerToggle(this, dl,  R.string.Open, R.string.Close);
  
    dl.addDrawerListener(dt);
    dt.syncState();
  
    nv = findViewById(R.id.nvFriend);
    nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
    {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
      {
        int id = menuItem.getItemId();
        switch (id)
        {
          case R.id.navhome:
            start = new Intent(FriendActivity.this, HomeActivity.class);
            startActivity(start);
            break;
        
          case R.id.navdaily:
            start = new Intent(FriendActivity.this, DailyActivity.class);
            startActivity(start);
            break;
        
          case R.id.navfriend:
            Toast.makeText(FriendActivity.this, "Friend",Toast.LENGTH_SHORT).show();
            break;
        
          case R.id.navgallery:
            start = new Intent(FriendActivity.this, GalleryActivity.class);
            startActivity(start);
            break;
        
          case R.id.navvideo:
            start = new Intent(FriendActivity.this, VideoActivity.class);
            startActivity(start);
            break;
        
          case R.id.navmusic:
            start = new Intent(FriendActivity.this, MusicActivity.class);
            startActivity(start);
            break;
        
          case R.id.navprofile:
            start = new Intent(FriendActivity.this, ProfileActivity.class);
            startActivity(start);
            break;
        
          default:
            return true;
        }
        return true;
      }
    });
  
    recyclerView = findViewById(R.id.recycler_view_friend);
    layoutManager = new GridLayoutManager(this, 3);
    ((GridLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
    recyclerView.setHasFixedSize(false);
    recyclerView.setLayoutManager(layoutManager);
    adapter = new FriendRecyclerAdapter(img_friend,friend_name);
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
