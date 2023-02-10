package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.*;

public class PickupService extends JFrame implements ActionListener {
    JTable table;
    JButton back,submit;
    JComboBox bedType;
    JCheckBox available;
    Choice carType;
    
    PickupService(){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel heading = new JLabel("Search for Car");
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        heading.setBounds(400,30,200,30);
        add(heading);
        
        JLabel lblbed = new JLabel("Type of Car");
        lblbed.setBounds(50,100,100,30);
        add(lblbed);
        
        carType = new Choice();
        carType.setBounds(150, 105, 200, 25);
        add(carType);

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from driver");
            while (rs.next()) {
                carType.add(rs.getString("company"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JLabel l1=new JLabel("Name");
        l1.setBounds(50,160,100,20);
        add(l1);
        
        JLabel l2=new JLabel("Age");
        l2.setBounds(200,160,100,20);
        add(l2);
        
        JLabel l3=new JLabel("Gender");
        l3.setBounds(330,160,100,20);
        add(l3);
        
        JLabel l4=new JLabel("Company");
        l4.setBounds(470,160,100,20);
        add(l4);
        
        JLabel l5=new JLabel("Model");
        l5.setBounds(620,160,100,20);
        add(l5);
        
        JLabel l6=new JLabel("Availablity");
        l6.setBounds(750,160,100,20);
        add(l6);
        
        JLabel l7=new JLabel("Location");
        l7.setBounds(900,160,100,20);
        add(l7);
        
        table = new JTable();
        table.setBounds(0,200,1000,300);
        add(table);
        
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from driver");
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
            String query = "select * from driver where company='"+carType.getSelectedItem()+"'";
            ResultSet rs=conn.s.executeQuery(query);
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
        new PickupService();
    }
}
