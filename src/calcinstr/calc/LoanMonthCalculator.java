/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.calc;

import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.models.Loan;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 *
 * @author victor
 */
public class LoanMonthCalculator extends LoanCalculator{
    
    protected BigDecimal amountRepayment;
    protected LocalDate dateRepayment;
    
    public LoanMonthCalculator(Loan loan, BigDecimal amountRepayment,
            LocalDate dateRepayment){
        
        this.loan = loan;
        this.amountRepayment = amountRepayment;
        this.dateRepayment = dateRepayment;
    }
    
    //(Sp) = (S*(R/100))/количество дней в году*(D - Dbp) + ((S - Sm)*(R/100))/количество дней в году*(Dep-D)
    @Override
    public BigDecimal calculateAmountForPeriod(LocalDate startDate, 
            LocalDate endDate) throws CalcInstrumentException {
      
        BigDecimal S = loan.getAmount().setScale(4);
        BigDecimal R = loan.getRate().setScale(4);
        BigDecimal Sm = amountRepayment;
        LocalDate D = dateRepayment;
        LocalDate Dbp = startDate;
        LocalDate Dep = endDate;
        BigDecimal diffDep_Dbp = BigDecimal.valueOf(getDifferenceDays(Dbp, Dep));
        BigDecimal diffDep_D = BigDecimal.valueOf(getDifferenceDays(Dep, D));
        BigDecimal diffD_Dbp = BigDecimal.valueOf(getDifferenceDays(D, Dbp));
        LOGGER.debug("D - Dbp = "+diffD_Dbp.toPlainString());
        LOGGER.debug("Dep - D = "+diffDep_D.toPlainString());
        
        
        BigDecimal daysInYear = BigDecimal.valueOf(getDaysCountInYear(diffDep_Dbp.longValue(), Dep));
        LOGGER.debug("Days in year = "+daysInYear.toPlainString());
        // R/100
        BigDecimal Rdiv100 = R.divide(BigDecimal.valueOf(100.00),4,RoundingMode.HALF_UP);
        LOGGER.debug("(R/100) = "+Rdiv100.toPlainString());
        // S * (R/100)
        BigDecimal SmultRdiv100 = S.multiply(Rdiv100);
        LOGGER.debug("S * (R/100) = "+SmultRdiv100.toPlainString());
        
        // (S * (R/100)) / Days in year
        BigDecimal SmultRdiv100divDaysInYear = SmultRdiv100.divide(daysInYear,4,RoundingMode.HALF_UP);
        LOGGER.debug("(S * (R/100)) / Days in year = "+SmultRdiv100divDaysInYear.toPlainString());
        
        // (S * (R/100)) / Days in year*(D - Dbp)
        BigDecimal firstAdder = SmultRdiv100divDaysInYear.multiply(diffD_Dbp);
        LOGGER.debug("(S * (R/100)) / Days in year*(D - Dbp) = "+firstAdder.toPlainString());
        
        // (S - Sm)
        BigDecimal diffS_Sm = S.subtract(Sm);
        LOGGER.debug("(S - Sm) = "+diffS_Sm.toPlainString());
        
        // (S - Sm)*(R/100)
        BigDecimal diffS_Sm_mult_persent = diffS_Sm.multiply(Rdiv100);
        LOGGER.debug("(S - Sm)*(R/100) = "+diffS_Sm_mult_persent.toPlainString());
        
        // ((S - Sm)*(R/100))/количество дней в году
        BigDecimal diffS_Sm_mult_persent_divDaysinY = diffS_Sm_mult_persent.divide(daysInYear,4,RoundingMode.HALF_UP);
        LOGGER.debug("((S - Sm)*(R/100))/количество дней в году = "+diffS_Sm_mult_persent_divDaysinY.toPlainString());
        
        // ((S - Sm)*(R/100))/количество дней в году*(Dep-D)
        BigDecimal secondAdder = diffS_Sm_mult_persent_divDaysinY.multiply(diffDep_D);
        LOGGER.debug("((S - Sm)*(R/100))/количество дней в году*(Dep-D) = "+secondAdder.toPlainString());        
        
        
        BigDecimal result = firstAdder.add(secondAdder).setScale(2, RoundingMode.HALF_UP);
        return result;
    }
    
}
