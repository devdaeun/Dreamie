package flower.dreamie.login.controller;


import flower.dreamie.login.entity.User;
import flower.dreamie.login.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "login/login";
    }

    @GetMapping("/memberForm")
    public String memberForm() {return "login/memberForm";}

    @PostMapping("/addmember")
    public String addMember(@ModelAttribute User login) {
        login.setRole(User.UserRole.일반);
        login.setActivation(User.ActivationStatus.active);

        userService.save(login);

        return "redirect:/";
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

}
