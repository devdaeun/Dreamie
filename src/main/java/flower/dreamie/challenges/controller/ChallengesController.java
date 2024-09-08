package flower.dreamie.challenges.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChallengesController {

    @GetMapping("/challenges")
    public String challengesList(){
        return "challenges/challenges";
    }

    @GetMapping("/challenges/form")
    public String challengesForm(){
        return "challenges/challengesForm";
    }
}
