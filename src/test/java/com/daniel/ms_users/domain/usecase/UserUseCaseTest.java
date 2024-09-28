package com.daniel.ms_users.domain.usecase;

import com.daniel.ms_users.TestConstants;
import com.daniel.ms_users.domain.exception.*;
import com.daniel.ms_users.domain.model.User;
import com.daniel.ms_users.domain.spi.IRolePersistencePort;
import com.daniel.ms_users.domain.spi.IUserPersistencePort;
import com.daniel.ms_users.domain.util.PasswordEncoderUtil;
import com.daniel.ms_users.domain.util.UserValidations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @InjectMocks
    private UserUseCase userUseCase;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private UserValidations userValidations;

    @Mock
    private PasswordEncoderUtil passwordEncoderUtil;

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUserReturnsSuccess() {
        when(userValidations.isAdult(TestConstants.USER_OWNER)).thenReturn(true);
        when(userPersistencePort.existByEmail(TestConstants.EMAIL)).thenReturn(false);
        when(rolePersistencePort.getRoleByName(TestConstants.ROLE_NAME_OWNER))
                .thenReturn(Optional.of(TestConstants.ROLE_OWNER));
        when(passwordEncoderUtil.encode(TestConstants.PASSWORD)).thenReturn(TestConstants.ENCODED_PASSWORD);
        when(userPersistencePort.saveUser(any(User.class))).thenReturn(TestConstants.USER_OWNER);

        User result = userUseCase.saveUser(TestConstants.USER_OWNER, TestConstants.ROLE_NAME_OWNER);

        assertNotNull(result);
        assertEquals(TestConstants.USER_OWNER, result);
        verify(userPersistencePort).saveUser(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenUserIsUnderageAndOwner() {
        // Arrange
        when(userValidations.isAdult(TestConstants.USER_OWNER)).thenReturn(false);

        // Act & Assert
        UserUnderageException exception = assertThrows(UserUnderageException.class,
                () -> userUseCase.saveUser(TestConstants.USER_OWNER, TestConstants.ROLE_VALUE_OWNER));
        assertEquals(ErrorMessages.USER_UNDERAGE.getMessage(), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyInUse() {
        // Arrange
        when(userPersistencePort.existByEmail(TestConstants.EMAIL)).thenReturn(true);

        // Act & Assert
        EmailAlreadyInUseException exception = assertThrows(EmailAlreadyInUseException.class,
                () -> userUseCase.saveUser(TestConstants.USER_OWNER, TestConstants.ROLE_NAME_OWNER));
        assertEquals(ErrorMessages.EMAIL_ALREADY_IN_USE.getMessage(TestConstants.EMAIL), exception.getMessage());
    }


    @Test
    void shouldThrowExceptionWhenRoleNotFound() {
        // Arrange
        when(userPersistencePort.existByEmail(TestConstants.EMAIL)).thenReturn(false);
        when(rolePersistencePort.getRoleByName(TestConstants.ROLE_NAME_OWNER)).thenReturn(Optional.empty());

        // Act & Assert
        RoleNotFoundException exception = assertThrows(RoleNotFoundException.class,
                () -> userUseCase.saveUser(TestConstants.USER_OWNER, TestConstants.ROLE_NAME_OWNER));
        assertEquals(ErrorMessages.ROLE_NOT_FOUND.getMessage(TestConstants.ROLE_NAME_OWNER), exception.getMessage());
    }

    @Test
    void shouldGetUserByIdWhenExists() {
        // Arrange
        when(userPersistencePort.getUserById(TestConstants.USER_ID)).thenReturn(Optional.of(TestConstants.USER_OWNER));

        // Act
        User result = userUseCase.getUserById(TestConstants.USER_ID);

        // Assert
        assertNotNull(result);
        assertEquals(TestConstants.USER_OWNER, result);
        verify(userPersistencePort, times(1)).getUserById(TestConstants.USER_ID);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundById() {
        // Arrange
        when(userPersistencePort.getUserById(TestConstants.USER_ID)).thenReturn(Optional.empty());

        // Act & Assert
        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userUseCase.getUserById(TestConstants.USER_ID));
        assertEquals(ErrorMessages.USER_NOT_FOUND.getMessage(TestConstants.USER_ID), exception.getMessage());
    }

    @Test
    void shouldGetUserByEmailWhenExists() {
        // Arrange
        when(userPersistencePort.getUserByEmail(TestConstants.EMAIL)).thenReturn(Optional.of(TestConstants.USER_OWNER));

        // Act
        User result = userUseCase.getUserByEmail(TestConstants.EMAIL);

        // Assert
        assertNotNull(result);
        assertEquals(TestConstants.USER_OWNER, result);
        verify(userPersistencePort, times(1)).getUserByEmail(TestConstants.EMAIL);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundByEmail() {
        // Arrange
        when(userPersistencePort.getUserByEmail(TestConstants.EMAIL)).thenReturn(Optional.empty());

        // Act & Assert
        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userUseCase.getUserByEmail(TestConstants.EMAIL));
        assertEquals(ErrorMessages.USER_NOT_FOUND_BY_EMAIL.getMessage(TestConstants.EMAIL), exception.getMessage());
    }

}