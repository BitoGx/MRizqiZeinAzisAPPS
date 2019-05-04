package id.web.bitocode.mrizqizeinazisapps.model;

public class YoutubeModel
{
  private String url;
  
  public String getVideoUrl()
  {
    return url;
  }
  
  public void setVideoUrl(String videoUrl)
  {
    this.url = videoUrl;
  }
  
  public YoutubeModel(String videoUrl)
  {
    this.url = videoUrl;
  }
}
