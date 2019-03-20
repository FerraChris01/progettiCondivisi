/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giocodellotto;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris
 */
public class ThGenera extends Thread{
    private dataStorage data;
    private Semaphore generaCerca;
    private Semaphore cercaGenera;
    
    public ThGenera(dataStorage data, Semaphore generaCerca, Semaphore cercaGenera)
    {
        this.data = data;
        this.generaCerca = generaCerca;
        this.cercaGenera = cercaGenera;
    }
    @Override
    public void run()
    {
        Random rn = new Random();
        while(!data.getFinito())
        {
            try {
                cercaGenera.acquire();
                cercaGenera.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(ThGenera.class.getName()).log(Level.SEVERE, null, ex);
            }
            int[] vect = data.getVectNumEstratti();
            for (int i = 0; i < 5; i++)
                vect[i] = rn.nextInt(10) + 1;            
            
            
            if (data.estrazioneFinita()) data.setFinito();      
            else data.incNumElEstrazioni();
            
            generaCerca.release();
            generaCerca.release();
        }
        
    }
}
