/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giocodellotto;

/**
 *
 * @author Chris
 */
public class dataStorage {
    private int[] numEstratti;
    private Estrazione[] estrazioni;
    private int numEstrazioni;
    private int numElEstrazioni;
    private boolean finito;
    
    public dataStorage(int numEstrazioni)
    {
        this.numEstrazioni = numEstrazioni;
        numEstratti = new int[5];
        estrazioni = new Estrazione[numEstrazioni];
        for (int i = 0; i < numEstrazioni; i++)
            estrazioni[i] = new Estrazione();
        
        numElEstrazioni = 0;
        finito = false;
    }
    public int[] getVectNumEstratti()
    {
        return numEstratti;
    }
    public Estrazione getElDiNumEl()
    {
        return estrazioni[numElEstrazioni];
    }
    public boolean estrazioneFinita()
    {
        if (numEstrazioni - 1 == numElEstrazioni) return true;
        else return false;
    }
    public void incNumElEstrazioni()
    {
        numElEstrazioni++;
    }
    public boolean getFinito()
    {
        return finito;
    }
    public void setFinito()
    {
        finito = true;
    }
    public Estrazione[] getVectEstrazioni()
    {
        return estrazioni;
    }
}
