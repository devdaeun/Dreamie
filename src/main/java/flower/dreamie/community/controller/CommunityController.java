package flower.dreamie.community.controller;

import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import flower.dreamie.answer.entity.Answer;
import flower.dreamie.answer.service.AnswerService;
import flower.dreamie.community.entity.Community;
import flower.dreamie.community.service.CommunityService;
import flower.dreamie.login.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommunityController {

    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) { this.communityService = communityService;
    }


    // 커뮤니티조회
    @RequestMapping("/community")
    public String List(Model model) {
        System.out.println("커뮤니티 조회 메서드 호출됨"); // 로그 추가
        model.addAttribute("community", communityService.getAllCommunity());
        return "community/community";
    }

    // 커뮤니티 작성하기
    @RequestMapping("/communityForm")
    public String communityForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
        if (user == null) {
            return "redirect:/loginForm";
        }

        model.addAttribute("community", new Community());
        return "community/communityForm";
    }

    // 커뮤니티 저장
    @RequestMapping("communitySave")
    public String saveCommunity(@ModelAttribute("community") Community community, HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println("User from session: " + user);

        community.setUser_id(user.getUser_id());

        communityService.saveCommunity(community);
        return "redirect:/community";
    }

    // 커뮤니티 상세 조회
    @RequestMapping("/community/{community_id}")
    public String detail(@PathVariable("community_id") Long community_id, Model model, HttpSession session) {
        System.out.println("요청된 community_id: " + community_id); // 추가된 로그
        Community community = communityService.getCommunityById(community_id);
        User user = (User) session.getAttribute("user");

        model.addAttribute("community", community);
        model.addAttribute("user", user);

        return "community/communityDetail";
    }

    // 커뮤니티 수정하기


    // 커뮤니티 삭제하기



}
