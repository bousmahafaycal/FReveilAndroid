package com.freveil.fsoft.freveil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by fayca on 28/02/2017.
 */

public class Communication {
    static String adresseIP = "", port="",reponse="";
    static  boolean connexionState = false;

    public static void changeServeur(String adresseIP, String port){
        // Modifie les cooordonnées du serveur.
        Communication.adresseIP = adresseIP;
        Communication.port = port;
    }

    public static boolean connexion(){
        // Renvoie true si la connexion au serveur fonctionne, false sinon

        System.out.println("[F]adresseIp : "+adresseIP);
        System.out.println("[F]port : "+port);
        connexionState = false;
        envoieCommande(createCommande("connexion",""));
        System.out.println("[F]connexionStateA : "+connexionState);
        System.out.println("[F]reponse : "+getResponse());

        return connexionState;
    }

    public  static  String getResponse(){
        return  reponse;
    }

    public static ArrayList<String> getArrayListResponse (){
        // Fonction permettant de récuperer la réponse sous forme d'arrayList.
        ArrayList<String> list = new ArrayList<String>();
        int a = Outils.compter(reponse,"<i>"); // i c'est pour item
        for (int i = 0; i < a ; i++){
            list.add(Outils.recupereBaliseAuto(reponse,"i",i+1,"i",false));
        }

        return list;
    }

    public static void envoieCommande(String commande, boolean join){
        // Fonction permettant d'envoyer une commande au serveur. remplace tous les retours à la ligne par [n]. la réponse à la commande doit tenir sur une ligne
        final String commande2 = commande;//.replace("\n","[n]");
        Thread ta;
        System.out.println("[F]On est la : "+commande.replace("\n","[n]"));

        ta =new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("[F]thread lancé");
                Socket socket;
                BufferedReader in;
                PrintWriter out;
                System.out.println("[F]thread lancé2");
                try {

                    // socket = new Socket("192.168.1.91",12800);

                    System.out.println("[F]FDemande de connexion");
                    socket = new Socket(adresseIP,Integer.parseInt(port));
                    System.out.println("[F]Socket créee");
                    out = new PrintWriter(socket.getOutputStream());
                    out.println(commande2);
                    out.flush();
                    System.out.println("[F]Socket envoyée");

                    in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
                    String message_distant = in.readLine();
                    System.out.println("[F]message : "+message_distant);
                    message_distant = message_distant.replace("[n]","\n");


                    System.out.println("[F]message : "+message_distant);
                    Communication.reponse = message_distant;

                    socket.close();
                    Communication.connexionState = true;
                    System.out.println("[F]connexionState : "+connexionState);

                }catch (Exception e){
                    System.out.println("[F]EXCEPTION : ");
                    System.out.println("[F]EXCEPTION : "+e.getMessage());

                }
                System.out.println("[F]LOL : ");
            }
        });


        ta.start();
        if (join){
            try{
                ta.join();
            }catch (Exception e){

            }
        }

    }

    public static void envoieCommande(String commande){
        // Fonction permettant d'envoyer une commande au serveur. remplace tous les retours à la ligne par [n]. la réponse à la commande doit tenir sur une ligne
        final String commande2 = commande;//.replace("\n","[n]");
        Thread ta;
        System.out.println("[F]On est la : "+commande.replace("\n","[n]"));

        ta =new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("[F]thread lancé");
                Socket socket;
                BufferedReader in;
                PrintWriter out;
                System.out.println("[F]thread lancé2");
                try {

                    // socket = new Socket("192.168.1.91",12800);

                    System.out.println("[F]FDemande de connexion");
                    socket = new Socket(adresseIP,Integer.parseInt(port));
                    System.out.println("[F]Socket créee");
                    out = new PrintWriter(socket.getOutputStream());
                    out.println(commande2);
                    out.flush();
                    System.out.println("[F]Socket envoyée");

                    in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
                    String message_distant = in.readLine();
                    System.out.println("[F]message : "+message_distant);
                    message_distant = message_distant.replace("[n]","\n");


                    System.out.println("[F]message : "+message_distant);
                    Communication.reponse = message_distant;

                    socket.close();
                    Communication.connexionState = true;
                    System.out.println("[F]connexionState : "+connexionState);

                }catch (Exception e){
                    System.out.println("[F]EXCEPTION : ");
                    System.out.println("[F]EXCEPTION : "+e.getMessage());

                }
                System.out.println("[F]LOL : ");
            }
        });


        ta.start();
        try{
            ta.join();
            ta.stop();
        }catch (Exception e){

        }

    }

    public  static  String createCommande(String commande,String argument){
        String chaine = Outils.constitueBalise("c",commande)+Outils.constitueBalise("a",argument);
        return chaine;
    }

}
