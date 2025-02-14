package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class balanceenquiry extends JFrame implements ActionListener {

    JLabel label2;
    JButton button1;
    String pin;

    balanceenquiry(String pin){
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550,830);
        add(image);

        JLabel label1 = new JLabel("YOUR CURRENT BALANCE IS Rs ");
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(430,180,700,35);
        label1.setForeground(Color.WHITE);
        image.add(label1);

        label2 = new JLabel();
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(430,220,400,35);
        label2.setForeground(Color.WHITE);
        image.add(label2);

        button1 = new JButton("BACK");
        button1.setBounds(700,406,150,35);
        button1.setBackground(new Color(65,125,128));
        button1.setForeground(Color.WHITE);
        button1.addActionListener(this);
        image.add(button1);

        int balance = 0;
        try{
            con con = new con();
            ResultSet resultSet = con.statement.executeQuery("Select * from  bank where '"+pin+"' ");
            while (resultSet.next()){
                if (resultSet.getString("type").equals("deposit")){
                    balance += Integer.parseInt(resultSet.getString("amount"));
                }else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        label2.setText(""+balance);

        setLayout(null);
        setSize(1500,1000);
        setLocation(0,0);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new mainClass(pin);

    }
    public static void main(String[] args) {
        new balanceenquiry("");
    }
}
