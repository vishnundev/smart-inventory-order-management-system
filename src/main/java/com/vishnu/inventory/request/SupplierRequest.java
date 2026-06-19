package com.vishnu.inventory.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data

public class SupplierRequest {

    @NotBlank

    private String name;

    @Email

    private String email;

    private String phone;

    private String address;

}