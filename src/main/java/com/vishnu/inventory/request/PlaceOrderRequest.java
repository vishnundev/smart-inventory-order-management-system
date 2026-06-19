package com.vishnu.inventory.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import lombok.Data;

@Data

public class PlaceOrderRequest {

    @Valid

    @NotEmpty

    private List<OrderItemRequest> items;

}