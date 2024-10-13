package flower.dreamie.login.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import flower.dreamie.login.entity.User;
import flower.dreamie.login.repository.UserRepository;
import flower.dreamie.login.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;


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

    @GetMapping("/modifyForm")
    public String modifyForm() { return  "mypage/modify"; }

    @GetMapping("/pwdCheck")
    public String pwdCheck() { return  "mypage/pwdCheck"; }

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

    // 이메일 중복 확인 API
    @PostMapping("/email-check")
    public ResponseEntity<?> emailCheck(@RequestBody Map<String, String> body) {
        String newEmail = body.get("email");          // 클라이언트에서 입력한 새 이메일
        String currentEmail = body.get("currentEmail");  // 현재 사용자의 이메일 (수정 시 사용)

        // 현재 이메일과 새 이메일이 다를 때만 중복 체크
        boolean exists = false;
        if (!newEmail.equals(currentEmail)) {
            exists = userService.existsByEmail(newEmail);  // 새 이메일에 대한 중복 여부 확인
        }

        // JSON 형식으로 중복 여부 결과 반환
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

    //회원 정보 수정
    @RequestMapping("/modify")
    public String modify(@RequestParam("id") String id,
                         @RequestParam("password") String password,
                         @RequestParam("email") String email,
                         @RequestParam("name") String name,
                         @RequestParam("work") String work, HttpSession session) {
        //아이디로 기존 회원 조회
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 비밀번호, 이메일, 이름, 직업만 수정 가능
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setWork(work);

        // 수정된 정보 저장
        userRepository.save(user);

        // 세션에 저장된 사용자 정보 업데이트
        session.setAttribute("user", user);  // 수정된 user 객체를 세션에 저장

//        return "redirect:/mypage"; // 마이페이지로 리다이렉트
        return "redirect:/modifyForm";
    }

    //비밀번호 확인
    @PostMapping("/check-password")
    public ModelAndView checkPwd(@RequestParam("password") String password, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");

        // 비밀번호 확인 (Service를 통해 처리)
        boolean isPasswordCorrect = userService.checkPassword(currentUser.getId(), password);

        if (isPasswordCorrect) {
            return new ModelAndView("redirect:/modifyForm");
        }
        else {
            ModelAndView modelAndView = new ModelAndView("mypage/pwdCheck");
            modelAndView.addObject("errorMessage", "비밀번호가 일치하지 않습니다.");
            return modelAndView;
        }
    }

    @RequestMapping("/naver-login")
    public String naverLogin(@RequestParam("code") String code, @RequestParam("state") String state) {
        if (Objects.equals(state, "1234")){
            // 1. 네이버로부터 받은 코드로 액세스 토큰 요청
            String accessToken = getAccessToken(code);

            // 2. 액세스 토큰으로 사용자 정보 요청
            Map<String, Object> userInfo = getUserInfo(accessToken);
            System.out.println(userInfo);

        }

        return "redirect:/";
    };

    private String getAccessToken(String code){
        String url = "https://nid.naver.com/oauth2.0/token?";
        String tokenUrl = url + "grant_type=authorization_code&client_id=NU4ciXZlz_FhRZKPNN5g&client_secret=LSFsxisf_5&grant_type=authorization_code&state=1234"+
                "&code=" + code;

        HttpHeaders headers = new HttpHeaders(); //api 요청을 위해 헤더를 설정합니다.
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); //url로 보낼거라고 설정

        //Http 요청 만들기
        HttpEntity<String> entity = new HttpEntity<>(tokenUrl, headers);

        // Post 형식으로 요청 전송하기
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());
        // 응답 본문에서 accessToken 추출
        String accessToken = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            accessToken = jsonNode.get("access_token").asText(); // access_token 필드에서 값 추출
            return accessToken;//응답 값 가져오기
        } catch (Exception e) {
            e.printStackTrace();
            return "오류나떠";
        }
    };

    private Map<String, Object> getUserInfo(String accessToken){
        String url = "https://openapi.naver.com/v1/nid/me";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return ResponseToMap(response.getBody());
    };

    private Map<String, Object> ResponseToMap(String responseBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {}); // JSON을 Map으로 변환
        } catch (Exception e) {
            e.printStackTrace();
            return null; // 예외 처리
        }
    }

}
