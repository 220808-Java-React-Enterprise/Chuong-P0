package com.revature.urbooks.ui.admin.users;


import com.revature.urbooks.daos.UserDAO;
import com.revature.urbooks.models.User;
import com.revature.urbooks.services.UserService;
import com.revature.urbooks.ui.IMenu;
import com.revature.urbooks.ui.admin.AdminMainMenu;
import com.revature.urbooks.utils.custom_exceptions.InvalidUserException;

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
                        searchUserByName();
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

    private void searchUserByName() {
        String firstName = "";
        String lastName = "";
        Scanner scan = new Scanner(System.in);

        System.out.println("\nSearching user name...");

        exit: {
            while (true) {
                System.out.print("\nEnter first name: ");
                firstName = scan.nextLine();

                System.out.print("\nEnter last name: ");
                lastName = scan.nextLine();

                try {
                    List<User> users = userService.searchUserFirstAndLast(firstName, lastName);
                    displaySearchedUser(users);
                    break exit;
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    private void displaySearchedUser(List<User> users) {
        System.out.println("Searched Users");
        System.out.println("================================================");
        for(User u : users) {
            System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n", 1 + ". " + u.getUsername(),  u.getFirstName(), u.getLastName(), u.getEmail(), u.getPhone(), u.getRole());
            System.out.println("");
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
