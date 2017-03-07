package com.freveil.fsoft.freveil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class quandMinute extends AppCompatActivity {
    String rap;
    Rappel r = new Rappel();
    String modifie = "b";
    SharedPreferences settings;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quand_minute);
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


        Date aujourdhui = new Date();
        DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(
                DateFormat.MEDIUM,
                DateFormat.MEDIUM);
        System.out.println("[F]"+mediumDateFormat.format(aujourdhui));
    }

    public ArrayList<Integer> additionnerMinutes(Date a, int b){
        int minutes = a.getMinutes() + b;
        int retenue = 0;
        if (minutes >= 60){
            retenue = minutes/60;
            minutes = minutes%60;
        }
        int hour = a.getHours()+retenue;
        if (hour >= 60){
            retenue = hour/60;
            hour = hour%60;
        }
        int date = a.getDate()+retenue;
        int nb = getNbDay(a.getMonth()+1,a.getYear());
        if (date >= nb){
            retenue = date/nb;
            date = date%nb;
        }
        int month = a.getMonth()+1+retenue;
        if (month >= 12){
            retenue = month/12;
            month = month%12;
        }
        int year = a.getYear()+retenue+1900;
        //System.out.println("[F]YEAR :"+a.getYear());


        ArrayList <Integer> c = new ArrayList<Integer>();
        c.add(year);
        c.add(month);
        c.add(date);
        c.add(hour);
        c.add(minutes);

        return c;
    }

    public int getNbDay(int month, int year){
        // Renvoie le nombre de jour
        int nb;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            nb = 31;
        }
        else if (month == 2 && year%4 == 0)
            nb = 29;
        else  if (month == 2)
            nb = 28;
        else
             nb = 30;
        return  nb;
    }


    private View.OnClickListener listenerbouton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Date aujourdhui = new Date();
            Intent intent = new Intent (quandMinute.this , ajoutRappel.class);
            try {
                EditText ed1 = (EditText)findViewById(R.id.editText);
                int minute = Integer.parseInt(ed1.getText().toString());
                ArrayList<Integer> liste = additionnerMinutes(aujourdhui,minute);
                r.setDateHeure(2,liste);
                settings = getSharedPreferences("FPref", 0);
                if (settings.getBoolean("modifie", false))
                    intent = new Intent (quandMinute.this , modificationRappel.class);
                intent.putExtra("rappel",r.toString());
                
                Toast toast= Toast.makeText(quandMinute.this,"Rappel unique ajouté  !",Toast.LENGTH_LONG);
                toast.show();
            }catch (Exception e){
                intent = new Intent (quandMinute.this , quandMenu.class);
                
                Toast toast= Toast.makeText(quandMinute.this,"Annulation !",Toast.LENGTH_LONG);
                toast.show();
            }

            startActivity(intent) ;


        }



    };

    private View.OnClickListener listenerbouton2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (quandMinute.this , quandMenu.class);
            intent.putExtra("rappel",r.toString());
            
            Toast toast= Toast.makeText(quandMinute.this,"Annulation  !",Toast.LENGTH_LONG);
            toast.show();
            startActivity(intent) ;


        }



    };
}
