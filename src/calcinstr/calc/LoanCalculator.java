/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.calc;

/**
 *
 * @author victor
 */


import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.models.Loan;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.apache.log4j.Logger;

public abstract class LoanCalculator {
    protected static final Logger LOGGER =  Logger.getLogger(LoanCalculator.class);
    protected final int DAYS_IN_LEAP_YEAR = 366;
    protected final int DAYS_IN_YEAR = 365;
    protected final int DAYS_IN_YEAR_USD = 360;
    
    protected Loan loan;

       
    
    public abstract BigDecimal calculateAmountForPeriod(LocalDate startDate, LocalDate endDate)throws CalcInstrumentException;
    
   
    
    
    protected long getDifferenceDays(LocalDate minuend, LocalDate subtrahend){               
        return minuend.toEpochDay()- subtrahend.toEpochDay();                
    }
    
    protected int getDaysCountInYear(long periodDays, LocalDate endPeriod){
        if(periodDays > DAYS_IN_LEAP_YEAR)
            return DAYS_IN_YEAR;
        else 
            return endPeriod.lengthOfYear();
    }
    
    protected Loan getLoan() {
        return loan;
    }

    protected void setLoan(Loan loan) {
        this.loan = loan;
    }
    
}
