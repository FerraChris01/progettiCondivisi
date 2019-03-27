/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spazipuntistatistiche;

/**
 *
 * @author ferrareis_christian
 */
public class dataStorage {
    private char[] buffer;
    private int numSpaziInseriti;
    private int numPuntiInseriti;
    private int numSpaziLetti;
    private int numPuntiLetti;
    private boolean finito;
    private int numEl;
    
    public dataStorage()
    {
        buffer = new char[10];
        numSpaziInseriti = 0;
        numSpaziLetti = 0;
        numPuntiLetti = 0;
        numPuntiInseriti = 0;
    }
    synchronized public char[] getBuffer()
    {
        return buffer;
    }
    synchronized public void pushEl(char el)
    {
        buffer[numEl++] = el;
        if (el == '.') numPuntiInseriti++;
        else if (el == ' ') numSpaziInseriti++;
    }
    synchronized public void resetNumEl()
    {
        numEl = 0;
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
    synchronized public void visualizza()
    {   
        for (char c : buffer)
        {
            System.out.println(c);
        }
        System.out.println("Numero punti inseriti: " + numPuntiInseriti);
        System.out.println("Numero punti letti: " + numPuntiLetti + "\n");
        System.out.println("Numero spazi inseriti: " + numSpaziInseriti);
        System.out.println("Numero spazi letti: " + numSpaziLetti);        
        System.out.println("-------------------------------");
    }
    synchronized public void setFinito()
    {
        finito = true;
    }
    
}
