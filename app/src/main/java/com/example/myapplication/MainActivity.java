package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
public class MainActivity extends AppCompatActivity {
    public final String URL_API = "https://jsonplaceholder.typicode.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getButton = findViewById(R.id.button);
        Button postButton = findViewById(R.id.button2);
        Button putButton = findViewById(R.id.button3);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromApi();
            }
        });
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDataToApi();
            }
        });
        putButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putDataToApi();
            }
        });
    }
    private void getDataFromApi() {
        Retrofit retrofit = RetrofitFactory.getRetrofit(URL_API);
        PlaceholderAPI placeholderAPI = retrofit.create(PlaceholderAPI.class);
        Call<List<PlaceholderPost>> call = placeholderAPI.getPosts();
        call.enqueue(new Callback<List<PlaceholderPost>>() {
            @Override
            public void onResponse(Call<List<PlaceholderPost>> call, Response<List<PlaceholderPost>> response) {
                if (response.isSuccessful()) {
                    List<PlaceholderPost> comm = response.body();
                    Log.d("Success", comm.get(0).getBody().toString());
                    TextView text = findViewById(R.id.text);
                    text.setText(comm.get(0).getBody().toString());
                } else {
                    Log.d("Fail", "Не удалось получить посты((((");
                }
            }
            @Override
            public void onFailure(Call<List<PlaceholderPost>> call, Throwable t) {
                Log.e("Error", "Ошибка!!");
            }
        });
    }
    private void postDataToApi() {
        PlaceholderPost newPost = new PlaceholderPost();
        newPost.setTitle("Jack");
        newPost.setPostId(101);
        newPost.setId(501);
        newPost.setBody("Этот пост оставили мы");
        Retrofit retrofit = RetrofitFactory.getRetrofit(URL_API);
        PlaceholderAPI placeholderAPI = retrofit.create(PlaceholderAPI.class);
        Call<Void> createPostCall = placeholderAPI.postComment(newPost);
        createPostCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("Success", "Наш пост успешно запостился!!!");
                    TextView text = findViewById(R.id.text);
                    text.setText(newPost.getBody());
                } else {
                    Log.d("Fail", "Наш пост не смог запоститься((((");
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Error", "Ошибка!!");
            }
        });
    }
    private void putDataToApi() {
        PlaceholderPost updatePost = new PlaceholderPost();
        updatePost.setId(1);
        updatePost.setBody("Этот пост обновили мы");
        Retrofit retrofit = RetrofitFactory.getRetrofit(URL_API);
        PlaceholderAPI placeholderAPI = retrofit.create(PlaceholderAPI.class);
        Call<PlaceholderPost> createPostCall = placeholderAPI.updateComment(1, updatePost);
        createPostCall.enqueue(new Callback<PlaceholderPost>() {
            @Override
            public void onResponse(Call<PlaceholderPost> call, Response<PlaceholderPost> response) {
                if (response.isSuccessful()) {
                    Log.d("Success", "Наш пост успешно обновился!!!");
                    TextView text = findViewById(R.id.text);
                    text.setText(updatePost.getBody());
                } else {
                    Log.d("Fail", "Наш пост не смог обновиться((((");
                }
            }
            @Override
            public void onFailure(Call<PlaceholderPost> call, Throwable t) {
                Log.e("Error", "Ошибка!!");
            }
        });
    }
}