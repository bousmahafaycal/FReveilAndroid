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

public class modifierCommande extends AppCompatActivity {
    Spinner spinner, spinner2;
    String rap;
    Rappel r = new Rappel();
    ArrayList<String> listeCommande = new ArrayList<String>();
    RadioGroup radiogroup;
    String modifie = "b";
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_commande);

        Intent intent2 = getIntent();
        if (intent2 != null){
            try{
                rap = intent2.getStringExtra("rappel");
                r.open(rap);
            }catch (Exception e){

            }try{
                modifie = intent2.getStringExtra("modifie");
            }catch (Exception e){

            }

        }


        radiogroup = (RadioGroup)findViewById(R.id.radiogroup);
        radiogroup.setOnCheckedChangeListener(first_radio_listener);


        spinner   = (Spinner)findViewById(R.id.spinner);
        spinner2   = (Spinner)findViewById(R.id.spinner2);
        Button bouton = null;
        bouton = (Button)findViewById(R.id.button);
        bouton.setOnClickListener(  listenerbouton  );

        Button bouton2 = null;
        bouton2 = (Button)findViewById(R.id.button2);
        bouton2.setOnClickListener(  listenerbouton2  );

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, r.part1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayList<String> liste = new ArrayList<String> ();
        for (int i = 0; i < r.part1.size(); i++){
            liste.add(String.valueOf(i+1));
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, liste);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
    }

    private View.OnClickListener listenerbouton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            boolean part1 = true;

            if (radiogroup.getCheckedRadioButtonId()!= R.id.radioButton ){
                part1 = false;
            }
            ArrayList<String> liste = new ArrayList<String>();


            try{
                r.changeCommande(part1,spinner.getSelectedItemPosition(),spinner2.getSelectedItemPosition());
                Toast toast= Toast.makeText(modifierCommande.this,"Modification de l'ordre des commandes réussie !",Toast.LENGTH_LONG);
                toast.show();
            }catch (Exception e){
                Toast toast= Toast.makeText(modifierCommande.this,"Modification de l'ordre des commandes annulée !",Toast.LENGTH_LONG);
                toast.show();
            }
            Intent intent = new Intent (modifierCommande.this , ajoutRappel.class);
            settings = getSharedPreferences("FPref", 0);
            if (settings.getBoolean("modifie", false))
                intent = new Intent (modifierCommande.this , modificationRappel.class);
            intent.putExtra("rappel",r.toString());
            startActivity(intent) ;


        }



    };

    private View.OnClickListener listenerbouton2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (modifierCommande.this , ajoutRappel.class);
            settings = getSharedPreferences("FPref", 0);
            if (settings.getBoolean("modifie", false))
                intent = new Intent (modifierCommande.this , modificationRappel.class);
            intent.putExtra("rappel",r.toString());
            Toast toast= Toast.makeText(modifierCommande.this,"Modification de l'ordre des commandes annulée",Toast.LENGTH_LONG);
            toast.show();
            startActivity(intent) ;


        }



    };

    RadioGroup.OnCheckedChangeListener first_radio_listener = new RadioGroup.OnCheckedChangeListener (){
        public void onCheckedChanged(RadioGroup ra, int v) {
            boolean part1 = true;

            if (radiogroup.getCheckedRadioButtonId()!= R.id.radioButton ){
                part1 = false;
            }

            ArrayAdapter adapter = new ArrayAdapter(ra.getContext(),android.R.layout.simple_spinner_item,r.part1);
            if (!part1)
                adapter = new ArrayAdapter(ra.getContext(),android.R.layout.simple_spinner_item,r.part2);;
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            ArrayList<String> liste = new ArrayList<String> ();
            int lon = r.part1.size();
            if (!part1)
                lon = r.part2.size();
            for (int i = 0; i < lon; i++){
                liste.add(String.valueOf(i+1));
            }
            adapter = new ArrayAdapter(ra.getContext(), android.R.layout.simple_spinner_item, liste);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter);

        }
    };
}
