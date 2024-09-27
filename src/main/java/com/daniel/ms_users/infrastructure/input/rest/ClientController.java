package com.daniel.ms_users.infrastructure.input.rest;

import com.daniel.ms_users.application.handler.IClientHandler;
import com.daniel.ms_users.infrastructure.input.rest.cosntants.ApiEndpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiEndpoints.CLIENT_API)
public class ClientController {

    private final IClientHandler clientHandler;


}
