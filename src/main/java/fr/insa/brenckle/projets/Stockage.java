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
        String res = texte.substring(texte.indexOf("(")+1,texte.indexOf(")"));
    contenu = res.split(",");
     return Double.parseDouble(contenu[emplacement]);
     
    }
    
    public static String recoordonnee(double abs, double ord){
        return "("+abs+","+ord+")";
    }
    
 //Méthode qui télécharge le treillis
public static Treillis telechargement(int idfichier){
    String contenu[];
    Treillis treillis = new Treillis();
try {
    BufferedReader flux=new BufferedReader(new FileReader("Treillis"+idfichier+".txt"));
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
              if(contenu[0].equals("AppuiSimple")){
               Appui compteurAppui   = new AppuiSimple( Integer.valueOf(contenu[1]),Integer.valueOf(contenu[2]),Integer.valueOf(contenu[3]),Double.parseDouble(contenu[4]),treillis.getTerrainTriangles());  
              treillis.ajouteN(compteurAppui);
              }
              if(contenu[0].equals("AppuiDouble")){
              Appui compteurAppui   = new AppuiDouble( Integer.valueOf(contenu[1]),Integer.valueOf(contenu[2]),Integer.valueOf(contenu[3]),Double.parseDouble(contenu[4]),treillis.getTerrainTriangles());  
              treillis.ajouteN(compteurAppui);
              }
              if(contenu[0].equals("NoeudSimple")){
                NoeudSimple compteurNoeudSimple   = new NoeudSimple( Integer.valueOf(contenu[1]),coordonnee(contenu[2],0),coordonnee(contenu[2],1));  
              treillis.ajouteN(compteurNoeudSimple);
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

public static void enregistrer(Treillis treillis, int nbr){
try
{
    
BufferedWriter curseur=new BufferedWriter(new FileWriter("Treillis"+nbr+".txt",true));
//La zone constructible
curseur.write("ZoneConstructible;"+treillis.getTXmin()+";"+treillis.getTXmax()+";"+treillis.getTYmin()+";"+treillis.getTYmax());
curseur.newLine();
//Les triangles
for(int i=0; i<treillis.getTerrainTriangles().size();i++){
curseur.write("Triangle;");
curseur.write(treillis.getTerrainTriangles().get(i).getidT()+";");
curseur.write(recoordonnee(treillis.getTerrainTriangles().get(i).getC1().getA().getPx(),treillis.getTerrainTriangles().get(i).getC1().getA().getPy())+";");
curseur.write(recoordonnee(treillis.getTerrainTriangles().get(i).getC1().getB().getPx(),treillis.getTerrainTriangles().get(i).getC1().getB().getPy())+";");
curseur.write(recoordonnee(treillis.getTerrainTriangles().get(i).getC2().getB().getPx(),treillis.getTerrainTriangles().get(i).getC1().getB().getPy())+"");
curseur.newLine();
}
curseur.write("FINTRIANGLES");
curseur.newLine();
//les types de barres

for(int i=0;i<treillis.getTypeBarre().size();i++){
curseur.write("TypeBarre;");
curseur.write(treillis.getTypeBarre().get(i).getCategorie()+";");
curseur.write(treillis.getTypeBarre().get(i).getCout()+";");
curseur.write(treillis.getTypeBarre().get(i).getLongmin()+";");
curseur.write(treillis.getTypeBarre().get(i).getLongmax()+";");
curseur.write(treillis.getTypeBarre().get(i).getRestension()+";");
curseur.write(treillis.getTypeBarre().get(i).getRescompression()+"");
curseur.newLine();
}
curseur.write("FINCATALOGUE");
curseur.newLine();
//les noeuds
for(int i=0; i<treillis.getNoeuds().size();i++){
    if(treillis.getNoeuds().get(i) instanceof AppuiDouble ||treillis.getNoeuds().get(i) instanceof AppuiSimple){
        if(treillis.getNoeuds().get(i) instanceof AppuiDouble){
            curseur.write("AppuiDouble;");
        }
        if(treillis.getNoeuds().get(i) instanceof AppuiSimple){
            curseur.write("AppuiSimple;");
        }
        
    curseur.write(treillis.getNoeuds().get(i).getIdNoeud()+";");
    curseur.write(((Appui)treillis.getNoeuds().get(i)).getTriangleAppui().getidT()+";");// cast : permet d'accéder au méthode dans Appui car ici on est sur que ce sont des appuis
    curseur.write(((Appui)treillis.getNoeuds().get(i)).getPremierPoint()+";");
    curseur.write(((Appui)treillis.getNoeuds().get(i)).getPosSegment()+"");
}
    if(treillis.getNoeuds().get(i) instanceof NoeudSimple){
        curseur.write("NoeudSimple;");
        curseur.write(treillis.getNoeuds().get(i).getIdNoeud()+";");
        curseur.write(recoordonnee(treillis.getNoeuds().get(i).getAbs(),treillis.getNoeuds().get(i).getOrd())+"");       
    }
    curseur.newLine();
}

curseur.write("FINNOEUDS");
curseur.newLine();

//les barres
for(int i=0; i<treillis.getBarres().size();i++){
curseur.write("Barre;");
curseur.write(treillis.getBarres().get(i).getId()+";");
curseur.write(treillis.getBarres().get(i).getType().getCategorie()+";");
curseur.write(treillis.getBarres().get(i).getDebut().getIdNoeud()+";");
curseur.write(treillis.getBarres().get(i).getFin().getIdNoeud()+"");
curseur.newLine();
}

curseur.write("FINBARRES");
curseur.newLine();

curseur.close();
}
catch (IOException err)
{System.out.println("Erreur :\n"+err);}
}

/*
public static void main(String[] args){
Treillis premier;
premier = telechargement(1);
//System.out.println(premier.getTypeBarre().get(1).getCout());
//System.out.println(premier.getNoeuds().get(0).getOrd());
//System.out.println(premier.getBarres().get(0).getFin().getIdNoeud());
enregistrer(premier,2);
}
*/
}
