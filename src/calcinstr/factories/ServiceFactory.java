
package calcinstr.factories;

import calcinstr.services.BankService;
import calcinstr.services.CompanyService;
import calcinstr.services.CurrencyService;
import calcinstr.services.LoanService;
import calcinstr.services.impl.BankServiceJavaDBImpl;
import calcinstr.services.impl.CompanyServiceJavaDBImpl;
import calcinstr.services.impl.CurrencyServiceJavaDBImpl;
import calcinstr.services.impl.LoanServiceJavaDBImpl;


public class ServiceFactory {
    
    private static BankService bankService;
    private static CompanyService companyService;
    private static CurrencyService currencyService;
    private static LoanService loanService;
    
    private ServiceFactory(){}
        
    public static BankService getBankService() {
        if(null != bankService)
            return bankService;
        else{
            bankService = new BankServiceJavaDBImpl();
            return bankService;
        }
            
    }

    public static CompanyService getCompanyService() {
        if(null != companyService)
            return companyService;
        else{
            companyService = new CompanyServiceJavaDBImpl();
            return companyService;
        }
        
    }

    public static CurrencyService getCurrencyService() {
        if( null != currencyService)
            return currencyService;
        else{
            currencyService = new CurrencyServiceJavaDBImpl();
            return currencyService;
        }
    }

    public static LoanService getLoanService() {
        if (null != loanService)
            return loanService;
        else{
            loanService = new LoanServiceJavaDBImpl();
            return loanService;
        }
    }
}
