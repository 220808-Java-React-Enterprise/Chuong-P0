package com.revature.urbooks;

import com.revature.urbooks.daos.UserDAO;
import com.revature.urbooks.services.UserService;
import com.revature.urbooks.ui.LoginMenu;

// link to ER Diagram: https://lucid.app/lucidchart/a9f6bb80-4667-4817-9683-794c3b286205/edit?invitationId=inv_4801180e-7cad-499e-9505-eee490503080#

public class Main {
    public static void main(String[] args) {
        new LoginMenu(new UserService(new UserDAO())).start();
    }
}




