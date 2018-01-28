package lingaraj.hourglass.in.newsapp.view.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.google.gson.Gson;
import lingaraj.hourglass.in.newsapp.R;
import lingaraj.hourglass.in.newsapp.databinding.ActivityMainBinding;
import lingaraj.hourglass.in.newsapp.restservice.models.responseworldnews.GuardianNewsResponse;
import lingaraj.hourglass.in.newsapp.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MAINACT";
  private GuardianNewsResponse world_news_response;
  private ActivityMainBinding activity_binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    activity_binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
    MainActivityViewModel view_model = ViewModelProviders.of(this).get(MainActivityViewModel.class);
    view_model.getLiveData().observeForever(new Observer<GuardianNewsResponse>() {
      @Override
      public void onChanged(@Nullable GuardianNewsResponse guardianNewsResponse) {
         world_news_response = guardianNewsResponse;
         setData();
      }
    });
  }

  private void setData() {
    if (world_news_response!=null && !world_news_response.getResponse().getStatus().isEmpty()) {
        String json_string = new Gson().toJson(world_news_response.getResponse().getResults());
        activity_binding.textView.setText(json_string);
        Log.d(TAG,json_string);
    }
    else {
      Log.d(TAG,"Empty response from Guardian");
    }


  }


}
