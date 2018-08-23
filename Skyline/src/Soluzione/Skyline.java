package Soluzione;

import java.io.File;
import java.util.ArrayList;

public class Skyline {

    public static void main(String[] args) {
    	
    	System.out.println("udefij");
        
        File edifici = new File(args[0]);
        File comandi = new File(args[1]);
        //File f = new File("C:\\Users\\Nika\\Downloads\\evSet.txt");
        
        //ArrayList<Citta> hhhh = Operazioni.importa(f);
        ArrayList<Citta> hhhh = Operazioni.importa(edifici);
        //importaComandi(comandi);
        
        //Operazioni.importaComandi(new File("C:\\Users\\Nika\\Downloads\\cmdSet.txt"));
        Operazioni.esegui(comandi);
        System.out.println(hhhh.toString());

    }
    
}
