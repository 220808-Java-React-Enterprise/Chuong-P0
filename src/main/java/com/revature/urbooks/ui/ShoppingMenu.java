package com.revature.urbooks.ui;

import com.revature.urbooks.daos.BookDAO;
import com.revature.urbooks.daos.OrderDAO;
import com.revature.urbooks.daos.OrderDetailDAO;
import com.revature.urbooks.daos.OrderHistoryDAO;
import com.revature.urbooks.models.Book;
import com.revature.urbooks.models.User;
import com.revature.urbooks.services.BookService;
import com.revature.urbooks.services.OrderDetailService;
import com.revature.urbooks.services.OrderHistoryService;
import com.revature.urbooks.services.OrderService;
import com.revature.urbooks.utils.custom_exceptions.InvalidUserException;

import java.util.*;

public class ShoppingMenu implements IMenu{

    private final User user;
    private final BookService bookService;

    private List<Book> books;

    private final Map<String, Book> cart;

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
                System.out.println("\nWelcome to UR Bookstore shopping page - " + user.getUsername() + "!");
                displayAllBooks();
                System.out.println("\n[1] Add to cart: ");
                System.out.println("[2] Edit cart: ");
                System.out.println("[3] View cart: ");
                System.out.println("[4] Check out: ");
                System.out.println("[x] Back to main home page: ");
                System.out.print("\nEnter: ");
                switch (scan.nextLine()) {
                    case "1":
                        enterItemNumberToBuy();
                        break;
                    case "2":
                        enterItemNumberToBuy();
                        break;
                    case "3":
                        viewCart(cart);
                        break;
                    case "4":
                        new OrderMenu(
                                user,
                                new OrderService(new OrderDAO()),
                                new OrderDetailService(new OrderDetailDAO()),
                                cart).start();
                        break;
                    case "x":
                        // return to shopping home page
                        new ShoppingHomePage(user, new BookService(new BookDAO()), new OrderHistoryService(new OrderHistoryDAO()));
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void viewCart(Map<String, Book> cart) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Items in your cart");
        System.out.printf("%-30s%-30s%-30s%-30s%-20s\n",  "ISBN",  "BOOK TITLE", "PUBLISHER", "PRICE", "QUANTITY");
        for(Map.Entry<String, Book> e : cart.entrySet()) {
            System.out.printf("%-30s%-30s%-30s%-30s%-20s\n",
                    e.getValue().getIsbn(),
                    e.getValue().getTitle(),
                    e.getValue().getPublisher_name(),
                    e.getValue().getPrice(),
                    e.getValue().getQuantity());
        }
        exit: {
            while (true) {
                if(cart.isEmpty()) {
                    System.out.println("Cart is empty");
                    System.out.println("\n[x] Back to UR Bookstore shopping page: ");
                    System.out.print("\nEnter: ");
                    switch (scan.nextLine()) {
                        case "x":
                            // return to shopping menu page
                            new ShoppingMenu(user, new BookService(new BookDAO()), new BookService(new BookDAO()).getAllBooks()).start();
                            break exit;
                        default:
                            System.out.println("\nInvalid input!");
                            break;
                    }
                } else {
                    System.out.println("\n[1] Check out: ");
                    System.out.println("\n[x] Back to UR Bookstore shopping page: ");
                    System.out.print("\nEnter: ");

                    switch (scan.nextLine()) {
                        case "1":
                            new OrderMenu(user, new OrderService(new OrderDAO()), new OrderDetailService(new OrderDetailDAO()), cart).start();
                            break;
                        case "x":
                            // return to shopping menu page
                            new ShoppingMenu(user, new BookService(new BookDAO()), new BookService(new BookDAO()).getAllBooks()).start();
                            break exit;
                        default:
                            System.out.println("\nInvalid input!");
                            break;
                    }
                }
            }
        }
    }

    private void enterItemNumberToBuy() {
        Scanner scan = new Scanner(System.in);
        int itemNumber;
        int quantity;

        bookSelection: {
            while (true) {
                System.out.print("\nEnter item # to  buy/add to cart: ");
                try {
                    itemNumber = scan.nextInt();
                    if (itemNumber >= 1 && itemNumber <= books.size()) {
                        break bookSelection;
                    } else {
                        throw new InvalidUserException("Invalid item number");
                    }
                } catch(InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        Book product = books.get(itemNumber-1);

        // item  ordered is not duplicate or in the cart
        Book newOrderBook = new Book(
                product.getId(),
                product.getIsbn(),
                product.getTitle(),
                product.getPublisher_name(),
                product.getPublisher_id(),
                product.getPrice(),
                0);

        bookQuantity: {
            while (true) {
                System.out.print("\nEnter quantity: ");

                try {
                    quantity = scan.nextInt();
                    String id = product.getId();
                    if(cart.containsKey(id)) {
                        Book orderedBook = orderedBook = cart.get(books.get(itemNumber-1).getId());
                        int currentQuantity = cart.get(id).getQuantity();
                        if(currentQuantity+quantity > product.getQuantity()) {
                            throw new InvalidUserException("Invalid input. Quantity ordered is greater than available book's quantity");
                        } else {
                            orderedBook.setQuantity(quantity + currentQuantity);
                            cart.put(product.getId(), orderedBook);
                        }
                    } else if (quantity > product.getQuantity()) {
                        throw new InvalidUserException("Invalid input. Quantity ordered is greater than available book's quantity");
                    } else {
                        newOrderBook.setQuantity(quantity);
                        cart.put(product.getId(), newOrderBook);
                    }

                    break bookQuantity;
                } catch(InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    private void displayAllBooks() {
        System.out.println("\nAll programming books...");
        System.out.printf("%-30s%-30s%-30s%-30s%-30s%-20s\n",  "ISBN",  "BOOK TITLE", "PUBLISHER", "PUBLISHER ID", "PRICE", "QUANTITY");
        System.out.printf("%-30s%-30s%-30s%-30s%-30s%-20s\n",  "=====", "===========", "=========", "============", "=====", "========");
        System.out.println("");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.printf("%-30s%-30s%-30s%-30s%-30s%-20s\n",
                    i+1 + ". " + book.getIsbn(),
                    book.getTitle(),
                    book.getPublisher_name(),
                    book.getPublisher_id(),
                    book.getPrice(),
                    book.getQuantity());
        }
    }
}
