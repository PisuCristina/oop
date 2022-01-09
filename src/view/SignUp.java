package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class SignUp extends JFrame {

    private static final JTextField user = new JTextField();
    private static final JPasswordField parola = new JPasswordField();
    private static final JTextField mail = new JTextField();
    private static final JPasswordField confirm = new JPasswordField();
    private static final JLabel userMsg = new JLabel("Username:");
    private static final JLabel mailMsg = new JLabel("E-mail:");
    private static final JLabel confirmMsg = new JLabel("Confirm your password:");
    private static final JLabel passMsg = new JLabel("Password"); //strong pass
    private static final JButton button = new JButton("Create account");
    private static final JLabel error = new JLabel();

    public SignUp() {
        setTitle("Join our Book Empire!");

        setSize(400, 400);

        setLayout(null);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setBackground(Color.blue);

        addSign();
        button.addActionListener(new SignUp.createClickListener());
    }

    private class createClickListener implements ActionListener{


        public void actionPerformed(ActionEvent e) {
            String password = new String(parola.getText());
            String testp = new String(confirm.getText());
            if(password.length() >= 8){
                // store account in database

                if(!password.equals(testp)){
                    JOptionPane.showMessageDialog(null,  "Passwords do not match!");
                    addError();
                } else{
                    try {
                        Class.forName("org.postgresql.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/oop", "postgres", "lambor345");
                        Statement st = conn.createStatement();
                        st.execute("insert into \"Users\" values('"+user.getText()+"','"+EncryptPassword.getEncryption(parola.getText())+"' ,'"+mail.getText()+"')");
                        JOptionPane.showMessageDialog(null, "sign up successful");
                        st.close();
                        conn.close();
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Password too short!");
                addError();
            }


        }
    }

    void addSign()
    {
        userMsg.setBounds(50, 60, 300, 20);
        user.setBounds(50, 90, 300, 25);
        add(userMsg);
        add(user);

        passMsg.setBounds(50, 120, 300, 20);
        parola.setBounds(50, 150, 300, 25);
        add(passMsg);
        add(parola);

        confirmMsg.setBounds(50, 180, 300, 20);
        confirm.setBounds(50, 210, 300, 20);
        add(confirmMsg);
        add(confirm);

        mailMsg.setBounds(50, 240, 300 ,20);
        mail.setBounds(50, 270, 300, 20);
        add(mailMsg);
        add(mail);

        button.setBounds(130, 300, 150, 30);
        add(button);
    }

    void addError(){
        error.setBounds(100, 350, 300, 20);
        error.setBackground(Color.red);
        add(error);
    }
}
