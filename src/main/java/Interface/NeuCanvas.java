/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import fr.insa.brenckle.projets.Barre;
import fr.insa.brenckle.projets.Charge;
import fr.insa.brenckle.projets.Noeud;
import fr.insa.brenckle.projets.Objet;
import fr.insa.brenckle.projets.TerrainPoints;
import fr.insa.brenckle.projets.Treillis;
import java.util.ArrayList;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author Guillaume R
 */
public class NeuCanvas extends Pane {
    
    private Interface I;
    private Canvas canvas;
    
    
    
    public NeuCanvas (Interface I){
        this.I = I;
        this.canvas = new Canvas (this.getWidth(), this.getHeight());
        this.getChildren().add(this.canvas);
        this.canvas.heightProperty().bind(this.heightProperty());
        this.canvas.widthProperty().bind(this.widthProperty());
        
        Text absSouris = new Text("X:0"); absSouris.setStyle("-fx-font-family: \"Segoe UI Semibold\"");
        Text ordSouris = new Text ("Y:0"); ordSouris.setStyle("-fx-font-family: \"Segoe UI Semibold\"");
        VBox posSouris = new VBox(3, absSouris, ordSouris);
        
        this.getChildren().add(posSouris);
        posSouris.setLayoutX(6); posSouris.setLayoutY(2);
        
        this.canvas.heightProperty().addListener((o) -> {
           this.redraw(); 
        });
        this.canvas.widthProperty().addListener((o) -> {
            this.redraw();
        });    
        this.canvas.setOnMouseDragged((t) -> {
            System.out.println(t.getX());
        });
        this.canvas.setOnScroll((t) -> {  //zoom, dézoom
            double x = t.getSceneX();
            double y = t.getSceneY();
            if (t.getDeltaY()>0){
                this.zoom(1.1, x, y);               
            } else if (t.getDeltaY()<0){
                this.zoom(0.9, x, y);
            }                    
        });
        ArrayList<Double> xy = new ArrayList<Double>();
        this.canvas.setOnMouseDragged((t) -> {  //déplacer graph avec la souris       
            canvas.setCursor(Cursor.CLOSED_HAND);
            if (xy.size()<4){   //liste qui retient les coordonnées de déplacement de la souris
                xy.add(t.getSceneX()); xy.add(t.getSceneY());
            } else if (xy.size() >= 4){   //déplace
                double dx = xy.get(2)-xy.get(0);
                double dy = xy.get(3)-xy.get(1);
                canvas.setTranslateX(canvas.getTranslateX()+dx);
                canvas.setTranslateY(canvas.getTranslateY()+dy);
                xy.clear();
                this.redraw();
            }
        });
        this.canvas.setOnMouseReleased((t) -> {
            xy.clear();
            canvas.setCursor(Cursor.DEFAULT);
        });
        this.canvas.setOnMouseClicked((t) -> {
            I.getControleur().clicDansInterface(t);
        });
        this.canvas.setOnMouseMoved((t) -> {
            double y = canvas.getHeight() - t.getY();
            absSouris.setText("X:"+ Double.toString(Math.round(t.getX()*100)/100));
            ordSouris.setText("Y:"+ Double.toString(Math.round(y*100)/100)); 
            I.getControleur().mouvementSouris(t);
        });

    }
    
    public GraphicsContext getGraphicsContext2D(){
        return this.getCanvas().getGraphicsContext2D();
    }
    
    public void redraw(){
        double largeur = canvas.getHeight();
        GraphicsContext context = this.getCanvas().getGraphicsContext2D();
        //canvas.setRotate(180);
        Treillis modele = this.getI().getTreillis();
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        context.setStroke(Color.web("#cccccc"));
        context.setLineWidth(6);
        context.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        ArrayList<TerrainPoints> listPT = new ArrayList<TerrainPoints>(this.getI().getMenuPrincipal().getMenuCreation().getSaisiePointTerrain().getP());
        for (TerrainPoints TP: listPT){   //redessine les premiers points
                TP.draw(context, largeur);
        }        
        
        for (int i = 0; i<modele.getTerrainTriangles().size(); i++){
            
                modele.getTerrainTriangles().get(i).getC1().getA().draw(context, largeur);  //dessine les points terrain
                modele.getTerrainTriangles().get(i).getC1().getB().draw(context, largeur);
                modele.getTerrainTriangles().get(i).getC2().getB().draw(context, largeur);

                modele.getTerrainTriangles().get(i).getC1().draw(context, largeur);  //dessin les segments terrain
                modele.getTerrainTriangles().get(i).getC2().draw(context, largeur); 
                modele.getTerrainTriangles().get(i).getC3().draw(context, largeur);
        }
        
        for (Barre b: modele.getBarres()){ //dessine les barres
            b.draw(context, largeur);
        }
        for (Charge c: modele.getCharge()){
            c.draw(context, largeur);
        }
        for (Noeud n: modele.getNoeuds()){ //dessine les noeuds
            n.draw(context, largeur);
        }
        if(I.getControleur().getSelection().isEmpty() == false) {
            for (Objet obj:I.getControleur().getSelection()){
            obj.drawSelection(context, canvas.getHeight());
        }
        }

    }
    
    public void zoom(double zoomRatio, double posX, double posY){
        double x = canvas.getScaleX();
        double y = canvas.getScaleY();
        canvas.setScaleX(x*zoomRatio); 
        canvas.setScaleY(y*zoomRatio);  
        this.redraw();
    }
    
    public void changeCouleurTerrain(Color couleur){ 
        Treillis modele = this.I.getTreillis();
                for (int i = 0; i<modele.getTerrainTriangles().size(); i++){
            
                modele.getTerrainTriangles().get(i).getC1().getA().setCouleur(couleur);  
                modele.getTerrainTriangles().get(i).getC1().getB().setCouleur(couleur);
                modele.getTerrainTriangles().get(i).getC2().getB().setCouleur(couleur);

                modele.getTerrainTriangles().get(i).getC1().setCouleur(couleur);  
                modele.getTerrainTriangles().get(i).getC2().setCouleur(couleur); 
                modele.getTerrainTriangles().get(i).getC3().setCouleur(couleur);
        }
    }
    
    
    
    /**
     * @return the I
     */
    public Interface getI() {
        return I;
    }

    /**
     * @return the canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }
    
    
    
    
 
}

