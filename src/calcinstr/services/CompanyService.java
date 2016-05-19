/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.services;

import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.models.Company;

/**
 *
 * @author Victor
 */
public interface CompanyService extends GenericService<Company>{  
    public Company getCompanyByName(String name) throws CalcInstrumentException;
}
