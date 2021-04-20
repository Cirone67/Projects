/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;
import java.io.*;
/**
 *
 * @author brenc
 */
public class Stockage {
    
    //Méthode de lecture des coordonnées
    public static double coordonnee(String texte, int emplacement){
    String contenu[];    
        texte.replaceAll("(","");
    contenu = texte.split(",");
     return Double.parseDouble(contenu[emplacement]);
     
    }
    
 //Méthode qui récupère le type de barre
public static Treillis telechargement(){
    String contenu[];
    Treillis treillis = new Treillis();
try {
    BufferedReader flux=new BufferedReader(new FileReader("Treillis.txt"));
String lignelue;
   while((lignelue=flux.readLine())!=null){
    contenu = lignelue.split(";");
        if(contenu[0].equals("ZoneConstructible")){
              treillis.setTXmin(Double.parseDouble(contenu[1]));
              treillis.setTXmax(Double.parseDouble(contenu[2]));        
              treillis.setTYmin(Double.parseDouble(contenu[3]));
              treillis.setTYmax(Double.parseDouble(contenu[4]));   
       }
       if(contenu[0].equals("Triangle")){
               
              TerrainTriangle terrainTriangles   = new TerrainTriangle( Integer.valueOf(contenu[1]),coordonnee(contenu[2],0),coordonnee(contenu[2],1),coordonnee(contenu[3],0),coordonnee(contenu[3],1),coordonnee(contenu[4],0),coordonnee(contenu[4],1));
              treillis.ajoute(terrainTriangles);    
       }
       if(contenu[0].equals("TypeBarre")){
              TypeBarre compteurTypeBarre   = new TypeBarre( Integer.valueOf(contenu[1]),Double.parseDouble(contenu[2]),Double.parseDouble(contenu[3]),Double.parseDouble(contenu[4]),Double.parseDouble(contenu[5]),Double.parseDouble(contenu[6]));  
              treillis.ajoute(compteurTypeBarre);      
       }
       if(contenu[0].equals("AppuiSimple")||contenu[0].equals("AppuiDouble")||contenu[0].equals("NoeudSimple")){
              if(contenu[0].equals("AppuiSimple")||contenu[0].equals("AppuiDouble")){
               Appui compteurAppui   = new Appui( Integer.valueOf(contenu[1]),Integer.valueOf(contenu[2]),Integer.valueOf(contenu[3]),Double.parseDouble(contenu[4]),treillis.getTerrainTriangles());  
              treillis.ajoute(compteurAppui);  
              }else{
              
                NoeudSimple compteurNoeudSimple   = new NoeudSimple( Integer.valueOf(contenu[1]),coordonnee(contenu[1],0),coordonnee(contenu[1],1));  
              treillis.ajoute(compteurNoeudSimple);
            }
       }
       if(contenu[0].equals("Barre")){
         Barre compteurBarre   = new Barre (Integer.valueOf(contenu[1]),Integer.valueOf(contenu[2]),Integer.valueOf(contenu[3]),Integer.valueOf(contenu[4]),treillis.getNoeuds(),treillis.getTypeBarre());
              treillis.ajoute(compteurBarre);  
       }
       }
flux.close();
}

catch(FileNotFoundException err){
System.out.println("Erreur :le fichier n’existe pas!\n "+err);}

catch(IOException err){
System.out.println("Erreur :\n"+err);}

return treillis;
}
/*
public static void main(String[] args){
Treillis premier;
premier = telechargement();
System.out.println(premier.getTypeBarre().get(1).getCout()); 

}
*/
}
