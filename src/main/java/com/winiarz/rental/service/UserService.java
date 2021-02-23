package com.winiarz.rental.service;

import com.winiarz.rental.model.User;
import com.winiarz.rental.model.UserRole;
import com.winiarz.rental.model.dto.UserDto;
import com.winiarz.rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Integer userId) {
        return userRepository.findById(userId).isPresent() ? userRepository.findById(userId).get() : null;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).isPresent() ? userRepository.findByEmail(email).get() : null;
    }

    public User findBySession(HttpServletRequest req){
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        return findById(userDto.getId());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User deleteUser(HttpServletRequest req, Integer userId) {
        User user = findById(userId);

        if (user != null) {
            UserDto dto = (UserDto) req.getSession().getAttribute("user");
            if (user.getEmail().equals(dto.getEmail())) {
                req.getSession().removeAttribute("user");
            }
            delete(user);
        }
        return user;
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public boolean existByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void save(User user){
        userRepository.save(user);
    }

    public User newUser(String firstname, String lastName, String phoneNumber, String email, String password1) {
        User user = new User(null, firstname, lastName, phoneNumber, email, password1, UserRole.CUSTOMER, false, Collections.emptyList());
        save(user);
        return user;
    }
}
