/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.ui.converters.impl;

import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.factories.ServiceFactory;
import calcinstr.models.Company;
import calcinstr.services.CompanyService;
import calcinstr.ui.converters.CompanyConverter;
import calcinstr.ui.entities.CompanyUI;

/**
 *
 * @author Victor
 */
public class CompanyConverterImpl implements CompanyConverter{
    
    private CompanyService companyService = ServiceFactory.getCompanyService();
    
    @Override
    public Company convert(CompanyUI uiModel) throws CalcInstrumentException{
        if(uiModel == null)
            return null;
        Company company = companyService.get(uiModel.getId());
        if (company != null){
            updateModelFromUIModel(company, uiModel);           
        }else{
            company = new Company();
            updateModelFromUIModel(company, uiModel);            
        }
        return company;
    }
    
    private void updateModelFromUIModel(Company model, CompanyUI uiModel){
        model.setName(uiModel.getName());
        model.setHead(uiModel.getHead());
        model.setAccountant(uiModel.getAccountant());
    }
}
