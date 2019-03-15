/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spazipunti;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris
 */
public class ThVisualizza extends Thread{
    private Semaphore prevSem;
    private Semaphore nextSem;
    private dataStorage data;
    public ThVisualizza(Semaphore prevSem, Semaphore nextSem, dataStorage data)
    {
        this.prevSem = prevSem;
        this.nextSem = nextSem;
        this.data = data;
    }
    @Override 
    public void run()
    {
        while (!data.getFinito())
        {
            try {
                prevSem.acquire();
                char[] temp = data.getbuffer();
                for (char c : temp)
                    System.out.println(c);
                
                System.out.println("Numero punti letti: " + data.getNumPuntiLetti());
                System.out.println("Numero spazi letti: " + data.getSpaziLetti());
                nextSem.release();
            } catch (InterruptedException ex) {
                Logger.getLogger(ThVisualizza.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
