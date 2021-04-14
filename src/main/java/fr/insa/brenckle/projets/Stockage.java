/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
/**
 *
 * @author brenc
 */
public class Stockage {
    
 //Méthode qui récupère le type de barre
public void RecupTypeBarre(ArrayList<TypeBarre> typeBarre){
    String contenu[];
try {
    BufferedReader flux=new BufferedReader(new FileReader("Treillis.txt"));
String lignelue;
   while((lignelue=flux.readLine())!=null){
    contenu = lignelue.split(";");   
       while(contenu[0].equals("TypeBarre")){
              TypeBarre compteurTypeBarre   = new TypeBarre( Integer.valueOf(contenu[1]),Double.parseDouble(contenu[2]),Double.parseDouble(contenu[3]),Double.parseDouble(contenu[4]),Double.parseDouble(contenu[5]),Double.parseDouble(contenu[6]));  
              typeBarre.add(compteurTypeBarre);
       }
       }
   //System.out.println(typeBarre);
flux.close();
}

catch(FileNotFoundException err){
System.out.println("Erreur :le fichier n’existe pas!\n "+err);}

catch(IOException err){
System.out.println("Erreur :\n"+err);}
}
}
