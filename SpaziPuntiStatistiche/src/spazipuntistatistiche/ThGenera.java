/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spazipuntistatistiche;

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
    private Semaphore visualizzaGenera;
    private Semaphore generaCerca;
    
    public ThGenera(dataStorage data, int numChar, Semaphore visualizzaGenera, Semaphore generaCerca)
    {
        this.data = data;
        this.numChar = numChar;
        this.visualizzaGenera = visualizzaGenera;
        this.generaCerca = generaCerca;
    }
    @Override
    public void run()
    {
        Random rn = new Random();
        while (numChar > 0)
        {
            try {
                visualizzaGenera.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(ThGenera.class.getName()).log(Level.SEVERE, null, ex);
            }
            data.resetNumEl();
            for (int i = 0; i  < data.getBuffer().length; i++)
            {
                int temp = rn.nextInt(28) + 65;
                char el = '&';
                if (temp == 91) el = '.';
                else if (temp == 92) el = ' ';
                else el = (char)temp;
                data.pushEl(el);
            }
            generaCerca.release();          
            generaCerca.release(); 
        }
    }
}
