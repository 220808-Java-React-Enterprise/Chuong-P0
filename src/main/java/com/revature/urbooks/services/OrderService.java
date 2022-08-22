package com.revature.urbooks.services;

import com.revature.urbooks.daos.OrderDAO;
import com.revature.urbooks.models.Book;
import com.revature.urbooks.models.Order;

import java.io.IOException;
import java.util.Map;

public class OrderService {
    private final OrderDAO  orderDAO;


    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;

    }

    public void saveOrder(Order order) {
        this.orderDAO.save(order);
    }
}
