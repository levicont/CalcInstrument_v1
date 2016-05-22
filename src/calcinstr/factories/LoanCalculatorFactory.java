/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.factories;

import calcinstr.calc.LoanCalculator;
import calcinstr.calc.LoanMonthCalculator;
import calcinstr.calc.LoanUSMonthCalculator;
import calcinstr.calc.LoanUSYearCalculator;
import calcinstr.calc.LoanYearCalculator;
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

    private static final String USD_CURRENCY_CODE = "usd";
    private static final String USD_CURRENCY_NAME = "доллар сша";

    private static LoanCalculator loanMonthCalculator;
    private static LoanCalculator loanUSDMonthCalculator;
    private static LoanCalculator loanYearCalculator;
    private static LoanCalculator loanUSDYearCalculator;

    private LoanCalculatorFactory() {

    }

    public static LoanCalculator getLoanYearCalculator(Loan loan) throws CalcInstrumentException {
        if (isUSDCurrency(loan.getCurrency())) {
            return new LoanUSYearCalculator(loan);

        } else {
            return new LoanYearCalculator(loan);
        }
    }

    public static LoanCalculator getLoanMounthCalculator(Loan loan, BigDecimal amountPeriod,
            LocalDate periodDate) throws CalcInstrumentException {
        if (isUSDCurrency(loan.getCurrency())) {
            return new LoanUSMonthCalculator(loan, amountPeriod, periodDate);

        } else {
            return new LoanMonthCalculator(loan, amountPeriod, periodDate);
        }
    }

    private static boolean isUSDCurrency(Currency currency) {
        return currency.getCode().equalsIgnoreCase(USD_CURRENCY_CODE)
                || currency.getName().equalsIgnoreCase(USD_CURRENCY_NAME);
    }

}
