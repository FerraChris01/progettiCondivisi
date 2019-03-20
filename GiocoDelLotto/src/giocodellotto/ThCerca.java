/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giocodellotto;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris
 */
public class ThCerca extends Thread{
    private dataStorage data;
    private boolean tipo; // true -> primo, false -> secondo
    private int numero;
    private Semaphore cercaGenera;
    private Semaphore generaCerca;
    
    public ThCerca(dataStorage data, boolean tipo, int numero, Semaphore cercaGenera, Semaphore generaCerca)
    {
        this.data = data;
        this.tipo = tipo;
        this.numero = numero;
        this.cercaGenera = cercaGenera;
        this.generaCerca = generaCerca;
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
            int[] vect = data.getVectNumEstratti();
            for(int e : vect)
            {
                if (e == numero)
                {
                    if (tipo)
                        data.getElDiNumEl().setPrimoN();
                    else 
                        data.getElDiNumEl().setSecondoN();
                }
            }
            cercaGenera.release();
        }
    }
    
}
