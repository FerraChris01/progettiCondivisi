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
public class Estrazione {
    private boolean primoN;
    private boolean secondoN;
    
    public Estrazione()
    {
        
    }
    public boolean getPrimoN()
    {
        return primoN;
    }
    public boolean getSecondoN()
    {
        return secondoN;
    }
    public void setPrimoN()
    {
        primoN = true;
    }
    public void setSecondoN()
    {
        secondoN = true;
    }
    public boolean getVincita()
    {
        return (primoN && secondoN);
    }
}
