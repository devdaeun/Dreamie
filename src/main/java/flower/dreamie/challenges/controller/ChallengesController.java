package flower.dreamie.challenges.controller;

import flower.dreamie.challenges.entity.Challenges;
import flower.dreamie.challenges.service.ChallengesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/challenges/info")
    public String challengesInfo(Model model, @RequestParam("challenges_id") long challengesId){
        Challenges challenges = challengesService.getChallengesRepository(challengesId);
        model.addAttribute("challenges", challenges);
        return "challenges/challengesInfo";
    }

    @PostMapping("/challenges/modify")
    public String challengesModify(Model model,@ModelAttribute Challenges challenges){
        challengesService.SaveChallenges(challenges);
        return "redirect:/challenges/info?challenges_id=" + challenges.getChallenges_id();
    }

    @PostMapping("/challenges/delete")
    public String challengesDelete(@RequestParam("challenges_id") long challengesId){
        challengesService.RemoveChallenges(challengesId);

        return "redirect:/challenges";
    }
}
