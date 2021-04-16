/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;

/**
 *
 * @author MAURY Robin
 */
import java.util.ArrayList;
import java.util.*;

public class TerrainTriangle {
    private TerrainSegment C1;
    private TerrainSegment C2;
    private TerrainSegment C3;
    private double idT;
    private static int nbrTriangle = 1;
    
    //get/set Terraintriangle
    public TerrainSegment getC1(){
        return C1;
    }
    public TerrainSegment getC2(){
        return C2;
    }
    public TerrainSegment getC3(){
        return C3;
    }
    public double getidT(){
        return idT;
    }
    
    public void setC1(TerrainSegment C1){
        this.C1 = C1;
    }
    public void setC2(TerrainSegment C2){
        this.C2 = C2;
    }
    public void setC3(TerrainSegment C3){
        this.C3 = C3;
    }
    public void setidT(double idT){
        this.idT = idT;
    }
    
    //construteur terraintriangle
    public TerrainTriangle(TerrainSegment C1, TerrainSegment C2,TerrainSegment C3){
        this.C1=C1;
        this.C2=C2;
        this.C3=C3;
        this.idT = nbrTriangle++;
    }
    
    public String toString(){
        return ("[["+C1+";"+C2+";"+C3+"]]");
    }
    
    //creation des triangles du terrain;
    public static ArrayList<TerrainTriangle> Creationtriangle (ArrayList<TerrainSegment> ST, ArrayList<TerrainSegment> STr, ArrayList<TerrainPoints> P , ArrayList<TerrainPoints> PTr , boolean verifie){
        int nbrST= ST.size();
        int nbrSTr = STr.size();
        int nbrP = P.size();
        int nbrPTr = PTr.size();
        int i, j ,k ,sortie; 
        TerrainTriangle TTtempo;
        ArrayList <TerrainTriangle> TT = new ArrayList<TerrainTriangle>();
        if(verifie == true){
            
        }
        if(verifie == false){
            TTtempo = new TerrainTriangle (ST.get(0),ST.get(1),STr.get(0));
            TT.add(TTtempo);
            j=0;
            for(i=2;i<nbrST-2;i++){
               TTtempo= new TerrainTriangle( STr.get(j),ST.get(i),STr.get(j+1));
               TT.add(TTtempo);
               j=j+1;
            }
            TTtempo = new TerrainTriangle(ST.get(nbrST-2),ST.get(nbrST-1),STr.get(nbrSTr-1));
            TT.add(TTtempo);
         
    }
        return TT;
}
}
