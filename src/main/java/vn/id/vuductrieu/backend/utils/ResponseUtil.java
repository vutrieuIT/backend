package vn.id.vuductrieu.backend.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseUtil {
    public static ResponseEntity<Object> responseWithMessage(HttpStatus httpStatus,String message) {
        Map<String, String> response = Map.of("message", message);
        return ResponseEntity.status(httpStatus).body(response);
    }
}
