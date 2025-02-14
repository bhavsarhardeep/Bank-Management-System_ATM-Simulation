package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class withdrawl extends JFrame implements ActionListener {

    String pin;
    TextField textField;
    JButton button1, button2;

    withdrawl(String pin){

        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550,830);
        add(image);

        JLabel label1 = new JLabel("MAXIMUM WITHDRAWL IS Rs. 10000");
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460,180,700,35);
        label1.setForeground(Color.WHITE);
        image.add(label1);

        JLabel label2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(460,220,400,35);
        label2.setForeground(Color.WHITE);
        image.add(label2);

        textField = new TextField();
        textField.setBounds(460,260, 320,25);
        textField.setFont(new Font("Ralway", Font.BOLD,22));
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        image.add(textField);

        button1 = new JButton("WITHDRAW");
        button1.setBounds(700,362,150,35);
        button1.setBackground(new Color(65,125,128));
        button1.setForeground(Color.WHITE);
        button1.addActionListener(this);
        image.add(button1);

        button2 = new JButton("BACK");
        button2.setBounds(700,406 ,150,35);
        button2.setBackground(new Color(65,125,128));
        button2.setForeground(Color.WHITE);
        button2.addActionListener(this);
        image.add(button2);


        setLayout(null);
        setSize(1500,1000);
        setLocation(0,0);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button1) {
            try {
                String amount = textField.getText();
                Date date = new Date();
                if (textField.getText().equals(" ")) {
                    JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
                } else {
                    con con = new con();
                    ResultSet resultSet = con.statement.executeQuery("Select * from bank where pin = '" + pin + "' ");
                    int balance = 0;
                    while (resultSet.next()) {
                        if (resultSet.getString("type").equals("deposit")) {
                            balance += Integer.parseInt(resultSet.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(resultSet.getString("amount"));
                        }
                    }
                    if (balance < Integer.parseInt(amount)) {
                        JOptionPane.showMessageDialog(null, "Insufficient balance");
                        return;
                    }
                    con.statement.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'withdrawl', '"+amount+"')");
                    JOptionPane.showMessageDialog(null, "Rs. " +amount+ " Debitted Successfully");
                    setVisible(false);
                    new mainClass(pin);
                }
            } catch (Exception E) {
                E.printStackTrace();
            }
        }else if (e.getSource()==button2){
            setVisible(false);
            new mainClass(pin);

        }
    }
    public static void main(String[] args) {
        new withdrawl("");
    }
}
