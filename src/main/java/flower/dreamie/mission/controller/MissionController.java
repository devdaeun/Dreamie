package flower.dreamie.mission.controller;


import flower.dreamie.challenges.entity.Challenges;
import flower.dreamie.challenges.service.ChallengesService;
import flower.dreamie.login.entity.User;
import flower.dreamie.login.service.UserService;
import flower.dreamie.mission.entity.Mission;
import flower.dreamie.mission.service.MissionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class MissionController {

    @Autowired
    MissionService missionService;

    @Autowired
    UserService userService;

    @Autowired
    ChallengesService challengesService;

    @PostMapping("/mission/add")
    public String missionAdd(@RequestParam("challenges_id") long challengesId, HttpSession session){
        User user = (User) session.getAttribute("user");
        long user_id = user.getUser_id();
        missionService.saveMission(challengesId, user_id);
        return "redirect:/challenges";
    }

    @PostMapping("/mission/complete")
    public String missionComplete(@RequestParam("mission_id") long missionId, HttpSession session){
        User user = (User) session.getAttribute("user");
        long user_id = user.getUser_id();
        missionService.missionUpdate(missionId);
        missionService.usermissionUpdate(user_id);
        return "redirect:/challenges";
    }

    @GetMapping("/mypage")
    public String myPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            long userId = user.getUser_id();
            // 미션 정보를 가져옴
            List<Mission> missions = missionService.findByUserId(userId);
            // 모델에 추가
            model.addAttribute("missions", missions);
        }

        return "mypage/mypage";
    }

    @GetMapping("/mypage/grape")
    public String grape(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            long userId = user.getUser_id();
            // 미션 정보를 가져옴
            List<Mission> missions = missionService.findByUserId(userId);
            // 모델에 추가
            model.addAttribute("missions", missions);
        }

        return "mypage/myGrape";
    }



}