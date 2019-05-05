package id.web.bitocode.mrizqizeinazisapps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity
{
  
  private DrawerLayout dl;
  private ActionBarDrawerToggle dt;
  private NavigationView nv;
  private Intent start;
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
  
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle("Profile");
  
    dl = findViewById(R.id.activity_profile);
    dt = new ActionBarDrawerToggle(this, dl,  R.string.Open, R.string.Close);
  
    dl.addDrawerListener(dt);
    dt.syncState();
  
    nv = findViewById(R.id.nvProfile);
    nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
    {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
      {
        int id = menuItem.getItemId();
        switch (id)
        {
          case R.id.navhome:
            start = new Intent(ProfileActivity.this, HomeActivity.class);
            startActivity(start);
            break;
        
          case R.id.navdaily:
            start = new Intent(ProfileActivity.this, DailyActivity.class);
            startActivity(start);
            break;
        
          case R.id.navfriend:
            start = new Intent(ProfileActivity.this, FriendActivity.class);
            startActivity(start);
            break;
        
          case R.id.navgallery:
            start = new Intent(ProfileActivity.this, GalleryActivity.class);
            startActivity(start);
            break;
        
          case R.id.navvideo:
            start = new Intent(ProfileActivity.this, VideoActivity.class);
            startActivity(start);
            break;
        
          case R.id.navmusic:
            start = new Intent(ProfileActivity.this, MusicActivity.class);
            startActivity(start);
            break;
        
          case R.id.navprofile:
            Toast.makeText(ProfileActivity.this, "Profile",Toast.LENGTH_SHORT).show();
            break;
        
          default:
            return true;
        }
        return true;
      }
    });
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem menuItem)
  {
    if(dt.onOptionsItemSelected(menuItem))
      return true;
    return super.onOptionsItemSelected(menuItem);
  }
  
}
