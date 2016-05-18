/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.config;

/**
 *
 * @author Victor
 */
public interface R {
    public interface ModelSettings{
        public int DEFAULT_ID = 0;
        public String EMPTY_STRING = "";
    }
    
    public interface DBSettings{
        public String DRIVER_NAME = "org.apache.derby.jdbc.ClientDriver";
        public String DB_URL = "jdbc:derby://localhost:1527/Loans;create=true";
    }
    
    public interface BankSQL{        
        public String ALL_BANKS_SQL = "SELECT ID_BANKS, NAMED FROM banks";
        public String GET_BANK_BY_ID_SQL = "SELECT ID_BANKS, NAMED FROM banks WHERE ID_BANKS=?";
        public String ADD_BANK_SQL = "INSERT INTO banks(named) VALUES(?)";
        public String DELETE_BANK_SQL = "DELETE FROM banks WHERE ID_BANKS=?";
        public String UPDATE_BANK_SQL = "UPDATE banks SET NAMED=? WHERE ID_BANKS=?";
        public String GET_BANK_BY_NAME = "SELECT ID_BANKS, NAMED FROM banks WHERE NAMED=?";
    }
    
    public interface CompanySQL{
        public String ALL_COMPANIES_SQL = "SELECT ID_COMPANIES, NAMED, HEAD, ACCOUNTANT FROM companies";
        public String GET_COMPANY_BY_ID_SQL = "SELECT ID_COMPANIES, NAMED, HEAD, ACCOUNTANT"
                + " FROM companies WHERE ID_COMPANIES=?";
        public String ADD_COMPANY_SQL = "INSERT INTO companies(named,head,accountant) VALUES(?,?,?)";
        public String DELETE_COMPANY_SQL = "DELETE FROM companies WHERE ID_COMPANIES=?";
        public String UPDATE_COMPANY_SQL = "UPDATE companies SET NAMED=?,HEAD=?,ACCOUNTANT=?"
                + "  WHERE ID_COMPANIES=?";
        public String GET_COMPANY_BY_NAME = "SELECT ID_COMPANIES, NAMED, HEAD, ACCOUNTANT "
                + "FROM companies WHERE NAMED=?";
    }
    
    public interface CurrencySQL{        
        public String ALL_CURRENCIES_SQL = "SELECT ID_CURRENCY, CODE, NAMED FROM currency";
        public String GET_CURRENCY_BY_ID_SQL = "SELECT ID_CURRENCY, CODE, NAMED "
                + "FROM currency WHERE ID_CURRENCY=?";
        public String ADD_CURRENCY_SQL = "INSERT INTO currency(code, named) VALUES(?,?)";
        public String DELETE_CURRENCY_SQL = "DELETE FROM currency WHERE ID_CURRENCY=?";
        public String UPDATE_CURRENCY_SQL = "UPDATE currency SET CODE=?, NAMED=? WHERE ID_CURRENCY=?";
        public String GET_CURRENCY_BY_NAME = "SELECT ID_CURRENCY, CODE, NAMED FROM currency WHERE NAMED=?";
    }
    
     public interface LoanSQL{        
        public String ALL_LOANS_SQL = "SELECT ID_LOAN, ID_COMPANIES, ID_BANKS, ID_CURRENCY,"
                + "AMOUNT, INTEREST, START_DATE, END_DATE, TYPE_LOAN FROM loans";
        public String GET_BANK_BY_ID_SQL = "SELECT ID_LOAN, ID_COMPANIES, ID_BANKS, ID_CURRENCY,"
                + "AMOUNT, INTEREST, START_DATE, END_DATE, TYPE_LOAN FROM loans WHERE ID_LOAN=?";
        public String ADD_BANK_SQL = "INSERT INTO loans(ID_COMPANIES, ID_BANKS, ID_CURRENCY,"
                + "AMOUNT, INTEREST, START_DATE, END_DATE, TYPE_LOAN) VALUES(?,?,?,?,?,?,?,?)";
        
        public String DELETE_BANK_SQL = "DELETE FROM loans WHERE ID_LOAN=?";
        public String UPDATE_BANK_SQL = "UPDATE loans SET ID_COMPANIES=?, ID_BANKS=?, ID_CURRENCY=?,"
                + "AMOUNT=?, INTEREST=?, START_DATE=?, END_DATE=?, TYPE_LOAN=? WHERE ID_LOAN=?";
        
    }
    
    
    
    
    public interface Errors{
        public String SQL_ERROR = "Ошибка при работе с БД";
        public String DATA_BASE_CONNECTION_ERROR = "Невозможно установить соединение с БД";
    }
}
