package flower.dreamie.mission.service;

import flower.dreamie.challenges.entity.Challenges;
import flower.dreamie.mission.entity.Mission;
import flower.dreamie.mission.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    public void SaveMission(Mission mission){
        missionRepository.save(mission);
    }

    public Mission findByUserId(long userId){
        return missionRepository.findByUserId(userId).orElse(null);
    }

}
