/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.services.impl;

import calcinstr.config.R;
import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.models.Bank;
import calcinstr.services.BankService;
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
public class BankServiceJavaDBImpl implements BankService{
    
    private static Connection conn = DBManager.getConnection();
    
    @Override
    public List<Bank> getAll() throws CalcInstrumentException {
        List<Bank> result  = new LinkedList<>(); 
        Statement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(R.BankSQL.ALL_BANKS_SQL);
            while (rs.next()){
                Bank bank = new Bank();
                bank.setId(rs.getInt(1));
                bank.setName(rs.getString(2));
                result.add(bank);
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
    public Bank get(Integer id) throws CalcInstrumentException {
        Bank result = null; 
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.BankSQL.GET_BANK_BY_ID_SQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()){
                result = new Bank();
                result.setId(rs.getInt(1));
                result.setName(rs.getString(2));                
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
    public Bank getBankByName(String name) throws CalcInstrumentException {
        Bank result = null; 
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.BankSQL.GET_BANK_BY_NAME);
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            while (rs.next()){
                result = new Bank();
                result.setId(rs.getInt(1));
                result.setName(rs.getString(2));                
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
    public int add(Bank record) throws CalcInstrumentException {
        int id = -1;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.BankSQL.ADD_BANK_SQL);
            stmt.setString(1, record.getName());
            stmt.executeUpdate();
            id = getBankByName(record.getName()).getId();
            
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
    public void update(Bank record) throws CalcInstrumentException {
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.BankSQL.UPDATE_BANK_SQL);
            stmt.setString(1, record.getName());
            stmt.setInt(2, record.getId());
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
    public void delete(Bank record) throws CalcInstrumentException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.BankSQL.DELETE_BANK_SQL);
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
