package com.example.InstalllmentSystem.entrypoint.swagger;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.exception.payment.PaymentAmountZeroException;
import com.example.InstalllmentSystem.core.exception.payment.PaymentNotFoundException;
import com.example.InstalllmentSystem.entrypoint.dto.PaymentDTO;
import com.example.InstalllmentSystem.entrypoint.handler.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Tag(name = "PaymentController", description = "controller where all payment endpoints are located")
public interface PaymentControllerAPI {

    @Operation(summary = "Get payment by id", description = "Use id of payment to list and find")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get by id", content = {@Content(schema = @Schema(implementation = Payment.class))}),
            @ApiResponse(responseCode = "400", description = "Fail to get payment, id not found", content = {@Content(schema =  @Schema(implementation = ApiError.class))})
    })
    Payment getById(String id) throws PaymentNotFoundException;

    @Operation(summary = "Get all payment", description = "List all payment with pageable limitation")
    Page<Payment> findAll(Pageable pageable);

    @Operation(summary = "Create payment", description = "Creating a payment using the data entered by the user (attributes of the DTO).")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created", content = {@Content(schema = @Schema(implementation = Payment.class))}),
            @ApiResponse(responseCode = "400", description = "Fail to create payment", content = {@Content(schema =  @Schema(implementation = ApiError.class))})
    })
    Payment create(PaymentDTO paymentDTO) throws PaymentAmountZeroException;

    @Operation(summary = "Delete payment", description = "Delete payment by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content", content = {@Content(schema = @Schema(implementation = Payment.class))}),
            @ApiResponse(responseCode = "400", description = "Fail to delete payment by id", content = {@Content(schema =  @Schema(implementation = ApiError.class))})
    })
    void deleteById(String id) throws PaymentNotFoundException;

    @Operation(summary = "Update payment", description = "Update payment by id, change amount and payment method")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated", content = {@Content(schema = @Schema(implementation = Payment.class))}),
            @ApiResponse(responseCode = "400", description = "Fail to update payment, id not found", content = {@Content(schema =  @Schema(implementation = ApiError.class))})
    })
    Payment update(String id, PaymentDTO paymentDTO) throws PaymentNotFoundException, PaymentAmountZeroException;
}
