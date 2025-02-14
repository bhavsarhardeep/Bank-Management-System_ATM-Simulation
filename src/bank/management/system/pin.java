package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pin extends JFrame implements ActionListener {

    JButton button1, button2;
    JPasswordField p1,p2;
    String pin;


    pin(String pin){

        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550,830);
        add(image);

        JLabel label1 = new JLabel("CHANGE YOUR PIN");
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(430,180,400,35);
        label1.setForeground(Color.WHITE);
        image.add(label1);

        JLabel label2 = new JLabel("New PIN");
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(430,220,150,35);
        label2.setForeground(Color.WHITE);
        image.add(label2);

        p1 =new JPasswordField();
        p1.setBounds(600,220, 120,25);
        p1.setFont(new Font("Ralway", Font.BOLD,22));
        p1.setBackground(new Color(65,125,128));
        p1.setForeground(Color.WHITE);
        image.add(p1);

        JLabel label3 = new JLabel("Re-Enter    New PIN");
        label3.setFont(new Font("System", Font.BOLD, 16));
        label3.setBounds(430,250,400,35);
        label3.setForeground(Color.WHITE);
        image.add(label3);

        p2 =new JPasswordField();
        p2.setBounds(600,250, 120,25);
        p2.setFont(new Font("Ralway", Font.BOLD,22));
        p2.setBackground(new Color(65,125,128));
        p2.setForeground(Color.WHITE);
        image.add(p2);


        button1 = new JButton("CHANGE");
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



        setSize(1500,1080);
        setLayout(null);
        setLocation(0,0);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String pin1 = p1.getText();
            String pin2 = p1.getText();
            if (!pin1.equals(pin2)){
                JOptionPane.showMessageDialog(null,"Entered PIN does not match");
                return;
            }
            if (e.getSource()== button1){
                if (p1.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Enter New PIN");
                    return;
                }
                if (p2.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Re-Enter New PIN");
                    return;
                }

                con con = new con();
                String q1 ="update bank set pin= '"+pin1+"' where pin = '"+pin+"' ";
                String q2 ="update login set pin= '"+pin1+"' where pin = '"+pin+"' ";
                String q3 ="update signupthree set pin= '"+pin1+"' where pin = '"+pin+"' ";

                con.statement.executeUpdate(q1);
                con.statement.executeUpdate(q2);
                con.statement.executeUpdate(q3);

                JOptionPane.showMessageDialog(null,"PIN changed successfully");
                setVisible(false);
                new mainClass(pin);

            } else if (e.getSource()==button2) {
                new mainClass(pin);
                setVisible(false);
                
            }


        }catch (Exception E){
            E.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new pin("");
    }
}
