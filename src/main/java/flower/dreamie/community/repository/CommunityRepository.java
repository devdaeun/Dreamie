package flower.dreamie.community.repository;

import flower.dreamie.community.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {
}
