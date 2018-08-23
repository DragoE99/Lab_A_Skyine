package Soluzione;

import java.util.ArrayList;
import java.util.Collections;

public class Citta{
    //campi
    private int anno; 
    private ArrayList<Edificio> listaEdifici;
    //costruttore
    public Citta(int anno, ArrayList<Edificio> listaEdifici){
        this.anno = anno;
        this.listaEdifici = listaEdifici;
    }
    //metodi
    public void build(Edificio e){
        listaEdifici.add(e);
    }
    public void demolish(Edificio e){
        listaEdifici.remove(e);
    }
    //getter
    public int getAnno(){
        return anno;
    }
    
    public ArrayList<Edificio> getLista() {
        return new ArrayList(listaEdifici);    //new ArrayList(listaEdifici)  ?? copia profonda and stuff
    }
    
    //nuovo metodo demolish che ho scritto io
    public void demolish(String id) {
        //TODO un metodo che elimina l'edificio che ha id come id
        
        for (int i = 0; i < listaEdifici.size(); ++i) {
            if (listaEdifici.get(i).getId().equals(id))
                listaEdifici.remove(i);
        }
    }
    
    public int maxDistanzaSud() {
        
        int max = 0;
        
        for(Edificio x : listaEdifici){
            if(x.getLato().equals("S")){
                if(x.getDistanza() + x.getBase() > max)
                max = x.getDistanza() + x.getBase();
            }
        }
        
        return max;
    }    
    
    public int maxDistanzaNord() {
        
        int max = 0;
        
        for(Edificio x : listaEdifici){
            if(x.getLato().equals("N")){
                if(x.getDistanza() + x.getBase() > max)
                max = x.getDistanza() + x.getBase();
            }
        }
        
        return max;
    }
    
    
    public void ordinaAltezze() {
        Collections.sort(this.listaEdifici);
    }
    
    public String toString() {
        String s;
        s = anno + "\n";
        for (Edificio e : listaEdifici)
            s += e.toString() + " ";
        return s;
    }
}
