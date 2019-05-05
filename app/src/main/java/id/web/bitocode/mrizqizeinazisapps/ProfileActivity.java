package id.web.bitocode.mrizqizeinazisapps;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/*
 *
 * Tanggal Pengerjaan : April 23, 2019 - May 5,2019
 * NIM   : 10116073
 * Nama  : Muhammad Rizqi Zein Azis
 * Kelas : AKB-2 / IF-2
 *
 *
 */

public class ProfileActivity extends AppCompatActivity implements OnMapReadyCallback
{
  
  private DrawerLayout dl;
  private ActionBarDrawerToggle dt;
  private NavigationView nv;
  private Intent start,startCalling,startEmail;
  private Button btncall,btnabout;
  private ImageButton btnfb,btnyoutube;
  private TextView textView;
  private GoogleMap mMap;
  
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
  
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);
    
    // add PhoneStateListener
    PhoneCallListener phoneListener = new PhoneCallListener();
    TelephonyManager telephonyManager = (TelephonyManager) this
            .getSystemService(Context.TELEPHONY_SERVICE);
    telephonyManager.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);
    
    btncall = findViewById(R.id.btnCall);
    btncall.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        startCalling = new Intent(Intent.ACTION_CALL);
        startCalling.setData(Uri.parse("tel:087823425195"));
        startActivity(startCalling);
      }
    });
  
    textView = findViewById(R.id.txtEmail);
    String editedtext = "Email : RizqiGx@Gmail.com\nNeed to Mail me just click the Email";
  
    SpannableString text   = new SpannableString(editedtext);
    ClickableSpan ClickReg = new ClickableSpan()
    {
      @Override
      public void onClick(@NonNull View view)
      {
        startEmail = new Intent(Intent.ACTION_SEND);
        startEmail.putExtra(Intent.EXTRA_EMAIL,new String[]{"RizqiGx@gmail.com"});
        startEmail.setType("message/rfc822");
        startActivity(Intent.createChooser(startEmail, "Choose an Email Client :"));
      }
    
      @Override
      public void updateDrawState(@NonNull TextPaint ds)
      {
        super.updateDrawState(ds);
        ds.setColor(Color.WHITE);
        ds.setUnderlineText(false);
      }
    };
    text.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD),8,26, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    text.setSpan(ClickReg,8,26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
  
    textView.setText(text);
    textView.setMovementMethod(LinkMovementMethod.getInstance());
    
    btnfb = findViewById(R.id.fbbutton);
    btnfb.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        start = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/RizqiGx"));
        startActivity(start);
      }
    });
  
    btnyoutube = findViewById(R.id.youtubebutton);
    btnyoutube.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        start = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UC8jOiL0YgD_Ha2g-nUMwTjg"));
        startActivity(start);
      }
    });
    
    btnabout = findViewById(R.id.btnAbout);
    btnabout.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        showCustomDialog();
      }
    });
    
  }
  
  private void showCustomDialog()
  {
    //before inflating the custom alert dialog layout, we will get the current activity viewgroup
    ViewGroup viewGroup = findViewById(android.R.id.content);
    
    //then we will inflate the custom alert dialog xml that we created
    View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_version, viewGroup, false);
    
    
    //Now we need an AlertDialog.Builder object
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    
    //setting the view of the builder to our custom view that we already inflated
    builder.setView(dialogView);
    
    //finally creating the alert dialog and displaying it
    final AlertDialog alertDialog = builder.create();
    alertDialog.show();
  
    Button btnversion = alertDialog.findViewById(R.id.btnVersion);
    btnversion.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        alertDialog.dismiss();
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
  
  private  class PhoneCallListener extends PhoneStateListener
  {
    private boolean stillCalling = false;
    
    String LOG_TAG = "LOGGING 123";
  
    @Override
    public void onCallStateChanged(int state, String incomingNumber)
    {
    
      if (TelephonyManager.CALL_STATE_RINGING == state)
      {
        // phone ringing
        Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
      }
      if (TelephonyManager.CALL_STATE_OFFHOOK == state)
      {
        // active
        Log.i(LOG_TAG, "OFFHOOK");
  
        stillCalling = true;
      }
      if (TelephonyManager.CALL_STATE_IDLE == state)
      {
        // run when class initial and phone call ended,
        // need detect flag from CALL_STATE_OFFHOOK
        Log.i(LOG_TAG, "IDLE");
      
        if (stillCalling)
        {
        
          Log.i(LOG_TAG, "restart app");
        
          // restart app
          Intent i = getBaseContext().getPackageManager()
                  .getLaunchIntentForPackage(
                          getBaseContext().getPackageName());
          i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          startActivity(i);
  
          stillCalling = false;
        }
      }
    }
  }
  
  @Override
  public void onMapReady(GoogleMap googleMap)
  {
    mMap = googleMap;
    
    // Add a marker in Sydney and move the camera
    LatLng home = new LatLng(-6.9204618, 107.5895059);
    float zoomLevel = 16.0f;
    mMap.addMarker(new MarkerOptions().position(home).title("My Home  in Bandung"));
    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home, zoomLevel));
  }
  
}
