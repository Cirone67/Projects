/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;

/**
 *
 * @author brenc
 */
public class Matrice {
  //définition des attributs de la classe
    private int nbrLig;
    private int nbrCol;
    private double [][] coeffs;
    private static final double EPSILON_PIVOT =1E-8;
//Constructeur (this c'est la matrice qui va etre créée)
Matrice(int nl, int nc){
this.nbrLig = nl;
this.nbrCol = nc;
this.coeffs = new double [nl][nc];
}

//Méthode matrice identité
static Matrice identite(int n){
    Matrice id = new Matrice(n,n);
    for(int i=0; i<n; i++){
        id.setMij( i, i ,1);
    }
           
   return id;
}

//Méthode matrice matTest1

static Matrice matTest1(int n){
    Matrice matTest1 = new Matrice(n,n);
    int a =0;
    for(int i=0;i<n;i++){
        for(int j=0; j<n; j++){
           matTest1.setMij(i,j,a); 
            
        a=a+1;    
        }
    }
    
    return matTest1;
}

//Méthode matrice matTest2

static Matrice matTest2(int n){
     Matrice matTest2 = new Matrice(n,n);
    int a =0;
    for(int i=0;i<n;i++){
        for(int j=0; j<n; j++){
            if(i==j){
           matTest2.setMij(i,j,-a);
            }else{
           matTest2.setMij(i,j,a); 
            }
        a=a+1;
        }
    }
    
    return matTest2;
}

//Méthode aléaUnOuDeux

static int aleaUnOuDeux(){
    int alea;
    alea =(int)( Math.random()*10)%2+1;
    return alea;
}
//Méthode matAleaZéroUnDeux

static Matrice matAleaZeroUnDeux(int nl, int nc){
 Matrice matAleaZeroUnDeux = new  Matrice(nl,nc);
 for(int i=0;i<nl;i++){
        for(int j=0; j<nc; j++){
            double valeur1 = Math.random();
            double valeur2 = Math.random();
            if(valeur2>valeur1){
             matAleaZeroUnDeux.setMij(i,j,0);   
            }else{
            matAleaZeroUnDeux.setMij(i,j,aleaUnOuDeux());    
            }
        }
    }
 return matAleaZeroUnDeux;
}

// Méthode creeVecteur
static Matrice creeVecteur(double vecteur []){
    Matrice creevecteur = new Matrice(vecteur.length,1);
    for(int i=0; i<vecteur.length; i++){
        creevecteur.setMij(i,0, vecteur[i]);
    }
    return creevecteur;
}

//Méthode get pour nbrLig

public int getNbrLig(){
    return this.nbrLig;
}
//Méthode get pour nbrCol

public int getNbrCol(){
    return this.nbrCol;
}

//Méthode set pour nbrLig
public void setNbrLig(int ln){
    this.nbrLig=ln;
}
//Méthode set pour nbrCol
public void setNbrCol(int lc){
    this.nbrCol=lc;
}

//Méthode get coeffs
public double getMij(int i, int j){
    return this.coeffs[i][j];
}

//Méthode set coeffs
public void setMij(int i, int j, double x){
    this.coeffs[i][j]=x;
}


//Méthodes String pour l'affichage
public void String(){
    for(int i=0;i<this.nbrLig;i++){
        for(int j=0; j<this.nbrCol; j++){
            System.out.print(formatDouble(this.getMij(i,j)) + "\t");
        }
    System.out.println();
    }
System.out.println();
}

    @Override
    public String toString() {
        // oui, il serait plus efficace d'utiliser un {@link java.lang.StringBuilder}
        // mais ils n'ont pas été vu
        String res = "";
        for (int i = 0; i < nbrLig; i++) {
            res = res + "[";
            for (int j = 0; j < nbrCol; j++) {
                res = res + formatDouble(this.getMij(i, j));
                if (j < nbrCol - 1) {
                    res = res + " ";
                }
            }
            res = res + "]\n";
        }
        return res;
    }

public String formatDouble(double x){
    return String.format("%+4.2E",x);
}

//Méthode concaténation Ligne

public Matrice concatLig(Matrice N){
    if(this.nbrCol== N.nbrCol){
Matrice MconcN = new Matrice(N.nbrLig+this.nbrLig,this.nbrCol);
for(int i=0;i<this.nbrLig;i++){
        for(int j=0; j<this.nbrCol; j++){
            MconcN.setMij(i,j,this.getMij(i,j));
        } 
}
for(int i=this.nbrLig;i<N.nbrLig+this.nbrLig;i++){
        for(int j=0; j<N.nbrCol; j++){
           MconcN.setMij(i,j, N.getMij(i-this.nbrLig,j));
        }
}
return MconcN; 
    }else{
      throw new Error("Pas le meme nombre de colonne");  
}  
}

//Méthode cacaténation Colonne
public Matrice concatCol(Matrice N){
    if(this.nbrLig == N.nbrLig){
        
Matrice MconcN = new Matrice(this.nbrLig,this.nbrCol+N.nbrCol);
System.out.println("nbr lig " +this.nbrLig);
for(int i=0;i<this.nbrLig;i++){
        for(int j=0; j<this.nbrCol; j++){
            MconcN.setMij(i,j,this.getMij(i,j));
        }
}
for(int i=this.nbrCol;i<N.nbrCol+this.nbrCol;i++){
        for(int j=0; j<N.nbrLig; j++){
           MconcN.setMij(j,i,N.getMij(j, i-this.nbrCol));
        }
}
return MconcN;
    }else{
      throw new Error("Pas le meme nombre de ligne");  
}  
}

   //Méthode transposition
  public  static Matrice transposition(Matrice m){
        Matrice transpose = new Matrice(m.nbrCol,m.nbrLig);
        for(int i=0; i<m.nbrCol; i++){
            for (int j=0; j<m.nbrLig; j++){
                transpose.setMij(i,j,m.getMij(j,i)) ;
            }
        }
    return transpose;
}
// Méthode supligne
    
  public  static Matrice subLignes(int min,int  max, Matrice m ){
        if (min < 0 || max < min || max >= m.getNbrLig()) {
            throw new Error("indices lignes invalides");
        }
        Matrice sublignes = new Matrice(max-min+1,m.nbrCol);
        for(int i=0; i<sublignes.nbrLig; i++){
            for (int j=0; j<sublignes.nbrCol; j++){
                sublignes.setMij(i,j,m.getMij(min+i,j)) ;
            }
        }
        return sublignes;
    }
    
    // Méthode subcols
    
  public  static Matrice subCols(int min,int  max, Matrice m ){
        if (min < 0 || max < min || max >= m.getNbrCol()) {
            throw new Error("indices colonnes invalides");
        }
        Matrice subcols = new Matrice(m.nbrLig,max-min+1);
        for(int i=0; i<subcols.nbrLig; i++){
            for (int j=0; j<subcols.nbrCol; j++){
                subcols.setMij(i,j,m.getMij(i,min+j));
            }
        }
        return subcols;
    }
    
// Addition de 2 matrices
  public Matrice add(Matrice m2) {
        if (this.getNbrLig() != m2.getNbrLig() || this.getNbrCol() != m2.getNbrCol()) {
            throw new Error("tailles incompatibles pour add");
        }
        Matrice res = new Matrice(this.getNbrLig(), this.getNbrCol());
        for (int i = 0; i < res.getNbrLig(); i++) {
            for (int j = 0; j < res.getNbrCol(); j++) {
                res.setMij(i, j, this.getMij(i, j) + m2.getMij(i, j));
            }
        }
        return res;
 }
     
//Multiplication de 2 matrices
   public Matrice mult(Matrice m2) {
        if (this.getNbrCol() != m2.getNbrLig()) {
            throw new Error("tailles incompatibles pour mult");
        }
        Matrice res = new Matrice(this.getNbrLig(), m2.getNbrCol());
        for (int i = 0; i < res.getNbrLig(); i++) {
            for (int j = 0; j < res.getNbrCol(); j++) {
                double cur = 0;
                for (int k = 0; k < this.getNbrCol(); k++) {
                    cur = cur + this.getMij(i, k) * m2.getMij(k, j);
                }
                res.setMij(i, j, cur);
            }
        }
        return res;
    }
   
//Calcul la matrice opposée
   public Matrice opp() {
        Matrice res = new Matrice(this.getNbrLig(), this.getNbrCol());
        for (int i = 0; i < res.getNbrLig(); i++) {
            for (int j = 0; j < res.getNbrCol(); j++) {
                res.setMij(i, j, -this.getMij(i, j));
            }
        }
        return res;
    }
   
//Méthode de la soustraction this-m2
    public Matrice moins(Matrice m2) {
        return this.add(m2.opp());
    }   
//__________________Méthode du pivot de Gauss________________________________
//Class ResGauss pour renvoier le résultat de la descente de Gauss
/*
    public ResGauss descenteGauss(){
        int sig =1;
        int i;
        for(i=0; i<this.getNbrLig()-1;i++){
            System.out.println("______________________________________");
            this.String();
            int lPGP = lignePlusGrandPivot(i);
            System.out.println("Pivot de la colonne"+ i + "trouve à  la ligne" + lPGP);
            sig = sig * permuteLigne(this,i,lPGP);
            System.out.println("Matrice apres permutation");
            this.String();
            
            for(int j=i+1; j<this.getNbrLig();j++){
                transvection(i,j);
            }
            System.out.println("Résultat");
            System.out.println("______________________________________");
            
            this.String();
        
        }
 return new ResGauss(i,sig); // ???? i pas sur à 100% ????
}
 */   
    public Matrice Gauss(){
        int sig =1;
        int i;
        for(i=0; i<this.getNbrLig()-1;i++){
            //System.out.println("______________________________________");
            //this.String();
            int lPGP = lignePlusGrandPivot(i);
            //System.out.println("Pivot de la colonne"+ i + "trouve à  la ligne" + lPGP);
            sig = sig * permuteLigne(this,i,lPGP);
            //System.out.println("Matrice apres permutation");
            //this.String();
            
            for(int j=i+1; j<this.getNbrLig();j++){
                transvection(i,j);
            }
            //System.out.println("Résultat");
            //System.out.println("______________________________________");
            
            //this.String();
        
        }
 return this;
}
// Méthode qui permutent 2 lignes
public static int permuteLigne(Matrice m,int l1,int l2){
    int compteur = 0;
    int drapeau = -1;
    double temps;
    for(int i=0;i<m.getNbrCol();i++){ 
        if(m.getMij(l1, i)== m.getMij(l2, i)){
            compteur = 1;
        }else{
        temps = m.getMij(l2, i);
        m.setMij(l2, i,m.getMij(l1, i));
        m.setMij(l1, i,temps);
    }
    }
    if(compteur == m.getNbrCol()){
        drapeau = 1;
    }
    return drapeau;
}
//Méthode transvection : calcule à l'aide du pivot
public void transvection (int i1, int i2){
if(getMij(i1,i1)==0) {
throw new Error("Pivot est nul");
}
double p = getMij(i2,i1)/getMij(i1,i1);
setMij(i2,i1,0);
// pour j différent de i1
for(int j=0; j<i1; j++) {
   this.setMij(i2,j, getMij(i2,j) - p*getMij(i1,j));
		}

for(int j=i1+1; j<getNbrCol(); j++) {
    this.setMij(i2,j, getMij(i2,j) - p*getMij(i1,j));
		}
}

//Méthode lignePlusGrandPivot : renvoie la ligne avec le plus grand nbr
 public int lignePlusGrandPivot(int l){
     int imax =-1; 
     double max =0;// Si que des 0, imax =-1;
   for(int i=l; i< this.getNbrLig(); i++) {
     if(Math.abs(this.getMij(i, l))>max) {
         imax = i;
         max = Math.abs(this.getMij(i, l));
     }
   }
    if(imax>-1){
            if(Math.abs(this.getMij(imax,l))<= EPSILON_PIVOT){
             imax = -1;
        }
    }   
return imax;
}
//______________________________________________________________________________

//Méthode qui résoud après le pivot et renvoie une matrice colonne solution de l'équation
 public double resolLig(int i){
     double valeur;
     double somme=0;
         for(int j=i+1;j<this.getNbrCol()-1;j++){
           somme = this.getMij(i, j)+somme; 
         }
      valeur = (this.getMij(i, this.getNbrCol()-1)-somme)/this.getMij(i, i);
             
     
     return valeur;
 }
 //Méthode qui multiplie la colonne souhaitée
public void mulColonne(int j, double temps){
   for(int i=0;i<this.getNbrLig();i++){
            this.setMij(i,j,temps*getMij(i,j)); 
        }
}

public Matrice solution(){
    Matrice res = new Matrice(this.getNbrLig(),1);
    for(int i=this.getNbrLig()-1; i>=0;i=i-1){
        double temps =this.resolLig(i);
        res.setMij(i,0,temps );
        this.mulColonne(i,temps);
    }
    return res;
}


//Méthode pour résoudre les systèmes linéaires
public Matrice resoudreSyst(Matrice vecteur){ // Systèmesousformedematrice.resoudreSyst(vecteurdelautrecotedel'égalité);
    Matrice res = this.concatCol(vecteur);
    res = res.Gauss().solution();
    //res.String();
    
return res;    
}
/*
//Méthode principale main
public static void main(String[] args){
    int nl, nc;
    System.out.println("Nombre de ligne");
    nl = Lire.i();
    System.out.println("Nombre de collonne");
    nc = Lire.i();
    Matrice m = new Matrice(nl,nc);
    Matrice m1 = new Matrice(nl,nc);
    Matrice m2 = new Matrice(nl,nc);
    //Affichage du tableau des coefficients
    for(int i=0;i<nl;i++){
        for(int j=0; j<nc; j++){
            System.out.print(m.coeffs[i][j] + "\t");
        }
    System.out.println();
    }
    
    //Affichage
    System.out.println("Nombre de colonne/ligne");
    int n;
    n = Lire.i();
    m1 = matTest1(n);
    m2 = matTest2(n);
    double vecteur [];
    vecteur = new double [n];
    for(int i=0;i<n;i++){
        vecteur[i]=i+1;
    }
    //m1 = m1.concatCol(creeVecteur(vecteur));
    
    //m1.descenteGauss();
    //System.out.println(m2.descenteGauss().toString());
    m2 = m2.concatCol(creeVecteur(vecteur));
    Matrice res =m2.Gauss().solution();
    res.String();
}
*/

}
class ResGauss{
    public int rang;
    public int signature;

//Constructeur
    public ResGauss(int rang,int signature){
     this.rang = rang;
     this.signature = signature;
    }
    public String toString(){
        String res;
        res = "{ResGauss : rang =" + this.rang + "; sigPerm =" + this.signature + " }";
     return res;
    }
}