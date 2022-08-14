package com.revature.chuong.bookstore.ui;

import com.revature.chuong.bookstore.daos.UserDAO;
import com.revature.chuong.bookstore.services.UserService;

public class Main {
    public static void main(String[] args) {
        new LoginMenu(new UserService(new UserDAO())).start();
    }
}
