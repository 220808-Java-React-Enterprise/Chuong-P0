package com.revature.urbooks.services;

import com.revature.urbooks.daos.OrderHistoryDAO;
import com.revature.urbooks.models.Order;

import java.util.List;

public class OrderHistoryService {

    private final OrderHistoryDAO orderHistoryDAO;

    public OrderHistoryService(OrderHistoryDAO orderHistoryDAO) {
        this.orderHistoryDAO = orderHistoryDAO;
    }

    public List<Order> getAllOrders(String userId) {
        return orderHistoryDAO.getAllOrders(userId);
    }
}
