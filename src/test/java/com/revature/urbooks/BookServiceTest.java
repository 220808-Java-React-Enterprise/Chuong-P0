package com.revature.urbooks;

import com.revature.urbooks.daos.BookDAO;
import com.revature.urbooks.daos.CrudDAO;
import com.revature.urbooks.models.Book;
import com.revature.urbooks.services.BookService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class BookServiceTest {

    @Mock
    CrudDAO crudDAO;

    BookService bookService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        bookService = new BookService(new BookDAO());

    }

}
