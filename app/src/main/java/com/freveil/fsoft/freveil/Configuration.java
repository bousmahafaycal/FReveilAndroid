package com.freveil.fsoft.freveil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Configuration extends AppCompatActivity {
    RadioGroup radiogroup;
    RadioGroup radiogroup2;
    SharedPreferences settings;
    String chaine = "";
    boolean afficher = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        radiogroup = (RadioGroup)findViewById(R.id.radiogroup);
        radiogroup.setOnCheckedChangeListener(first_radio_listener);

        radiogroup2 = (RadioGroup)findViewById(R.id.radiogroup2);
        radiogroup2.setOnCheckedChangeListener(first_radio_listener2);

        Button bouton = null;
        bouton = (Button)findViewById(R.id.button);
        bouton.setOnClickListener(  listenerbouton  );

        SharedPreferences settings;
        settings = getSharedPreferences("FPref", 0);
        String adresseIp = settings.getString("adresseIp", "");
        String port = settings.getString("port", "");
        Communication.changeServeur(adresseIp,port);

        Thread ta;


        ta =new Thread(new Runnable() {
            @Override
            public void run() {



                while (true){
                    Communication.envoieCommande(Communication.createCommande("getPresence",""));
                    chaine = Communication.getResponse();
                    Communication.envoieCommande(Communication.createCommande("getBouton",""));
                    runOnUiThread(new Runnable() {

                        @Override

                        public void run() {

                                afficher = false;
                                if (chaine.equals("9"))
                                    radiogroup.check(R.id.radioButton);
                                else
                                    radiogroup.check(R.id.radioButton2);


                                if (Communication.getResponse().equals("7"))
                                    radiogroup2.check(R.id.radioButton3);
                                else
                                    radiogroup2.check(R.id.radioButton4);


                                afficher  = true;
                        }});

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        ta.start();

    }




    private View.OnClickListener listenerbouton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (Configuration.this , MainActivity.class);
            startActivity(intent) ;




        }



    };

    RadioGroup.OnCheckedChangeListener first_radio_listener = new RadioGroup.OnCheckedChangeListener (){
        public void onCheckedChanged(RadioGroup ra, int v) {
            boolean part1 = true;
            Toast toast;

            if (radiogroup.getCheckedRadioButtonId() == R.id.radioButton ){
                Communication.envoieCommande(Communication.createCommande("setPresence","1"));
                toast= Toast.makeText(Configuration.this,"Mode présent.",Toast.LENGTH_SHORT);

            }
            else {
                Communication.envoieCommande(Communication.createCommande("setPresence","0"));
                toast= Toast.makeText(Configuration.this,"Mode absent.",Toast.LENGTH_SHORT);
            }

            //toast.show();

        }
    };

    RadioGroup.OnCheckedChangeListener first_radio_listener2 = new RadioGroup.OnCheckedChangeListener (){
        public void onCheckedChanged(RadioGroup ra, int v) {
            Toast toast;

            if (radiogroup2.getCheckedRadioButtonId() == R.id.radioButton3 ){
                Communication.envoieCommande(Communication.createCommande("setBouton","1"));
                toast= Toast.makeText(Configuration.this,"Appui sur le bouton.",Toast.LENGTH_SHORT);
            }
            else{
                Communication.envoieCommande(Communication.createCommande("setBouton","0"));
                toast= Toast.makeText(Configuration.this,"Bouton non appuyé.",Toast.LENGTH_SHORT);
            }
            //toast.show();

        }
    };
}

