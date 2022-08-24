package com.revature.urbooks.ui.admin.publishers;

import com.revature.urbooks.daos.UserDAO;
import com.revature.urbooks.models.Book;
import com.revature.urbooks.models.Publisher;
import com.revature.urbooks.models.User;
import com.revature.urbooks.services.BookService;
import com.revature.urbooks.services.PublisherService;
import com.revature.urbooks.services.UserService;
import com.revature.urbooks.ui.IMenu;
import com.revature.urbooks.ui.admin.AdminMainMenu;

import java.util.List;
import java.util.Scanner;

public class AdminPublisherMenu implements IMenu {
    private final User user;
    private final PublisherService publisherService;

    public AdminPublisherMenu(User user, PublisherService publisherService) {
        this.user = user;
        this.publisherService = publisherService;
    }

    @Override
    public void start() {
        String userInput = "";
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\nManage Users");
                System.out.println("[1] View all publishers");
                System.out.println("[2] Edit publisher");
                System.out.println("[x] Back to admin main menu!");

                System.out.print("\nEnter: ");
                userInput = scan.nextLine();

                switch (userInput) {
                    case "1":
                        displayAllPublishers();
                        break;
                    case "2":
                        editPublishers();
                        break;
                    case "x":
                        //AdminMainMenu(User user, UserService userService)
                        new AdminMainMenu(user, new UserService(new UserDAO()));
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void editPublishers() {
        List<Publisher> publishers = publisherService.getAllPublishers();
        System.out.println("\nAll publishers books...");
        System.out.printf("%-10s%-30s%-30s%-30s%-30S%-30s%-20s\n", "ITEM #",  "NAME          ",  "ADDRESS     ",    "CITY          ", "STATE       ",      "ZIPCODE       ",      "PHONE       ");
        System.out.printf("%-10s%-30s%-30s%-30s%-30S%-30s%-20s\n",  "=====",   "===============", "===============", "===============", "===============", "===============", "===============");
        System.out.println("");
        for (int i = 0; i < publishers.size(); i++) {
            Publisher publisher = publishers.get(i);
            System.out.printf("%-10s%-30s%-30s%-30s%-30S%-30s%-20s\n",
                    i+1 + ". ",
                    publisher.getName(),
                    publisher.getAddress(),
                    publisher.getCity(),
                    publisher.getState(),
                    publisher.getZipcode(),
                    publisher.getPhone());
        }
    }

    private void displayAllPublishers() {
        List<Publisher> publishers = publisherService.getAllPublishers();
        System.out.println("\nAll publishers books...");
        System.out.printf("%-10s%-30s%-30s%-30s%-30S%-30s%-20s\n", "ITEM #",  "NAME          ",  "ADDRESS     ",    "CITY          ", "STATE       ",      "ZIPCODE       ",      "PHONE       ");
        System.out.printf("%-10s%-30s%-30s%-30s%-30S%-30s%-20s\n",  "=====",   "===============", "===============", "===============", "===============", "===============", "===============");
        System.out.println("");
        for (int i = 0; i < publishers.size(); i++) {
            Publisher publisher = publishers.get(i);
            System.out.printf("%-10s%-30s%-30s%-30s%-30S%-30s%-20s\n",
                    i+1 + ". ",
                    publisher.getName(),
                    publisher.getAddress(),
                    publisher.getCity(),
                    publisher.getState(),
                    publisher.getZipcode(),
                    publisher.getPhone());
        }
    }
}