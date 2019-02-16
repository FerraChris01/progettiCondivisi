/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cavallisemafori;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris
 */
public class CavalliSemafori {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
            String vincitore;
            dataStorage dt = new dataStorage();
            ThCavallo clop1 = new ThCavallo("clop1", dt);
            ThCavallo clop2 = new ThCavallo("clop2", dt);
            ThCavallo clop3 = new ThCavallo("clop3", dt);
            ThCavallo clop4 = new ThCavallo("clop4", dt);
            ThCavallo clop5 = new ThCavallo("clop5", dt);
            ThVisualizza thVis = new ThVisualizza(dt);
            
            Scanner s = new Scanner(System.in);
            System.out.println("Su quale cavallo scommetti?");
            vincitore = s.nextLine();
            
            clop1.start();
            clop2.start();
            clop3.start();
            clop4.start();
            clop5.start();
            thVis.start();
          
        try {
            clop1.join();
            clop2.join();
            clop3.join();
            clop4.join();
            clop5.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(CavalliSemafori.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        clop1.interrupt();
        clop2.interrupt();
        clop3.interrupt();
        clop4.interrupt();
        clop5.interrupt();
        thVis.interrupt();
        
        String temp = dt.getVincitore();
        if (temp == vincitore) System.out.println("Hai vinto! Vince il cavallo " + temp);
        else System.out.println("Hai perso! Vince il cavallo " + temp);        
    }
    
}
