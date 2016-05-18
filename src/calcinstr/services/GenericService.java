/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.services;

import calcinstr.exceptions.CalcInstrumentException;
import java.util.List;


public interface GenericService<T> {    
    public List<T> getAll() throws CalcInstrumentException;
    public T get(Integer id) throws CalcInstrumentException;
    public int add(T record) throws CalcInstrumentException;
    public void update(T record) throws CalcInstrumentException;
    public void delete(T record) throws CalcInstrumentException;    
    
}
