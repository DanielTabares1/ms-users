package com.daniel.ms_users.infrastructure.input.rest;

import com.daniel.ms_users.application.dto.EmployeeRequest;
import com.daniel.ms_users.application.handler.IEmployeeHandler;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/owner")
public class OwnerController {

    private final IEmployeeHandler employeeHandler;

    @PostMapping("/employee")
    @Operation(
            summary = "Creates a new Employee user",
            description = "An owner can create a new Employee to attend restaurants"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee successfully created",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Unauthorized to access this function",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content)
    })
    public ResponseEntity<User> addNewEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        User newUser = employeeHandler.saveEmployee(employeeRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
