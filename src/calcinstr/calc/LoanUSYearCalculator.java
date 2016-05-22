/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.calc;

import static calcinstr.calc.LoanCalculator.LOGGER;
import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.models.Loan;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 *
 * @author victor
 */
public class LoanUSYearCalculator extends LoanYearCalculator{
    
    
    public LoanUSYearCalculator(Loan loan) {
        super(loan);
    }
    
    //(S*(R/100))/360*(Dep - Dbp)
    @Override
    public BigDecimal calculateAmountForPeriod(LocalDate startDate, 
            LocalDate endDate) throws CalcInstrumentException {
      
        BigDecimal S = loan.getAmount().setScale(2);
        BigDecimal R = loan.getRate().setScale(2);
        LocalDate Dbp = startDate;
        LocalDate Dep = endDate;
        long diffDays = getDifferenceDays(Dep, Dbp);
        BigDecimal daysInYear = BigDecimal.valueOf(DAYS_IN_YEAR_USD).setScale(2);
        LOGGER.debug("Days in year = "+daysInYear.toPlainString());
        
        BigDecimal persent = R.divide(BigDecimal.valueOf(100.00),4,RoundingMode.HALF_UP);
        LOGGER.debug("Persent = "+persent.toPlainString());
        
        BigDecimal multPersent = S.multiply(persent);
        LOGGER.debug("S * persent = "+multPersent.toPlainString());
        
        BigDecimal multPersentDivDaysInYear = multPersent.divide(daysInYear,4,RoundingMode.HALF_UP);
        LOGGER.debug("S * persent / Days in year = "+multPersentDivDaysInYear.toPlainString());
        
        BigDecimal result = multPersentDivDaysInYear.multiply(BigDecimal.valueOf(diffDays));
        return result.setScale(2, RoundingMode.HALF_UP);
    }
}
