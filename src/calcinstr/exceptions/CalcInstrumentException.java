/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.exceptions;

/**
 *
 * @author Victor
 */
public class CalcInstrumentException extends Exception{
    
    public CalcInstrumentException(String message){
        super(message);
    }
    
    public CalcInstrumentException(String message, Throwable ex){
        super(message, ex);
    }
}
