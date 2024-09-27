package com.daniel.ms_users.infrastructure.input.rest;

import com.daniel.ms_users.application.handler.IUserHandler;
import com.daniel.ms_users.domain.model.User;
import com.daniel.ms_users.infrastructure.input.rest.cosntants.ApiEndpoints;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiEndpoints.EMPLOYEE_API)
public class EmployeeController {

    private final IUserHandler userHandler;

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

}
