package com.freveil.fsoft.freveil;

/**
 * Created by fayca on 26/02/2017.
 */

import java.util.ArrayList;

public class Rappel {
    ArrayList<Integer> listeDateHeure = new ArrayList<Integer>();
    int type = -1;
    ArrayList<String> part1 = new ArrayList<String>();
    ArrayList<ArrayList<String>> argument1 = new ArrayList<ArrayList<String>>();
    ArrayList<String> part2 = new ArrayList<String>();
    ArrayList<ArrayList<String>> argument2 = new ArrayList<ArrayList<String>>();

    boolean debut = true;
    ArrayList <Integer> listeDateHeureAncien = new ArrayList<Integer>();
    int typeAncien = -1;



    public void setDateHeure(int type,  ArrayList<Integer> listeDateHeure){
        // Définir quand le rappel va être appelé

        if (type >= 0 && type <= 3){
            boolean ajouter = false;
            if (type == 0 && listeDateHeure.size() == 2 ) ajouter = true;
            if (type == 1 && listeDateHeure.size() == 3 ) ajouter = true;
            if (type == 2 && listeDateHeure.size() == 5 ) ajouter = true;
            if (type == 3 && listeDateHeure.size() == 1 ) ajouter = true;

            if (ajouter){
                this.type = type;
                System.out.println("[F]AJOUTER"+this.type);
                this.listeDateHeure = listeDateHeure;
                System.out.println("[F]AJOUTER"+toString());
            }

        }
    }

    public boolean changeCommande(boolean part1, int nb, int nb2){
        if (part1 && (nb<0 || nb2 <0 || nb>= this.part1.size() ||nb2 >= this.part1.size()))
            return  false;
        else if (! part1 && (nb < 0 || nb2 < 0 || nb>= this.part2.size() || nb2>= this.part2.size()))
            return false;
        ArrayList<ArrayList<String>> argument ;
        ArrayList<String> part ;
        if (part1){
            part = this.part1;
            argument = this.argument1;
        }
        else {
            part = this.part2;
            argument = this.argument2;
        }
        String commande = part.get(nb);
        ArrayList<String> argumentNow = argument.get(nb) ;
        delCommande(part1,nb);
        addCommande(part1,commande,argumentNow,nb2 );

        return true;
    }


    public void addCommande(boolean part1,String commande,ArrayList<String> liste){
        // Ajouter une commannde et ses arguments
        System.out.println("[F]addCiommande");
        ArrayList<String> part ;
        ArrayList<ArrayList<String>> argument ;
        if (part1){
            part = this.part1;
            argument = this.argument1;
        }
        else {
            part = this.part2;
            argument = this.argument2;
        }
        System.out.println("[F]avant"+this.part1.toString());
        System.out.println("[F]avant"+this.part2.toString());
        part.add(commande);
        System.out.println("[F]après"+this.part1.toString());
        System.out.println("[F]après"+part2.toString());
        argument.add(liste);
        System.out.println("[F]ListeAjoutée");
    }

    public void addCommande(boolean part1,String commande,ArrayList<String> liste,int nb){
        // Ajouter une commannde et ses arguments
        System.out.println("[F]addCiommande NB");
        ArrayList<String> part ;
        ArrayList<ArrayList<String>> argument ;
        if (part1){
            part = this.part1;
            argument = this.argument1;
        }
        else {
            part = this.part2;
            argument = this.argument2;
        }
        System.out.println("[F]avantNB"+part.toString());
        System.out.println("[F]NB;"+nb);
        part.add(nb,commande);
        System.out.println("[F]aprèsNB"+part.toString());
        argument.add(nb,liste);
    }

    public void delCommande(boolean part1, int nb){
        // Supprimer une commande à partir de son index
        ArrayList<String> part ;
        ArrayList<ArrayList<String>> argument ;
        if (part1){
            part = this.part1;
            argument = this.argument1;
        }
        else {
            part = this.part2;
            argument = this.argument2;
        }
        part.remove(nb);
        argument.remove(nb);
    }

    public void initialisation(){
        listeDateHeure = new ArrayList<Integer>();
        listeDateHeureAncien  = new ArrayList<Integer>();
        typeAncien = -1;
        type = -1;
        part1 = new ArrayList<String>();
        argument1 = new ArrayList<ArrayList<String>>();
        part2 = new ArrayList<String>();
        argument2 = new ArrayList<ArrayList<String>>();
    }

    public void open(String chaine){
        // Ouvre l'objet à partir d'une chaine
        initialisation();
        int nb;
        boolean isTypeAncien = false;
        if (Outils.compter(chaine,"<TypeAncien>") == 0 ){
            typeAncien  = Integer.parseInt(Outils.recupereBaliseAuto(chaine,"Type",1,"Type",false));
            debut = false;
        }



        else if (Outils.compter(chaine,"<TypeAncien>") == 1 ){
            typeAncien = Integer.parseInt(Outils.recupereBaliseAuto(chaine,"TypeAncien",1,"TypeAncien",false));
            debut = Outils.recupereBaliseAuto(chaine,"Debut",1,"Debut",false) == "true";
            nb = Outils.compter(chaine,"<ListeDateHeureAncien>");
            System.out.println("[F]NB ListeDateHeureAncien "+ nb);
            for (int i  = 0; i < nb; i++){
                listeDateHeureAncien.add(Integer.parseInt(Outils.recupereBaliseAuto(chaine,"ListeDateHeureAncien",i+1,"ListeDateHeureAncien",false)));
            }
            chaine = Outils.recupereBaliseAuto(chaine,"Ajout",1,"Ajout",false);
            isTypeAncien = true;
        }


        type = Integer.parseInt(Outils.recupereBaliseAuto(chaine,"Type",1,"Type",false));
        System.out.println("[F]TYPE : "+type);


        System.out.println("[F]"+chaine);
        String chainePart1 = Outils.recupereBaliseAuto(chaine,"Part 1",1,"Part 1",false);
        String chainePart2 = Outils.recupereBaliseAuto(chaine,"Part 2",1,"Part 2",false);
        nb = Outils.compter(chaine,"<ListeDateHeure>");
        String chaineModule = "", chaineNom = "";
        int nbArguments;
        ArrayList<String> liste = new ArrayList<String>();

        for (int i  = 0; i < nb; i++){
            listeDateHeure.add(Integer.parseInt(Outils.recupereBaliseAuto(chaine,"ListeDateHeure",i+1,"ListeDateHeure",false)));
            if (!isTypeAncien){
                listeDateHeureAncien.add(Integer.parseInt(Outils.recupereBaliseAuto(chaine,"ListeDateHeure",i+1,"ListeDateHeure",false)));
            }
        }







        nb = Outils.compter(chainePart1,"<Module>");
        for (int i = 0; i < nb ; i++){
            liste = new ArrayList<String>();
            chaineModule = Outils.recupereBaliseAuto(chainePart1,"Module", i+1, "Module",false);
            chaineNom = Outils.recupereBaliseAuto(chaineModule, "Nom",1,"Nom",false);
            part1.add(chaineNom);
            nbArguments = Outils.compter(chaineModule,"<Argument>");
            for (int i2 = 0; i2 < nbArguments; i2++){
                liste.add(Outils.recupereBaliseAuto(chaineModule,"Argument",i2+1,"Argument",false));
            }
            argument1.add(liste);


        }

        nb = Outils.compter(chainePart2,"<Module>");


        for (int i = 0; i < nb ; i++){
            liste = new ArrayList<String>();
            chaineModule = Outils.recupereBaliseAuto(chainePart2,"Module", i+1, "Module",false);
            chaineNom = Outils.recupereBaliseAuto(chaineModule, "Nom",1,"Nom",false);
            part2.add(chaineNom);
            nbArguments = Outils.compter(chaineModule,"<Argument>");
            for (int i2 = 0; i2 < nbArguments; i2++){
                liste.add(Outils.recupereBaliseAuto(chaineModule,"Argument",i2+1,"Argument",false));
            }
            argument2.add(liste);


        }

        System.out.println("[F]part1:"+part1.toString());
        System.out.println("[F]TYPEANCIEN LISTE : "+listeDateHeureAncien.toString());
        System.out.println("[F]TYPEANCIEN  : "+typeAncien);

    }

    public String  toString(){
        // Representation de l'objet par une chaine
        System.out.println("[F]TOSTRING");
        String chaine4 = "";
        if (typeAncien != -1 ) {

            System.out.println("[F]DIFF -1");
            chaine4 = Outils.constitueBalise("TypeAncien", String.valueOf(typeAncien)) + "\n";
            chaine4 += Outils.constitueBalise("Debut", String.valueOf(debut)) + "\n";
            for (int i = 0; i < listeDateHeureAncien.size(); i++) {
                chaine4 += Outils.constitueBalise("ListeDateHeureAncien", String.valueOf(listeDateHeureAncien.get(i))) + "\n";
            }
            chaine4 = Outils.constitueBalise("Suppression", chaine4);
            System.out.println("[F]DIFF -1 : "+chaine4.replace("\n","[n]"));
        }

        String chaine = "";
        String chaine2 = "";
        String chaine3 = "";
        chaine += Outils.constitueBalise("Type",String.valueOf(type))+"\n";
        for (int i = 0; i < listeDateHeure.size(); i++)
            chaine2 += Outils.constitueBalise("ListeDateHeure",String.valueOf(listeDateHeure.get(i)))+"\n";
        chaine += chaine2+"\n\n";;
        chaine2 = "";
        System.out.println("[F]PART1"+this.part1.toString());
        System.out.println("[F]PART1.SIZE! "+part1.size());
        for (int i = 0; i < part1.size(); i++){
            chaine3 = Outils.constitueBalise("Nom",String.valueOf(part1.get(i)))+"\n";
            for (int i2 = 0; i2 < argument1.get(i).size(); i2++){
                chaine3+= Outils.constitueBalise("Argument",argument1.get(i).get(i2));
            }
            chaine2 +=Outils.constitueBalise("Module",chaine3)+"\n";
        }

        chaine += Outils.constitueBalise("Part 1",chaine2)+"\n\n";
        chaine2 = "";
        System.out.println("[F]PART2.SIZE! "+part2.size());
        System.out.println("[F]PART2"+this.part2.toString());

        for (int i = 0; i < part2.size(); i++){
            chaine3 = Outils.constitueBalise("Nom",String.valueOf(part2.get(i)))+"\n";
            for (int i2 = 0; i2 < argument2.get(i).size(); i2++){
                chaine3+= Outils.constitueBalise("Argument",argument2.get(i).get(i2));
            }
            chaine2 += Outils.constitueBalise("Module",chaine3)+"\n";
        }
        chaine += Outils.constitueBalise("Part 2",chaine2);

        if (typeAncien != -1){
            chaine = chaine4+"\n"+Outils.constitueBalise("Ajout",chaine);
        }
        System.out.println("[F]SAVE! "+chaine.replace("\n"," "));

        return chaine;
    }

    public  boolean isARappel(){
        // Fonction qui renvoie un boolen pour dire si le rappel est valide
        if (type == -1 || ( part1.size() == 0 && part2.size() == 0))
            return  false;
        return true;
    }

    public String decrisQuandRappel(String chaine){
        // Renvoie une chaine de caractère décrivant quand est-ce que le rappel va être appelé
        if (type == 0){
            chaine += "journalier a été défini à "+listeDateHeure.get(0)+"h"+listeDateHeure.get(1)+".";
        } else if (type == 1){
            chaine += "hebdomadaire a été défini les "+getJour(listeDateHeure.get(0))+" à "+listeDateHeure.get(1)+"h"+listeDateHeure.get(2)+".";
        } else if (type == 2) {
            chaine += "unique a été défini pour le "+listeDateHeure.get(0)+"/"+listeDateHeure.get(1)+"/"+listeDateHeure.get(2)+" à "+
                    listeDateHeure.get(3)+"h"+listeDateHeure.get(4)+".";
        }
        return chaine;
    }

    public String decrisQuandRappeAncien(String chaine){
        // Renvoie une chaine de caractère décrivant quand est-ce que le rappel va être appelé
        if (typeAncien == 0){
            chaine += "journalier a été défini à "+listeDateHeureAncien.get(0)+"h"+listeDateHeureAncien.get(1)+".";
        } else if (typeAncien == 1){
            chaine += "hebdomadaire a été défini les "+getJour(listeDateHeureAncien.get(0))+" à "+listeDateHeureAncien.get(1)+"h"+listeDateHeureAncien.get(2)+".";
        } else if (typeAncien == 2) {
            chaine += "unique a été défini pour le "+listeDateHeureAncien.get(0)+"/"+listeDateHeureAncien.get(1)+"/"+listeDateHeureAncien.get(2)+" à "+
                    listeDateHeureAncien.get(3)+"h"+listeDateHeureAncien.get(4)+".";
        }
        return chaine;
    }

    public String getJour(int jour){
        // renvoie le nom du jour (0 pour lundi, ... 6 pour dimanche)
        String [] tab = {"lundi","mardi","mercredi","jeudi","vendredi","samedi","dimanche"};
        return tab[jour];
    }
}
