package com.revature.urbooks.ui.admin;

import com.revature.urbooks.daos.BookDAO;
import com.revature.urbooks.daos.PublisherDAO;
import com.revature.urbooks.daos.UserDAO;
import com.revature.urbooks.models.User;
import com.revature.urbooks.services.BookService;
import com.revature.urbooks.services.PublisherService;
import com.revature.urbooks.services.UserService;
import com.revature.urbooks.ui.IMenu;
import com.revature.urbooks.ui.LoginMenu;
import com.revature.urbooks.ui.admin.books.AdminBookMenu;
import com.revature.urbooks.ui.admin.publishers.AdminPublisherMenu;
import com.revature.urbooks.ui.admin.users.AdminUserMenu;

import java.util.Scanner;

public class AdminMainMenu implements IMenu {
    private final User user;
    private final UserService userService;

    public AdminMainMenu(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nWelcome to the admin main menu " + user.getUsername() + "!");
                System.out.println("[1] Manage users");
                System.out.println("[2] Manage books");
                System.out.println("[3] Manage Publisher");
                System.out.println("[x] Log out!");
                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        new AdminUserMenu(user, new UserService(new UserDAO())).start();
                        break;
                    case "2":
                        new AdminBookMenu(user, new BookService(new BookDAO())).start();
                        break;
                    case "3":
                        new AdminPublisherMenu(user, new PublisherService(new PublisherDAO())).start();
                        break;
                    case "x":
                        new LoginMenu(new UserService(new UserDAO())).start();
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }


}
