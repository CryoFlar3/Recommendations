package org.computermentors.sample.googleplayservices.api;

import org.computermentors.sample.googleplayservices.model.ActiveListings;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface Api {

    @GET("/listings/active")
    void activeListings(@Query("includes") String includes, Callback<ActiveListings> callback);
}
