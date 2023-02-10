package hotel.management.system;

import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HotelManagementSystem extends JFrame implements ActionListener{
    
    HotelManagementSystem(){
        setSize(1366, 565);
        setLocation(0, 75);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/first.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1366, 565);
        add(image);
        
        JLabel text = new JLabel("HOTEL MANAGEMENT SYSTEM");
        text.setBounds(20,430,1000,30);
        text.setForeground(Color.white);
        text.setFont(new Font("serif", Font.PLAIN, 40));
        image.add(text);
        JButton next = new JButton("Next");
        next.setBounds(1150,450,150,50);
        next.setBackground(Color.white);
        next.setForeground(Color.black);
        next.addActionListener(this);
        next.setFont(new Font("serif", Font.PLAIN, 24));
        image.add(next);
        setVisible(true);
        
        while(true){
            text.setVisible(false);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            text.setVisible(true);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new login();
    }
    
    public static void main(String[] args) {
       new HotelManagementSystem();
    }
    
}
