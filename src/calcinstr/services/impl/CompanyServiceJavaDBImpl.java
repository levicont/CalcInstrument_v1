/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.services.impl;

import calcinstr.config.R;
import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.models.Company;
import calcinstr.services.CompanyService;
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
public class CompanyServiceJavaDBImpl implements CompanyService{

     private static Connection conn = DBManager.getConnection();
    
    @Override
    public List<Company> getAll() throws CalcInstrumentException {
        List<Company> result  = new LinkedList<>(); 
        Statement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(R.CompanySQL.ALL_COMPANIES_SQL);
            while (rs.next()){
                Company company = new Company();
                company.setId(rs.getInt(1));
                company.setName(rs.getString(2));
                company.setHead(rs.getString(3));
                company.setAccountant(rs.getString(4));
                result.add(company);
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
    public Company get(Integer id) throws CalcInstrumentException {
        Company result = null; 
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.CompanySQL.GET_COMPANY_BY_ID_SQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()){
                result = new Company();
                result.setId(rs.getInt(1));
                result.setName(rs.getString(2));
                result.setHead(rs.getString(3));
                result.setAccountant(rs.getString(4));                
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
    public int add(Company record) throws CalcInstrumentException {
        int id = -1;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.CompanySQL.ADD_COMPANY_SQL);
            stmt.setString(1, record.getName());
            stmt.setString(2, record.getHead());
            stmt.setString(3, record.getAccountant());            
            stmt.executeUpdate();
            id = getCompanyByName(record.getName()).getId();
            
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
    public Company getCompanyByName(String name) throws CalcInstrumentException {
       Company result = null; 
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.CompanySQL.GET_COMPANY_BY_NAME);
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            while (rs.next()){
                result = new Company();
                result.setId(rs.getInt(1));
                result.setName(rs.getString(2));
                result.setHead(rs.getString(3));
                result.setAccountant(rs.getString(4));                
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
    public void update(Company record) throws CalcInstrumentException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.CompanySQL.UPDATE_COMPANY_SQL);
            stmt.setString(1, record.getName());
            stmt.setString(2, record.getHead());
            stmt.setString(3, record.getAccountant());  
            stmt.setInt(4, record.getId());
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
    public void delete(Company record) throws CalcInstrumentException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(R.CompanySQL.DELETE_COMPANY_SQL);
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
