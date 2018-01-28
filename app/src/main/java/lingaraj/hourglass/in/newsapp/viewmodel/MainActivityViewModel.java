package lingaraj.hourglass.in.newsapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import lingaraj.hourglass.in.newsapp.RetrofitCloudAdapter;
import lingaraj.hourglass.in.newsapp.restservice.GuardianNetworkCalls;
import lingaraj.hourglass.in.newsapp.restservice.models.responseworldnews.GuardianNewsResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by lingaraj on 1/27/18.
 *  // In MVVM model of Android  your viewModel job is to share the network call data with the View when requested, if there is no network call made
 *   before it will make an network call and share the relevant data, else it will share the last loaded data.
 *   Main use of this class is to make sure that whenever you make an network call and immediately rotate your activity/Fragment, the network call have to be made again,
 *   but in this case this will save to the viewModel.
 */

public class MainActivityViewModel extends AndroidViewModel
{
   private final  String TAG ="MAINACTVIEWMODEL";
   private MutableLiveData<GuardianNewsResponse> guardian_world_news_response;

  public MainActivityViewModel(@NonNull Application application) {
    super(application);
  }

  public LiveData<GuardianNewsResponse> getLiveData(){
     if (guardian_world_news_response == null)
     {
       guardian_world_news_response = new MutableLiveData<GuardianNewsResponse>();
       Log.d(TAG,"Network Call executed");
       getWorldNews();
     }
     else {
       Log.d(TAG,"Network Response already exist in Activity Life cycle model using it");

     }
     return guardian_world_news_response;

   }

  private void getWorldNews() {
    final  String guardian_api_key = "61817285-0d28-4247-ae75-20352cf16219";
     RestAdapter restAdapter = RetrofitCloudAdapter.getNetworkAdapter();
     GuardianNetworkCalls service = restAdapter.create(GuardianNetworkCalls.class);
     service.getWorldNews(guardian_api_key, new Callback<GuardianNewsResponse>() {
       @Override public void success(GuardianNewsResponse response_callback, Response response) {
         if (!response_callback.getResponse().getStatus().isEmpty()){
           guardian_world_news_response.postValue(response_callback);
         }

       }

       @Override public void failure(RetrofitError error) {
                guardian_world_news_response = null;
                 Log.d(TAG,error.toString());

       }
     });
  }
}
