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
    private Semaphore prevVis;
    private Semaphore nextVis;
    
    public ThCerca(boolean tipo, dataStorage data, Semaphore prevSem, Semaphore nextSem, Semaphore prevVis, Semaphore nextVis)
    {
        this.tipo = tipo;
        this.data = data;
        this.prevSem = prevSem;
        this.nextSem = nextSem;
        this.prevVis = prevVis;
        this.nextVis = nextVis;
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
                    try {
                        if (c == '.') 
                        {
                            prevVis.acquire();
                            data.incPuntiLetti();
                            nextVis.release();       
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ThCerca.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else    
                {
                    try {
                        if (c == ' ')
                        {
                            prevVis.acquire();
                            data.incSpaziLetti();
                            nextVis.release();
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ThCerca.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            nextSem.release();            
        }
    }
}
