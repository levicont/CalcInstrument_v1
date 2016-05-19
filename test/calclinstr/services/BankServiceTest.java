/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calclinstr.services;

import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.factories.ServiceFactory;
import calcinstr.models.Bank;
import calcinstr.services.BankService;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victor
 */
public class BankServiceTest {
    
    private BankService bankService = ServiceFactory.getBankService();
    
    
    public BankServiceTest() {
    }
     
   
    @Test
    public void addTest()throws CalcInstrumentException{
        Bank b = new Bank();
        b.setName("PB");
        int id = bankService.add(b);
        assertNotEquals(-1, id);
        b.setId(id);
        bankService.delete(b);
        b = bankService.get(id);
        assertNull(b);
    }
   
}
