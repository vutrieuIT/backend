package vn.id.vuductrieu.backend.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.id.vuductrieu.backend.dto.LoginDto;
import vn.id.vuductrieu.backend.service.UserService;
import vn.id.vuductrieu.backend.utils.ResponseUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {this.userService = userService;}

    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
        Map<String, String> response = new HashMap<>();
        try {
            String token = userService.login(loginDto);
            response.put("Authorization", token);
            return ResponseEntity.ok(response);
        } catch (EmptyResultDataAccessException e)
        {
            return ResponseUtil.responseWithMessage(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseUtil.responseWithMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.responseWithMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }

    @PostMapping("register")
    public ResponseEntity<Object> register(@RequestBody LoginDto loginDto) {
        Map<String, String> response = new HashMap<>();
        try {
            String token = userService.register(loginDto);
            response.put("Authorization", token);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseUtil.responseWithMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.responseWithMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }
}
