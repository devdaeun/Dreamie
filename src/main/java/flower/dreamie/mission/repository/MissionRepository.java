package flower.dreamie.mission.repository;

import flower.dreamie.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("SELECT m FROM Mission m LEFT JOIN Challenges c ON m.challenges_id = c.challengesId WHERE m.userId = :userId and m.succese = '진행중'")
    List<Mission> findByUserChallenge(@Param("userId") long userId);

    @Modifying
    @Query("UPDATE Mission m SET m.succese = '완료', m.succese_at = CURRENT_DATE WHERE m.mission_id = :missionId")
    void missionUpdate(long missionId);

    @Modifying
    @Query("UPDATE User u SET u.mission = u.mission + 1 WHERE u.user_id = :user_id")
    void usermissionUpdate(long user_id);
}



