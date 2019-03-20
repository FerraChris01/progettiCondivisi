/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spazipunti;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ferrareis_christian
 */
public class ThGenera extends Thread{
    private dataStorage data;
    private int numChar;
    private Semaphore prevSem;
    private Semaphore nextSem;
    
    public ThGenera(dataStorage data, int numChar, Semaphore prevSem, Semaphore nextSem)
    {
        this.data = data;
        this.numChar = numChar;
        this.prevSem = prevSem;
        this.nextSem = nextSem;
    }
    @Override 
    public void run()
    {
        Random rand = new Random();   //28 + 65;
        while (numChar > 0)
        {           
            while (data.getNumEl() < 10 && numChar > 0)
            {
                int k = rand.nextInt(29) + 65;
                char el = '&';
                if (k == 91) el = '.';
                else if (k == 92) el = ' ';
                else el = (char)k;
                data.pushAndInc(el);
                numChar--;                
            }
            nextSem.release();
            nextSem.release();
            if (numChar > 0)
            {
                try {
                    prevSem.acquire();
                    prevSem.acquire();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThGenera.class.getName()).log(Level.SEVERE, null, ex);
                }
                data.resetNumEl();          
            }
        }
        data.setFinito();
    }
    
}
