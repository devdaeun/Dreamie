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


    public boolean deactivateUser(Long user_id) {
        if (userRepository.existsById(user_id)) {
            userRepository.deleteById(user_id);
            return true;
        } else {
            return false;
        }
    }

}
