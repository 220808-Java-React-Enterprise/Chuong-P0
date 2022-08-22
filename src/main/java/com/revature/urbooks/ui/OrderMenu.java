package com.revature.urbooks.ui;

import com.revature.urbooks.daos.BookDAO;
import com.revature.urbooks.daos.OrderDAO;
import com.revature.urbooks.daos.UserDAO;
import com.revature.urbooks.models.Book;
import com.revature.urbooks.models.Order;
import com.revature.urbooks.models.User;
import com.revature.urbooks.services.BookService;
import com.revature.urbooks.services.OrderDetailService;
import com.revature.urbooks.services.OrderService;
import com.revature.urbooks.services.UserService;


import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class OrderMenu implements IMenu{
    private  final OrderService orderService;

    private final OrderDetailService orderDetailService;
    private  final User user;

    private Map<String, Book> cart;

    public OrderMenu(
            User user,
            OrderService orderService,
            OrderDetailService orderDetailService,
            Map<String, Book> cart) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
        this.user = user;
        this.cart = cart;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nWelcome to UR Bookstore checkout page - " + user.getUsername() + "!");
                System.out.println("\n[1] Proceed to check out: ");
                System.out.println("[x] Back shopping menu: ");
                System.out.print("\nEnter: ");
                switch (scan.nextLine()) {
                    case "1":
                        Order order = submitCheckoutPage();
                        displayCheckoutSummary(order);
                        break;
                    case "x":
                        // return to shopping menu page
                        //new ShoppingMenu(user, new BookService(new BookDAO()), new BookService().getAllBooks()).start();
                        new LoginMenu(new UserService(new UserDAO()));
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void displayCheckoutSummary(Order order) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nUR Bookstore checkout summary page - " + user.getUsername() + "!");
        System.out.println("Order id: " + order.getId());
        System.out.println("Subtotal: " + order.getSubTotal());
        System.out.println("Tax: " + order.getTax());
        System.out.println("Grand Total: " + order.getGrandTotal());
        System.out.println("");
        System.out.println("Thank you for shopping with UR Books");

        exit: {
            while (true) {
                System.out.println("[x] Back shopping menu: ");
                System.out.print("\nEnter: ");
                switch (scan.nextLine()) {
                    case "x":
                        // return to shopping menu page
                        new ShoppingMenu(user, new BookService(new BookDAO()), new BookService(new BookDAO()).getAllBooks()).start();
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private  Order submitCheckoutPage() {
        if(cart.isEmpty()) {
            exit:
            while(true) {
                Scanner scan = new Scanner(System.in);
                System.out.println("Cart is empty");
                System.out.println("[x] Back shopping menu: ");
                System.out.print("\nEnter: ");
                switch (scan.nextLine()) {
                    case "x":
                        // return to shopping menu page
                        new ShoppingMenu(user, new BookService(new BookDAO()), new BookService(new BookDAO()).getAllBooks()).start();
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
        double taxRate = 1.2/100;
        String id;
        double subTotal = 0.0;
        double tax = 0.0;
        double grandTotal = 0.09;

        for (Map.Entry<String, Book> bookEntry : cart.entrySet()) {
            subTotal += bookEntry.getValue().getPrice() * bookEntry.getValue().getQuantity();
        }

        id = UUID.randomUUID().toString();
        tax = subTotal * taxRate;
        grandTotal += tax + subTotal;

        Order order = new Order(id, subTotal, tax, grandTotal, user);
        order.setUser(user);

        orderService.saveOrder(order);
        this.orderDetailService.saveToOrderDetail(order, this.cart);

        cart.clear();

        return order;
    }
}
