/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spazipunti;

import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ferrareis_christian
 */
public class SpaziPunti {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Inserire il numero di caratteri da generare: ");        
        int numChar = sc.nextInt();
        
        Semaphore generaCerca = new Semaphore(1);
        Semaphore cercaGenera = new Semaphore(0);
        Semaphore visCerca = new Semaphore(0);
        Semaphore cercaVis = new Semaphore(1);
        dataStorage data = new dataStorage();
        ThGenera thg = new ThGenera(data, numChar, generaCerca, cercaGenera);
        ThCerca thc1 = new ThCerca(true, data, cercaGenera, generaCerca, visCerca, cercaVis);
        ThCerca thc2 = new ThCerca(false, data, cercaGenera, generaCerca, visCerca, cercaVis);
        ThVisualizza thv = new ThVisualizza(cercaVis, visCerca, data);
        
        thg.start();
        thc1.start();
        thc2.start();
        thv.start();
         
        try {
            thg.join();
            thc1.join();
            thc2.join();
            thv.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(SpaziPunti.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Numero punti inseriti: " + data.getPuntiInseriti());
        System.out.println("Numero punti letti: " + data.getNumPuntiLetti());
        System.out.println("--------------------------------------------");
        System.out.println("Numero spazi inseriti: " + data.getSpaziInseriti());
        System.out.println("Numero spazi letti: " + data.getSpaziLetti());       
        
    }
    
}
