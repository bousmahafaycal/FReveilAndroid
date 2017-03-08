package com.freveil.fsoft.freveil;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class supprimeRappel extends AppCompatActivity {
    Spinner spinner, spinner2;
    String rap;
    ArrayList<String> listeCommande = new ArrayList<String>();
    RadioGroup radiogroup;
    ArrayList<String> liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprime_rappel);



        radiogroup = (RadioGroup)findViewById(R.id.radiogroup);
        radiogroup.setOnCheckedChangeListener(first_radio_listener);


        spinner   = (Spinner)findViewById(R.id.spinner);
        Button bouton = null;
        bouton = (Button)findViewById(R.id.button);
        bouton.setOnClickListener(  listenerbouton  );

        Button bouton2 = null;
        bouton2 = (Button)findViewById(R.id.button2);
        bouton2.setOnClickListener(  listenerbouton2  );



        Rappel r = new Rappel();
        Communication.envoieCommande("<c>getRappelJournalier</c>");

        SharedPreferences settings;
        settings = getSharedPreferences("FPref", 0);
        String adresseIp = settings.getString("adresseIp", "");
        String port = settings.getString("port", "");
        Communication.changeServeur(adresseIp,port);

        Thread ta;

        ta =new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                runOnUiThread(new Runnable() {

                    @Override

                    public void run() {
                        liste = Communication.getArrayListResponse();
                        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item, liste);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);

                    }});
            }
        });


        ta.start();
    }

    private View.OnClickListener listenerbouton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            String chaine = "<c>supprimeRappel</c>\n";

            if (radiogroup.getCheckedRadioButtonId()== R.id.radioButton ){
                chaine += "<a><Type>0</Type><Nom>";
            }else if (radiogroup.getCheckedRadioButtonId()== R.id.radioButton2 ){
                chaine += "<a><Type>1</Type><Nom>";
            }
            else {
                chaine += "<a><Type>2</Type><Nom>";
            }

            try{
                chaine += liste.get(spinner.getSelectedItemPosition())+"</Nom></a>";
                Communication.envoieCommande(chaine);
                Toast toast= Toast.makeText(supprimeRappel.this,"Supression de rappel réussie !",Toast.LENGTH_LONG);
                toast.show();
            }catch (Exception e){
                Toast toast= Toast.makeText(supprimeRappel.this,"Supression de rappel annulée !",Toast.LENGTH_LONG);
                toast.show();
            }
            Intent intent = new Intent (supprimeRappel.this , MenuReveil.class);
            startActivity(intent) ;


        }



    };

    private View.OnClickListener listenerbouton2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (supprimeRappel.this , MenuReveil.class);
            Toast toast= Toast.makeText(supprimeRappel.this,"Supression de rappel annulée",Toast.LENGTH_LONG);
            toast.show();
            startActivity(intent) ;


        }



    };

    RadioGroup.OnCheckedChangeListener first_radio_listener = new RadioGroup.OnCheckedChangeListener (){
        public void onCheckedChanged(RadioGroup ra, int v) {

            if (radiogroup.getCheckedRadioButtonId()== R.id.radioButton ){
                Communication.envoieCommande("<c>getRappelJournalier</c>");

            }
            else if (radiogroup.getCheckedRadioButtonId()== R.id.radioButton2){
                Communication.envoieCommande("<c>getRappelHebdomadaire</c>");
            }
            else {
                Communication.envoieCommande("<c>getRappelUnique</c>");
            }


            Thread ta;

            ta =new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    runOnUiThread(new Runnable() {

                        @Override

                        public void run() {
                            liste = Communication.getArrayListResponse();
                            ArrayAdapter adapter = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item, liste);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(adapter);

                        }});
                }
            });


            ta.start();




        }
    };
}

