package vn.id.vuductrieu.backend.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import vn.id.vuductrieu.backend.dto.LoginDto;
import vn.id.vuductrieu.backend.entity.User;
import vn.id.vuductrieu.backend.repository.UserRepository;
import vn.id.vuductrieu.backend.utils.JwtUtil;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public String login(LoginDto loginDto) {
        Optional<User> user = userRepository.findByUsername(loginDto.getUsername());
        if (user.isPresent()) {
            if (user.get().getPassword().equals(loginDto.getPassword())) {
                return jwtUtil.generateToken(user.get());
            } else {
                throw new IllegalArgumentException("Wrong password");
            }
        } else {
            throw new EmptyResultDataAccessException("User not found", 1);
        }
    }

    public String register(LoginDto loginDto) {
        Optional<User> user = userRepository.findByUsername(loginDto.getUsername());
        if (user.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        } else {
            User newUser = new User();
            newUser.setUsername(loginDto.getUsername());
            newUser.setPassword(loginDto.getPassword());
            userRepository.save(newUser);
            return jwtUtil.generateToken(newUser);
        }
    }
}
