package com.revature.urbooks.ui.admain;

import com.revature.urbooks.daos.UserDAO;
import com.revature.urbooks.dto.UserDto;
import com.revature.urbooks.models.User;
import com.revature.urbooks.services.UserService;
import com.revature.urbooks.ui.IMenu;
import com.revature.urbooks.ui.LoginMenu;

import java.util.List;
import java.util.Scanner;

public class AdminCrudUsers implements IMenu {

    private final User user;
    private final UserService userService;

    public AdminCrudUsers(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit: {
            this.viewUsers();
            while (true) {
                System.out.println("\nWelcome to the main menu " + user.getUsername() + "!");
                System.out.println("[1] Edit/Update users");
                System.out.println("[1] Add user");
                System.out.println("[x] Sign out!");
                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
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

    private void viewUsers() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("");
                System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n","User Name","First Name","Last Name", "Email", "Phone", "User Role");
                List<User> users = this.userService.getAllUsers();

                for (User u : users) {
                    System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n", 1 + ". " + user.getUsername(),  user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), user.getRole());
                    System.out.println("");
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
