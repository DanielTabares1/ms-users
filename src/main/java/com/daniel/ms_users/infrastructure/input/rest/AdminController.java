package com.daniel.ms_users.infrastructure.input.rest;

import com.daniel.ms_users.application.dto.OwnerRequest;
import com.daniel.ms_users.application.handler.IOwnerHandler;
import com.daniel.ms_users.domain.model.User;
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
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final IOwnerHandler ownerHandler;

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
    public ResponseEntity<User> addNewOwner(@Valid @RequestBody OwnerRequest ownerRequest){
        User newUser = ownerHandler.saveOwner(ownerRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

}
