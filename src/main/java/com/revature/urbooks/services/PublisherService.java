package com.revature.urbooks.services;

import com.revature.urbooks.daos.PublisherDAO;
import com.revature.urbooks.models.Publisher;
import com.revature.urbooks.models.User;

import java.util.List;

public class PublisherService  {

    private final PublisherDAO publisherDAO;

    public PublisherService(PublisherDAO publisherDAO) {
        this.publisherDAO = publisherDAO;
    }

    public List<Publisher> getAllPublishers() {
        return publisherDAO.getAllPublishers();
    }
}
