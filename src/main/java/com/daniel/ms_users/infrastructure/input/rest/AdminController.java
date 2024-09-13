package com.daniel.ms_users.infrastructure.input.rest;

import com.daniel.ms_users.application.dto.OwnerRequest;
import com.daniel.ms_users.application.exception.UserUnderageException;
import com.daniel.ms_users.application.handler.IOwnerHandler;
import io.swagger.v3.oas.annotations.Operation;
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
            summary = "Creates a new user with role Owner",
            description = "An administrator can create a Owner for management of restaurants"   )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created Owner"),
            @ApiResponse(responseCode = "403", description = "You are not authorized to use this function"),
            @ApiResponse(responseCode = "400", description = "Bad request, verify the data you are sending")
    })
    public ResponseEntity<String> addNewOwner(@Valid @RequestBody OwnerRequest ownerRequest){
        try{
            ownerHandler.saveOwner(ownerRequest);
        }
        catch (UserUnderageException e){
            return new ResponseEntity<>("User must be over 18 years", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Succesfully created Owner", HttpStatus.CREATED);
    }

}
