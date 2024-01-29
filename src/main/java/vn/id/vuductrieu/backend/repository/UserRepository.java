package vn.id.vuductrieu.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;
import vn.id.vuductrieu.backend.entity.User;

import java.util.Optional;

@RestController
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
