package lingaraj.hourglass.in.newsapp;

import com.squareup.okhttp.OkHttpClient;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;

/**
 * Created by lingaraj on 1/28/18.
 */

public class RetrofitCloudAdapter {

  public static RestAdapter getNetworkAdapter(){
     final String rest_server_url = "http://content.guardianapis.com";
    OkHttpClient okHttpClient = new OkHttpClient();
    return new RestAdapter.Builder()
        .setClient(new OkClient(okHttpClient))
        .setEndpoint(rest_server_url)
        .setLogLevel(RestAdapter.LogLevel.FULL).setLog(new AndroidLog("CLOUD_API_REQUESTS"))
        .build();
  }



}
