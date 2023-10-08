/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AVSCAN;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author dmitr
 */
public class Database {
    public ArrayList<Data> items = new ArrayList<>();
    
    public Database(){
        connectToDatabase();
    }
    
    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/avscan", "root", "Y6wx4k6xz!");
//here sonoo is database name, root is username and password  
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from ITEMS");
            while (rs.next()) {
                System.out.println(rs.getLong(1) + "  " + rs.getString(2));
                Data item = new Data(rs.getLong(1), rs.getString(2));
                this.items.add(item);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("CAN\'T CONNECT TO DATABASE!! Using local data");
            items.add(new Data( 9415947039036L, "Soft Notebook"));
            items.add(new Data(9414952106030L, "Hard Notebook"));
        }
    }
}
