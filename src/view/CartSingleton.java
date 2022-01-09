package view;

import java.util.ArrayList;
import java.util.List;

public class CartSingleton {
    private List<Book> cart;
    private static final CartSingleton cartSingleton = new CartSingleton();

    private CartSingleton(){
        cart = new ArrayList<>();
    }

    public static CartSingleton getInstance(){
        return  cartSingleton;
    }

    public List<Book> getCart(){
        return cart;
    }

    public void addBookToCart(Book book){
        cart.add(book);
    }

    public void resetCart(){
        cart = new ArrayList<>();
    }
}
