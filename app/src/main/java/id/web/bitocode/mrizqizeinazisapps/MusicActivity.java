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
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MusicActivity extends AppCompatActivity
{
  
  private DrawerLayout dl;
  private ActionBarDrawerToggle dt;
  private NavigationView nv;
  private Intent start;
  private AudioItemAdapter audioItemAdapter;
  private String[] title =
          {
                  "Hatsukoi Sunrider OST", "Ascendancy Summer Night Stonehearth OST",
                  "Tsuki ni Murakumo Hana ni Kaze"
          };
  
  // POJO to hold data about audio items
  private static class AudioItem {
    
    // raw resource id of audio cell
    final int audioResId;
    
    private AudioItem(int audioResId) {
      this.audioResId = audioResId;
    }
  }
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_music);
  
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle("Music");
  
    dl = findViewById(R.id.activity_music);
    dt = new ActionBarDrawerToggle(this, dl,  R.string.Open, R.string.Close);
  
    dl.addDrawerListener(dt);
    dt.syncState();
  
    nv = findViewById(R.id.nvMusic);
    nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
    {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
      {
        int id = menuItem.getItemId();
        switch (id)
        {
          case R.id.navhome:
            start = new Intent(MusicActivity.this, HomeActivity.class);
            startActivity(start);
            break;
        
          case R.id.navdaily:
            start = new Intent(MusicActivity.this, DailyActivity.class);
            startActivity(start);
            break;
        
          case R.id.navfriend:
            start = new Intent(MusicActivity.this, FriendActivity.class);
            startActivity(start);
            break;
        
          case R.id.navgallery:
            start = new Intent(MusicActivity.this, GalleryActivity.class);
            startActivity(start);
            break;
        
          case R.id.navvideo:
            start = new Intent(MusicActivity.this, VideoActivity.class);
            startActivity(start);
            break;
        
          case R.id.navmusic:
            Toast.makeText(MusicActivity.this, "Music",Toast.LENGTH_SHORT).show();
            break;
        
          case R.id.navprofile:
            start = new Intent(MusicActivity.this, ProfileActivity.class);
            startActivity(start);
            break;
        
          default:
            return true;
        }
        return true;
      }
    });
  
    RecyclerView rv = findViewById(R.id.recycler_view_music);
  
    // arrange cells in vertical column
    rv.setLayoutManager(new LinearLayoutManager(this));
    
    ArrayList<AudioItem> audioItems = new ArrayList<>();
    audioItems.add(new AudioItem(R.raw.hatsukoi));
    audioItems.add(new AudioItem(R.raw.ascsummer));
    audioItems.add(new AudioItem(R.raw.yuuhei_satelite));
    audioItemAdapter = new AudioItemAdapter(audioItems,title);
    rv.setAdapter(audioItemAdapter);
    
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem menuItem)
  {
    if(dt.onOptionsItemSelected(menuItem))
      return true;
    return super.onOptionsItemSelected(menuItem);
  }
  
  @Override
  protected void onPause() {
    super.onPause();
    audioItemAdapter.stopPlayer();
  }
  
  private class AudioItemAdapter extends RecyclerView.Adapter<AudioItemAdapter.AudioItemsViewHolder> implements Handler.Callback {
    
    private static final int MSG_UPDATE_SEEK_BAR = 1845;
    
    private MediaPlayer mediaPlayer;
    
    private Handler uiUpdateHandler;
    
    private List<AudioItem> audioItems;
    private int playingPosition;
    private AudioItemsViewHolder playingHolder;
    private String[] msc_title;
    
    AudioItemAdapter(List<AudioItem> audioItems,String [] title) {
      this.audioItems = audioItems;
      this.msc_title = title;
      this.playingPosition = -1;
      uiUpdateHandler = new Handler(this);
    }
    
    @Override
    public AudioItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new AudioItemsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, parent, false));
    }
    
    @Override
    public void onBindViewHolder(AudioItemsViewHolder holder, int position) {
       String id = msc_title[position] ;
      if (position == playingPosition) {
        playingHolder = holder;
        // this view holder corresponds to the currently playing audio cell
        // update its view to show playing progress
        updatePlayingView();
      } else {
        // and this one corresponds to non playing
        updateNonPlayingView(holder);
      }
      holder.tvIndex.setText(String.format(Locale.US, "%d", position));
      holder.music_title.setText(id);
    }
    
    @Override
    public int getItemCount() {
      return audioItems.size();
    }
    
    @Override
    public void onViewRecycled(AudioItemsViewHolder holder) {
      super.onViewRecycled(holder);
      if (playingPosition == holder.getAdapterPosition()) {
        // view holder displaying playing audio cell is being recycled
        // change its state to non-playing
        updateNonPlayingView(playingHolder);
        playingHolder = null;
      }
    }
    
    /**
     * Changes the view to non playing state
     * - icon is changed to play arrow
     * - seek bar disabled
     * - remove seek bar updater, if needed
     *
     * @param holder ViewHolder whose state is to be chagned to non playing
     */
    private void updateNonPlayingView(AudioItemsViewHolder holder) {
      if (holder == playingHolder) {
        uiUpdateHandler.removeMessages(MSG_UPDATE_SEEK_BAR);
      }
      holder.sbProgress.setEnabled(false);
      holder.sbProgress.setProgress(0);
      holder.ivPlayPause.setImageResource(R.drawable.ic_play_arrow);
    }
    
    /**
     * Changes the view to playing state
     * - icon is changed to pause
     * - seek bar enabled
     * - start seek bar updater, if needed
     */
    private void updatePlayingView() {
      playingHolder.sbProgress.setMax(mediaPlayer.getDuration());
      playingHolder.sbProgress.setProgress(mediaPlayer.getCurrentPosition());
      playingHolder.sbProgress.setEnabled(true);
      if (mediaPlayer.isPlaying()) {
        uiUpdateHandler.sendEmptyMessageDelayed(MSG_UPDATE_SEEK_BAR, 100);
        playingHolder.ivPlayPause.setImageResource(R.drawable.ic_pause);
      } else {
        uiUpdateHandler.removeMessages(MSG_UPDATE_SEEK_BAR);
        playingHolder.ivPlayPause.setImageResource(R.drawable.ic_play_arrow);
      }
    }
    
    void stopPlayer() {
      if (null != mediaPlayer) {
        releaseMediaPlayer();
      }
    }
    
    @Override
    public boolean handleMessage(Message msg) {
      switch (msg.what) {
        case MSG_UPDATE_SEEK_BAR: {
          playingHolder.sbProgress.setProgress(mediaPlayer.getCurrentPosition());
          uiUpdateHandler.sendEmptyMessageDelayed(MSG_UPDATE_SEEK_BAR, 100);
          return true;
        }
      }
      return false;
    }
    
    // Interaction listeners e.g. click, seekBarChange etc are handled in the view holder itself. This eliminates
    // need for anonymous allocations.
    class AudioItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
      SeekBar sbProgress;
      ImageView ivPlayPause;
      TextView tvIndex,music_title;
      
      AudioItemsViewHolder(View itemView) {
        super(itemView);
        ivPlayPause = itemView.findViewById(R.id.ivPlayPause);
        ivPlayPause.setOnClickListener(this);
        sbProgress = itemView.findViewById(R.id.sbProgress);
        sbProgress.setOnSeekBarChangeListener(this);
        tvIndex = itemView.findViewById(R.id.tvIndex);
        music_title = itemView.findViewById(R.id.txtmusictitle);
      }
      
      @Override
      public void onClick(View v) {
        if (getAdapterPosition() == playingPosition) {
          // toggle between play/pause of audio
          if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
          } else {
            mediaPlayer.start();
          }
        } else {
          // start another audio playback
          playingPosition = getAdapterPosition();
          if (mediaPlayer != null) {
            if (null != playingHolder) {
              updateNonPlayingView(playingHolder);
            }
            mediaPlayer.release();
          }
          playingHolder = this;
          startMediaPlayer(audioItems.get(playingPosition).audioResId);
        }
        updatePlayingView();
      }
      
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
          mediaPlayer.seekTo(progress);
        }
      }
      
      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {
      }
      
      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
      }
    }
    
    private void startMediaPlayer(int audioResId) {
      mediaPlayer = MediaPlayer.create(getApplicationContext(), audioResId);
      mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
          releaseMediaPlayer();
        }
      });
      mediaPlayer.start();
    }
    
    private void releaseMediaPlayer() {
      if (null != playingHolder) {
        updateNonPlayingView(playingHolder);
      }
      mediaPlayer.release();
      mediaPlayer = null;
      playingPosition = -1;
    }
    
  }
  
}
