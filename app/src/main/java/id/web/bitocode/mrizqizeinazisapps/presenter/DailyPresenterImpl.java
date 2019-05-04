package id.web.bitocode.mrizqizeinazisapps.presenter;

import java.util.ArrayList;
import java.util.List;

import id.web.bitocode.mrizqizeinazisapps.model.DailyModel;
import id.web.bitocode.mrizqizeinazisapps.view.DailyView;

public class DailyPresenterImpl implements DailyPresenter
{
  
  private DailyView dailyView;
  
  private List<DailyModel> dailyModels = new ArrayList<>();
  private int no = 4;
  
  public DailyPresenterImpl(DailyView dailyView)
  {
    this.dailyView = dailyView;
    setData();
  }
  
  private void setData()
  {
    DailyModel dm = new DailyModel();
    dm.setId(1);
    dm.setNamaActivity("Tidur");
    dm.setWaktuActivity("09:00 PM/03:00 AM - 04:00 AM/05:00 AM");
    dailyModels.add(dm);
  
    DailyModel dm1 = new DailyModel();
    dm1.setId(2);
    dm1.setNamaActivity("Persiapan ( Mandi/Males-Malesan/Baca Buku/Duduk di kasur");
    dm1.setWaktuActivity("04:00 AM/05:00 AM - 06:00 AM");
    dailyModels.add(dm1);
  
    DailyModel dm2 = new DailyModel();
    dm2.setId(3);
    dm2.setNamaActivity("OTW Kuliah");
    dm2.setWaktuActivity("06:00 AM - 06:45 AM");
    dailyModels.add(dm2);
  
    DailyModel dm3 = new DailyModel();
    dm3.setId(4);
    dm3.setNamaActivity("Kuliah");
    dm3.setWaktuActivity("07:00 AM - 05:30 PM");
    dailyModels.add(dm3);
  
    DailyModel dm4 = new DailyModel();
    dm4.setId(5);
    dm4.setNamaActivity("OTW Rumah");
    dm4.setWaktuActivity("05:30 PM - 07:00 PM");
    dailyModels.add(dm4);
  
    DailyModel dm5 = new DailyModel();
    dm5.setId(6);
    dm5.setNamaActivity("Istirahat ( Tidur, Discord, Ngerjain Tugas, Main )");
    dm5.setWaktuActivity("07:00 PM - 09:00 PM/03:00 AM");
    dailyModels.add(dm5);
  }
  
  @Override
  public void load()
  {
    dailyView.onLoad(dailyModels);
  }
}
