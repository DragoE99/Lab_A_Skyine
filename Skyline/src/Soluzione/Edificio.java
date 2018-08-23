package Soluzione;

public class Edificio implements Comparable{
    //campi
    private int anno;
    private String id;
    private String lato;
    private int distanza;
    private int base;
    private int altezza;
    //costruttore
    public Edificio(int anno, String id, String lato, int distanza, int base, int altezza){
        this.anno = anno;
        this.id = id;
        this.lato = lato;
        this.distanza = distanza;
        this.base = base;
        this.altezza = altezza;
    }
    //metodi
    
    //getter
    public int getAnno(){
        return anno;
    }
    public String getId(){
        return id;
    }
    public String getLato(){
        return lato;
    }
    public int getDistanza(){
        return distanza;
    }
    public int getBase(){
        return base;
    }
    public int getAltezza(){
        return altezza;
    }
    
    //toString
    public String toString() {
        return id + " " + lato + " " + distanza + " " + base + " " + altezza;
    }
    
    @Override
    public int compareTo(Object arg0) {
        
        if(arg0 instanceof Edificio) 
            return this.compareTo((Edificio) arg0);
        
        else throw new IllegalArgumentException();
      
    }
    
    public int compareTo(Edificio e) {
        return e.getAltezza() - this.getAltezza();
    }
 }
