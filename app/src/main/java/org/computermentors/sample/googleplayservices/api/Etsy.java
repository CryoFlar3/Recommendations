package org.computermentors.sample.googleplayservices.api;

import org.computermentors.sample.googleplayservices.model.ActiveListings;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class Etsy {
    public static final String API_KEY = "oa8p7pjxjn6oi0j0j2hn89wj";

    private static RequestInterceptor getInterceptor(){
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addEncodedQueryParam("api_key", API_KEY);
            }
        };
    }

    private static Api getApi(){
        return new RestAdapter.Builder()
                .setEndpoint("https://openapi.etsy.com/v2")
                .setRequestInterceptor(getInterceptor())
                .build()
                .create(Api.class);

    }

    public static void getActiveListings(Callback<ActiveListings> callback){
        getApi().activeListings("Images,Shop", callback);
    }
}
