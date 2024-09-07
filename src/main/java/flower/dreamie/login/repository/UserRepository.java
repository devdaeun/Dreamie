package flower.dreamie.login.repository;


import flower.dreamie.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(String id);
    // id = 회원번호 , id = 아이디
}
