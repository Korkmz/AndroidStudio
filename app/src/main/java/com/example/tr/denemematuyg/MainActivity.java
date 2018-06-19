package com.example.tr.denemematuyg;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static Bitmap selectedimages;
    ArrayList<Bitmap> listImage;

    Intent intent1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent1=new Intent(this, Main2Activity.class);
        Bitmap adds= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.add);
        Bitmap sil=BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.sil);
        Bitmap carp=BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.carp);

        Bitmap bol=BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.bol);
        listImage=new ArrayList<>();
        listImage.add(adds);
        listImage.add(sil);
        listImage.add(carp);
        listImage.add(bol);
    }
    public void topla(View view){
        intent1.putExtra("sayi", "0");
        selectedimages=listImage.get(0);
        startActivity(intent1);

    }
    public void cikar(View view){
        intent1.putExtra("sayi", "1");
        selectedimages=listImage.get(1);
        startActivity(intent1);

    }
    public void carp(View view){
        intent1.putExtra("sayi", "2");
        selectedimages=listImage.get(2);
        startActivity(intent1);

    }
    public void bolme(View view){
        intent1.putExtra("sayi", "3");

        selectedimages=listImage.get(3);
        startActivity(intent1);

    }
}
