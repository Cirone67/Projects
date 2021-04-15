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
public static void RecupTypeBarre(ArrayList<TypeBarre> typeBarre){
    String contenu[];
try {
    BufferedReader flux=new BufferedReader(new FileReader("Treillis.txt"));
String lignelue;
   while((lignelue=flux.readLine())!=null){
    contenu = lignelue.split(";");
    System.out.println(contenu[0]);
       while(contenu[0].equals("TypeBarre")){
              TypeBarre compteurTypeBarre   = new TypeBarre( Integer.valueOf(contenu[1]),Double.parseDouble(contenu[2]),Double.parseDouble(contenu[3]),Double.parseDouble(contenu[4]),Double.parseDouble(contenu[5]),Double.parseDouble(contenu[6]));  
              typeBarre.add(compteurTypeBarre);
        contenu[0]=" ";      
       }
       }
flux.close();
}

catch(FileNotFoundException err){
System.out.println("Erreur :le fichier n’existe pas!\n "+err);}

catch(IOException err){
System.out.println("Erreur :\n"+err);}
}
/*
public static void main(String[] args){
Treillis premier = new Treillis();
RecupTypeBarre(premier.typeBarre);
System.out.println(premier.typeBarre.get(1).getCout());  

}
*/
}
