/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cavallisemafori;

/**
 *
 * @author Chris
 */
public class dataStorage {
    private String var;
    private boolean finito;
    private String vincitore;
    
    public dataStorage()
    {      
        finito = false;
    }
    synchronized public void setClop(String verso)
    {
        var = verso;
    }
    public String getClop()
    {
        return var;
    }
    public void setFinito()
    {
        finito = true;
    }
    public boolean getFinito()
    {
        return finito;
    }
    public void setVincitore(String verso)
    {
        vincitore = verso;
    }
    public String getVincitore()
    {
        return vincitore;
    }
}
