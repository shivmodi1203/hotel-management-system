package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class AddRooms extends JFrame implements ActionListener{
    
    JTextField tfroom,tfprice;
    JComboBox availablecombo,cleancombo,typecombo;
    JButton add,cancel;
    
    AddRooms(){
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel heading = new JLabel("Add Rooms");
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        heading.setBounds(150,20,200,20);
        add(heading);
        
        JLabel lblroomno = new JLabel("Room Number");
        lblroomno.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblroomno.setBounds(60,80,120,20);
        add(lblroomno);
         
        tfroom = new JTextField();
        tfroom.setBounds(200,80,150,30);
        add(tfroom);
        
        JLabel lblavailable = new JLabel("Available");
        lblavailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblavailable.setBounds(60,130,120,20);
        add(lblavailable);
        
        String availableOption[] = {"Available","Occupied"};
        availablecombo = new JComboBox(availableOption);
        availablecombo.setBounds(200,130,150,30);
        availablecombo.setBackground(Color.white);
        add(availablecombo);
        
        JLabel lblclean = new JLabel("Cleaning Status");
        lblclean.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblclean.setBounds(60,180,120,20);
        add(lblclean);
        
        String cleanOption[] = {"Cleaned","Dirty"};
        cleancombo = new JComboBox(cleanOption);
        cleancombo.setBounds(200,180,150,30);
        cleancombo.setBackground(Color.white);
        add(cleancombo);
        
        JLabel lblprice = new JLabel("Price");
        lblprice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblprice.setBounds(60,230,120,20);
        add(lblprice);
         
        tfprice = new JTextField();
        tfprice.setBounds(200,230,150,30);
        add(tfprice);
        
        JLabel lbltype = new JLabel("Bed Type");
        lbltype.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbltype.setBounds(60,280,120,20);
        add(lbltype);
        
        String typeOption[] = {"Single Bed","Double Bed"};
        typecombo = new JComboBox(typeOption);
        typecombo.setBounds(200,280,150,30);
        typecombo.setBackground(Color.white);
        add(typecombo);
        
        add = new JButton("Add");
        add.setBounds(60,350,130,30);
        add.setBackground(Color.black);
        add.setForeground(Color.white);
        add.addActionListener(this);
        add(add);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(220,350,130,30);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/twelve.jpg"));
//        JLabel image = new JLabel(i1);
        Image i2 = i1.getImage().getScaledInstance(500,500,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 30, 500, 350);
        add(image);
        
        setBounds(200,180,940,470);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == add){
            String roomnumber = tfroom.getText();
            String availablity = (String) availablecombo.getSelectedItem();
            String status = (String) cleancombo.getSelectedItem();
            String price = tfprice.getText();
            String type = (String) typecombo.getSelectedItem();
            
            try {
            Conn conn = new Conn();
            String query = "insert into room values('"+roomnumber+"','"+availablity+"','"+status+"','"+price+"','"+type+"')";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"New Room Added Successfully");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
            
        }else{
            setVisible(false);
        } 
        
        
    }
    
    public static void main(String[] args) {
        new AddRooms();
    }
}
