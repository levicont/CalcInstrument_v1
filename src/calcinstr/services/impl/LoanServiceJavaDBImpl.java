/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.services.impl;

import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.models.Loan;
import calcinstr.services.LoanService;
import java.util.List;

/**
 *
 * @author Victor
 */
public class LoanServiceJavaDBImpl implements LoanService{

    @Override
    public List<Loan> getAll() throws CalcInstrumentException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Loan get(Integer id) throws CalcInstrumentException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void add(Loan record) throws CalcInstrumentException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void update(Loan record) throws CalcInstrumentException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void delete(Loan record) throws CalcInstrumentException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}