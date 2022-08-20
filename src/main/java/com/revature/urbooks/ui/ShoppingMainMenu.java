package com.revature.urbooks.ui;

import com.revature.urbooks.daos.UserDAO;
import com.revature.urbooks.dto.UserDto;
import com.revature.urbooks.models.User;
import com.revature.urbooks.models.Book;

import com.revature.urbooks.services.BookService;
import com.revature.urbooks.services.UserService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ShoppingMainMenu implements IMenu {
    private final User user;
    private final BookService bookService;


    public ShoppingMainMenu(User user, BookService bookService) {
        this.user = user;
        this.bookService = bookService;

    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nWelcome to the UR Bookstore - " + user.getUsername() + "!");
                System.out.println("[1] View all books");
                System.out.println("[2] Ordered history");
                System.out.println("[x] Logout");
                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        // view all available books to buy
                        viewAllBooks();
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

    private void viewAllBooks() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nViewing all programming books...");
                List<Book> books = this.bookService.getAllBooks();

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

                break exit;
            }
        }
    }


}
