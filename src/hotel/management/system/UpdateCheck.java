package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
//import net.proteanit.sql.*;

public class UpdateCheck extends JFrame implements ActionListener {

    JButton back, update, check;
    JTextField tfroom, tfname, tfcheckin, tfpaid, tfpanding;
    Choice customer;

    UpdateCheck() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel text = new JLabel("Update Status");
        text.setFont(new Font("Tahoma", Font.BOLD, 20));
        text.setBounds(90, 20, 200, 30);
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
        lblroom.setBounds(30, 120, 200, 20);
        add(lblroom);

        tfroom = new JTextField();
        tfroom.setBounds(200, 120, 150, 25);
        add(tfroom);

        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblname.setBounds(30, 160, 100, 20);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 160, 150, 25);
        add(tfname);

        JLabel lblcheckin = new JLabel("Checkin Time");
        lblcheckin.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblcheckin.setBounds(30, 200, 100, 20);
        add(lblcheckin);

        tfcheckin = new JTextField();
        tfcheckin.setBounds(200, 200, 150, 25);
        add(tfcheckin);

        JLabel lblpaid = new JLabel("Amount Paid");
        lblpaid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblpaid.setBounds(30, 240, 100, 20);
        add(lblpaid);

        tfpaid = new JTextField();
        tfpaid.setBounds(200, 240, 150, 25);
        add(tfpaid);

        JLabel lblpanding = new JLabel("Panding Amount");
        lblpanding.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblpanding.setBounds(30, 280, 120, 20);
        add(lblpanding);

        tfpanding = new JTextField();
        tfpanding.setBounds(200, 280, 150, 25);
        add(tfpanding);

        check = new JButton("Check");
        check.setBounds(30, 340, 100, 30);
        check.setBackground(Color.black);
        check.setForeground(Color.white);
        check.addActionListener(this);
        add(check);

        update = new JButton("Update");
        update.setBounds(150, 340, 100, 30);
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(270, 340, 100, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/nine.jpg"));
        JLabel image = new JLabel(i1);
//        Image i2 = i1.getImage().getScaledInstance(500,500,Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 500, 300);
        add(image);

        setBounds(150, 100, 1016, 600);
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
                    tfname.setText(rs.getString("name"));
                    tfcheckin.setText(rs.getString("checkin_time"));
                    tfpaid.setText(rs.getString("deposit"));
                }
                ResultSet rs2 = conn.s.executeQuery("select * from room where roomnumber = '" + tfroom.getText() + "'");
                while (rs2.next()) {
                    String price = rs2.getString("price");
                    int amountPanding = Integer.parseInt(price) - Integer.parseInt(tfpaid.getText());
                    tfpanding.setText("" + amountPanding);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            String number = customer.getSelectedItem();
            String room = tfroom.getText();
            String name = tfname.getText();
            String checkin = tfcheckin.getText();
            String paid = tfpaid.getText();
            try {
                Conn conn = new Conn();
                conn.s.executeUpdate("update customer set room = '" + room + "',name = '" + name + "',checkin_time = '" + checkin + "',deposit = '" + paid + "' where number = '" + number + "'");
                JOptionPane.showMessageDialog(null, "Data Updated Successfully");
//                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new UpdateCheck();
    }
}
