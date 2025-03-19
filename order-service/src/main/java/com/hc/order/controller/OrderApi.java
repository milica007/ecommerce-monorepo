package com.hc.order.controller;

import com.hc.order.dto.OrderCreateDTO;
import com.hc.order.dto.OrderDTO;
import com.hc.order.dto.OrderUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Orders API", description = "API for managing orders")
public interface OrderApi {
    @Operation(summary = "Creates a new order.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Order created",
                    content = @Content(schema = @Schema(implementation = OrderDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content
            )
    })
    @PostMapping(value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    OrderDTO create(@RequestBody @Valid OrderCreateDTO createDTO);

    @Operation(summary = "Fetches an order by ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order found",
                    content = @Content(schema = @Schema(implementation = OrderDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content
            )
    })
    @GetMapping(value = "/orders/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN') or @orderSecurity.checkOwnership(#id)")
    OrderDTO getById(@PathVariable Long id);

    @Operation(summary = "Fetches a list of all orders, optionally filtered by user ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of orders")
    })
    @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<OrderDTO> getAll(@RequestParam(required = false) Long userId);

    @Operation(summary = "Updates an existing order by ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order updated",
                    content = @Content(schema = @Schema(implementation = OrderDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content
            )
    })
    @PatchMapping(value = "/orders/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN') or @orderSecurity.checkOwnership(#id)")
    OrderDTO update(@PathVariable Long id, @RequestBody OrderUpdateDTO updateDTO);

    @Operation(summary = "Deletes an order by ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Order deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content
            )
    })
    @DeleteMapping(value = "/orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN') or @orderSecurity.checkOwnership(#id)")
    void delete(@PathVariable Long id);
}
