package view;

import javax.swing.*;

public class PurchaseView extends JFrame {

    public PurchaseView() {
        setTitle("Thank you!");
        setSize(450, 150);
        setLocationRelativeTo(null);
    }

    public void addMessage(String string) {
        JLabel message = new JLabel(string);
        message.setHorizontalAlignment(SwingConstants.CENTER);
        add(message);
    }
}