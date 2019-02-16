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
    private String verso;
    private dataStorage dt;
    private int nPassi;
    
    public ThCavallo(String verso,dataStorage dt)
    {
        this.verso = verso;
        this.dt = dt;
        nPassi = 0;
    }
    @Override
    public void run()
    {
        while (nPassi < 50 && !dt.getFinito())
        {
            dt.setClop(verso);
            nPassi++;
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThCavallo.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (Thread.interrupted()) return;
        }
        if (nPassi == 50) {
            dt.setFinito();
            dt.setVincitore(verso);
        }
    }
}
