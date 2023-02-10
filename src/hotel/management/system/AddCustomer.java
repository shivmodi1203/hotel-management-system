package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.Date;

public class AddCustomer extends JFrame implements ActionListener{
    
    JTextField tfnumber,tfname,tfcountry,tfdeposit;
    JComboBox idcombo,gendercombo;
    JRadioButton rbmale,rbfemale;
    Choice croom;
    JLabel checkintime;
    JButton add,back;
    
    AddCustomer(){
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel heading = new JLabel("New Customer Form");
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        heading.setBounds(100,20,300,30);
        add(heading);
        
        JLabel lblid = new JLabel("ID");
        lblid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblid.setBounds(35,80,100,20);
        add(lblid);
        
        String idOption[] = {"Aadhar","Passport","Driving License","Voter-id Card","Ration Card"};
        idcombo = new JComboBox(idOption);
        idcombo.setBounds(200,80,150,25);
        idcombo.setBackground(Color.white);
        add(idcombo);
        
        JLabel lblnumber = new JLabel("ID Number");
        lblnumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblnumber.setBounds(35,120,100,20);
        add(lblnumber);
        
        tfnumber = new JTextField();
        tfnumber.setBounds(200,120,150,25);
        add(tfnumber);
        
        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblname.setBounds(35,160,100,20);
        add(lblname);
         
        tfname = new JTextField();
        tfname.setBounds(200,160,150,25);
        add(tfname);
        
        JLabel lblgender = new JLabel("Gender");
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblgender.setBounds(35,200,100,20);
        add(lblgender);
        
        rbmale = new JRadioButton("MALE");
        rbmale.setBounds(200,200,70,25);
        rbmale.setFont(new Font("Tahoma",Font.PLAIN,14));
        rbmale.setBackground(Color.white);
        add(rbmale);
        
        rbfemale = new JRadioButton("FEMALE");
        rbfemale.setBounds(280,200,80,25);
        rbfemale.setFont(new Font("Tahoma",Font.PLAIN,14));
        rbfemale.setBackground(Color.white);
        add(rbfemale);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbmale);
        bg.add(rbfemale);
        
        JLabel lblcountry = new JLabel("Country");
        lblcountry.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblcountry.setBounds(35,240,100,20);
        add(lblcountry);
        
        tfcountry = new JTextField();
        tfcountry.setBounds(200,240,150,25);
        add(tfcountry);
        
        JLabel lblroom = new JLabel("Allocate Room");
        lblroom.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblroom.setBounds(35,280,110,25);
        add(lblroom);
        
        croom = new Choice();
        
        try {
            Conn conn = new Conn();
            String query = "select * from room where availablity = 'Available'";
            ResultSet rs = conn.s.executeQuery(query);
            while(rs.next()){
                croom.add(rs.getString("roomnumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        croom.setBounds(200,280,150,25);
        add(croom);
        
        JLabel lbltime = new JLabel("Checkin Time");
        lbltime.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbltime.setBounds(35,320,100,20);
        add(lbltime);
        
        Date date = new Date();
        
        checkintime = new JLabel(""+date);
        checkintime.setFont(new Font("Tahoma", Font.PLAIN, 16));
        checkintime.setBounds(200,320,150,25);
        add(checkintime);
        
        JLabel lbldeposit = new JLabel("Deposit");
        lbldeposit.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbldeposit.setBounds(35,360,100,20);
        add(lbldeposit);
        
        tfdeposit = new JTextField();
        tfdeposit.setBounds(200,360,150,25);
        add(tfdeposit);
        
        add = new JButton("Add");
        add.setBounds(35,400,150,30);
        add.setBackground(Color.black);
        add.setForeground(Color.white);
        add.addActionListener(this);
        add(add);
        
        back = new JButton("Back");
        back.setBounds(200,400,150,30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/fifth.png"));
        Image i2 = i1.getImage().getScaledInstance(300,400,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 300, 400);
        add(image);
        
        setBounds(300,200,800,500);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == add){
            String id = (String) idcombo.getSelectedItem();
            String number = tfnumber.getText();
            String name = tfname.getText();
            String gender = null;
            if(rbmale.isSelected()){
                gender = "Male";
            }else if(rbfemale.isSelected()){
                gender = "Female";
            }
            String country = tfcountry.getText();
            String room = croom.getSelectedItem();
            String time = checkintime.getText();
            String deposit = tfdeposit.getText();
            
            try {
                Conn conn = new Conn();
                String query = "insert into customer values('"+id+"','"+number+"','"+name+"','"+gender+"','"+country+"','"+room+"','"+time+"','"+deposit+"')";
                String query2 = "update room set availablity = 'Occupied' where roomnumber = '"+room+"'";
                conn.s.executeUpdate(query);
                conn.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null,"New Customer Added Successfully");
                setVisible(false);
                
                new Reception();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }else if(ae.getSource() == back){
            setVisible(false);
            new Reception();
        }
//            String name = tfname.getText();
//            String age = tfage.getText();
//            String gender = (String) gendercombo.getSelectedItem();
//            String company = tfcar.getText();
//            String model = tfmodel.getText();
//            String availablity = (String) availablecombo.getSelectedItem();
//            String location = tflocation.getText();
//
//            try {
//            Conn conn = new Conn();
//            String query = "insert into driver values('"+name+"','"+age+"','"+gender+"','"+company+"','"+model+"','"+availablity+"','"+location+"')";
//            conn.s.executeUpdate(query);
//            JOptionPane.showMessageDialog(null,"New Driver Added Successfully");
//            setVisible(false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//            
//        }else{
//            setVisible(false);
//        } 
    }
    
    public static void main(String[] args) {
        new AddCustomer();
    }
}
