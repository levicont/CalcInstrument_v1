/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.factories;

import calcinstr.calc.LoanCalculator;
import calcinstr.calc.LoanUAHMonthCalculator;
import calcinstr.calc.LoanMonthCalculator;
import calcinstr.calc.LoanYearCalculator;
import calcinstr.calc.LoanUAHYearCalculator;
import calcinstr.config.R;
import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.models.Currency;
import calcinstr.models.Loan;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author victor
 */
public class LoanCalculatorFactory implements R.ModelSettings {

    private static final String UAH_CURRENCY_CODE = "uah";
    private static final String UAH_CURRENCY_NAME = "гривна украина";

    private static LoanCalculator loanMonthCalculator;
    private static LoanCalculator loanUSDMonthCalculator;
    private static LoanCalculator loanYearCalculator;
    private static LoanCalculator loanUSDYearCalculator;

    private LoanCalculatorFactory() {

    }

    public static LoanCalculator getLoanYearCalculator(Loan loan) throws CalcInstrumentException {
        if (!isUAHCurrency(loan.getCurrency())) {
            return new LoanYearCalculator(loan);

        } else {
            return new LoanUAHYearCalculator(loan);
        }
    }

    public static LoanCalculator getLoanMounthCalculator(Loan loan, BigDecimal amountPeriod,
            LocalDate periodDate) throws CalcInstrumentException {
        if (!isUAHCurrency(loan.getCurrency())) {
            return new LoanMonthCalculator(loan, amountPeriod, periodDate);

        } else {
            return new LoanUAHMonthCalculator(loan, amountPeriod, periodDate);
        }
    }

    private static boolean isUAHCurrency(Currency currency) {
        return currency.getCode().equalsIgnoreCase(UAH_CURRENCY_CODE)
                || currency.getName().equalsIgnoreCase(UAH_CURRENCY_NAME);
    }

}
