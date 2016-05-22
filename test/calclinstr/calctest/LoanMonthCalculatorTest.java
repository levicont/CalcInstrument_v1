/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calclinstr.calctest;

import calcinstr.calc.LoanCalculator;
import calcinstr.calc.LoanMonthCalculator;
import calcinstr.config.R;
import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.models.Bank;
import calcinstr.models.Company;
import calcinstr.models.Currency;
import calcinstr.models.Loan;
import calcinstr.util.DateUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * 
 * --------------------	Первый случай	-----------------------------
Есть кредит
	Валюта(C) 			-	UAH (грн)
	Сумма(S)			-	1000
	Ставка(R)			-	10
	Дата выдачи(Db)		-	25.01.2016г.
	Дата окончания(De)	-	25.07.2016г.
	Тип кредита(Tl)		-	мес. (ежемесячное погашение)

Сумма ежемесячного погашения(Sm)	= 50 
Дата ежемесячного погашения	(D)	= 10.03.2016
Дата начала периода расчета(Dbp)	= 01.03.2016
Дата окончания периода расчета (Dep)	= 01.04.2016

Cумма %% за период (Sp)	= (S*(R/100))/количество дней в году*(D - Dbp) + (S - Sm)*(R/100))/количество дней в году*(Dep-D) 
* = (1000*(10/100))/366*(10.03.2016-01.03.2016) + ((1000-50)*(10/100))/366*(01.04.2016-10.03.2016) = 8,17
 */

/** 
 *
 * @author victor
 */
public class LoanMonthCalculatorTest extends GenericLoanCalculatorTest{
       
    @Before
    public void init(){
       initLoan();
        
    }
        
    @Test
    public void calculateAmountForPeriodTest()throws CalcInstrumentException {
       BigDecimal Sm = BigDecimal.valueOf(50);
       LocalDate D = LocalDate.of(2016, Month.MARCH, 10);
       LocalDate Dbp = LocalDate.of(2016, Month.MARCH, 1);
       LocalDate Dep = LocalDate.of(2016, Month.APRIL, 1);
       
       BigDecimal expected = BigDecimal.valueOf(8.17);
       
        LoanCalculator calc = new LoanMonthCalculator(loan, Sm, D);
        BigDecimal result = calc.calculateAmountForPeriod(Dbp, Dep);
       
        assertEquals(expected, result);
    }

}
