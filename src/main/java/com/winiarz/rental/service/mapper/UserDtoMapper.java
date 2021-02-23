package com.winiarz.rental.service.mapper;

import com.winiarz.rental.model.User;
import com.winiarz.rental.model.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserDtoMapper {

    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getRole());
    }
}
