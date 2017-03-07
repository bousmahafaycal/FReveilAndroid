package com.freveil.fsoft.freveil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class suppressionCommande extends AppCompatActivity {
    Spinner spinner ;
    String rap;
    Rappel r = new Rappel();
    RadioGroup radiogroup;
    ArrayAdapter adapter ;
    ArrayAdapter adapter2;
    String modifie = "b";
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppression_commande);

        spinner = (Spinner)findViewById(R.id.spinner);

        Intent intent2 = getIntent();
        if (intent2 != null){
            try{
                rap = intent2.getStringExtra("rappel");
                r.open(rap);
                Toast toast= Toast.makeText(suppressionCommande.this,"Lecture rappel réussie : "+r.type,Toast.LENGTH_LONG);
                toast.show();
            }catch (Exception e){

            }try{
                modifie = intent2.getStringExtra("modifie");
            }catch (Exception e){

            }

        }

        radiogroup = (RadioGroup)findViewById(R.id.radiogroup);
        radiogroup.setOnCheckedChangeListener(first_radio_listener);

        Button bouton = null;
        bouton = (Button)findViewById(R.id.button);
        bouton.setOnClickListener(  listenerbouton  );

        Button bouton2 = null;
        bouton2 = (Button)findViewById(R.id.button2);
        bouton2.setOnClickListener(  listenerbouton2  );

         adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, r.part1);
         adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, r.part2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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
                r.delCommande(part1,spinner.getSelectedItemPosition());
                Toast toast= Toast.makeText(suppressionCommande.this,"Supression de commande réussie !",Toast.LENGTH_LONG);
                toast.show();
            }catch (Exception e){
                Toast toast= Toast.makeText(suppressionCommande.this,"Supression de commande annulée !",Toast.LENGTH_LONG);
                toast.show();
            }
            Intent intent = new Intent (suppressionCommande.this , ajoutRappel.class);

            settings = getSharedPreferences("FPref",0);
            if (settings.getBoolean("modifie", false))
                intent = new Intent (suppressionCommande.this , modificationRappel.class);
            intent.putExtra("rappel",r.toString());
            startActivity(intent) ;


        }



    };

    private View.OnClickListener listenerbouton2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (suppressionCommande.this , ajoutRappel.class);

            settings = getSharedPreferences("FPref", 0);
            if (settings.getBoolean("modifie", false))
                intent = new Intent (suppressionCommande.this , modificationRappel.class);
            intent.putExtra("rappel",r.toString());
            Toast toast= Toast.makeText(suppressionCommande.this,"Suppression de commande annulée",Toast.LENGTH_LONG);
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

        }
    };
}
