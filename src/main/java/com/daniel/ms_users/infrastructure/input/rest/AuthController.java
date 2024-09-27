package com.daniel.ms_users.infrastructure.input.rest;

import com.daniel.ms_users.application.dto.AuthenticationRequest;
import com.daniel.ms_users.application.dto.AuthenticationResponse;
import com.daniel.ms_users.application.dto.ClientRequest;
import com.daniel.ms_users.application.handler.IClientHandler;
import com.daniel.ms_users.application.handler.IUserHandler;
import com.daniel.ms_users.domain.model.User;
import com.daniel.ms_users.infrastructure.input.rest.cosntants.ApiEndpoints;
import com.daniel.ms_users.infrastructure.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpoints.AUTH_API)
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final IUserHandler userHandler;

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

    @Operation(
            summary = "Authenticate a user",
            description = "Allows a user to authenticate using email and password"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Authentication failed",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content)
    })
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @Operation(
            summary = "Find user by email",
            description = "Retrieve a user's information using their email address"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content)
    })
    @GetMapping("/findByEmail")
    public ResponseEntity<User> getUserById(@RequestParam String email){
        User user = userHandler.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
