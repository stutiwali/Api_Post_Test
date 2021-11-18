package com.example.api_post_test;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("")
    Call<Logins> createPost(@Body Logins logins);
}
