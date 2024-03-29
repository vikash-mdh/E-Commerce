package com.example.ecommerce;
import java.sql.*;

public class DbConnection {
    private final String dburl = "jdbc:mysql://localhost:3306/ecommerce_feb";

    private final String userName = "root";

    private final String password = "vikashku";

    private Statement getStatement(){
        try{
            Connection connection = DriverManager.getConnection(dburl, userName, password);
            return connection.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getQueryTable(String query){
        try{
            Statement statement = getStatement();
            return statement.executeQuery(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int updateDatabase(String query){
        try{
            Statement statement = getStatement();
            return statement.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        DbConnection conn = new DbConnection();
        ResultSet rs = conn.getQueryTable("select * from customer");
        if(rs!=null){
            System.out.println("Connection successful.");
        }else{
            System.out.println("Connection failed.");
        }
    }
}
