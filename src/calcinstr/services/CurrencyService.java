/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.services;

import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.models.Currency;

/**
 *
 * @author Victor
 */
public interface CurrencyService extends GenericService<Currency>{
  public Currency getCurrencyByName(String name)throws CalcInstrumentException;
}
