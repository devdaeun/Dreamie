package flower.dreamie.login.service;


import flower.dreamie.login.entity.User;
import flower.dreamie.login.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(User login) {
        userRepository.save(login);
    }

    public User findById(String id) {
        return userRepository.findById(id).orElse(null); // id를 String으로 사용
    }

    //아이디 중복 확인
    public boolean existsById(String id) {
        return userRepository.existsById(id);  //db에 해당 아이디가 존재하는지 확인
    }

    //이메일 중복 확인
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);  //db에 해당 이메일이 존재하는지 확인
    }

    // 회원탈퇴
    public void deactivateUser(Long user_id) {
        // user_id로 유저 정보 조회
        Optional<User> userOptional = userRepository.findById(user_id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setActivation(User.ActivationStatus.deleted); // activation 상태를 deleted로 설정
            userRepository.save(user); // 데이터베이스에 업데이트된 상태 저장
        } else {
            throw new RuntimeException("아이디가 " + user_id + "인 사용자를 찾을 수 없습니다."); // 에러 메시지를 한글로 변경
        }
    }

    //회원탈퇴
    public User login(String id, String password) {
        Optional<User> userOptional = userRepository.findById(id); // 로그인용 아이디로 사용자 조회
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 탈퇴한 유저는 로그인 불가 (activation 상태 확인)
            if (user.getActivation() == User.ActivationStatus.deleted) {
                throw new RuntimeException("탈퇴한 회원입니다.");
            }
            // 비밀번호 검증
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                throw new RuntimeException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new RuntimeException("존재하지 않는 아이디입니다.");
        }
    }


}
