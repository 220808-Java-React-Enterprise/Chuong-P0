package com.revature.urbooks.ui;

import com.revature.urbooks.models.Book;
import com.revature.urbooks.models.User;
import com.revature.urbooks.services.BookService;

import java.util.*;

public class ShoppingMenu implements IMenu{

    private final User user;
    private final BookService bookService;

    private List<Book> books;

    private final Map<Integer, Integer> cart;

    public ShoppingMenu(User user, BookService bookService, List<Book> books) {
        this.user = user;
        this.bookService = bookService;
        this.books = books;
        this.cart = new HashMap<>();
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                displayAllBooks();
                System.out.println("\n[1] Add to cart: ");
                System.out.println("[2] Edit cart: ");
                System.out.println("[3] View cart: ");
                System.out.println("[3] View cart: ");
                System.out.println("[4] Check out: ");
                System.out.print("\nEnter: ");
                switch (scan.nextLine()) {
                    case "1":
                        enterItemNumberToBuy();
                        break;
                    case "x":
                        // exit shopping menu. return to ShoppingMainMenu page
                        new ShoppingMainMenu(user, bookService);
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void enterItemNumberToBuy() {
        Scanner scan = new Scanner(System.in);
        exit: {
            while(true) {
                System.out.println("\n[1] Enter Item # to buy: ");
                System.out.println("[x] Done: ");
                System.out.print("\nEnter: ");
                String s = scan.nextLine();
                if(s.equals("x")) break exit;
                Integer itemNumber = Integer.parseInt(s);
                if(itemNumber > 0 && itemNumber < books.size()) {
                    addToCart(itemNumber);
                } else {
                    System.out.println("Bad input");
                }
            }
        }
    }

    private void addToCart(Integer itemNumber) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter quantity: ");
        int requestQuantity = scan.nextInt();
        if(cart.containsKey(itemNumber)) {
            int currentQuantity = cart.get(itemNumber);
            cart.put(itemNumber, currentQuantity +  requestQuantity);
        } else {
            cart.put(itemNumber, requestQuantity);
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
