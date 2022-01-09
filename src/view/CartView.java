package view;
import view.Book;
import view.PurchaseView;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CartView extends JFrame{

    private List<Book> shop;
    private Double totalPrice = 0.0;

    public CartView(List<Book> bookList){
        setTitle("Shopping Cart");
        setSize(750, 750);
        setLayout(new GridLayout(10, 1));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(Color.white);
        addCompShop(CartSingleton.getInstance().getCart());
    }

    private final JButton buyButton = new JButton("Buy all");

    void addCompShop(List<Book> bookList){
        for(int i = 0; i < bookList.size(); i++){
        addPanel(bookList.get(i), i);
        }
        addTotal();
        add(buyButton);
        buyButton.addActionListener(new BuyListener());
    }

    private void addPanel(Book book, int y){

        totalPrice += book.getPrice();

        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setBounds(0, y * 70 + 10, 600, 60);
        //Border blackline = BorderFactory.createLineBorder(Color.black);
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        //panel.setBorder(blackline);

        JLabel book1 = new JLabel(book.getTitle());
        book1.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        JLabel author1 = new JLabel(book.getAuthor());
        author1.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        JLabel year = new JLabel(book.getYearOfPublication().toString());
        year.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        JLabel cat = new JLabel(book.getCategory());
        cat.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        JLabel price1 = new JLabel(book.getPrice().toString() + " Lei");
        price1.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        book1.setBounds(0, 0, 400, 75);
        author1.setBounds(0, 75, 400, 75 );
        price1.setBounds(400, 0, 100, 0);
        panel.add(book1);
        panel.add(author1);
        panel.add(year);
        panel.add(price1);
        panel.add(cat);
        add(panel);
    }

    public void addTotal(){
        JPanel totalCost = new JPanel();
        totalCost.setVisible(true);
        totalCost.setSize(600, 60);
        totalCost.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        JLabel total = new JLabel("Total Price: " + totalPrice.toString() + " Lei");
        total.setFont(new Font("Serif", Font.PLAIN, 36));
        totalCost.add(total);
        add(totalCost);

    }

    public void closeCart(){
        setVisible(false);
    }

    private class BuyListener implements ActionListener{
        private BuyListener(){

        }
        @Override
        public void actionPerformed(ActionEvent e) {
            PurchaseView purchaseView = new PurchaseView();
            purchaseView.addMessage("Thank you for your order." +
                    "You can collect it in one of our stores");
            purchaseView.setVisible(true);
            CartSingleton.getInstance().resetCart();
            closeCart();
        }
    }


}