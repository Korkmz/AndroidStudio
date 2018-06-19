package com.example.tr.denemematuyg;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    ImageView imageView;
    Random random;
    TextView sayi1,sayi2,zamanla,skore;
    int number1, number2,sonuc,tahmin;
    EditText editText2;
    int rekor;
    public CountDownTimer counr;
    SharedPreferences shrp1;
    int degismez;
    int dusun;
    Intent int2;
    int deger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tanım();
        shrp1=this.getSharedPreferences("com.example.tr.denemematuyg", Context.MODE_PRIVATE);  //hafizaya kayıt yapar


        imageView=(ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(MainActivity.selectedimages);  //Resimi aldık

        sayiUret();
        ekranYaz();
        zamankontrol();

        int2=getIntent();
        deger=Integer.parseInt(int2.getExtras().getString("sayi"));

        degismez=shrp1.getInt("rekori",0);
        System.out.println("REKER:"+degismez);
        skore.setText("Score :"+ degismez);



    }
    public void ekranYaz(){
        sayi1.setText(String.valueOf(number1));    //RASGELE TÜRETİP YAZDIK
        sayi2.setText(String.valueOf(number2));
    }
    public void sayiUret(){    ///RASGELE SAYİ URETİP
        random=new Random();
        number1=random.nextInt(10)+1;
        number2=random.nextInt(10)+1;

    }       ///RASGELE SAYİ URETİR
    public void tanım(){
        sayi1=(TextView) findViewById(R.id.sayi1);
        sayi2=(TextView) findViewById(R.id.sayi2);
        zamanla=(TextView) findViewById(R.id.zamanla);
        skore=(TextView) findViewById(R.id.skore);
        editText2=(EditText) findViewById(R.id.editText2);
    }
    public  void rekorartir(){
        rekor=shrp1.getInt("rekori",0);
        rekor=rekor+10;
        shrp1.edit().putInt("rekori",rekor).apply();

        skore.setText("Score :"+ rekor);

    }
    public void rekorazalt(){
       rekor=shrp1.getInt("rekori",0);
        rekor=rekor-10;
        shrp1.edit().putInt("rekori",rekor).apply();
        skore.setText("Score :"+rekor);
    }

    public void kontol1(View view){
        durumbak();
        tahmin=Integer.parseInt(editText2.getText().toString());   //TAHMİNİ ALDIK
        Toast.makeText(this,"tahmin"+tahmin,Toast.LENGTH_SHORT);

        if (tahmin == sonuc){

            sayiUret();
            ekranYaz();   //RASGELE TÜRETİP YAZDIK


             counr.cancel();    //Sayaci sifirla
             counr.start();
            rekorartir();
        }else{
            rekorazalt();
        }
    }
    public void durumbak(){
        if(deger ==0 ) {
            sonuc=number1+number2;
        }else if(deger == 1){
            sonuc=number1-number2;
        }else if(deger ==2 ) {
            sonuc=number1*number2;
        }else if(deger == 3){
            sonuc=number1/number2;
        }
    }
    public void zamankontrol(){

        counr=new CountDownTimer(31000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                zamanla.setText("Süre :"+ millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {   //SÜRE BİİTTİĞİ ZAMAN
                rekorazalt();
                durumbak();

                zamanla.setText("Süre :0");
                AlertDialog.Builder alert=new AlertDialog.Builder(Main2Activity.this);
                alert.setTitle("Zaman Bitti !");
                alert.setMessage("Cevap : "+ sonuc);


                alert.setPositiveButton("Cik", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent geri=new Intent(Main2Activity.this, MainActivity.class);
                        startActivity(geri);
                    }
                });

                alert.setNegativeButton("Devam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        sayiUret();
                        ekranYaz();
                        zamankontrol();

                    }
                });
                alert.show();

            }
        }.start();

    }

    public void ciktop(View view){
        AlertDialog.Builder alertoplam=new AlertDialog.Builder(Main2Activity.this);
        alertoplam.setTitle("CIKIS");
        alertoplam.setMessage("Cikmak istediğinize emin misiniz ?");
        alertoplam.setNegativeButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                Intent cik=new Intent(Main2Activity.this, MainActivity.class);
                startActivity(cik);
                counr.cancel();
            }
        });
        alertoplam.setPositiveButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertoplam.show();

    }
}
