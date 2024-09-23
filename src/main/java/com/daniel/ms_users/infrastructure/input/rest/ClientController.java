package com.daniel.ms_users.infrastructure.input.rest;

import com.daniel.ms_users.application.handler.IClientHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client")
public class ClientController {

    private final IClientHandler clientHandler;


}
