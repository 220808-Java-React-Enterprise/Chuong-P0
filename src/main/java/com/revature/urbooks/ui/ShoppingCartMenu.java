package com.revature.urbooks.ui;

import com.revature.urbooks.daos.UserDAO;
import com.revature.urbooks.models.Book;
import com.revature.urbooks.models.User;
import com.revature.urbooks.services.BookService;
import com.revature.urbooks.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCartMenu implements IMenu{

    private final User user;
    private final BookService bookService;

    private final List<Integer> cart;
    private final List<Book> books;

    public ShoppingCartMenu(User user, BookService bookService, List<Book> books) {
        this.user = user;
        this.bookService = bookService;
        this.books = books;
        this.cart = new ArrayList<>();
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                displayAllBooks();
                System.out.println("\n[1] Enter items # to buy: ");
                System.out.println("\n[2] Edit cart: ");
                System.out.println("\n[3] View cart: ");
                System.out.println("\n[x] Log out: ");
                switch (scan.nextLine()) {
                    case "1":
                        break;
                    case "x":
                        // exit shopping menu. return to login page
                        new LoginMenu(new UserService(new UserDAO())).start();
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void displayAllBooks() {

        System.out.println("\nAll programming books...");
        System.out.println("");
        System.out.printf("%-30s%-30s%-30s%-30s%-30s%-20s\n",  "ISBN",  "BOOK TITLE", "PUBLISHER", "PUBLISHER ID", "PRICE", "QUANTITY");
        System.out.printf("%-30s%-30s%-30s%-30s%-30s%-20s\n",  "=====", "===========", "=========", "============", "=====", "========");
        System.out.println("");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.printf("%-30s%-30s%-30s%-30s%-30s%-20s\n", i+1 + ". " + book.getIsbn(),  book.getTitle(), book.getPublisher_name(), book.getPublisher_id(), book.getPrice(), book.getQuantity());
        }

        try {
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nInvalid input!");
        }
    }
}
