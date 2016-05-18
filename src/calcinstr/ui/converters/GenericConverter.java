/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.ui.converters;

import calcinstr.exceptions.CalcInstrumentException;

/**
 *
 * @author Victor
 * @param <T> db model
 * @param <E> user interface (UI) model
 */
public interface GenericConverter<T,E> {
    public T convert(E uiModel)throws CalcInstrumentException;
}
