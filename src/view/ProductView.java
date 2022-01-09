package view;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProductView extends JFrame{

    private static List<Book> bList;
    private static List<Book> favList;

    public List<Book> getFavList() {
        return favList;
    }

    public List<Book> getList() {
        return bList;
    }

    public ProductView(){
        setTitle("Products");
        setSize(750, 1000);
        setLayout(new GridLayout(22, 0));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBackground(Color.white);
        addBooks();
        createBList(30);
        createFList(30);
        JButton goToCart = new JButton("Go to cart");
        goToCart.addActionListener(new PageView.CartListener());
        add(goToCart);
        JButton goToFav = new JButton("Go to Favorites");
        goToFav.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FavoriteView favoriteView = new FavoriteView(favList, bList);
                favoriteView.setVisible(true);
            }
        });
        add(goToFav);

    }




    private void addBooks(){
        BookRepo bookRepo = new BookRepo();
        bookRepo.populateList();
        List<Book> list = bookRepo.getBooks();
        for(int  i=0; i< list.size(); i++){
            addBlPanel(list.get(i).getTitle(), list.get(i).getAuthor(), list.get(i).getPrice(), list.get(i).getCategory(), list.get(i).getYearOfPublication(),i);
        }
    }

    public void addBlPanel(String title, String author,Double price, String category, Integer yearOfPublication, int y){
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setBounds(0, y * 120, 600, 120);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        //panel.setBorder(blackline);

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
        panel.add(book1);
        panel.add(author1);
        panel.add(year);
        panel.add(price1);
        panel.add(cat);
        JButton buy1 = new JButton("Add to Cart");
        buy1.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel title = (JLabel) panel.getComponent(0);
                JLabel author = (JLabel) panel.getComponent(1);
                JLabel year = (JLabel) panel.getComponent(2);
                JLabel price = (JLabel) panel.getComponent(3);
                JLabel cat = (JLabel) panel.getComponent(4);
                String[] price2 = price.getText().split(" ");

                Book book = new Book(0, title.getText(), author.getText(), Double.parseDouble(price2[0]), cat.getText(), Integer.parseInt(year.getText()));

                CartSingleton.getInstance().addBookToCart(book);
                System.out.println(cat.getText());
            }
        });
        panel.add(buy1);

        JButton favorite = new JButton("Add to Favorites");
        favorite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel title = (JLabel) panel.getComponent(0);
                JLabel author = (JLabel) panel.getComponent(1);
                JLabel year = (JLabel) panel.getComponent(2);
                JLabel price = (JLabel) panel.getComponent(3);
                JLabel cat = (JLabel) panel.getComponent(4);
                String[] price2 = price.getText().split(" ");

                favList.add(new Book(0, title.getText(), author.getText(), Double.parseDouble(price2[0]), cat.getText(), Integer.parseInt(year.getText())));
            }
        });
        panel.add(favorite);
        panel.setLayout(new GridLayout(3,3));
        add(panel);
    }

    public void createBList(int size){
        bList = new ArrayList<>(size);
    }

    public void createFList(int size){
        favList = new ArrayList<>(size);
    }

    public List<Book> getbList() {
        return bList;
    }
}

