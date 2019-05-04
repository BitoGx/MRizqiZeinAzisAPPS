package id.web.bitocode.mrizqizeinazisapps.adapter;

import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import id.web.bitocode.mrizqizeinazisapps.R;

public class GalleryRecyclerAdapter extends RecyclerView.Adapter<GalleryRecyclerAdapter.ImageViewHolder>
{
  
  private int[] images;
  private String[] title;
  public GalleryRecyclerAdapter(int[] images, String[] title)
  {
    this.images = images;
    this.title = title;
  }
  
  @NonNull
  @Override
  public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
  {
    
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.album_layout,viewGroup, false);
    ImageViewHolder imageViewHolder = new ImageViewHolder(view);
    
    
    return imageViewHolder;
  }
  
  @Override
  public void onBindViewHolder(@NonNull ImageViewHolder viewHolder, int i)
  {
    int id = images[i];
    String idn = title[i];
    viewHolder.album.setImageResource(id);
    viewHolder.album_title.setText(idn);
  }
  
  @Override
  public int getItemCount()
  {
    return images.length;
  }
  
  public static  class ImageViewHolder extends RecyclerView.ViewHolder
  {
    
    ImageView album;
    TextView album_title;
  
    public ImageViewHolder(@NonNull View itemView)
    {
      super(itemView);
      album = itemView.findViewById(R.id.album);
      album_title = itemView.findViewById(R.id.album_title);
    }
  }
  
}
