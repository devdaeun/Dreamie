package flower.dreamie.login.service;


import flower.dreamie.login.entity.User;
import flower.dreamie.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
