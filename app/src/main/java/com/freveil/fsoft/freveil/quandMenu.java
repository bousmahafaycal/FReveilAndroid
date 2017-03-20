package com.freveil.fsoft.freveil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class quandMenu extends AppCompatActivity {
    Rappel r = new Rappel();
    String rap;
    String modifie = "b";
    SharedPreferences settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quand_menu);

        Intent intent2 = getIntent();
        if (intent2 != null){
            try{
                rap = intent2.getStringExtra("rappel");
                r.open(rap);
            }catch (Exception e){

            }

            try{
                modifie = intent2.getStringExtra("modifie");
            }catch (Exception e){

            }

        }

        Button bouton = null;
        bouton = (Button)findViewById(R.id.button);

        Button bouton2 = null;
        bouton2 = (Button)findViewById(R.id.button2);

        Button bouton3 = null;
        bouton3 = (Button)findViewById(R.id.button3);

        Button bouton4 = null;
        bouton4 = (Button)findViewById(R.id.button4);

        Button bouton5 = null;
        bouton5 = (Button)findViewById(R.id.button5);

        Button bouton6 = null;
        bouton6 = (Button)findViewById(R.id.button6);


        // On attribue un listener adapté aux vues qui en ont besoin
        bouton.setOnClickListener(  listenerbouton  );
        bouton2.setOnClickListener(  listenerbouton2  );
        bouton3.setOnClickListener(  listenerbouton3  );

        bouton4.setOnClickListener(  listenerbouton4  );
        bouton5.setOnClickListener(  listenerbouton5  );
        bouton6.setOnClickListener(  listenerbouton6  );
    }


    private View.OnClickListener listenerbouton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (quandMenu.this , ajoutRappel.class);
            settings = getSharedPreferences("FPref", 0);
            if (settings.getBoolean("modifie", false))
                intent = new Intent (quandMenu.this , modificationRappel.class);
            intent.putExtra("rappel",r.toString());
            startActivity(intent) ;
        }



    };

    private View.OnClickListener listenerbouton2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (quandMenu.this , quandHeure.class);
            intent.putExtra("rappel",r.toString());
            startActivity(intent) ;


        }



    };

    private View.OnClickListener listenerbouton3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (quandMenu.this , quandJour.class);
            intent.putExtra("rappel",r.toString());
            startActivity(intent) ;


        }



    };

    private View.OnClickListener listenerbouton4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (quandMenu.this , quandDate.class);
            intent.putExtra("rappel",r.toString());
            startActivity(intent) ;


        }



    };

    private View.OnClickListener listenerbouton5 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (quandMenu.this , quandMinute.class);
            intent.putExtra("rappel",r.toString());
            startActivity(intent) ;


        }



    };

    private View.OnClickListener listenerbouton6 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Toast toast= Toast.makeText(quandMenu.this,"Fonctionnalité pas encore dévelopée !",Toast.LENGTH_SHORT);
            toast.show();

            /*Intent intent = new Intent (quandMenu.this , suppressionCommande.class);
            intent.putExtra("rappel",r.toString());
            startActivity(intent) ;*/


        }



    };
}
