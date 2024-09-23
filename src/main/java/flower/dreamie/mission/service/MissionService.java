package flower.dreamie.mission.service;

import flower.dreamie.challenges.entity.Challenges;
import flower.dreamie.mission.entity.Mission;
import flower.dreamie.mission.repository.MissionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    public void saveMission(long challengesId, long user_id){
        Mission mission = new Mission();
        mission.setUser_id(user_id);
        mission.setChallanges_id(challengesId);
        missionRepository.save(mission);
    }
    @Transactional
    public void missionUpdate(long missionId){
        missionRepository.missionUpdate(missionId);
    }
    @Transactional
    public void usermissionUpdate (long user_id){
        missionRepository.usermissionUpdate(user_id);
    }

    public List<Mission> findByUserId(long userId){
        return missionRepository.findByUserChallenge(userId);
    }

}