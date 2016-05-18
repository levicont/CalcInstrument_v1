/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.util;

import calcinstr.config.R;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author victor
 */
public class DBManager {
    private static Connection conn;
    
    
    static{
        try{
            Class.forName(R.DBSettings.DRIVER_NAME).newInstance();
            conn = DriverManager.getConnection(R.DBSettings.DB_URL);
            
        }catch(Exception ex){
            System.out.println(R.Errors.DATA_BASE_CONNECTION_ERROR);
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    public static Connection getConnection(){
        return conn;
    }   
   
    
    public static void close(AutoCloseable closeable){
        try{
            if(closeable != null){
                closeable.close();
            }            
        }catch(Exception ex){
            //Do nothing
        }
                
    }
    
    
}
