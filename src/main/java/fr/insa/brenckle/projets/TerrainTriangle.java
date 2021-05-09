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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TerrainTriangle extends Objet{
    private TerrainSegment C1;
    private TerrainSegment C2;
    private TerrainSegment C3;
    private int idT;
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
    public int getidT(){
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
    public void setidT(int idT){
        this.idT = idT;
    }
    
    //construteur terraintriangle
    public TerrainTriangle(TerrainSegment C1, TerrainSegment C2,TerrainSegment C3){
        this.C1=C1;
        this.C2=C2;
        this.C3=C3;
        this.idT = nbrTriangle++;
    }
    
    //constructeur pour le téléchargement
    public TerrainTriangle(int id,double P1x,double P1y,double P2x,double P2y, double P3x,double P3y){
        TerrainPoints P1 = new TerrainPoints(P1x,P1y);
        TerrainPoints P2 = new TerrainPoints(P2x,P2y);
        TerrainPoints P3 = new TerrainPoints(P3x,P3y);
        TerrainSegment C1 = new TerrainSegment(P1,P2);
        TerrainSegment C2 = new TerrainSegment(P2,P3);
        TerrainSegment C3 = new TerrainSegment(P3,P1);
        this.C1=C1;
        this.C2=C2;
        this.C3=C3;
        this.idT = id;
        nbrTriangle++;
    
    }
    
    public String toString(){
        return ("[["+C1+";"+C2+";"+C3+"]]"+idT);
    }
    
    //creation des triangles du terrain;
public static ArrayList<TerrainTriangle> Creationtriangle (ArrayList<TerrainSegment> ST, ArrayList<TerrainSegment> STr, boolean verifie){
        int nbrST= ST.size();
        int nbrSTr = STr.size();
        double yminPT;
        int i, j ,k, r,cond, sortie=0; 
        TerrainTriangle TTtempo;
        ArrayList <TerrainTriangle> TT = new ArrayList<TerrainTriangle>();
        if(verifie == true){//forme speciale
            yminPT=ST.get(0).getA().getPy();
            for(i=0;i<nbrST-1;i++){
                if(yminPT>ST.get(i).getA().getPy());
                yminPT=ST.get(i).getA().getPy();
            }
            //initilisation
            if(ST.get(0).getA().getPx()==ST.get(1).getA().getPx()){
                TTtempo = new TerrainTriangle(ST.get(0),ST.get(1),TerrainSegment.InvSegment(STr.get(0)));
                TT.add(TTtempo);
                j=2;
                cond=1;
                k=nbrST-1;
            }else{
                TTtempo = new TerrainTriangle(ST.get(nbrST-1),ST.get(0),TerrainSegment.InvSegment(STr.get(0)));
                TT.add(TTtempo);
                j=1;
                cond=0;
                k=nbrST-2;
            }
                for(i=0;i<nbrSTr-1;i++){
                     //pour gerer un "segment point", ne pas faire de verticale juste apres le point le plus faible
                    if((STr.get(i).getA().getPx()==STr.get(i).getB().getPx())&&(STr.get(i).getA().getPy()==STr.get(i).getB().getPy())){
                        i=i+1;
                        //prend en compte si le point le plus bas est l'avant dernier du terrain : condition speciale 
                        if((i==nbrSTr-1)&&(ST.get(j+2).getB().getPx()==ST.get(j).getA().getPx())&&(ST.get(j+2).getB().getPy()==ST.get(j).getA().getPy())){
  
                            TTtempo = new TerrainTriangle(ST.get(j),ST.get(j+1),ST.get(j+2));
                            TT.add(TTtempo);
                            sortie=1;//pour gerer la fin du terrain
                        }else{
                        TTtempo= new TerrainTriangle(ST.get(j),TerrainSegment.InvSegment(STr.get(i)),ST.get(k));
                        TT.add(TTtempo);
                        }
                        j=j+1;
                        k=k-1;
                        if(cond==1){
                            cond=0;
                        }else{
                            cond=1;
                        }
                    }
                    //pour gerer les verticales, ne pas finir sur une verticale recente
                    if(i!=nbrSTr-1){
                    if((STr.get(i).getB().getPx()==STr.get(i+1).getB().getPx())&&(STr.get(i).getA().getPx()==STr.get(i+1).getA().getPx())){
                        if(cond==1){
                            cond=0;
                            j=j+1;
                            i=i+1;
                        }else{
                            cond=1;
                            j=j+1;
                            i=i+1; 
                        }
                        }
                    
                    r = i%2;
                    if(i!=nbrSTr-1){
                    if((r!=0)&&(cond==0)){
                        TTtempo = new TerrainTriangle(STr.get(i),TerrainSegment.InvSegment(STr.get(i+1)),ST.get(k));
                        TT.add(TTtempo);
                        k=k-1;
                    }
                    if((r==0)&&(cond==0)){
                        TTtempo= new TerrainTriangle(STr.get(i),ST.get(j),TerrainSegment.InvSegment(STr.get(i+1)));
                        TT.add(TTtempo);
                        j=j+1;
                    }
                    if((r!=0)&&(cond==1)){
                        TTtempo = new TerrainTriangle(STr.get(i),ST.get(j),TerrainSegment.InvSegment(STr.get(i+1)));
                        TT.add(TTtempo);
                        j=j+1;
                    }
                    if((r==0)&&(cond==1)){
                        TTtempo= new TerrainTriangle(STr.get(i),TerrainSegment.InvSegment(STr.get(i+1)),ST.get(k));
                        TT.add(TTtempo);
                        k=k-1;
                    }
                }
                }
                }
                //fermeture du terrain
                if(sortie==0){
                if(yminPT!=ST.get(j).getB().getPy()){
                    TTtempo= new TerrainTriangle(STr.get(nbrSTr-1),ST.get(j+1),ST.get(k));
                    TT.add(TTtempo);
                }else{
                    TTtempo= new TerrainTriangle(STr.get(nbrSTr-1),ST.get(j),ST.get(k));
                    TT.add(TTtempo);
                }
                }
                //retire d'eventuel erreur du au condition , notamment autour d'un point bas 
                for(i=0;i<TT.size()-1;i++){
                    if((TT.get(i).getC1().getB().getPx()==TT.get(i).getC2().getB().getPx())&&(TT.get(i).getC3().getA().getPx()==TT.get(i).getC2().getB().getPx())&&(TT.get(i).getC3().getA().getPy()==TT.get(i).getC2().getB().getPy())&&(TT.get(i).getC1().getB().getPy()==TT.get(i).getC2().getB().getPy())){
                        TT.remove(i);
                    }
                }
        }
        //forme polygonale
        if(verifie == false){
            TTtempo = new TerrainTriangle (ST.get(0),ST.get(1),TerrainSegment.InvSegment(STr.get(0)));
            TT.add(TTtempo);
            j=0;
            for(i=2;i<nbrST-2;i++){
               TTtempo= new TerrainTriangle( STr.get(j),ST.get(i),TerrainSegment.InvSegment(STr.get(j+1)));
               TT.add(TTtempo);
               j=j+1;
            }
            TTtempo = new TerrainTriangle(STr.get(nbrSTr-1),ST.get(nbrST-2),ST.get(nbrST-1));
            TT.add(TTtempo);
         
    }
        System.out.println(TT);
        return TT;
}

    @Override
    public double distancePoint(double abs, double ord) {  //distance avec le centre du triangle
        return Double.MAX_VALUE; //A FAIRE
    }

    @Override
    public void draw(GraphicsContext context) {
        double [] x = new double [3]; 
        double [] y = new double [3];
        x[0] = this.getC1().getA().getPx(); y[0] = this.getC1().getA().getPy();
        x[1] = this.getC2().getB().getPx(); y[1] = this.getC2().getB().getPy();
        x[2] = this.getC2().getA().getPx(); y[1] = this.getC2().getA().getPy();
        context.setFill(Color.AQUA.deriveColor(1, 1, 1, 0.2));
        context.strokePolygon(x, y, 3);
        context.fillPolygon(x, y, 3);
    }
    public void drawSelection(GraphicsContext context) {
        
    }
}
