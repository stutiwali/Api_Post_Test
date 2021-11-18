package com.example.api_post_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Api api;

    EditText phone,country,imei;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(),"Heloooo",Toast.LENGTH_SHORT).show();

        initialize();
        onClickActivity();
    }

    private void onClickActivity() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phone.getText().toString().isEmpty() && country.getText().toString().isEmpty() && imei.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }

                postData( phone.getText().toString(),country.getText().toString(),imei.getText().toString());

            }
        });
    }

    private void postData(String ph, String cnt, String im) {

        Logins logins=new Logins(ph,cnt,im);

        Toast.makeText(getApplicationContext(),"ph="+ph,Toast.LENGTH_SHORT).show();

       api=RetrofitInstance.getRetrofit().create(Api.class);
        Call<Logins> call = api.createPost(logins);

        call.enqueue(new Callback<Logins>() {
            @Override
            public void onResponse(Call<Logins> call, Response<Logins> response) {

                Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Logins> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error found :"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }


    private void initialize() {

        phone=(EditText) findViewById(R.id.phone);
        country=(EditText) findViewById(R.id.country);
        imei=(EditText) findViewById(R.id.imei);
    }
}