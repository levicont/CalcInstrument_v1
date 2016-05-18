/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.ui.converters.impl;

import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.factories.ServiceFactory;
import calcinstr.models.Currency;
import calcinstr.services.CurrencyService;
import calcinstr.ui.entities.CurrencyUI;
import calcinstr.ui.converters.CurrencyConverter;


public class CurrencyConverterImpl implements CurrencyConverter {
    
    private CurrencyService currencyService = ServiceFactory.getCurrencyService();
    
    @Override
    public Currency convert(CurrencyUI uiModel) throws CalcInstrumentException{
         if(uiModel == null)
            return null;
        Currency currency = currencyService.get(uiModel.getId());
        if (currency != null){
            updateModelFromUIModel(currency, uiModel);           
        }else{
            currency = new Currency();
            updateModelFromUIModel(currency, uiModel);            
        }
        return currency;
    }
    
     private void updateModelFromUIModel(Currency model, CurrencyUI uiModel){
        model.setName(uiModel.getName());
        model.setCode(uiModel.getCode());
    }
    
}
