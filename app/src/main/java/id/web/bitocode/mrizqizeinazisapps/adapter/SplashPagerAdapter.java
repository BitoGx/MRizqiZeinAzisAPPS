package id.web.bitocode.mrizqizeinazisapps.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.web.bitocode.mrizqizeinazisapps.model.PagerModel;

public class SplashPagerAdapter extends PagerAdapter
{
  private Context mContext;
  
  public SplashPagerAdapter(Context context)
  {
    mContext = context;
  }
  
  @Override
  public Object instantiateItem(ViewGroup collection, int position)
  {
    PagerModel modelObject = PagerModel.values()[position];
    LayoutInflater inflater = LayoutInflater.from(mContext);
    ViewGroup layout = (ViewGroup) inflater.inflate(modelObject.getLayoutResId(), collection, false);
    collection.addView(layout);
    return layout;
  }
  
  @Override
  public void destroyItem(ViewGroup collection, int position, Object view) {
    collection.removeView((View) view);
  }
  
  @Override
  public int getCount() {
    return PagerModel.values().length;
  }
  
  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }
  
  @Override
  public CharSequence getPageTitle(int position) {
    PagerModel customPagerEnum = PagerModel.values()[position];
    return mContext.getString(customPagerEnum.getTitleResId());
  }
  
}
