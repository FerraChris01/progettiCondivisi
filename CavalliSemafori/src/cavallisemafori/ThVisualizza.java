/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cavallisemafori;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris
 */
public class ThVisualizza extends Thread{
    private dataStorage dt;
    
    public ThVisualizza(dataStorage dt)
    {
        this.dt = dt;
    }
    @Override
    public void run()
    {
        while (!dt.getFinito())
        {
            try {
                System.out.println(dt.getClop());            
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThVisualizza.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (Thread.interrupted()) return;
        }
    }
}
