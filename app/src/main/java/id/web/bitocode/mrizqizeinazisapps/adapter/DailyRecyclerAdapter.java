package id.web.bitocode.mrizqizeinazisapps.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.web.bitocode.mrizqizeinazisapps.R;
import id.web.bitocode.mrizqizeinazisapps.model.DailyModel;

public class DailyRecyclerAdapter extends RecyclerView.Adapter<DailyRecyclerAdapter.ViewHolder>
{
  private List<DailyModel> dailyModels;
  private OnCallbackListener listener;
  
  public  DailyRecyclerAdapter(List<DailyModel> dailyModels)
  {
    this.dailyModels = dailyModels;
  }
  
  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
  {
    return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view_daily,viewGroup ,false));
  }
  
  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
  {
    DailyModel dm = dailyModels.get(i);
    viewHolder.textViewNama.setText(dm.getNamaActivity());
    viewHolder.textViewGenre.setText(dm.getWaktuActivity());
  }
  
  @Override
  public int getItemCount()
  {
    return dailyModels.size();
  }
  
  public void setOnCallbackListener(OnCallbackListener listener)
  {
    this.listener = listener;
  }
  
  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
  {
    TextView textViewNama;
    TextView textViewGenre;
    
    public ViewHolder(View itemView)
    {
      super(itemView);
      itemView.setOnClickListener(this);
      
      textViewNama = itemView.findViewById(R.id.nama);
      textViewGenre = itemView.findViewById(R.id.genre);
    }
    
    @Override
    public void onClick(View v)
    {
      if (listener != null)
      {
        listener.onClick(dailyModels.get(getAdapterPosition()));
      }
    }
  }
  
  public interface OnCallbackListener
  {
    void onClick(DailyModel user);
  }
  
}
