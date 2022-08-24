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

    public List<Order> getAllOrdersByLatestDate(String userId) {
        return orderHistoryDAO.getAllOrdersByLatestDate(userId);
    }

    public List<Order> getAllOrdersByOldestDate(String userId) {
        return orderHistoryDAO.getAllOrdersByOldestDate(userId);
    }

    public List<Order> getAllOrdersByMostExpensive(String userId) {
        return orderHistoryDAO.getAllOrdersByMostExpensive(userId);
    }

    public List<Order> getAllOrdersByLeastExpensive(String userId) {
        return orderHistoryDAO.getAllOrdersByLeastExpensive(userId);
    }
}
