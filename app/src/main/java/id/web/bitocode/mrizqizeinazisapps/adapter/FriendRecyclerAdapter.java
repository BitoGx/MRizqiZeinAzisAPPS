package id.web.bitocode.mrizqizeinazisapps.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import id.web.bitocode.mrizqizeinazisapps.R;

public class FriendRecyclerAdapter extends RecyclerView.Adapter<FriendRecyclerAdapter.ImageViewHolder>
{
  
  private int[] img_friend;
  private String [] friend_name;
  
  public FriendRecyclerAdapter(int[] img_friend, String[] friend_name)
  {
    this.img_friend = img_friend;
    this.friend_name = friend_name;
  }
  
  @NonNull
  @Override
  public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
  {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.friend_layout,viewGroup, false);
    ImageViewHolder imageViewHolder = new ImageViewHolder(view);
  
    return imageViewHolder;
  }
  
  @Override
  public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i)
  {
    int id = img_friend[i];
    String idn = friend_name[i];
    imageViewHolder.friend.setImageResource(id);
    imageViewHolder.friend_name.setText(idn);
  }
  
  @Override
  public int getItemCount()
  {
    return img_friend.length;
  }
  
  public class ImageViewHolder extends RecyclerView.ViewHolder
  {
    ImageView friend;
    TextView friend_name;
  
    public ImageViewHolder(@NonNull View itemView)
    {
      super(itemView);
      friend = itemView.findViewById(R.id.friend);
      friend_name = itemView.findViewById(R.id.friend_name);
    }
  }
}
