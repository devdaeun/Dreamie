package flower.dreamie.challenges.service;

import flower.dreamie.challenges.entity.Challenges;
import flower.dreamie.challenges.repository.ChallengesRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengesService {
    @Autowired
    private ChallengesRepository challengesRepository;

    public void SaveChallenges(Challenges challenges){
        challengesRepository.save(challenges);
    }

    public Challenges getChallengesRepository(long challengesId) {
        return challengesRepository.findByChallengesId(challengesId).orElse(null);
    }

    public List<Challenges> getAllChallenges(){
        return challengesRepository.findAll();//전체 데이터 출력 메소드
    }
}
