/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spazipuntistatistiche;

import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ferrareis_christian
 */
public class SpaziPuntiStatistiche {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Semaphore visualizzaGenera = new Semaphore(1);
        Semaphore generaCerca = new Semaphore(0);
        Semaphore cercaVisualizza = new Semaphore(0);
        
        Scanner sc = new Scanner(System.in);
        int numChar;
        
        System.out.println("Inserire il numero di caratteri da estrarre: ");
        numChar = sc.nextInt();
        
        dataStorage data = new dataStorage();
        ThGenera thg = new ThGenera(data, numChar, visualizzaGenera, generaCerca);
        ThCerca thc1 = new ThCerca(data, true, generaCerca, cercaVisualizza);
        ThCerca thc2 = new ThCerca(data, false, generaCerca, cercaVisualizza);
        ThVisualizza thv = new ThVisualizza(data, cercaVisualizza, visualizzaGenera);        

        try {
            thg.join();
            thc1.join();
            thc2.join();
            thv.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(SpaziPuntiStatistiche.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
