package flower.dreamie.mission.controller;


import flower.dreamie.login.entity.User;
import flower.dreamie.mission.service.MissionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MissionController {

    @Autowired
    MissionService missionService;

    @PostMapping("/mission/add")
    public String missionAdd(@RequestParam("challenges_id") long challengesId, HttpSession session){
        User user = (User) session.getAttribute("user");
        long user_id = user.getUser_id();
        missionService.saveMission(challengesId, user_id);
        return "redirect:/challenges";
    }

}
