package view;

import view.Book;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FavoriteView extends JFrame{

    List<Book> bookList;

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void createBList(int size){
        bookList = new ArrayList<>(size);
    }

    public FavoriteView(List<Book> favList, List<Book> bookList) {

        setBookList(bookList);
        setTitle("Favorites");
        setSize(750, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLayout(new GridLayout(11, 1));
        for(int i = 0; i < favList.size(); i++) {
            Book book = favList.get(i);
            addBlPanel(book.getTitle(), book.getAuthor(), book.getYearOfPublication(), book.getPrice(), book.getCategory(), i);
        }
        JButton goToCart = new JButton("Go to cart");
        goToCart.addActionListener(new PageView.CartListener());
        add(goToCart);
    }



    public void addBlPanel(String title, String author, Integer yearOfPublication,  Double price,String category, int y){
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setBounds(0, y * 70, 600, 60);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        JLabel book1 = new JLabel(title);
        book1.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        JLabel author1 = new JLabel(author);
        author1.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        JLabel year = new JLabel(yearOfPublication.toString());
        year.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        JLabel cat = new JLabel(category);
        cat.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        JLabel price1 = new JLabel(price.toString() + " Lei");
        price1.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        book1.setBounds(0, 0, 400, 75);
        author1.setBounds(0, 75, 400, 75 );
        price1.setBounds(400, 0, 100, 0);
        panel.add(book1);
        panel.add(author1);
        panel.add(year);
        panel.add(price1);
        panel.add(cat);
        JButton buy1 = new JButton("Add to Cart");
        buy1.addActionListener( new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JLabel title = (JLabel) panel.getComponent(0);
                JLabel author = (JLabel) panel.getComponent(1);
                JLabel year = (JLabel) panel.getComponent(2);
                JLabel price = (JLabel) panel.getComponent(3);
                JLabel category = (JLabel) panel.getComponent(4);
                String[] price2 = price.getText().split(" ");

                CartSingleton.getInstance().addBookToCart(new Book(0, title.getText(), author.getText(), Double.parseDouble(price2[0]), category.getText(), Integer.parseInt(year.getText())));
            }
        });
        panel.add(buy1);
        add(panel);
    }


}