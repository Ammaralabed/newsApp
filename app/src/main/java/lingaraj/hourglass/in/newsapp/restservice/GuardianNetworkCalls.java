package lingaraj.hourglass.in.newsapp.restservice;

import lingaraj.hourglass.in.newsapp.restservice.models.responseworldnews.GuardianNewsResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by lingaraj on 1/27/18.
 */

public interface GuardianNetworkCalls {
  @GET("/world")
  public void getWorldNews(@Query("api-key") String query, Callback<GuardianNewsResponse> worldNewsResponseCallback);

}
