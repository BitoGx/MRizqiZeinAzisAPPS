package id.web.bitocode.mrizqizeinazisapps.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import java.util.List;

import id.web.bitocode.mrizqizeinazisapps.R;
import id.web.bitocode.mrizqizeinazisapps.model.YoutubeModel;

public class VideoRecyclerAdapter extends RecyclerView.Adapter<VideoRecyclerAdapter.VideoviewHolder>
{
  
  private List<YoutubeModel> youtubeVideoList;
  
  public VideoRecyclerAdapter(List<YoutubeModel> youtubeVideoList)
  {
    this.youtubeVideoList = youtubeVideoList;
  }
  
  @NonNull
  @Override
  public VideoviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
  {
    View view = LayoutInflater.from( viewGroup.getContext()).inflate(R.layout.video_layout, viewGroup, false);
  
    return new VideoviewHolder(view);
  }
  
  @Override
  public void onBindViewHolder(@NonNull VideoviewHolder videoviewHolder, int i)
  {
    videoviewHolder.videoWeb.loadData( youtubeVideoList.get(i).getVideoUrl(), "text/html" , "utf-8" );
  }
  
  @Override
  public int getItemCount()
  {
    return youtubeVideoList.size();
  }
  
  
  public class VideoviewHolder extends RecyclerView.ViewHolder
  {
    WebView videoWeb;
  
    public VideoviewHolder(View itemView)
    {
      super(itemView);
    
      videoWeb = itemView.findViewById(R.id.videoWebView);
    
      videoWeb.getSettings().setJavaScriptEnabled(true);
      videoWeb.setWebChromeClient(new WebChromeClient()
      {
      
      });
    }
  }
}
