package com.revature.yolp;

import com.revature.yolp.daos.UserDAO;
import com.revature.yolp.services.UserService;
import com.revature.yolp.ui.LoginMenu;
import com.revature.yolp.utils.database.ConnectionFactory;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        new LoginMenu(new UserService(new UserDAO())).start();
    }
}



// link to ER Diagram: https://lucid.app/lucidchart/a9f6bb80-4667-4817-9683-794c3b286205/edit?invitationId=inv_4801180e-7cad-499e-9505-eee490503080#
