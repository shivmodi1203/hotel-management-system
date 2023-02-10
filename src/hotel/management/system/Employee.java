package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.*;

public class Employee extends JFrame implements ActionListener {
    
    JTable table;
    JButton back;
    
    Employee(){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel l1=new JLabel("Name");
        l1.setBounds(50,10,100,20);
        add(l1);
        
        JLabel l2=new JLabel("Age");
        l2.setBounds(180,10,100,20);
        add(l2);
        
        JLabel l3=new JLabel("Gender");
        l3.setBounds(300,10,100,20);
        add(l3);
        
        JLabel l4=new JLabel("JOb");
        l4.setBounds(430,10,100,20);
        add(l4);
        
        JLabel l5=new JLabel("Salary");
        l5.setBounds(560,10,100,20);
        add(l5);
        
        JLabel l6=new JLabel("Phone");
        l6.setBounds(685,10,100,20);
        add(l6);
        
        JLabel l7=new JLabel("Email");
        l7.setBounds(820,10,100,20);
        add(l7);
        
        JLabel l8=new JLabel("Aadhar");
        l8.setBounds(940,10,100,20);
        add(l8);
        
        table = new JTable();
        table.setBounds(0,40,1035,300);
        add(table);
        
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        back = new JButton("Back");
        back.setBounds(350,500,300,30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);
        
        setBounds(150,100,1050, 600);
        setVisible(true);
        
    }
    
    Employee(String str){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel l1=new JLabel("Name");
        l1.setBounds(50,10,100,20);
        add(l1);
        
        JLabel l2=new JLabel("Age");
        l2.setBounds(180,10,100,20);
        add(l2);
        
        JLabel l3=new JLabel("Gender");
        l3.setBounds(300,10,100,20);
        add(l3);
        
        JLabel l4=new JLabel("JOb");
        l4.setBounds(430,10,100,20);
        add(l4);
        
        JLabel l5=new JLabel("Salary");
        l5.setBounds(560,10,100,20);
        add(l5);
        
        JLabel l6=new JLabel("Phone");
        l6.setBounds(685,10,100,20);
        add(l6);
        
        JLabel l7=new JLabel("Email");
        l7.setBounds(820,10,100,20);
        add(l7);
        
        JLabel l8=new JLabel("Aadhar");
        l8.setBounds(940,10,100,20);
        add(l8);
        
        table = new JTable();
        table.setBounds(0,40,1035,300);
        add(table);
        
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from employee where job='Manager'");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        back = new JButton("Back");
        back.setBounds(350,500,300,30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);
        
        setBounds(150,100,1050, 600);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        
            setVisible(false);
            new Reception();
    }
    
    
    public static void main(String[] args) {
        new Employee();
    }
}

