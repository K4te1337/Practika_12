package com.example.myapplication;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface PlaceholderAPI {
    @GET("posts")
    Call<List<PlaceholderPost>> getPosts();
    @POST("posts")
    Call<Void> postComment(@Body PlaceholderPost comm);
    @PUT("posts/{id}")
    Call<PlaceholderPost> updateComment(@Path("id") int postId, @Body PlaceholderPost comm);
}
