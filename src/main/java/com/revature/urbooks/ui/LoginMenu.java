package com.revature.urbooks.ui;

import com.revature.urbooks.daos.BookDAO;
import com.revature.urbooks.daos.UserDAO;
import com.revature.urbooks.models.User;
import com.revature.urbooks.services.BookService;
import com.revature.urbooks.services.UserService;
import com.revature.urbooks.utils.custom_exceptions.InvalidUserException;

import java.util.Scanner;
import java.util.UUID;

public class LoginMenu implements IMenu {
    private final UserService userService;

    public LoginMenu(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void start() {
        String userInput = "";
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\nWelcome to UR-Bookstore!");
                System.out.println("[1] Login");
                System.out.println("[2] Register");
                System.out.println("[x] Exit Application!");

                System.out.print("\nEnter: ");
                userInput = scan.nextLine();

                switch (userInput) {
                    case "1":
                        login();
                        break;
                    case "2":
                        User user = signup();
                        userService.register(user);
                        new ShoppingMainMenu(user, new BookService(new BookDAO())).start();
                        break;
                    case "x":
                        System.out.println("\nGoodbye!");
                        System.exit(0);

                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    // Each user of ADMIN or DEFAULT has theirs own menu
    private void login() {
        String username = "";
        String password = "";
        Scanner scan = new Scanner(System.in);

        System.out.println("\nLogging in...");

        exit: {
            while (true) {
                System.out.print("\nEnter username: ");
                username = scan.nextLine();

                System.out.print("\nEnter password: ");
                password = scan.nextLine();

                try {
                    User user = userService.login(username, password);
                    if (user.getRole().equals("ADMIN"))
                        new AdminMainMenu(user, new UserService(new UserDAO())).start();
                    else
                        new ShoppingMainMenu(user, new BookService(new BookDAO())).start();

                    if (user.getRole().equals("DEFAULT"))
                        new ShoppingMainMenu(user, new BookService(new BookDAO())).start();
                    break exit;
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private User signup() {
        String username = "";
        String password = "";
        String password2 = "";
        String email = "";
        String phone = "";
        String firstName = "";
        String lastName = "";

        User user;
        Scanner scan = new Scanner(System.in);

        System.out.println("\nCreating account...");

        exit:
        {
            while (true) {
                // if username is valid exit this loop
                usernameExit:
                {
                    while (true) {
                        System.out.print("\nEnter a username (alphanumeric with at least 8 characters): ");
                        username = scan.nextLine();

                        try {
                            userService.isValidUsername(username);
                            userService.isDuplicateUsername(username);
                            break usernameExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                //if user password is valid exit this loop
                passwordExit:
                {
                    while (true) {
                        try {
                            System.out.print("\nEnter a password (alphanumeric with at least 8 characters): ");
                            password = scan.nextLine();

                            userService.isValidPassword(password);

                            System.out.print("\nRe-enter password: ");
                            password2 = scan.nextLine();

                            userService.isSamePassword(password, password2);
                            break passwordExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                //if user email is valid exit this loop
                emailExit:
                {
                    while (true) {
                        try {
                            System.out.print("\nEnter a valid email: ");
                            email = scan.nextLine();

                            userService.isValidEmail(email);

                            userService.isSamePassword(password, password2);
                            break emailExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                //if user phone is valid exit this loop
                phoneExit:
                {
                    while (true) {
                        try {
                            System.out.print("\nEnter phone (xxx-xxx-xxxx): ");
                            phone = scan.nextLine();
                            break phoneExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                //if user firstName is valid exit this loop
                firstNameExit:
                {
                    while (true) {
                        try {
                            System.out.print("\nEnter first name: ");
                            firstName = scan.nextLine();
                            break firstNameExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                //if user firstName is valid exit this loop
                lastNameExit:
                {
                    while (true) {
                        try {
                            System.out.print("\nEnter last name: ");
                            lastName = scan.nextLine();
                            break lastNameExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

               confirmExit: {
                   while (true) {
                       System.out.println("\nIs this correct (y/n):");
                       System.out.println("Username: " + username + "\nPassword: " + password);
                       System.out.print("\nEnter: ");

                       switch (scan.nextLine().toLowerCase()) {
                           case "y":
                               user = new User(
                                       UUID.randomUUID().toString(),
                                       username,
                                       password,
                                       "DEFAULT",
                                       email,
                                       phone,
                                       firstName,
                                       lastName);
                               return user;
                           case "n":
                               System.out.println("\nRestarting...");
                               break confirmExit;
                           default:
                               System.out.println("\nInvalid input!");
                               break;
                       }
                   }
               }
            }
        }
    }
}
