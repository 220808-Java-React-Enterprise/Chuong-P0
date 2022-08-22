package com.revature.urbooks.services;

import com.revature.urbooks.daos.OrderDAO;
import com.revature.urbooks.daos.OrderDetailDAO;
import com.revature.urbooks.models.Book;
import com.revature.urbooks.models.Order;

import java.util.Map;

public class OrderDetailService {

    private final OrderDetailDAO orderDetailDAO;


    public OrderDetailService(OrderDetailDAO orderDetailDAO) {
        this.orderDetailDAO = orderDetailDAO;

    }

    public void saveToOrderDetail(Order order, Map<String, Book> map) {
        this.orderDetailDAO.saveToOrderDetail(order, map);
    }
}
