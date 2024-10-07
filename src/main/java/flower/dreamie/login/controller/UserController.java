package flower.dreamie.login.controller;


import flower.dreamie.login.entity.User;
import flower.dreamie.login.repository.UserRepository;
import flower.dreamie.login.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String terms() {
        return "login/terms";
    }

    @GetMapping("/memberForm")
    public String memberForm() {
        return "login/memberForm";
    }

    @PostMapping("/addmember")
    public String addMember(@ModelAttribute User login) {
        login.setRole(User.UserRole.일반);
        login.setActivation(User.ActivationStatus.active);

        userService.save(login);

        return "redirect:/memberForm?success=true";
    }

    @PostMapping("/login")
    public String login(@RequestParam("id") String id, @RequestParam("pwd") String pwd, HttpSession session) {
        User user = userService.findById(id); // 로그인용 아이디로 사용자 조회
        if (user != null) {
            // 탈퇴한 유저인지 확인
            if (user.getActivation() == User.ActivationStatus.deleted) {
                // 탈퇴한 회원일 경우 로그인 실패 처리
                return "redirect:/loginForm?error=deactivated"; // 탈퇴한 회원이라는 에러 메시지 전달
            }

            // 비밀번호 검증
            if (user.getPassword().equals(pwd)) {
                session.setAttribute("user", user);
                session.setAttribute("id", id); // id를 세션에 저장
                return "redirect:/"; // 로그인 후 이동할 페이지
            }
        }

        // 로그인 실패 처리
        return "redirect:/loginForm?error=true"; // 로그인 실패 시 error 파라미터 추가
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
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
    public ResponseEntity<String> deactivateUser(@RequestBody Map<String, Long> requestData) {
        Long user_id = requestData.get("user_id"); // 요청 데이터에서 user_id 추출
        System.out.println("받은 user_id: " + user_id); // user_id 값을 로그로 출력
        try {
            userService.deactivateUser(user_id);
            return ResponseEntity.ok("사용자가 비활성화되었습니다."); // 성공 메시지 한글로
        } catch (Exception e) {
            e.printStackTrace(); // 에러를 콘솔에 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("사용자 비활성화 중 오류가 발생했습니다."); // 에러 메시지 한글로
        }
    }


}
