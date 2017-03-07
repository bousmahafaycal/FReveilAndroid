package com.freveil.fsoft.freveil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ajoutRappel extends AppCompatActivity {
    Rappel r = new Rappel();
    String rap;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_rappel);
        System.out.println("[F]OnCREZ");
        Intent intent = getIntent();
        if (intent != null){
            try{
                rap = intent.getStringExtra("rappel");
                System.out.println("[F]recuRappel:"+rap.replace("\n",""));
                r.open(rap);

            }catch (Exception e){

            }

        }

        settings = getSharedPreferences("FPref", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("modifie", false);
        editor.commit();

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

        TextView textView = (TextView)findViewById(R.id.textView);
        if  (r.type != -1){
            textView.setText(r.decrisQuandRappel("Le rappel de type "));
            textView.setTextColor(Color.parseColor("#1fbf3a"));
        }

    }

    private View.OnClickListener listenerbouton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder monDialogue = new AlertDialog.Builder(v.getContext());
            System.out.println("[F]Save Fayçal save:"+r.toString().replace("\n"," "));
            monDialogue.setTitle("Sauvegarde");
            if (r.isARappel())
                monDialogue.setMessage("Souhaitez-vous sauvegarder ce rappel ?");
            else
                monDialogue.setMessage("Vous n'avez pas défini l'heure et/ou la date ou votre rappel. Souhaitez vous continuer à modifier ce rappel ?");

            //Bouton du dialogue
            monDialogue.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (r.isARappel()){
                        Communication.envoieCommande(Communication.createCommande("ajoutRappel",r.toString()));
                        Toast toast= Toast.makeText(ajoutRappel.this,"Rappel ajouté !",Toast.LENGTH_LONG);
                        toast.show();
                        retournerMenuReveil();
                    }



                }
            });
            monDialogue.setNegativeButton("Non", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    Toast toast = Toast.makeText(ajoutRappel.this, "Ajout de rappel annulé !", Toast.LENGTH_LONG);
                    toast.show();
                    retournerMenuReveil();

                }
            });
            monDialogue.show();



        }



    };

    public void retournerMenuReveil(){
        Intent intent = new Intent (ajoutRappel.this , MenuReveil.class);
        startActivity(intent) ;
    }

    private View.OnClickListener listenerbouton2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (ajoutRappel.this , quandMenu.class);
            intent.putExtra("rappel",r.toString());
            ArrayList<Integer> list = new ArrayList<Integer>();
            /*list.add(2017);
            list.add(3);
            list.add(1);
            list.add(1);
            list.add(15);
            r.setDateHeure(2,list);*/

            startActivity(intent) ;


        }



    };

    private View.OnClickListener listenerbouton3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (ajoutRappel.this , affichageCommande.class);
            intent.putExtra("rappel",r.toString());
            startActivity(intent) ;


        }



    };

    private View.OnClickListener listenerbouton4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (ajoutRappel.this , ajoutCommande.class);
            intent.putExtra("rappel",r.toString());
            startActivity(intent) ;


        }



    };

    private View.OnClickListener listenerbouton5 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (ajoutRappel.this , modifierCommande.class);
            intent.putExtra("rappel",r.toString());
            startActivity(intent) ;


        }



    };

    private View.OnClickListener listenerbouton6 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (ajoutRappel.this , suppressionCommande.class);
            intent.putExtra("rappel",r.toString());
            startActivity(intent) ;


        }



    };


}
