package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
//import net.proteanit.sql.*;

public class UpdateRoom extends JFrame implements ActionListener {

    JButton back, update, check;
    JTextField tfroom, tfavailable, tfstatus;
    Choice customer;

    UpdateRoom() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel text = new JLabel("Update Room Status");
        text.setFont(new Font("Tahoma", Font.BOLD, 20));
        text.setBounds(30, 20, 250, 30);
        add(text);

        JLabel lblid = new JLabel("Customer ID");
        lblid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblid.setBounds(30, 80, 100, 20);
        add(lblid);

        customer = new Choice();
        customer.setBounds(200, 80, 150, 25);
        add(customer);

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from customer");
            while (rs.next()) {
                customer.add(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblroom = new JLabel("Room Number");
        lblroom.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblroom.setBounds(30, 130, 200, 20);
        add(lblroom);

        tfroom = new JTextField();
        tfroom.setBounds(200, 130, 150, 25);
        add(tfroom);

        JLabel lblavailable = new JLabel("Availability");
        lblavailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblavailable.setBounds(30, 180, 100, 20);
        add(lblavailable);

        tfavailable = new JTextField();
        tfavailable.setBounds(200, 180, 150, 25);
        add(tfavailable);

        JLabel lblstatus = new JLabel("Cleaning Status");
        lblstatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblstatus.setBounds(30, 230, 150, 20);
        add(lblstatus);

        tfstatus = new JTextField();
        tfstatus.setBounds(200, 230, 150, 25);
        add(tfstatus);

        check = new JButton("Check");
        check.setBounds(30, 300, 100, 30);
        check.setBackground(Color.black);
        check.setForeground(Color.white);
        check.addActionListener(this);
        add(check);

        update = new JButton("Update");
        update.setBounds(150, 300, 100, 30);
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(270, 300, 100, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/seventh.jpg"));
//        JLabel image = new JLabel(i1);
        Image i2 = i1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(450, 50, 500, 300);
        add(image);

        setBounds(150, 100, 1016, 450);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == check) {
            String id = customer.getSelectedItem();
            String query = "select * from customer where number = '" + id + "'";

            try {
                Conn conn = new Conn();
                ResultSet rs = conn.s.executeQuery(query);
                while (rs.next()) {
                    tfroom.setText(rs.getString("room"));
                }
                ResultSet rs2 = conn.s.executeQuery("select * from room where roomnumber = '" + tfroom.getText() + "'");
                while (rs2.next()) {
                    tfavailable.setText(rs2.getString("availablity"));
                    tfstatus.setText(rs2.getString("cleaning_status"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            String room = tfroom.getText();
            String available = tfavailable.getText();
            String status = tfstatus.getText();

            try {
                Conn conn = new Conn();
                conn.s.executeUpdate("update room set availablity = '" + available + "',cleaning_status = '" + status + "' where roomnumber = '" + room + "'");
                JOptionPane.showMessageDialog(null, "Data Updated Successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new UpdateRoom();
    }
}
