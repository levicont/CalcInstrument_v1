/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.services.impl;

import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.models.Bank;
import calcinstr.services.BankService;
import java.util.List;

/**
 *
 * @author Victor
 */
public class BankServiceJavaDBImpl implements BankService{

    @Override
    public List<Bank> getAll() throws CalcInstrumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Bank get(Integer id) throws CalcInstrumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void add(Bank record) throws CalcInstrumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Bank record) throws CalcInstrumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Bank record) throws CalcInstrumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
