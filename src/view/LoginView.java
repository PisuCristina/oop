package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginView extends JFrame {

    private class LoginClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


            try {
                Class.forName("org.postgresql.Driver");
                Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/oop", "postgres", "lambor345");
                Statement st = conn.createStatement();
                String query = "SELECT \"User_name\", \"id\", \"password\" FROM \"Users\" WHERE \"User_name\" = \'"+user.getText()+"\'";
               // String query1 = "\"SELECT \"user_id\", \"book_id\" FROM \"favorites\"";
                ResultSet rs = st.executeQuery(query);
               // ResultSet rs1 = st.executeQuery(query1);

                if (rs.next()) {
                    String Uname = new String(rs.getString(1));
                    String pass = new String(rs.getString(3));
                    String name = user.getText();
                    if (Uname.equals(name) && EncryptPassword.comparePassword(parola.getText(),pass)) {
                        PageView page = new PageView();
                        ProductView productView = new ProductView();
                        SearchView searchView = new SearchView(" ");
                        page.setProductView(productView);
                        page.setSearchView(searchView);
                        page.setVisible(true);
                        loginClose();
                    }

                    else{
                        JOptionPane.showMessageDialog(null,
                                "Wrong username or password");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,
                            "Wrong username or password");
                }
                st.close();
                conn.close();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
    }

                public void loginClose(){
                    setVisible(false);
                }

            private class SignupClickListener implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                    SignUp signup = new SignUp();
                    signup.setVisible(true);
                }
            }

            private void addGreetingMessage () {
                helloMsg.setBounds(120, 50, 300, 40);
                helloMsg.setFont(new Font("Serif", Font.PLAIN, 32));
                add(helloMsg);
            }

            private final JLabel helloMsg = new JLabel("Hello group 30421");
            private final JLabel instr = new JLabel("Please input your credentials below: ");
            private final JTextField user = new JTextField("Username");
            private final JPasswordField parola = new JPasswordField("Password");
            private final JLabel userMsg = new JLabel("Username:");
            private final JLabel passMsg = new JLabel("Password");
            private final JButton button = new JButton("Log in");
            private final JLabel error = new JLabel("Incorrect");
            private final JLabel notlogged = new JLabel("Don't have an account? Sign up!");
            private final JButton signup = new JButton("Sign Up");

    public LoginView() {
                setTitle("Online Book Shop");

                setSize(400, 400);

                setLayout(null);

                setLocationRelativeTo(null);

                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                setBackground(Color.blue);

                addInput();
                button.addActionListener(new LoginClickListener());
                signup.addActionListener(new SignupClickListener());
            }

            private void addError () {
                error.setBounds(120, 50, 300, 40);
                error.setFont(new Font("Serif", Font.PLAIN, 32));
                add(error);
            }

            private void addInput () {

                instr.setBounds(100, 60, 300, 20);
                add(instr);

                userMsg.setBounds(80, 100, 300, 20);
                user.setBounds(80, 130, 300, 25);
                add(userMsg);
                add(user);

                passMsg.setBounds(80, 160, 300, 20);
                parola.setBounds(80, 190, 300, 25);
                add(passMsg);
                add(parola);

                button.setBounds(160, 230, 80, 30);
                add(button);

                notlogged.setBounds(100, 270, 300, 30);
                add(notlogged);

                signup.setBounds(160, 310, 80, 30);
                add(signup);
            }


        }


