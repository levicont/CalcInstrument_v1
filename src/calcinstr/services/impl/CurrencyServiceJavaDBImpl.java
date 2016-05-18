/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.services.impl;

import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.models.Currency;
import calcinstr.services.CurrencyService;
import java.util.List;

/**
 *
 * @author Victor
 */
public class CurrencyServiceJavaDBImpl implements CurrencyService{

    @Override
    public List<Currency> getAll() throws CalcInstrumentException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Currency get(Integer id) throws CalcInstrumentException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void add(Currency record) throws CalcInstrumentException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void update(Currency record) throws CalcInstrumentException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void delete(Currency record) throws CalcInstrumentException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
