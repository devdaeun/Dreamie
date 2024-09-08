package flower.dreamie.challenges.service;

import flower.dreamie.challenges.entity.Challenges;
import flower.dreamie.challenges.repository.ChallengesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChallengesService {
    @Autowired
    private ChallengesRepository challengesRepository;

    public void SaveChallenges(Challenges challenge_id){
        challengesRepository.save(challenge_id);
    }

    public Challenges getChallengesRepository(long challengesId) {
        return challengesRepository.findByChallengesId(challengesId).orElse(null);
    }
}
