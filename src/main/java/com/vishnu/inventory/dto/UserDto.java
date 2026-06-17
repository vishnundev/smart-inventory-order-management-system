package com.vishnu.inventory.dto;

import com.vishnu.inventory.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDto {


private Long id;

private String name;

private String email;

private Role role;


}
