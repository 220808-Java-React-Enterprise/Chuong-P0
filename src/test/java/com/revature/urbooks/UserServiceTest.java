package com.revature.urbooks;

import com.revature.urbooks.daos.UserDAO;
import com.revature.urbooks.models.User;
import com.revature.urbooks.services.UserService;
import com.revature.urbooks.utils.custom_exceptions.InvalidUserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    /* dependency injection */
    private UserService sut; // sut = system under test
    private final UserDAO mockUserDao = mock(UserDAO.class);

    @Before
    public void setup() {
        sut = new UserService(mockUserDao);
    }

    @Test
    public void test_isValidUsername_givenCorrectUsername() {
        // Arrange
        String validUsername = "bduong0929";

        // Act
        boolean flag = sut.isValidUsername(validUsername);

        // Assert
        Assert.assertTrue(flag);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isNotValidUsername_givenInCorrectUsername() {
        // Arrange
        String invalidUsername = "bduong";

        // Act
        sut.isValidUsername(invalidUsername);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isNotValidUsername_givenEmptyUsername() {
        // Arrange
        String invalidUsername = "";

        // Act
        sut.isValidUsername(invalidUsername);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isNotValidUsername_givenOnlyNumbers() {
        // Arrange
        String invalidUsername = "123456";

        // Act
        sut.isValidUsername(invalidUsername);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isNotValidUsername_startingWithUnderscore() {
        // Arrange
        String invalidUsername = "_bduong0929";

        // Act
        sut.isValidUsername(invalidUsername);
    }

    @Test
    public void test_login_validLoginGivenCorrectCredentials() {
        // Arrange
        UserService spiedSut = Mockito.spy(sut);
        String validUsername = "bduong0929";
        String validPassword = "Passw0rd";
        when(spiedSut.isValidUsername(validUsername)).thenReturn(true);
        when(spiedSut.isValidPassword(validPassword)).thenReturn(true);
        when(mockUserDao.getUserByUsernameAndPassword(validUsername, validPassword)).thenReturn(new User());

        // Act
        User user = spiedSut.login(validUsername, validPassword);

        // Assert
        Assert.assertNotNull(user);
        verify(mockUserDao, times(1)).getUserByUsernameAndPassword(validUsername, validPassword);
    }

    @Test(expected = InvalidUserException.class)
    public void test_login_invalidLoginGivenIncorrectCredentials() {
        // Arrange
        UserService spiedSut = Mockito.spy(sut);
        String invalidUsername = "invalid";
        String invalidPassword = "invalid";
        when(mockUserDao.getUserByUsernameAndPassword(invalidUsername, invalidPassword)).thenReturn(null);

        // Act
        sut.login(invalidUsername, invalidPassword);
    }
}