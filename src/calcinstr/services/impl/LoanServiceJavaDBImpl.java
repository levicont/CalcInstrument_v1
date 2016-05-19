/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.services.impl;

import calcinstr.config.R;
import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.factories.ServiceFactory;
import calcinstr.models.Bank;
import calcinstr.models.Company;
import calcinstr.models.Currency;
import calcinstr.models.Loan;
import calcinstr.services.BankService;
import calcinstr.services.CompanyService;
import calcinstr.services.CurrencyService;
import calcinstr.services.LoanService;
import calcinstr.util.DBManager;
import calcinstr.util.DateUtil;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Victor
 */
public class LoanServiceJavaDBImpl implements LoanService{
    private static final Logger LOGGER = Logger.getLogger(LoanServiceJavaDBImpl.class);
    private static Connection conn = DBManager.getConnection();
    private static BankService bankService = ServiceFactory.getBankService();
    private static CompanyService companyService = ServiceFactory.getCompanyService();
    private static CurrencyService currencyService = ServiceFactory.getCurrencyService();
    
    @Override
    public List<Loan> getAll() throws CalcInstrumentException {
        List<Loan> result  = new LinkedList<>(); 
        
        Statement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(R.LoanSQL.ALL_LOANS_SQL);
            while (rs.next()){
                Loan loan = new Loan();
                loan.setId(rs.getInt(1));
                loan.setCompany(companyService.get(rs.getInt(2)));
                loan.setBank(bankService.get(rs.getInt(3)));
                loan.setCurrency(currencyService.get(rs.getInt(4)));
                loan.setAmount(rs.getBigDecimal(5));
                loan.setRate(rs.getBigDecimal(6));
                loan.setStartDate(rs.getDate(7));
                loan.setEndDate(rs.getDate(8));
                loan.setType(rs.getString(9));                            
                result.add(loan);
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            throw new CalcInstrumentException(R.Errors.SQL_ERROR,ex);
        }finally{
            DBManager.close(stmt);
            DBManager.close(rs);
        }
        
        return result;
    }

    @Override
    public Loan get(Integer id) throws CalcInstrumentException {
        Loan result = null; 
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.LoanSQL.GET_LOAN_BY_ID_SQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()){
                result = new Loan();
                result.setId(rs.getInt(1));
                result.setCompany(companyService.get(rs.getInt(2)));
                result.setBank(bankService.get(rs.getInt(3)));
                result.setCurrency(currencyService.get(rs.getInt(4)));
                result.setAmount(rs.getBigDecimal(5));
                result.setRate(rs.getBigDecimal(6));
                result.setStartDate(rs.getDate(7));
                result.setEndDate(rs.getDate(8));
                result.setType(rs.getString(9));      
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            throw new CalcInstrumentException(R.Errors.SQL_ERROR,ex);
        }finally{
            DBManager.close(stmt);
            DBManager.close(rs);
        }        
        return result; 
    }

    @Override
    public int add(Loan record) throws CalcInstrumentException {
        int id = -1;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.LoanSQL.ADD_LOAN_SQL,
                    new String[]{R.LoanSQL.LOAN_ID_COLUMN_NAME});
            stmt.setInt(1, record.getCompany().getId());
            stmt.setInt(2, record.getBank().getId());
            stmt.setInt(3, record.getCurrency().getId());
            stmt.setBigDecimal(4, record.getAmount());
            stmt.setBigDecimal(5, record.getRate());
            stmt.setDate(6, DateUtil.getSQLDate(record.getStartDate()));
            stmt.setDate(7, DateUtil.getSQLDate(record.getEndDate()));
            stmt.setString(8, record.getType());
            
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            while(rs.next()){
                id = rs.getInt(1);
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            throw new CalcInstrumentException(R.Errors.SQL_ERROR,ex);
        }finally{
            DBManager.close(stmt);
            DBManager.close(rs);
        }        
        return id;  
    }

    @Override
    public void update(Loan record) throws CalcInstrumentException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.LoanSQL.UPDATE_LOAN_SQL);
            stmt.setInt(1, record.getCompany().getId());
            stmt.setInt(2, record.getBank().getId());
            stmt.setInt(3, record.getCurrency().getId());
            stmt.setBigDecimal(4, record.getAmount());
            stmt.setBigDecimal(5, record.getRate());
            stmt.setDate(6, DateUtil.getSQLDate(record.getStartDate()));
            stmt.setDate(7, DateUtil.getSQLDate(record.getEndDate()));
            stmt.setString(8, record.getType());
            stmt.setInt(9, record.getId());            
            stmt.executeUpdate();            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            throw new CalcInstrumentException(R.Errors.SQL_ERROR,ex);
        }finally{
            DBManager.close(stmt);
            DBManager.close(rs);
        } 
    }

    @Override
    public void delete(Loan record) throws CalcInstrumentException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.LoanSQL.DELETE_LOAN_SQL);
            stmt.setInt(1, record.getId());
            stmt.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            throw new CalcInstrumentException(R.Errors.SQL_ERROR,ex);
        }finally{
            DBManager.close(stmt);
            DBManager.close(rs);
        }  
    }
    
}
