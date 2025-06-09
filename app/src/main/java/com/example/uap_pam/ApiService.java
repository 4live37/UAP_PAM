package com.example.uap_pam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @GET("plant")
    Call<List<Plant>> getPlants();

    @POST("plant")
    @FormUrlEncoded
    Call<Plant> addPlant(
            @Field("plant_name") String name,
            @Field("price") String price,
            @Field("description") String desc
    );

    @GET("plant/{id}")
    Call<Plant> getPlant(@Path("id") int id);

    @PUT("/plant/{id}")
    Call<Plant> updatePlant(@Path("id") int id, @Body Plant plant);

    @FormUrlEncoded
    Call<Plant> updatePlant(
            @Path("id") int id,
            @Field("plant_name") String name,
            @Field("price") String price,
            @Field("description") String desc
    );

    @DELETE("plant/{id}")
    Call<Void> deletePlant(@Path("id") int id);

    @POST("/plant")
    Call<Plant> createPlant(@Body Plant plant);
}