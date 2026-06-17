package com.vishnu.inventory.service;

import com.vishnu.inventory.request.LoginRequest;

import com.vishnu.inventory.request.RegisterRequest;

import com.vishnu.inventory.response.AuthResponse;

public interface AuthService {


AuthResponse register(

        RegisterRequest request

);

AuthResponse login(

        LoginRequest request

);


}
