package com.daniel.ms_users.infrastructure.input.rest;

import com.daniel.ms_users.application.dto.AuthenticationRequest;
import com.daniel.ms_users.application.dto.AuthenticationResponse;
import com.daniel.ms_users.application.dto.ClientRequest;
import com.daniel.ms_users.application.handler.IClientHandler;
import com.daniel.ms_users.domain.model.User;
import com.daniel.ms_users.infrastructure.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

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
    public ResponseEntity<User> addClient(@Valid @RequestBody ClientRequest clientRequest){
        User newUser = clientHandler.saveClient(clientRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
