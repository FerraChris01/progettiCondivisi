/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spazipunti;

import java.util.Vector;

/**
 *
 * @author ferrareis_christian
 */
public class dataStorage {
    private char[] buffer;
    private int numEl;
    private int numSpaziInseriti;
    private int numPuntiInseriti;
    private int numSpaziLetti;
    private int numPuntiLetti;
    private boolean finito;
    
    public dataStorage()
    {
        buffer = new char[10];
        numSpaziInseriti = 0;
        numPuntiInseriti = 0;   
        numSpaziLetti = 0;
        numPuntiLetti = 0;
        numEl = 0;
        finito = false;
    }
    synchronized char[] getbuffer()
    {
        return buffer;
    }
    synchronized public void pushAndInc(char c)
    {
        buffer[numEl++] = c;
        if (c == '.') numPuntiInseriti++;
        else if (c == ' ') numSpaziInseriti++;
    }
    synchronized public int getNumEl()
    {
        return numEl;
    }
    synchronized public void resetNumEl()
    {
        numEl = 0;
    }
    synchronized public void setFinito()
    {
        finito = true;
    }
    synchronized public boolean getFinito()
    {
        return finito;
    }
    synchronized public void incPuntiLetti()
    {
        numPuntiLetti++;
    }
    synchronized public void incSpaziLetti()
    {
        numSpaziLetti++;
    }
    public int getNumPuntiLetti()
    {
        return numPuntiLetti;
    }
    public int getSpaziLetti()
    {
        return numSpaziLetti;
    }    
    public int getPuntiInseriti()
    {
        return numPuntiInseriti;
    }
    public int getSpaziInseriti()
    {
        return numSpaziInseriti;
    }
    
}
