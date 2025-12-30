package com.example.InstallmentSystem.entrypoint.swagger;

import com.example.InstallmentSystem.core.domain.Contract;
import com.example.InstallmentSystem.core.exception.contract.ContractNotFoundException;
import com.example.InstallmentSystem.core.exception.contract.ContractPeriodZeroException;
import com.example.InstallmentSystem.core.exception.contract.ContractRequestAmountZeroException;
import com.example.InstallmentSystem.entrypoint.dto.ContractDTO;
import com.example.InstallmentSystem.entrypoint.handler.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Tag(name = "ContractController", description = "controller where all contract endpoints are located")
public interface ContractControllerAPI {

    @Operation(summary = "Get contract by id", description = "Use id of contract to list and find")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get", content = {@Content(schema = @Schema(implementation = Contract.class))}),
            @ApiResponse(responseCode = "400", description = "Fail to get contract, id not found", content = {@Content(schema =  @Schema(implementation = ApiError.class))})
    })
    Contract getById(String id) throws ContractNotFoundException;

    @Operation(summary = "Get all contract", description = "List all contracts with pageable limitation")
    Page<Contract> findAll(Pageable pageable);

    @Operation(summary = "Create contract", description = "Creating a contract using the data entered by the user (attributes of the DTO).")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created", content = {@Content(schema = @Schema(implementation = Contract.class))}),
            @ApiResponse(responseCode = "400", description = "Fail to create contract", content = {@Content(schema =  @Schema(implementation = ApiError.class))})
    })
    Contract create(ContractDTO contractDTO) throws ContractPeriodZeroException, ContractRequestAmountZeroException;

    @Operation(summary = "Delete contract", description = "Delete contract by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content", content = {@Content(schema = @Schema(implementation = Contract.class))}),
            @ApiResponse(responseCode = "400", description = "Fail to delete contract by id", content = {@Content(schema =  @Schema(implementation = ApiError.class))})
    })
    void deleteById(String id) throws ContractNotFoundException;

    @Operation(summary = "Update contract", description = "Update contract by id, change operation period and requested amount")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Update", content = {@Content(schema = @Schema(implementation = Contract.class))}),
            @ApiResponse(responseCode = "400", description = "Fail to update contract, id not found", content = {@Content(schema =  @Schema(implementation = ApiError.class))})
    })
    Contract update(String id, ContractDTO contractDTO) throws ContractRequestAmountZeroException, ContractNotFoundException;
}
