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
    public void setC2(TerrainSegment CC){
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
    
    
    //creation des triangles du terrain;
    public static ArrayList<TerrainTriangle> Creationtriangle (ArrayList<TerrainSegment> ST){
        int nbrST= ST.size();
        ArrayList <TerrainTriangle> TT = new ArrayList<TerrainTriangle>();
        
        return TT;
    }
}
