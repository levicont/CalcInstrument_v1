/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calclinstr.calctest;

import calcinstr.calc.LoanCalculator;
import calcinstr.calc.LoanYearCalculator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
/*
Есть кредит
	Валюта(C) 		-	USD (доллар США)
	Сумма(S)		-	1000
	Ставка(R)		-	10
	Дата выдачи(Db)		-	25.01.2016г.
	Дата окончания(De)	-	25.07.2016г.
	Тип кредита(Tl)		-	год

Сумма ежемесячного погашения(Sm)		= 0	
Дата ежемесячного погашения	(D)		= 0 
Дата начала периода расчета(Dbp)		= 01.03.2016  
Дата окончания периода расчета (Dep)		= 01.04.2016 

Cумма %% за период (Sp)	= (S*(R/100))/360*(Dep - Dbp) 
= (1000*(10/100))/360*(01.04.2016-01.03.2016) = 8,61


*/
/**
 *
 * @author victor
 */
public class LoanUSYearCalculator extends GenericLoanCalculatorTest{
    
    @Before
    public void setUp(){
        initLoan();
    }
    
     @Test
    public void testCalculateAmountForPeriod() throws Exception {
        
        LocalDate Dbp = LocalDate.of(2016, Month.MARCH, 1);
        LocalDate Dep = LocalDate.of(2016, Month.APRIL, 1);

        BigDecimal expected = BigDecimal.valueOf(8.61).setScale(2);

        LoanCalculator calc = new calcinstr.calc.LoanUSYearCalculator(loan);
        BigDecimal result = calc.calculateAmountForPeriod(Dbp, Dep);

        assertEquals(expected, result);
    }
    
}
