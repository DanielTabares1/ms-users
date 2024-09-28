package com.daniel.ms_users.application.handler;

import com.daniel.ms_users.application.dto.OwnerRequest;
import com.daniel.ms_users.application.handler.impl.OwnerHandler;
import com.daniel.ms_users.application.mapper.IOwnerRequestMapper;
import com.daniel.ms_users.domain.api.IUserServicePort;

import com.daniel.ms_users.domain.model.User;
import com.daniel.ms_users.domain.model.UserRoles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static com.daniel.ms_users.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OwnerHandlerTest {

    @InjectMocks
    private OwnerHandler ownerHandler;

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private IOwnerRequestMapper ownerRequestMapper;


    private OwnerRequest ownerRequest;
    private User user;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        ownerRequest = new OwnerRequest(NAME, LAST_NAME, DOCUMENT_NUMBER, CELLPHONE, new Date(), EMAIL, PASSWORD);
        user = USER_CLIENT;
    }


    @Test
    void saveOwner() {
        when(ownerRequestMapper.toUser(any(OwnerRequest.class))).thenReturn(user);
        when(userServicePort.saveUser(any(User.class), eq(UserRoles.OWNER.toString()))).thenReturn(user);

        User result = ownerHandler.saveOwner(ownerRequest);

        verify(ownerRequestMapper).toUser(ownerRequest);
        verify(userServicePort).saveUser(user, UserRoles.OWNER.toString());

        assertEquals(user, result);
    }


}