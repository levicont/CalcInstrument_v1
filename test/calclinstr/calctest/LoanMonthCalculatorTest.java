/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calclinstr.calctest;

import calcinstr.calc.LoanCalculator;
import calcinstr.calc.LoanMonthCalculator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*
Есть кредит
	Валюта(C) 		-	USD (доллар США)
	Сумма(S)		-	1000
	Ставка(R)		-	10
	Дата выдачи(Db)		-	25.01.2016г.
	Дата окончания(De)	-	25.07.2016г.
	Тип кредита(Tl)		-	мес. (ежемесячное погашение)

Сумма ежемесячного погашения(Sm)	= 100
Дата ежемесячного погашения	(D)	= 10.03.2016
Дата начала периода расчета(Dbp)	= 01.03.2016 
Дата окончания периода расчета (Dep)	= 01.04.2016 

Cумма %% за период (Sp)	= (S*(R/100))/360*(D - Dbp) + (S - Sm)*(R/100))/360*(Dep-D)
= (1000*(10/100))/360*(10.03.2016-01.03.2016) + ((1000-100)*(10/100))/360*(01.04.2016-10.03.2016) = 8,00



*/

/**
 *
 * @author victor
 */
public class LoanMonthCalculatorTest extends GenericLoanCalculatorTest{
    
    
    @Before
    public void setUp() {
        initLoan();
    }

    @Test
    public void testCalculateAmountForPeriod() throws Exception {
        BigDecimal Sm = BigDecimal.valueOf(100);
        LocalDate D = LocalDate.of(2016, Month.MARCH, 10);
        LocalDate Dbp = LocalDate.of(2016, Month.MARCH, 1);
        LocalDate Dep = LocalDate.of(2016, Month.APRIL, 1);

        BigDecimal expected = BigDecimal.valueOf(8.00).setScale(2);

        LoanCalculator calc = new LoanMonthCalculator(loan, Sm, D);
        BigDecimal result = calc.calculateAmountForPeriod(Dbp, Dep);

        assertEquals(expected, result);
    }
    
}
