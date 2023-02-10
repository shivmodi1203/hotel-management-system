package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class AddDriver extends JFrame implements ActionListener{
    
    JTextField tfname,tfage,tfcar,tfmodel,tflocation;
    JComboBox gendercombo,availablecombo;
    JButton add,cancel;
    
    AddDriver(){
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel heading = new JLabel("Add Drivers");
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        heading.setBounds(150,10,200,20);
        add(heading);
        
        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblname.setBounds(60,60,120,20);
        add(lblname);
         
        tfname = new JTextField();
        tfname.setBounds(200,60,150,30);
        add(tfname);
        
        JLabel lblage = new JLabel("Age");
        lblage.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblage.setBounds(60,100,120,20);
        add(lblage);
        
        tfage = new JTextField();
        tfage.setBounds(200,100,150,30);
        add(tfage);
        
        JLabel lblgender = new JLabel("Gender");
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblgender.setBounds(60,140,120,20);
        add(lblgender);
        
        String genderOption[] = {"Male","Female"};
        gendercombo = new JComboBox(genderOption);
        gendercombo.setBounds(200,140,150,30);
        gendercombo.setBackground(Color.white);
        add(gendercombo);
        
        JLabel lblcar = new JLabel("Car Company");
        lblcar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblcar.setBounds(60,180,120,20);
        add(lblcar);
        
        tfcar = new JTextField();
        tfcar.setBounds(200,180,150,30);
        add(tfcar);
        
        JLabel lblmodel = new JLabel("Car Model");
        lblmodel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblmodel.setBounds(60,220,120,20);
        add(lblmodel);
        
        tfmodel = new JTextField();
        tfmodel.setBounds(200,220,150,30);
        add(tfmodel);
        
        JLabel lblavailable = new JLabel("Available");
        lblavailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblavailable.setBounds(60,260,120,20);
        add(lblavailable);
        
        String driverOption[] = {"Available","Busy"};
        availablecombo = new JComboBox(driverOption);
        availablecombo.setBounds(200,260,150,30);
        availablecombo.setBackground(Color.white);
        add(availablecombo);
        
        JLabel lbllocation = new JLabel("Location");
        lbllocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbllocation.setBounds(60,300,120,20);
        add(lbllocation);
        
        tflocation = new JTextField();
        tflocation.setBounds(200,300,150,30);
        add(tflocation);
        
        add = new JButton("Submit");
        add.setBounds(60,370,130,30);
        add.setBackground(Color.black);
        add.setForeground(Color.white);
        add.addActionListener(this);
        add(add);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(220,370,130,30);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/eleven.jpg"));
//        JLabel image = new JLabel(i1);
        Image i2 = i1.getImage().getScaledInstance(500,500,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 30, 500, 350);
        add(image);
        
        setBounds(200,200,980,470);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == add){
            String name = tfname.getText();
            String age = tfage.getText();
            String gender = (String) gendercombo.getSelectedItem();
            String company = tfcar.getText();
            String model = tfmodel.getText();
            String availablity = (String) availablecombo.getSelectedItem();
            String location = tflocation.getText();

            try {
            Conn conn = new Conn();
            String query = "insert into driver values('"+name+"','"+age+"','"+gender+"','"+company+"','"+model+"','"+availablity+"','"+location+"')";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"New Driver Added Successfully");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
            
        }else{
            setVisible(false);
        } 
        
        
    }
    
    public static void main(String[] args) {
        new AddDriver();
    }
}
