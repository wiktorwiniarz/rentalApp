package com.winiarz.rental.model.dto;

import com.winiarz.rental.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Integer id;

    private String email;
    private UserRole role;
}
