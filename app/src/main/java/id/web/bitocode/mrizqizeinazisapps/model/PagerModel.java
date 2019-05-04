package id.web.bitocode.mrizqizeinazisapps.model;

import id.web.bitocode.mrizqizeinazisapps.R;

public enum PagerModel
{
  PAGE1(R.string.Slide1, R.layout.slider_activity_1),
  PAGE2(R.string.Slide2, R.layout.slider_activity_2),
  PAGE3(R.string.Slide3, R.layout.slider_activity_3);
  
  private int mTitleResId;
  private int mLayoutResId;
  
  PagerModel(int titleResId, int layoutResId) {
    mTitleResId = titleResId;
    mLayoutResId = layoutResId;
  }
  
  public int getTitleResId() {
    return mTitleResId;
  }
  
  public int getLayoutResId() {
    return mLayoutResId;
  }
}
