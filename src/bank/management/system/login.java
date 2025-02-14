package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class login extends JFrame implements ActionListener {

    JLabel lable1, lable2, lable3;
    JTextField textField2;
    JPasswordField passwordField3;
    JButton button1, button2, button3;

    login(){
        super("Bank Management System");

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 100,100);
        add(image);

        ImageIcon card1 =new ImageIcon(ClassLoader.getSystemResource("icon/card.png"));
        Image card2 = card1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon card3 = new ImageIcon(card2);
        JLabel cardimage = new JLabel(card3);
        cardimage.setBounds(630, 315, 100,100);
        add(cardimage);



        lable1 = new JLabel("WELCOME TO ATM");
        lable1.setForeground(Color.WHITE);
        lable1.setFont(new Font("AvantGrade", Font.BOLD, 38));
        lable1.setBounds(230,125, 450, 40);
        add(lable1);

        lable2 = new JLabel("CARD NO: ");
        lable2.setForeground(Color.WHITE);
        lable2.setFont(new Font("Ralway", Font.BOLD, 28));
        lable2.setBounds(150,190,375 , 30);
        add(lable2);

        textField2 = new JTextField(15);
        textField2.setBounds(325,190,230,30);
        textField2.setFont(new Font("Arial", Font.BOLD,14));
        add(textField2);

        lable3 = new JLabel("PIN: ");
        lable3.setForeground(Color.WHITE);
        lable3.setFont(new Font("Ralway", Font.BOLD, 28));
        lable3.setBounds(150,250, 450, 40);
        add(lable3);

        passwordField3 = new JPasswordField(15);
        passwordField3.setBounds(325,250, 230,30);
        passwordField3.setFont(new Font("Arial",Font.BOLD,14));
        add(passwordField3);

        button1 =new JButton("SIGN IN");
        button1.setFont(new Font("Arial", Font.BOLD, 14));
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.BLACK);
        button1.setBounds(300,300,100,30);
        button1.addActionListener(this);
        add(button1);

        button2 =new JButton("CLEAR");
        button2.setFont(new Font("Arial", Font.BOLD, 14));
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.BLACK);
        button2.setBounds(430,300,100,30);
        button2.addActionListener(this);
        add(button2);

        button3 =new JButton("SIGN UP");
        button3.setFont(new Font("Arial", Font.BOLD, 14));
        button3.setForeground(Color.WHITE);
        button3.setBackground(Color.BLACK);
        button3.setBounds(300,350,230,30);
        button3.addActionListener(this);
        add(button3);



        ImageIcon bg1=new ImageIcon(ClassLoader.getSystemResource("icon/backbg.png"));
        Image bg2 = bg1.getImage().getScaledInstance(850,480, Image.SCALE_DEFAULT);
        ImageIcon bg3 = new ImageIcon(bg2);
        JLabel bgimage = new JLabel(bg3);
        bgimage.setBounds(0, 0, 850,480);
        add(bgimage);


        setLayout(null);
        setSize(850, 450);
        setLocation(450, 200);
//        setUndecorated(true);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if (e.getSource()== button1){
                con c = new con();
                String cardno = textField2.getText();
                String pin = passwordField3.getText();
                String q = "select * from login where cardno = '"+cardno+"' and pin = '"+pin+"' ";
                ResultSet resultSet = c.statement.executeQuery(q);
                if (resultSet.next()){
                    setVisible(false);
                    new mainClass(pin);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } else if (e.getSource()== button2) {
                textField2.setText("");
                passwordField3.setText("");
            }else if (e.getSource()== button3){
                new signup();
                setVisible(false);
            }
        }catch (Exception E){
            E.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new login();
    }
}
