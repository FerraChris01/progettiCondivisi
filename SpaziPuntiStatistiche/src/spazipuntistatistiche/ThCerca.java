/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spazipuntistatistiche;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ferrareis_christian
 */
public class ThCerca extends Thread{
    private dataStorage data;
    private boolean tipo; // punto -> true, spazio -> false
    private Semaphore generaCerca;
    private Semaphore cercaVisualizza;
    
    public ThCerca(dataStorage data, boolean tipo, Semaphore generaCerca, Semaphore cercaVisualizza)
    {
        this.data = data;
        this.tipo = tipo;
        this.generaCerca = generaCerca;
        this.cercaVisualizza = cercaVisualizza;
    }
    
    @Override 
    public void run()
    {
        while (!data.getFinito())
        {
            try {
                generaCerca.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(ThCerca.class.getName()).log(Level.SEVERE, null, ex);
            }
            char[] temp = data.getBuffer();
            for (char c : temp)
            {
                if (tipo)
                    if (c == '.') data.incPuntiLetti();
                else
                    if (c == ' ') data.incSpaziLetti();
            }
            cercaVisualizza.release();
        }
    }
}
