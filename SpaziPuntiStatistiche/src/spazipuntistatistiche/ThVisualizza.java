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
public class ThVisualizza extends Thread{
    private dataStorage data;
    private Semaphore cercaVisualizza;
    private Semaphore visualizzaGenera;
    
    public ThVisualizza(dataStorage data, Semaphore cercaVisualizza, Semaphore visualizzaGenera)
    {
        this.data = data;
        this.cercaVisualizza = cercaVisualizza;
        this.visualizzaGenera = visualizzaGenera;
    }
    @Override
    public void run()
    {
        while (!data.getFinito())
        {
            try {
                cercaVisualizza.acquire();
                cercaVisualizza.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(ThVisualizza.class.getName()).log(Level.SEVERE, null, ex);
            }
            data.visualizza();
            visualizzaGenera.release();
        }
    }
}
