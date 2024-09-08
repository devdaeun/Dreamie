package flower.dreamie.challenges.controller;

import flower.dreamie.challenges.entity.Challenges;
import flower.dreamie.challenges.service.ChallengesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class ChallengesController {

    @Autowired
    ChallengesService challengesService;

    @GetMapping("/challenges")
    public String challengesList(Model model){
        List<Challenges> challenges = challengesService.getAllChallenges();
        if (challenges == null ) {
            challenges = Collections.emptyList();
        }
        model.addAttribute("challenges", challenges);
        return "challenges/challenges";
    }

    @GetMapping("/challenges/form")
    public String challengesForm(){
        return "challenges/challengesForm";
    }

    @PostMapping("/challenges/add")
    public String challengesAdd(@ModelAttribute Challenges challenges){
        challengesService.SaveChallenges(challenges);
        return "redirect:/challenges";
    }
}
