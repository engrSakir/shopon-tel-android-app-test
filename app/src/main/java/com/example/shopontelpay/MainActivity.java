package com.example.shopontelpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        if (isNetworkAvailable(this)) {
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    Intent intent=new Intent(MainActivity.this,Browser.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "NO INTERNET", Toast.LENGTH_SHORT);
            toast.show();
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    Intent intent=new Intent(MainActivity.this,NoInternet.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000);
        }
    }
    // Check Internet Connection
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}