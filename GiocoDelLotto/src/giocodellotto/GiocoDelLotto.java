/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giocodellotto;

import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris
 */
public class GiocoDelLotto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Semaphore generaCerca = new Semaphore(0);
        Semaphore cercaGenera = new Semaphore(2);
        
        Scanner sc = new Scanner(System.in);
        int nEstrazioni, primoN, secondoN;
        System.out.println("Inserire il numero di estrazioni da simulare: ");
        nEstrazioni = sc.nextInt();
        System.out.println("Inserire il primo numero della puntata: ");
        primoN = sc.nextInt();
        System.out.println("Inserire il secondo numero della puntata: ");
        secondoN = sc.nextInt();
        
        dataStorage data = new dataStorage(nEstrazioni);
        ThGenera thg = new ThGenera(data, generaCerca, cercaGenera);
        ThCerca thc1 = new ThCerca(data, true, primoN, cercaGenera, generaCerca);
        ThCerca thc2 = new ThCerca(data, false, secondoN, cercaGenera, generaCerca);        
        
        thg.start();
        thc1.start();
        thc2.start();
        
        try {
            thg.join();
            thc1.join();
            thc2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(GiocoDelLotto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int nVincite = 0;
        for (Estrazione es : data.getVectEstrazioni())
            if (es.getVincita()) nVincite++;
        
        System.out.println("Si e' fatto ambo " + nVincite + " volte");        
    }
    
}
