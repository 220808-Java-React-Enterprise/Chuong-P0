package com.revature.urbooks.ui.admin.books;

import com.revature.urbooks.daos.UserDAO;
import com.revature.urbooks.models.Book;
import com.revature.urbooks.models.User;
import com.revature.urbooks.services.BookService;
import com.revature.urbooks.services.UserService;
import com.revature.urbooks.ui.IMenu;
import com.revature.urbooks.ui.admin.AdminMainMenu;

import java.util.List;
import java.util.Scanner;

public class AdminBookMenu implements IMenu {
    private final User user;
    private final BookService bookService;

    public AdminBookMenu(User user, BookService bookService) {
        this.user = user;
        this.bookService = bookService;
    }

    @Override
    public void start() {
        String userInput = "";
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\nManage Users");
                System.out.println("[1] View all books");
                System.out.println("[2] Edit book quantity");
                System.out.println("[x] Back to admin main menu!");

                System.out.print("\nEnter: ");
                userInput = scan.nextLine();

                switch (userInput) {
                    case "1":
                        displayAllBooks();
                        break;
                    case "2":
                        editBookQuanitty();
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

    private void editBookQuanitty() {
        List<Book> books = bookService.getAllBooks();
        System.out.println("\nAll programming books...");
        System.out.printf("%-10s%-30s%-30s%-30s%-20s\n", "ITEM #",  "ISBN          ",  "BOOK TITLE     ", "PRICE          ", "QUANTITY       ");
        System.out.printf("%-5s%-30s%-30s%-30s%-20s\n",  "=====",   "===============", "===============", "===============", "===============");
        System.out.println("");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.printf("%-5s%-30s%-30s%-30s%-20s\n",
                    i+1 + ". ",
                    book.getIsbn(),
                    book.getTitle(),
                    String.format("%.2f", book.getPrice()),
                    book.getQuantity());
        }
        int userInputItemNum = 0;
        int userInputQuantity = 0;

        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                exitBookItemNum :
                    while(true) {
                        System.out.println("[1] Enter book # to replenish inventory: ");
                        userInputItemNum = scan.nextInt();
                        if (userInputItemNum >= 1 && userInputItemNum <= books.size()) {
                            break exitBookItemNum;
                        }else {
                            System.out.println("Invalid input. Book item # is out of range");
                        }
                    }// end while
                exitBookQuantity :
                while(true) {
                    System.out.println("Enter quantity to replenish (10min-50max)");
                    userInputQuantity = scan.nextInt();
                    if (userInputQuantity >= 10 && userInputQuantity <= 50) {
                        break exit;
                    } else {
                        System.out.println("Invalid input. range is 10min to 50max for quantity");
                    }
                }// end while
            }// end while
        }//exit
        Book selectedBook = books.get(userInputItemNum - 1);
        selectedBook.setQuantity(selectedBook.getQuantity() + userInputQuantity);
        bookService.update(selectedBook);
        System.out.println(selectedBook.getTitle() +   " quantity updated");
    }

    private void displayAllBooks() {
        List<Book> books = bookService.getAllBooks();
        System.out.println("\nAll programming books...");
        System.out.printf("%-10s%-30s%-30s%-30s%-20s\n", "ITEM #",  "ISBN          ",  "BOOK TITLE     ", "PRICE          ", "QUANTITY       ");
        System.out.printf("%-5s%-30s%-30s%-30s%-20s\n",  "=====",   "===============", "===============", "===============", "===============");
        System.out.println("");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.printf("%-5s%-30s%-30s%-30s%-20s\n",
                    i+1 + ". ",
                    book.getIsbn(),
                    book.getTitle(),
                    String.format("%.2f", book.getPrice()),
                    book.getQuantity());
        }
    }
}
