package com.revature.urbooks.ui;

import com.revature.urbooks.daos.UserDAO;
import com.revature.urbooks.models.User;

import com.revature.urbooks.services.BookService;
import com.revature.urbooks.services.UserService;

import java.util.Scanner;

//Shopping home page
public class ShoppingHomePage implements IMenu {
    private final User user;
    private final BookService bookService;


    public ShoppingHomePage(User user, BookService bookService) {
        this.user = user;
        this.bookService = bookService;

    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nWelcome to the home page of UR Bookstore - " + user.getUsername() + "!");
                System.out.println("[1] Start shopping for books");
                System.out.println("[2] Ordered history");
                System.out.println("[x] Logout");
                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        new ShoppingMenu(user, bookService, bookService.getAllBooks()).start();
                        break;
                    case "2":


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
    }// end start()
}// end class
