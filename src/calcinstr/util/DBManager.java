/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.util;

import calcinstr.config.R;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

/**
 *
 * @author victor
 */
public class DBManager {
    private static final Logger LOGGER = Logger.getLogger(DBManager.class);
    private static Connection conn;
    
    static{
        if (conn == null)
            initDataBase();
    }
    
    private static Boolean dbExists() {
        Boolean exists = false;
        try {
            Class.forName(R.DBSettings.DRIVER_NAME).newInstance();
            conn = DriverManager.getConnection(R.DBSettings.DB_URL);
            exists = true;
            LOGGER.info(R.Messages.DB_CONNECTED_MSG);
        } catch (Exception ex) {
            LOGGER.warn(R.Errors.DATA_BASE_CONNECTION_ERROR);
            LOGGER.warn(ex.getMessage());
        }
        return exists;
    }
    
    private static void initDataBase(){
        if(dbExists())
            initTables();
    }
    
    private static void initTables(){
        initBankTable();
        initCompanyTable();
        initCurrencyTable();
        initLoanTable();
    }
    
    private static void initBankTable(){
        if (conn == null)
            return;
        Statement stmt = null;
        boolean tableExists = false;
        try{
            stmt = conn.createStatement();
            stmt.executeQuery(R.BankSQL.ALL_BANKS_SQL);
            LOGGER.debug("Table banks found");
            tableExists = true;
        }catch(SQLException ex){
            LOGGER.debug("Table banks not found");           
        }
        
        if(!tableExists && null != stmt){
            try{
                stmt.execute(R.BankSQL.CREATE_TABLE_BANKS_SQL);
            }catch(SQLException ex){
                LOGGER.warn("Table banks could not be created "+ex.getMessage()); 
            }
        }        
        close(stmt);        
    }
    
    private static void initCompanyTable(){
        if (conn == null)
            return;
        Statement stmt = null;
        boolean tableExists = false;
        try{
            stmt = conn.createStatement();
            stmt.executeQuery(R.CompanySQL.ALL_COMPANIES_SQL);
            LOGGER.debug("Table company found");
            tableExists = true;
        }catch(SQLException ex){
            LOGGER.warn("Table company not found");           
        }
        
        if(!tableExists && null != stmt){
            try{
                stmt.execute(R.CompanySQL.CREATE_TABLE_COMPANIES_SQL);
            }catch(SQLException ex){
                LOGGER.warn("Table company could not be created"); 
            }
        }        
        close(stmt);        
    }
    
    private static void initCurrencyTable(){
        if (conn == null)
            return;
        Statement stmt = null;
        boolean tableExists = false;
        try{
            stmt = conn.createStatement();
            stmt.executeQuery(R.CurrencySQL.ALL_CURRENCIES_SQL);
            LOGGER.debug("Table currency found");
            tableExists = true;
        }catch(SQLException ex){
            LOGGER.warn("Table currency not found");           
        }
        
        if(!tableExists && null != stmt){
            try{
                stmt.execute(R.CurrencySQL.CREATE_TABLE_CURRENCY_SQL);
            }catch(SQLException ex){
                LOGGER.warn("Table currency could not be created"); 
            }
        }        
        close(stmt);        
    }
    
     private static void initLoanTable(){
        if (conn == null)
            return;
        Statement stmt = null;
        boolean tableExists = false;
        try{
            stmt = conn.createStatement();
            stmt.executeQuery(R.LoanSQL.ALL_LOANS_SQL);
            LOGGER.debug("Table loan found");
            tableExists = true;
        }catch(SQLException ex){
            LOGGER.warn("Table loan not found");           
        }
        
        if(!tableExists && null != stmt){
            try{
                stmt.execute(R.LoanSQL.CREATE_TABLE_LOAN_SQL);
            }catch(SQLException ex){
                LOGGER.warn("Table loan could not be created"); 
            }
        }        
        close(stmt);        
    }

    public static Connection getConnection() {       
            return conn;       
    }

    public static void close(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception ex) {
            //Do nothing
        }

    }

}
