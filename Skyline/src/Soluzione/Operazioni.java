package Soluzione;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Operazioni {
    
    private static ArrayList<Citta> anniModifiche;
    //private static int maxDistanzaNord = 0;
    //private static int maxDistanzaSud = 0;
    //private static ArrayList<Edificio> listaNord = new ArrayList<Edificio>();
    //private static ArrayList<Edificio> listaSud = new ArrayList<Edificio>();
    

    public static ArrayList<Citta> importa(File f){
        /*ArrayList<Citta>*/ anniModifiche = new ArrayList<Citta>();
        String x;
        anniModifiche.add(new Citta(2050, new ArrayList<Edificio>()));
        int contatoreAnno = 0;
       
        try{
            BufferedReader in = new BufferedReader(new FileReader(f));
            
            while((x = in.readLine()) != null){
                
                StringTokenizer st = new StringTokenizer(x);
                
                while(st.hasMoreTokens()){
                    
                    String istruzione = st.nextToken();
                    int anno = Integer.parseInt(st.nextToken());
                    String id = st.nextToken();
                    
                    if(istruzione.equals("build")) {
                        
                        String lato = st.nextToken();                          //forse ci conviene mettere "lato" come String, altrimenti c'è qualche conversione da fare
                        int distanza = Integer.parseInt(st.nextToken());
                        int base = Integer.parseInt(st.nextToken());
                        int altezza = Integer.parseInt(st.nextToken());
                        
                        Edificio attuale = new Edificio(anno, id, lato, distanza, base, altezza);
                        
                        if (anno == anniModifiche.get(contatoreAnno).getAnno()) {
                            
                            anniModifiche.get(contatoreAnno).build(attuale);
                        
                        } else {
                        
                            anniModifiche.add(new Citta(anno, anniModifiche.get(contatoreAnno).getLista()));   //copia della citta precedente
                            contatoreAnno++;
                            anniModifiche.get(contatoreAnno).build(attuale);
                        
                        }
                    }
                    
                    else if(istruzione.equals("demolish")) {
                        
                        if (anno == anniModifiche.get(contatoreAnno).getAnno()) {
                            
                            anniModifiche.get(contatoreAnno).demolish(id);
                            
                        } else {
                            
                            anniModifiche.add(new Citta(anno, anniModifiche.get(contatoreAnno).getLista()));   //la copia della citta precedente
                            contatoreAnno++;
                            anniModifiche.get(contatoreAnno).demolish(id);
                          
                        }                      
                    }
                }
                
            }

            in.close();
           
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        //TEST==================================================================================00
        /*for (Citta c : anniModifiche) {
            c.ordinaAltezze();
            System.out.println(c.toString());
        }*/
        
        return anniModifiche;
    }
    
    
    public static void esegui(File f){
            
            try {
                Scanner sc = new Scanner(f);
        
                while (sc.hasNextLine()) {
                    String output = sc.nextLine();
                    StringTokenizer st = new StringTokenizer(output);
                    
                    while(st.hasMoreTokens()){
                        
                        String istruzione = st.nextToken();
                    
                        if(istruzione.equals("s")){
                            int year = Integer.parseInt(st.nextToken());
                            System.out.println(istruzione +  " " + year + " : " + size(year));
                        }
                        else if(istruzione.equals("h")){
                            int year = Integer.parseInt(st.nextToken());
                            System.out.println(istruzione +  " " + year + " : " + (float)height(year));
                        }
                        else
                            System.out.println("comando non valido");
                    }
                }
                sc.close();
            } 
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    
    //==================================================================================================================
    
    public static int size(int year){
        
        int countYear = trova(year);
        
        //ArrayList<Edificio> listaAnnoRichiesto = anniModifiche.get(countYear).getLista();
        
        /*int maxDistanzaNord = 0;
        int maxDistanzaSud = 0;
        
        for(Edificio x : listaAnnoRichiesto){
            if(x.getLato().equals("N")){
                if(x.getDistanza() + x.getBase() > maxDistanzaNord)
                maxDistanzaNord = x.getDistanza() + x.getBase();
            } else {
                if(x.getDistanza() + x.getBase() > maxDistanzaSud)
                maxDistanzaSud = x.getDistanza() + x.getBase();
            }
        }*/
        
        return  anniModifiche.get(countYear).maxDistanzaNord() + anniModifiche.get(countYear).maxDistanzaSud();
    }
 
    
    //TODO splittare la città in N e S, fare height di una e altra e somma finale
    
  //TODO Adattare height in modo che quando la cittÃ  Ã¨ costruita solo da un lato lo spazio vuoto 
    //tra fiume e primo edificio della cittÃ  non faccia media nel calcolo
    
    public static double height(int anno) {
        
    	int countYear = trova(anno);
    	int AmpN= anniModifiche.get(countYear).maxDistanzaNord();
    	int AmpS= anniModifiche.get(countYear).maxDistanzaSud();
    	boolean[] bSud = new boolean[AmpS];        
        boolean[] bNord = new boolean[AmpN];
    	anniModifiche.get(countYear).ordinaAltezze();
    	double AltMedia = 0;
    	
    	//ricavo lista di edifici dell'anno di cui voglio l'altezza
    	ArrayList<Edificio> listaAnnoRichiesto = anniModifiche.get(countYear).getLista();
    	
    	for(int i = 0; i < listaAnnoRichiesto.size(); i++){//scansione lista di edifici
    		 
    		Edificio x = listaAnnoRichiesto.get(i);
    		
    		if(x.getLato().equals("S")){
    			
    			for(int j =x.getDistanza() ; j < (x.getDistanza() + x.getBase()); j++){		//scansiono l'array nell'intervallo di valori dell'edificio
    				
    				if(bSud[j]==false){
    					AltMedia += x.getAltezza();
    					bSud[j]=true;
    				
    				}
    			}
    		
    		}else{
    			for(int j=x.getDistanza() ;	j < (x.getDistanza() + x.getBase()); j++){
    				
    				if(bNord[j] ==false){
    					AltMedia += x.getAltezza();
    					bNord[j]=true;
    				}
    			}
    			
    		}
    		
    	}
    	
    	
    	return(AltMedia/(AmpN+AmpS));    
    
    
    }
    
    
    
    
 /*   private static double mediaPesata(Stack<Edificio> sEdifici, Stack<Integer> sAmpiezze, double ampiezza){
        if(ampiezza == 0){
            return 0;
        }
        else
        {
           Edificio e = new Edificio( 0, "", "", 0, 0, 0);
           int a = 0;
            int somma = 0;
            while(!sEdifici.empty()) {
                e = sEdifici.pop();
                a = sAmpiezze.pop();
                //System.out.println(e.getAltezza());
                //System.out.println(a);
                somma += e.getAltezza() * a ;
              //sommatoria di Altezza * ampiezza(effettivamente occupata)
            }
            //System.out.println(somma/(double)ampiezza+"");
            return somma/(double)ampiezza;
        }
    }
*/
    
    
    
    private static int trova(int anno) {
        
        int countYear = anniModifiche.size() - 1;
        
        while((anniModifiche.get(countYear).getAnno() > anno) && (countYear > 0))
                countYear--;
        //System.out.println(countYear + "ffffff");
        return countYear;
    }
    
    
    
    //TODO controlla e usa questi controllori
    
    
    private void controlloAnno(int anno) throws IllegalArgumentException {
        if(anno < 2050) throw new IllegalArgumentException("Anno non valido");
    }
    /*private void controlloId(String id, int anno) throws IllegalArgumentException {
        for(ArrayList<Citta> x : anniModifiche)
            if(x.getId().equals(id)) throw new IllegalArgumentException("Nome edificio non valido");
    }*/
    private void controlloLato(String lato) throws IllegalArgumentException {
        if(!(lato.equals("N") || lato.equals("S"))) throw new IllegalArgumentException("Lato non valido");
    }
    private void controlloDistanza(int distanza) throws IllegalArgumentException {
        if(distanza < 0) throw new IllegalArgumentException("Distanza non valida");
    }
    private void controlloAltezza(int altezza) throws IllegalArgumentException {
        if(altezza < 0) throw new IllegalArgumentException("Altezza non valida");
    }
    private void controlloBase(int base) throws IllegalArgumentException {
        if(base < 0) throw new IllegalArgumentException("Base non valida");
    }
    private void controlloIstruzione(String istruzione) throws IllegalArgumentException {
        if(!(istruzione.equals("build") || istruzione.equals("demolish") || istruzione.equals("s") || istruzione.equals("h"))) throw new IllegalArgumentException("Istruzione non valida");
    }


    
    
}

      
    
    //TODO ALTRI METODI PER I CONTROLLI DELL'INPUT
    



