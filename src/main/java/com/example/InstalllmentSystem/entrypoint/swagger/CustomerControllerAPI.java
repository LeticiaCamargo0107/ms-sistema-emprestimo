package com.example.InstalllmentSystem.entrypoint.swagger;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.exception.customer.CustomerAddressNotFoundException;
import com.example.InstalllmentSystem.core.exception.customer.CustomerBirthDateException;
import com.example.InstalllmentSystem.core.exception.customer.CustomerDocumentNotFoundException;
import com.example.InstalllmentSystem.core.exception.customer.CustomertNotFoundException;
import com.example.InstalllmentSystem.entrypoint.dto.CustomerDTO;
import com.example.InstalllmentSystem.entrypoint.handler.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Tag(name = "CustomerController", description = "controller where all customer endpoints are located")
public interface CustomerControllerAPI {

    @Operation(summary = "Get customer by id", description = "Use id of customer to list and find")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get by id", content = {@Content(schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "400", description = "Fail to get customer, id not found", content = {@Content(schema =  @Schema(implementation = ApiError.class))})
    })
    Customer getById(String id) throws CustomertNotFoundException;

    @Operation(summary = "Get all customers", description = "List all customers with pageable limitation")
    Page<Customer> findAll(Pageable pageable);

    @Operation(summary = "Create customer", description = "Creating a customer using the data entered by the user (attributes of the DTO).")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created", content = {@Content(schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "400", description = "Fail to create customer", content = {@Content(schema =  @Schema(implementation = ApiError.class))})
    })
    Customer create(CustomerDTO customerDTO) throws CustomerBirthDateException, CustomerAddressNotFoundException;

    @Operation(summary = "Delete customer", description = "Delete customer by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content", content = {@Content(schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "400", description = "Fail to delete customer by id", content = {@Content(schema =  @Schema(implementation = ApiError.class))})
    })
    void deleteById(String id) throws CustomertNotFoundException;

    @Operation(summary = "Update customer", description = "Update customer by id, change name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated", content = {@Content(schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "400", description = "Fail to update customer, id not found", content = {@Content(schema =  @Schema(implementation = ApiError.class))})
    })
    Customer update(String id, CustomerDTO customerDTO) throws CustomerDocumentNotFoundException, CustomertNotFoundException;
}
