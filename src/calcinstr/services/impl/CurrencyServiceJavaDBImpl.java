/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.services.impl;

import calcinstr.config.R;
import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.models.Company;
import calcinstr.models.Currency;
import calcinstr.services.CurrencyService;
import calcinstr.util.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Victor
 */
public class CurrencyServiceJavaDBImpl implements CurrencyService{

    private static Connection conn = DBManager.getConnection();
    
    @Override
    public List<Currency> getAll() throws CalcInstrumentException {
        List<Currency> result  = new LinkedList<>(); 
        Statement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(R.CurrencySQL.ALL_CURRENCIES_SQL);
            while (rs.next()){
                Currency currency = new Currency();
                currency.setId(rs.getInt(1));
                currency.setCode(rs.getString(2));
                currency.setName(rs.getString(3));                
                result.add(currency);
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
    public Currency get(Integer id) throws CalcInstrumentException {
        Currency result = null; 
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.CurrencySQL.GET_CURRENCY_BY_ID_SQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()){
                result = new Currency();
                result.setId(rs.getInt(1));
                result.setCode(rs.getString(2));
                result.setName(rs.getString(3));                                
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
    public int add(Currency record) throws CalcInstrumentException {
        int id = -1;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.CurrencySQL.ADD_CURRENCY_SQL);
            stmt.setString(1, record.getCode());
            stmt.setString(2, record.getName());
            stmt.executeUpdate();
            id = getCurrencyByName(record.getName()).getId();
            
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
    public Currency getCurrencyByName(String name) throws CalcInstrumentException {
       Currency result = null; 
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.CurrencySQL.GET_CURRENCY_BY_NAME);
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            while (rs.next()){
                result = new Currency();
                result.setId(rs.getInt(1));
                result.setCode(rs.getString(2));
                result.setName(rs.getString(3));                            
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
    public void update(Currency record) throws CalcInstrumentException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.CurrencySQL.UPDATE_CURRENCY_SQL);
            stmt.setString(1, record.getCode());
            stmt.setString(2, record.getName());            
            stmt.setInt(3, record.getId());
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
    public void delete(Currency record) throws CalcInstrumentException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.CurrencySQL.DELETE_CURRENCY_SQL);
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
