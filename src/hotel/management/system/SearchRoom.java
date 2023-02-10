package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.*;

public class SearchRoom extends JFrame implements ActionListener {
    JTable table;
    JButton back,submit;
    JComboBox bedType;
    JCheckBox available;
    
    SearchRoom(){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel heading = new JLabel("Search for Room");
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        heading.setBounds(400,30,200,30);
        add(heading);
        
        JLabel lblbed = new JLabel("Bed Type");
        lblbed.setBounds(50,100,100,30);
        add(lblbed);
        
        String bedOption[] = {"Single Bed","Double Bed"};
        bedType = new JComboBox(bedOption);
        bedType.setBounds(150,100,150,25);
        bedType.setBackground(Color.white);
        add(bedType);
        
        available = new JCheckBox("Only display available");
        available.setBounds(650,100,150,25);
        available.setBackground(Color.white);
        add(available);
        
        JLabel l1=new JLabel("Room Number");
        l1.setBounds(50,160,100,20);
        add(l1);
        
        JLabel l2=new JLabel("Availability");
        l2.setBounds(270,160,100,20);
        add(l2);
        
        JLabel l3=new JLabel("Cleaning Status");
        l3.setBounds(450,160,100,20);
        add(l3);
        
        JLabel l4=new JLabel("Price");
        l4.setBounds(670,160,100,20);
        add(l4);
        
        JLabel l5=new JLabel("Bed Type");
        l5.setBounds(870,160,100,20);
        add(l5);
        
        table = new JTable();
        table.setBounds(0,200,1000,300);
        add(table);
        
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from room");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        submit = new JButton("Submit");
        submit.setBounds(300,520,120,30);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        add(submit);
        
        back = new JButton("Back");
        back.setBounds(500,520,120,30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);
        
        setBounds(150,100,1016, 600);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==submit){
            try {
            Conn conn = new Conn();
            String query1 = "select * from room where bed_type='"+bedType.getSelectedItem()+"'";
            String query2 = "select * from room where bed_type='"+bedType.getSelectedItem()+"' AND availablity ='Available'";
            ResultSet rs;
            if(available.isSelected()){
                rs = conn.s.executeQuery(query2);
            }else{
                rs = conn.s.executeQuery(query1);
            }
            table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            setVisible(false);
            new Reception();
        }
    }
    
    
    public static void main(String[] args) {
        new SearchRoom();
    }
}
