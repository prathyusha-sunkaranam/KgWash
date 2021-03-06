package com.mansopresk.mansopresk01.kgwash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mansopresk.mansopresk01.kgwash.Maps.MapsActivity;
import com.mansopresk.mansopresk01.kgwash.Navigation.NavigationMainActivity;
import com.mansopresk.mansopresk01.kgwash.Order.PriceOrderActivity;

import pl.droidsonroids.gif.GifImageView;

public class DeliveryActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    GifImageView maps;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        maps = findViewById(R.id.userlocation1);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DeliveryActivity.this, MapsActivity.class);
                startActivity(it);
            }
        });
        sharedpreferences = getSharedPreferences("admindetails", MODE_PRIVATE);
        String uname1 = sharedpreferences.getString("deliveryemail", null);
        if (sharedpreferences != null) {
            if (uname1 != null || uname1 == "") {
            } else {
                Intent it = new Intent(DeliveryActivity.this, MainActivity.class);
                startActivity(it);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delivery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itemorder) {
            Intent i = new Intent(DeliveryActivity.this, PriceOrderActivity.class);
            startActivity(i);
            return true;
        } else if (id == R.id.itemlog) {
            Intent it = new Intent(DeliveryActivity.this, NavigationMainActivity.class);
            getApplicationContext().getSharedPreferences("admindetails", 0).edit().clear().apply();
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
