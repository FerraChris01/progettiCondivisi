package cavallisemafori;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chris
 */
public class ThCavallo extends Thread{
    private Semaforo sem1;
    private String verso;
    private visualizza dt;
    private int nPassi;
    
    public ThCavallo(String verso, visualizza dt, Semaforo sem1)
    {
        this.verso = verso;
        this.dt = dt;
        this.sem1 = sem1;
        nPassi = 0;
    }
    @Override
    public void run()
    {
        while (nPassi < 50)
        {
            sem1.Wait();
            dt.setClop(verso);
            sem1.Signal();
            nPassi++;
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThCavallo.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (Thread.interrupted()) return;
        }
    }
}
