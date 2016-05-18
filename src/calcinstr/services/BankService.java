
package calcinstr.services;

import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.models.Bank;


public interface BankService extends GenericService<Bank>{
    public Bank getBankByName(String name)throws CalcInstrumentException;
}
