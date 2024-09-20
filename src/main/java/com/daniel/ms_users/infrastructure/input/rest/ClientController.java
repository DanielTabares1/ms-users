package com.daniel.ms_users.infrastructure.input.rest;

import com.daniel.ms_users.application.dto.ClientRequest;
import com.daniel.ms_users.application.handler.IClientHandler;
import com.daniel.ms_users.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
public class ClientController {


    private final IClientHandler clientHandler;
    @Operation(
            summary = "Creates a new Client account",
            description = "An client can create a new account"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client successfully created",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Unauthorized to access this function",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<User> addClient(@RequestBody ClientRequest clientRequest){
        User newUser = clientHandler.saveClient(clientRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

}
