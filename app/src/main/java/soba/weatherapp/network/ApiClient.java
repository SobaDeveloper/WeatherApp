package soba.weatherapp.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import soba.weatherapp.utils.Constants;

/**
 * Created by SobaDeveloper on 5/15/16.
 */
public class ApiClient {

    private static ApiInterface mApiInterface;
    private static ApiClient sInstance;

    private ApiClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        final Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(Constants.API_BASE_URL)
                .build();
        mApiInterface = retrofit.create(ApiInterface.class);
    }

    public static void init(){
        if(sInstance == null){
            sInstance = new ApiClient();
        }
    }

    public static ApiInterface getApi(){
        return mApiInterface;
    }
}
