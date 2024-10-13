package flower.dreamie.motivation.service;

import flower.dreamie.motivation.entity.Motivation;
import flower.dreamie.motivation.repository.MotivationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotivationService {

    @Autowired
    private MotivationRepository motivationRepository;

    public String[] getRandomMotivation() {
        Motivation motivation = motivationRepository.findRandomMotivation();
        if (motivation != null) {
            return new String[]{motivation.getContent(), motivation.getContent_en()}; // 한국어와 영어 명언을 배열로 반환
        }
        return new String[]{"No motivation found", "No motivation found"}; // 예외 처리
    }
}

