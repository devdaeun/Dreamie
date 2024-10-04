package flower.dreamie.login.controller;


import flower.dreamie.login.entity.User;
import flower.dreamie.login.repository.UserRepository;
import flower.dreamie.login.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "login/login";
    }

    @GetMapping("/terms")
    public String terms() {return "login/terms";}

    @GetMapping("/memberForm")
    public String memberForm() {return "login/memberForm";}

    @PostMapping("/addmember")
    public String addMember(@ModelAttribute User login) {
        login.setRole(User.UserRole.일반);
        login.setActivation(User.ActivationStatus.active);

        userService.save(login);

        return "redirect:/memberForm?success=true";
    }

    @PostMapping("/login")
    public String login(@RequestParam("id") String id, @RequestParam("pwd") String pwd, HttpSession session) {
        User user = userService.findById(id);
        if (user != null && user.getPassword().equals(pwd)) {
            session.setAttribute("user", user);
            session.setAttribute("id", id); // id를 세션에 저장
            return "redirect:/"; // 로그인 후 이동할 페이지
        } else {
            return "redirect:/loginForm?error=true"; // 로그인 실패 시 error 파라미터 추가
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    // 아이디 중복 확인 API
    @PostMapping("/check-id")
    public ResponseEntity<?> checkId(@RequestBody Map<String, String> body) {
        String id = body.get("id");  // 요청에서 전달된 아이디 값 추출
        boolean exists = userService.existsById(id);  // 서비스 계층을 통해 아이디 중복 여부 확인

        // 결과를 JSON 형식으로 반환
        return ResponseEntity.ok(Collections.singletonMap("exists", exists));
    }

    // 이메일 중복 확인 API
    @PostMapping("/check-email")
    public ResponseEntity<?> checkEmail(@RequestBody Map<String, String> body) {
        String email = body.get("email");  // 요청에서 전달된 이메일 값 추출
        boolean exists = userService.existsByEmail(email);  // 서비스 계층을 통해 이메일 중복 여부 확인

        // 결과를 JSON 형식으로 반환
        return ResponseEntity.ok(Collections.singletonMap("exists", exists));
    }

    // 회원탈퇴 처리
    @PostMapping("/deactivate")
    public ResponseEntity<String> deactivateUser(@RequestBody Map<String, Long> request) {
        Long user_id = request.get("user_id");

        boolean isDeactivated = userService.deactivateUser(user_id);

        if (isDeactivated) {
            return ResponseEntity.ok("탈퇴 처리 완료");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
        }
    }


}
