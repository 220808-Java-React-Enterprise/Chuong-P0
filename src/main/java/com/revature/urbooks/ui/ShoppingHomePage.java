package com.revature.urbooks.ui;

import com.revature.urbooks.daos.UserDAO;
import com.revature.urbooks.models.Order;
import com.revature.urbooks.models.User;

import com.revature.urbooks.services.BookService;
import com.revature.urbooks.services.OrderHistoryService;
import com.revature.urbooks.services.UserService;

import java.util.List;
import java.util.Scanner;

//Shopping home page
public class ShoppingHomePage implements IMenu {
    private final User user;
    private final BookService bookService;

    private final OrderHistoryService orderHistoryService;


    public ShoppingHomePage(User user, BookService bookService, OrderHistoryService orderHistoryService) {
        this.user = user;
        this.bookService = bookService;
        this.orderHistoryService = orderHistoryService;
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
                        displayOrdersHistory();
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

    private void displayOrdersHistory() {
        List<Order> orders = orderHistoryService.getAllOrders(user.getId());
        System.out.println("Your ordered history " + user.getUsername());
        System.out.println("============================================");
        System.out.printf("%-50s%-30s%-30s%-30s%-20s\n",  "ORDER NUMBER",  "SUB TOTAL", "TAX", "GRAND TOTAL", "STATUS");
        for(Order o : orders) {
            String status = "Processed";
            if(!o.isStatus()) {
                status = "Pending";
            }
            System.out.printf("%-50s%-30s%-30s%-30s%-20s\n",
                    o.getId(),
                    o.getSubTotal(),
                    o.getTax(),
                    o.getGrandTotal(),
                    status);


        }
    }
}// end class
