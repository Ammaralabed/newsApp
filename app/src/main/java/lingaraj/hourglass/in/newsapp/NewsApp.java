package lingaraj.hourglass.in.newsapp;

import android.app.Application;
import android.util.Log;
import com.squareup.okhttp.OkHttpClient;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;

/**
 * Created by lingaraj on 1/27/18.
 */

public class NewsApp extends Application {
  private static final String TAG = "NEWSAPP";
  private final String rest_server_url = "http://content.guardianapis.com";
  private RestAdapter cloudAdapter;

  @Override
  public void onCreate() {
    super.onCreate();
  }

  private void setCloudAdapter()
  {
    Log.d(TAG, "set cloud adapter");
    OkHttpClient okHttpClient = new OkHttpClient();
    //OkHttpClient okHttpClient = new OkHttpClient();
    cloudAdapter = new RestAdapter.Builder()
        .setClient(new OkClient(okHttpClient))
        .setEndpoint(rest_server_url)
        .setLogLevel(RestAdapter.LogLevel.FULL).setLog(new AndroidLog("CLOUD_API_REQUESTS"))
        .build();
    return;

  }

  private RestAdapter getCloudAdapter(){
    if (cloudAdapter==null){
      setCloudAdapter();
    }
    return cloudAdapter;

  }



}
