package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.*;

public class Department extends JFrame implements ActionListener {
    
    JTable table;
    JButton back;
    
    Department(){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel l1=new JLabel("Department");
        l1.setBounds(120,10,100,20);
        add(l1);
        
        JLabel l2=new JLabel("Budget");
        l2.setBounds(480,10,100,20);
        add(l2);
        
        table = new JTable();
        table.setBounds(0,40,700,300);
        add(table);
        
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from department");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        back = new JButton("Back");
        back.setBounds(190,400,300,30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);
        
        setBounds(150,100,715, 480);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
        
            setVisible(false);
            new Reception();
    }
    
    
    public static void main(String[] args) {
        new Department();
    }
}

