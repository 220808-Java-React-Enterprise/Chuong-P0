package com.revature.urbooks.ui;

import com.revature.urbooks.daos.UserDAO;
import com.revature.urbooks.models.User;
import com.revature.urbooks.services.UserService;

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
                System.out.println("[1] View all users");
                System.out.println("[1] View all books");
                System.out.println("[x] Sign out!");
                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        new AdminCrudUsers(this.user, this.userService).start();
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
