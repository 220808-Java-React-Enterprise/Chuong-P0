package com.revature.urbooks.ui.admain.users;


import com.revature.urbooks.daos.BookDAO;
import com.revature.urbooks.daos.OrderHistoryDAO;
import com.revature.urbooks.daos.UserDAO;
import com.revature.urbooks.models.User;
import com.revature.urbooks.services.BookService;
import com.revature.urbooks.services.OrderHistoryService;
import com.revature.urbooks.services.UserService;
import com.revature.urbooks.ui.IMenu;
import com.revature.urbooks.ui.ShoppingHomePage;
import com.revature.urbooks.ui.admain.AdminMainMenu;

import java.util.List;
import java.util.Scanner;

public class AdminUserMenu implements IMenu {

    private final User user;
    private final UserService userService;

    public AdminUserMenu(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }

    @Override
    public void start() {
        String userInput = "";
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\nManage Users");
                System.out.println("[1] View all users");
                System.out.println("[2] Search user by name");
                System.out.println("[x] Back to admin main menu!");

                System.out.print("\nEnter: ");
                userInput = scan.nextLine();

                switch (userInput) {
                    case "1":
                        displayUsersList();
                        break;
                    case "2":
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

    private void displayCurrentUser() {
        System.out.println("Current User");
        System.out.println(user.getUsername() + ":" + user.getEmail() + ":" + user.getPhone() + ":" + user.getId());
    }

    private void displayUsersList() {
        List<User> users = userService.getAllUsers();
        System.out.println("All Users");
        System.out.println("================================================");
        for(User u : users) {
            System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n", 1 + ". " + u.getUsername(),  u.getFirstName(), u.getLastName(), u.getEmail(), u.getPhone(), u.getRole());
            System.out.println("");
        }
    }
}
