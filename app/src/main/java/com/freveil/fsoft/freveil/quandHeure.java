package com.freveil.fsoft.freveil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class quandHeure extends AppCompatActivity {
    Rappel r = new Rappel();
    String rap;
    String modifie = "b";
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quand_heure);

        Button bouton = null;
        bouton = (Button)findViewById(R.id.button);

        Button bouton2 = null;
        bouton2 = (Button)findViewById(R.id.button2);

        bouton.setOnClickListener(  listenerbouton  );
        bouton2.setOnClickListener(  listenerbouton2  );

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


    }


    private View.OnClickListener listenerbouton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent (quandHeure.this , ajoutRappel.class);
            ArrayList<Integer> liste = new ArrayList<Integer>();
            TimePicker t = (TimePicker)findViewById(R.id.timePicker);
            System.out.println("[F]0");
            try{
                liste.add(t.getCurrentHour());
                System.out.println("[F]4:"+t.getCurrentHour());
                liste.add(t.getCurrentMinute());
                System.out.println("[F]5:"+t.getCurrentMinute());
                r.setDateHeure(0,liste);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("[F]"+e.getMessage());
            }



            settings = getSharedPreferences("FPref", 0);
            if (settings.getBoolean("modifie", false))
                intent = new Intent (quandHeure.this , modificationRappel.class);
            intent.putExtra("rappel",r.toString());
            Toast toast= Toast.makeText(quandHeure.this,"Heure du rappel journalier définie !",Toast.LENGTH_LONG);
            toast.show();
            startActivity(intent) ;
        }



    };

    private View.OnClickListener listenerbouton2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent (quandHeure.this , quandMenu.class);

            intent.putExtra("rappel",r.toString());
            
            Toast toast= Toast.makeText(quandHeure.this,"Annulation  !",Toast.LENGTH_LONG);
            toast.show();
            startActivity(intent) ;


        }



    };
}
