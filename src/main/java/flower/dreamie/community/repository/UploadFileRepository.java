package flower.dreamie.community.repository;

import flower.dreamie.community.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {

    @Query("SELECT u FROM UploadFile u WHERE u.community.uploadFile = :communityId")
    UploadFile findByCommunityId(@Param("communityId") Long communityId);

}
