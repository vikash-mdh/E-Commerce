package com.example.ecommerce;

import javafx.collections.ObservableList;
import java.util.*;
import java.sql.ResultSet;

public class Order {
    public static boolean placeOrder(Customer customer, Product product){
        String groupOrderId = "select max(group_order_id)+1 id from orders";
        DbConnection connection = new DbConnection();
        try{
            ResultSet rs = connection.getQueryTable(groupOrderId);
            if(rs.next()){
                String placeOrder = "insert into orders (group_order_id, customer_id, product_id) " +
                        "values ("+rs.getInt("id")+", "+customer.getId()+", "+product.getId()+")";
                return connection.updateDatabase(placeOrder) != 0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static int placeMultipleOrder(Customer customer, ObservableList<Product> productList){
        String groupOrderId = "select max(group_order_id)+1 id from orders";
        DbConnection connection = new DbConnection();
        try{
            ResultSet rs = connection.getQueryTable(groupOrderId);
            int count = 0;
            if(rs.next()){
                for(Product product: productList){
                    String placeOrder = "insert into orders (group_order_id, customer_id, product_id) " +
                            "values ("+rs.getInt("id")+", "+customer.getId()+", "+product.getId()+")";
                    count += connection.updateDatabase(placeOrder);
                }

                return count;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;

    }
}
