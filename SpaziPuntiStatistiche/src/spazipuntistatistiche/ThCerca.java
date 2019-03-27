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
        do 
        {
            try {
                generaCerca.acquire();
                System.out.println("risvegliato");
            } catch (InterruptedException ex) {
                Logger.getLogger(ThCerca.class.getName()).log(Level.SEVERE, null, ex);
            }
            char[] temp = data.getBuffer();
            for (int i = 0; i < temp.length; i++)
            {
                if (tipo)
                {
                    if (temp[i] == '.') 
                    {
                        data.incPuntiLetti();
                        System.out.println("letto punto " + tipo);
                    }                    
                }
                else
                {
                    if (temp[i] == ' ') 
                    {
                        data.incSpaziLetti();
                        System.out.println("letto spazio " + tipo);
                    }                    
                }                    
            }
            cercaVisualizza.release();
        } while (!data.getFinito());
    }
}
