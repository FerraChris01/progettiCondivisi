/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cavallisemafori;

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
        
            visualizza dt = new visualizza();
            Semaforo sem1 = new Semaforo(1);
            ThCavallo clop1 = new ThCavallo("clop1", dt, sem1);
            ThCavallo clop2 = new ThCavallo("clop2", dt, sem1);
            ThCavallo clop3 = new ThCavallo("clop3", dt, sem1);
            ThCavallo clop4 = new ThCavallo("clop4", dt, sem1);
            ThCavallo clop5 = new ThCavallo("clop5", dt, sem1);
            ThVisualizza thVis = new ThVisualizza(dt);
            
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
        
        dt.setFinito();
        
        clop1.interrupt();
        clop2.interrupt();
        clop3.interrupt();
        clop4.interrupt();
        clop5.interrupt();
        thVis.interrupt();
    }
    
}
