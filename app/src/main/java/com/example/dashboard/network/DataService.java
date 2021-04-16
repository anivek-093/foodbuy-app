package com.example.dashboard.network;

import com.example.dashboard.model.Product;
import com.example.dashboard.model.ProductDescription;
import com.example.dashboard.model.ProductsResponse;
import com.example.dashboard.model.User;
import com.example.dashboard.model.UserResponseObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DataService {
    @GET("/users")
    Call<UserResponseObject> getUserWithEmail(@Query("email") String email);

    @Headers("Content-Type: application/json")
    @POST("/users")
    Call<UserResponseObject> postNewUser(
            @Query("userType") String userType,
            @Body User user
    );

    @Headers("Content-Type: application/json")
    @POST("/products")
    Call<Product> postOneProduct(
            @Body Product product
    );

    @Headers("Content-Type: application/json")
    @GET("/products")
    Call<ProductsResponse> getAllProducts();

    @Headers("Content-Type: application/json")
    @GET("/products")
    Call<ProductsResponse> getProductByName(
            @Query("productName") String productName,
            @Query("userType") String userType
    );
}
