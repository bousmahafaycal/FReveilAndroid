package com.freveil.fsoft.freveil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class quandJour extends AppCompatActivity {

    Spinner spinner;
    String rap;
    Rappel r = new Rappel();
    ArrayList<String> listeCommande = new ArrayList<String>();
    String modifie = "b";
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quand_jour);

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





        spinner   = (Spinner)findViewById(R.id.spinner);
        Button bouton = null;
        bouton = (Button)findViewById(R.id.button);
        bouton.setOnClickListener(  listenerbouton  );

        Button bouton2 = null;
        bouton2 = (Button)findViewById(R.id.button2);
        bouton2.setOnClickListener(  listenerbouton2  );

        listeCommande.add("Lundi");
        listeCommande.add("Mardi");
        listeCommande.add("Mercredi");
        listeCommande.add("Jeudi");
        listeCommande.add("Vendredi");
        listeCommande.add("Samedi");
        listeCommande.add("Dimanche");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listeCommande);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private View.OnClickListener listenerbouton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent (quandJour.this , ajoutRappel.class);
            ArrayList<Integer> liste = new ArrayList<Integer>();
            TimePicker t = (TimePicker)findViewById(R.id.timePicker);
            System.out.println("[F]0");
            liste.add(spinner.getSelectedItemPosition());
            try{
                liste.add(t.getCurrentHour());
                System.out.println("[F]4:"+t.getCurrentHour());
                liste.add(t.getCurrentMinute());
                System.out.println("[F]5:"+t.getCurrentMinute());
                r.setDateHeure(1,liste);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("[F]"+e.getMessage());
            }
            settings = getSharedPreferences("FPref", 0);
            if (settings.getBoolean("modifie", false))
                intent = new Intent (quandJour.this , modificationRappel.class);
            intent.putExtra("rappel",r.toString());
            
            Toast toast= Toast.makeText(quandJour.this,"Heure du rappel hebdomadaire définie !",Toast.LENGTH_LONG);
            toast.show();
            startActivity(intent) ;
        }



    };

    private View.OnClickListener listenerbouton2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent (quandJour.this , quandMenu.class);
            intent.putExtra("rappel",r.toString());
            
            Toast toast= Toast.makeText(quandJour.this,"Annulation  !",Toast.LENGTH_LONG);
            toast.show();
            startActivity(intent) ;


        }



    };
}
