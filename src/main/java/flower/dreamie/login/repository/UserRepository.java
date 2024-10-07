package flower.dreamie.login.repository;


import flower.dreamie.login.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(String id);
    // id = 회원번호 , id = 아이디

    boolean existsById(String id);  // 해당 아이디가 존재하는지 여부를 반환

    boolean existsByEmail(String email);  // 해당 이메일이 존재하는지 여부를 반환

    // 회원 탈퇴 (user_id를 기준으로 비활성화)
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.activation = :status WHERE u.user_id = :user_id")
    void updateUserActivation(@Param("user_id") Long user_id, @Param("status") User.ActivationStatus status);

}
