package com.revature.urbooks.ui.admain;

import com.revature.urbooks.daos.UserDAO;
import com.revature.urbooks.models.User;
import com.revature.urbooks.services.UserService;
import com.revature.urbooks.ui.IMenu;
import com.revature.urbooks.ui.LoginMenu;
import com.revature.urbooks.ui.admain.AdminCrudUsers;
import com.revature.urbooks.ui.admain.users.AdminUserMenu;

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
                System.out.println("[1] Manage all users");
                System.out.println("[1] Manage all books");
                System.out.println("[x] Log out!");
                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        new AdminUserMenu(user, new UserService(new UserDAO())).start();
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