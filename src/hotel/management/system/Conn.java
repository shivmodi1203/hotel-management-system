package hotel.management.system;

import java.sql.*;

public class Conn {
    
    Connection c;
    Statement s;
    Conn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagementsystem", "root", "root");
            s = c.createStatement();
//            
//            s=c.createStatement();
//            ResultSet rs=s.executeQuery("select * from hotelmanagementsystem.login");
//            while(rs.next())
//                System.out.println(rs.getString(1)+" "+rs.getString(2));
//            c.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
//    public static void main(String[] args) {
//            new Conn();
//        }
    
}
