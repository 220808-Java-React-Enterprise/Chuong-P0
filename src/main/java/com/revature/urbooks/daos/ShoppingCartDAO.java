package com.revature.urbooks.daos;

import com.revature.urbooks.models.User;

import java.io.IOException;
import java.util.List;

public class ShoppingCartDAO implements CrudDAO<User>{
    @Override
    public void save(User obj) throws IOException {

    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
