package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.Date;

public class Checkout extends JFrame implements ActionListener{
    
    JTextField tfroom, tfavailable, tfstatus;
    Choice customer;
    JLabel lblroomnumber,lblcheckintime,lblcheckouttime;
    JButton back, check;
    
    Checkout(){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel heading = new JLabel("Checkout");
        heading.setFont(new Font("Tahoma", Font.BOLD, 25));
        heading.setForeground(Color.blue);
        heading.setBounds(150,20,200,20);
        add(heading);
        
        JLabel lblid = new JLabel("Customer ID");
        lblid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblid.setBounds(30, 80, 100, 20);
        add(lblid);

        customer = new Choice();
        customer.setBounds(200, 80, 150, 25);
        add(customer);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/tick.png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image1 = new JLabel(i3);
        image1.setBounds(355 , 80, 20, 20);
        add(image1);
        
        JLabel lblroom = new JLabel("Room Number");
        lblroom.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblroom.setBounds(30, 130, 200, 20);
        add(lblroom);

        lblroomnumber = new JLabel();
        lblroomnumber.setBounds(200, 130, 150, 25);
        add(lblroomnumber);
        
        JLabel lblcheckin = new JLabel("Checkin Time");
        lblcheckin.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblcheckin.setBounds(30, 180, 100, 20);
        add(lblcheckin);
        
        lblcheckintime = new JLabel();
        lblcheckintime.setBounds(200, 180, 150, 25);
        add(lblcheckintime);
        
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from customer");
            while (rs.next()) {
                customer.add(rs.getString("number"));
                lblroomnumber.setText(rs.getString("room"));
                lblcheckintime.setText(rs.getString("checkin_time"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JLabel lblcheckout = new JLabel("Checkout");
        lblcheckout.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblcheckout.setBounds(30, 230, 100, 20);
        add(lblcheckout);
        
        Date date = new Date();
        lblcheckouttime = new JLabel(""+date);
        lblcheckouttime.setBounds(200, 230, 150, 25);
        add(lblcheckouttime);
        
        check = new JButton("Checkout");
        check.setBounds(30, 340, 150, 30);
        check.setBackground(Color.black);
        check.setForeground(Color.white);
        check.addActionListener(this);
        add(check);

        back = new JButton("Back");
        back.setBounds(220, 340, 150, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icon/sixth.jpg"));
        Image i5 = i4.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image2 = new JLabel(i6);
        image2.setBounds(420 , 80, 500, 290);
        add(image2);
        
        setBounds(150,100,1016, 600);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource() == check){
            String query1="delete from customer where number = '"+customer.getSelectedItem()+"'";
            String query2 = "update room set availablity = 'Available' where roomnumber='"+lblroomnumber.getText()+"' ";
            
            try {
            Conn conn = new Conn();
            conn.s.executeUpdate(query1);
            conn.s.executeUpdate(query2);
            
            JOptionPane.showMessageDialog(null, "Checkout Done");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
            
        }else{
            setVisible(false);
            new Reception();
        }
        
    }
    
    public static void main(String[] args) {
        new Checkout();
    }
    
}
