package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;


public class PageView extends JFrame {
   private  static ProductView productView;
    private static SearchView searchView;

    public static void setSearchView(SearchView searchView) {
        PageView.searchView = searchView;
    }
    public PageView() {
        setTitle("Saphira's Library");
        setSize(1000, 1000);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addComponents();

    }

    private class LoginClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            LoginView loginView = new LoginView();
            loginView.setVisible(true);
        }

    }

    private void addComponents() {
        addMenuBar();
        //scrollBar();
        imgAdd();
    }

    public static class CartListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //List<Book> bookList = productView.getbList();
            CartView cart = new CartView(productView.getbList());
            cart.setVisible(true);
        }
    }

    private void addMenuBar() {

        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Products");
        JMenu m2 = new JMenu("Search");
        JMenu m3 = new JMenu("Favorites");
        JButton m17 = new JButton("Go to Favorites");
        m17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FavoriteView favoriteView = new FavoriteView(productView.getFavList(), productView.getbList());
                favoriteView.setVisible(true);
            }
        });
        m3.add(m17);
        JMenu m4 = new JMenu("Cart");
        JButton m14 = new JButton("Go to cart");
        m14.addActionListener(new CartListener());
        m4.add(m14);
        JMenu m5 = new JMenu("Login");
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(m4);
        mb.add(m5);
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginClickListener());
        JButton cat1 = new JButton("Fantasy");
        JButton cat2 = new JButton("Science fiction");
        JButton cat3 = new JButton("Biography");
        JButton cat4 = new JButton("Dark humour");
        JButton cat5 = new JButton("Novel");
        JButton cat6 = new JButton("Romance");
        JButton cat7 = new JButton("Psychology");
        JButton cat8 = new JButton("Fiction");
        JButton cat9 = new JButton("Foreign Languages");
        JButton cat10 = new JButton("Marketing");
        JButton product = new JButton("Products");
        cat1.addActionListener(new CategoryListener());
        cat2.addActionListener(new CategoryListener());
        cat3.addActionListener(new CategoryListener());
        cat4.addActionListener(new CategoryListener());
        cat5.addActionListener(new CategoryListener());
        cat6.addActionListener(new CategoryListener());
        cat7.addActionListener(new CategoryListener());
        cat8.addActionListener(new CategoryListener());
        cat9.addActionListener(new CategoryListener());
        cat10.addActionListener(new CategoryListener());
        product.addActionListener(new ProductListener());
        m1.add(product);
        m1.add(cat1);
        m1.add(cat2);
        m1.add(cat3);
        m1.add(cat4);
        m1.add(cat5);
        m1.add(cat6);
        m1.add(cat7);
        m1.add(cat8);
        m1.add(cat9);
        m1.add(cat10);
        getContentPane().add(BorderLayout.NORTH, mb);
        mb.setBackground(new java.awt.Color(45, 203, 46));
        setJMenuBar(mb);


        JTextField tf = new JTextField(30);


        JButton find = new JButton("Find");
        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SearchView searchView = new SearchView(tf.getText());
                searchView.setVisible(true);

            }
        });



        m2.add(tf);
        m2.add(find);
        m5.add(loginButton);
    }


    private void scrollBar() {


        setLayout(new BorderLayout());
        JScrollBar horizonBar = new JScrollBar(JScrollBar.HORIZONTAL, 60, 40, 0, 400);
        JScrollBar verticalBar = new JScrollBar(JScrollBar.VERTICAL, 60, 60, 0, 150);
        horizonBar.setUnitIncrement(2);
        horizonBar.setBlockIncrement(1);
        add(horizonBar, BorderLayout.SOUTH);
        add(verticalBar, BorderLayout.EAST);
        setVisible(true);
        horizonBar.setBackground(new java.awt.Color(246, 245, 245));
        verticalBar.setBackground(new java.awt.Color(248, 248, 248));
    }


    private void imgAdd() {
        Image icon = Toolkit.getDefaultToolkit().getImage("cat.jpg");
        setIconImage(icon);

        setLayout(new BorderLayout());

        ImageIcon icon1 = new ImageIcon("app.jpg");
        Image img1 = icon1.getImage();
        Image newimg2 = img1.getScaledInstance(950, 350,  java.awt.Image.SCALE_SMOOTH);
        icon1 = new ImageIcon(newimg2);
        JLabel label1 = new JLabel(icon1);
        setVisible(true);
        add(label1, BorderLayout.PAGE_START);

        ImageIcon icon2 = new ImageIcon("sale.jpg");
        Image img = icon2.getImage();
        Image newimg = img.getScaledInstance(900, 250,  java.awt.Image.SCALE_SMOOTH);
        icon2 = new ImageIcon(newimg);
        JLabel label2 = new JLabel(icon2);
        setVisible(true);
        add(label2, BorderLayout.PAGE_END);

        ImageIcon icon3 = new ImageIcon("comertiatl.jpg");
        Image img2 = icon3.getImage();
        Image newimg3 = img2.getScaledInstance(500, 300,  java.awt.Image.SCALE_SMOOTH);
        icon3 = new ImageIcon(newimg3);
        JLabel label3 = new JLabel(icon3);
        setVisible(true);
        add(label3, BorderLayout.LINE_START);

        ImageIcon icon4 = new ImageIcon("comertial2.jpg");
        Image img4 = icon4.getImage();
        Image newimg4 = img4.getScaledInstance(500, 300,  java.awt.Image.SCALE_SMOOTH);
        icon4 = new ImageIcon(newimg4);
        JLabel label4 = new JLabel(icon4);
        setVisible(true);
        add(label4, BorderLayout.LINE_END);


    }
    public class ProductListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductView productView = new ProductView();
            productView.setVisible(true);
        }
    }

    public static class SearchCartListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            List<Book> bookList = searchView.getList();
            CartView cart = new CartView(bookList);
            cart.setVisible(true);
        }
    }

    public class CategoryListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {


            Object source = e.getSource();
            JButton buttonC = (JButton) source;
            System.out.println(buttonC.getText());
            SearchView searchView = new SearchView(buttonC.getText());
            searchView.setVisible(true);
        }
        }


    public void setProductView(ProductView productView) {
        this.productView = productView;
    }
}

