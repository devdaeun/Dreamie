package flower.dreamie.challenges.repository;

import flower.dreamie.challenges.entity.Challenges;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChallengesRepository extends JpaRepository<Challenges,Long> {
    Optional<Challenges> findByChallengesId(long challengesId);
}
