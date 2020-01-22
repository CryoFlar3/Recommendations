package org.computermentors.sample.googleplayservices.api;

import org.computermentors.sample.googleplayservices.model.ActiveListings;

import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("/listing/active")
    void activeListings(@Query("includes") String includes, Callback<ActiveListings> callback);
}
