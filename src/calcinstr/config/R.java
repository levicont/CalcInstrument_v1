/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.config;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Victor
 */
public interface R {
    public interface ApplicationSettings{
        public String APP_TITLE = "CalcInstrument_v1";
    }
    
    public interface ModelSettings{
        public int DEFAULT_ID = 0;
        public String EMPTY_STRING = "";
        public String YEAR_LOAN_TYPE_KEY = "year";
        public String MOUNTH_LOAN_TYPE_KEY = "mounth";
        public Map<String,String> LOAN_TYPES = new HashMap<String,String>(){{
            put(MOUNTH_LOAN_TYPE_KEY,"мес");
            put(YEAR_LOAN_TYPE_KEY,"год");
            }};
        public String ALL_COMPANY_FILTER_NAME = "Все компании";
        public String ALL_BANK_FILTER_NAME = "Все банки";
        public String ALL_CURRENCY_FILTER_NAME = "Все валюты";
        
    
        
    }
    
    public interface DBSettings{
        public String DRIVER_NAME = "org.apache.derby.jdbc.EmbeddedDriver";
        public String DB_URL = "jdbc:derby:Loans;create=true";
    }
    
    public interface BankSQL{ 
        public String CREATE_TABLE_BANKS_SQL = "CREATE TABLE BANKS" +
                       "(ID_BANKS INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
                       "NAMED VARCHAR(100) CONSTRAINT NREQ NOT NULL," +
                        "CONSTRAINT UNIQUEN UNIQUE (NAMED) )";         
        public String ALL_BANKS_SQL = "SELECT ID_BANKS, NAMED FROM banks";
        public String GET_BANK_BY_ID_SQL = "SELECT ID_BANKS, NAMED FROM banks WHERE ID_BANKS=?";
        public String ADD_BANK_SQL = "INSERT INTO banks(named) VALUES(?)";
        public String DELETE_BANK_SQL = "DELETE FROM banks WHERE ID_BANKS=?";
        public String UPDATE_BANK_SQL = "UPDATE banks SET NAMED=? WHERE ID_BANKS=?";
        public String GET_BANK_BY_NAME = "SELECT ID_BANKS, NAMED FROM banks WHERE NAMED=?";
    }
    
    public interface CompanySQL{
        public String CREATE_TABLE_COMPANIES_SQL = "CREATE TABLE COMPANIES" +
                    "(ID_COMPANIES INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
                    "NAMED VARCHAR(200) NOT NULL, " +
                    "HEAD VARCHAR(500), " +
                    "ACCOUNTANT VARCHAR(500), " +                   
                    "CONSTRAINT UNIQUENC UNIQUE (NAMED) )";
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
        public String CREATE_TABLE_CURRENCY_SQL = "CREATE TABLE CURRENCY " +
                "(ID_CURRENCY INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                "CODE VARCHAR(50) CONSTRAINT CODEREQ NOT NULL, " +
                "NAMED VARCHAR(100) CONSTRAINT NAMEDREQ NOT NULL, " +                
                "CONSTRAINT UNIQUENAMED UNIQUE (NAMED) )";
        public String ALL_CURRENCIES_SQL = "SELECT ID_CURRENCY, CODE, NAMED FROM currency";
        public String GET_CURRENCY_BY_ID_SQL = "SELECT ID_CURRENCY, CODE, NAMED "
                + "FROM currency WHERE ID_CURRENCY=?";
        public String ADD_CURRENCY_SQL = "INSERT INTO currency(code, named) VALUES(?,?)";
        public String DELETE_CURRENCY_SQL = "DELETE FROM currency WHERE ID_CURRENCY=?";
        public String UPDATE_CURRENCY_SQL = "UPDATE currency SET CODE=?, NAMED=? WHERE ID_CURRENCY=?";
        public String GET_CURRENCY_BY_NAME = "SELECT ID_CURRENCY, CODE, NAMED FROM currency WHERE NAMED=?";
    }
    
     public interface LoanSQL{
        public String CREATE_TABLE_LOAN_SQL ="CREATE TABLE LOANS " +
                    "( ID_LOAN INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "ID_COMPANIES INTEGER, " +
                    "ID_BANKS INTEGER, " +
                    "ID_CURRENCY INTEGER, " +
                    "AMOUNT DECIMAL (20,2)," +
                    "INTEREST DECIMAL (5,2), " +
                    "START_DATE DATE, " +
                    "END_DATE DATE,  " +
                    "TYPE_LOAN VARCHAR (100), " +                    
                    "CONSTRAINT FKIN_COMPANIES FOREIGN KEY (ID_COMPANIES) REFERENCES COMPANIES (ID_COMPANIES), " +
                    "CONSTRAINT FKIN_BANKS FOREIGN KEY (ID_BANKS) REFERENCES BANKS (ID_BANKS), " +
                    "CONSTRAINT FKIN_CURRENCY FOREIGN KEY (ID_CURRENCY) REFERENCES CURRENCY (ID_CURRENCY) )";
        public String LOAN_ID_COLUMN_NAME = "ID_LOAN"; 
        public String ALL_LOANS_SQL = "SELECT ID_LOAN, ID_COMPANIES, ID_BANKS, ID_CURRENCY,"
                + "AMOUNT, INTEREST, START_DATE, END_DATE, TYPE_LOAN FROM loans";
        public String GET_LOAN_BY_ID_SQL = "SELECT ID_LOAN, ID_COMPANIES, ID_BANKS, ID_CURRENCY,"
                + "AMOUNT, INTEREST, START_DATE, END_DATE, TYPE_LOAN FROM loans WHERE ID_LOAN=?";
        public String ADD_LOAN_SQL = "INSERT INTO loans(ID_COMPANIES, ID_BANKS, ID_CURRENCY,"
                + "AMOUNT, INTEREST, START_DATE, END_DATE, TYPE_LOAN) VALUES(?,?,?,?,?,?,?,?)";
        
        public String DELETE_LOAN_SQL = "DELETE FROM loans WHERE ID_LOAN=?";
        public String UPDATE_LOAN_SQL = "UPDATE loans SET ID_COMPANIES=?, ID_BANKS=?, ID_CURRENCY=?,"
                + "AMOUNT=?, INTEREST=?, START_DATE=?, END_DATE=?, TYPE_LOAN=? WHERE ID_LOAN=?";
        
    }
    
    public interface Messages{
        public String DB_CONNECTED_MSG = "The DataBase connection is established";
    }    
    
    public interface Errors{
        public String SQL_ERROR = "Ошибка при работе с БД";
        public String SQL_DATA_IS_UNAVAILEABLE = "Невозможно загрузить данные";
        public String DATA_BASE_CONNECTION_ERROR = "Невозможно установить соединение с БД";
        public String DELETE_ITEM_ERROR = "Невозможно удалить запись";
        public String SAVE_ITEM_ERROR = "Невозможно сохранить запись";
    }
}
