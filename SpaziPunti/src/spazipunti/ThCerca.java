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
 * @author ferrareis_christian
 */
public class ThCerca extends Thread{
    private boolean tipo;   //true punti false spazi
    private dataStorage data;
    private Semaphore prevSem;
    private Semaphore nextSem;
    
    public ThCerca(boolean tipo, dataStorage data, Semaphore prevSem, Semaphore nextSem)
    {
        this.tipo = tipo;
        this.data = data;
        this.prevSem = prevSem;
        this.nextSem = nextSem;
    }
    @Override 
    public void run()
    {
        while (!data.getFinito())
        {
            try {
                prevSem.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(ThCerca.class.getName()).log(Level.SEVERE, null, ex);
            }
            char[] buffer = data.getbuffer();
            for (char c : buffer)
            {
                if (tipo) 
                {
                    if (c == '.') 
                        data.incPuntiLetti();
                }
                else    
                {
                    if (c == ' ') 
                        data.incSpaziLetti();
                }
            }
            nextSem.release();            
        }
    }
}
