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
public class TerrainTriangle extends TerrainSegment{
    private TerrainSegment C1;
    private TerrainSegment C2;
    private TerrainSegment C3; 
    
    public TerrainSegment getC1(){
        return C1;
    }
    public TerrainSegment getC2(){
        return C2;
    }
    public TerrainSegment getC3(){
        return C3;
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
}
