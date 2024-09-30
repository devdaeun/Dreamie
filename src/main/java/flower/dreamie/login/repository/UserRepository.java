package flower.dreamie.login.repository;


import flower.dreamie.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(String id);
    // id = 회원번호 , id = 아이디

    boolean existsById(String id);  // 해당 아이디가 존재하는지 여부를 반환

    boolean existsByEmail(String email);  // 해당 이메일이 존재하는지 여부를 반환
}
