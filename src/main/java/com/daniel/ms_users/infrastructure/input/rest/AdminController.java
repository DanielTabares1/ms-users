package com.daniel.ms_users.infrastructure.input.rest;

import com.daniel.ms_users.application.dto.OwnerRequest;
import com.daniel.ms_users.application.handler.IOwnerHandler;
import com.daniel.ms_users.application.handler.IUserHandler;
import com.daniel.ms_users.domain.model.User;
import com.daniel.ms_users.infrastructure.input.rest.cosntants.ApiEndpoints;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpoints.ADMIN_API)
@RequiredArgsConstructor
public class AdminController {

    private final IOwnerHandler ownerHandler;
    private final IUserHandler userHandler;

    @PostMapping("/owner")
    @Operation(
            summary = "Creates a new Owner user",
            description = "An administrator can create a new Owner to manage restaurants."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner successfully created",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Unauthorized to access this function",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> addNewOwner(@Valid @RequestBody OwnerRequest ownerRequest){
        User newUser = ownerHandler.saveOwner(ownerRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get user by ID",
            description = "Retrieve a user's information using their ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id){
        User user = userHandler.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(
            summary = "Get user's role by email",
            description = "Retrieve a user's role using their email address"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @GetMapping("/users/role-by-email/{email}")
    public ResponseEntity<String> getUserByEmail(@PathVariable String email){
        User user = userHandler.getUserByEmail(email);
        return new ResponseEntity<>(user.getRole().getName(), HttpStatus.OK);
    }

}
