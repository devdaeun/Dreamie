package flower.dreamie.community.service;

import flower.dreamie.community.entity.Community;
import flower.dreamie.community.repository.CommunityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CommunityService {

    private final CommunityRepository communityRepository;
    public CommunityService(CommunityRepository communityRepository ){ this.communityRepository = communityRepository;}

    // 커뮤니티조회
    public List<Community> getAllCommunity() {return communityRepository.findAll();}

    // 커뮤니티 저장
    public void saveCommunity(Community community) {communityRepository.save(community);}

    // 커뮤니티 상세 조회
    public Community getCommunityById(Long community_id) {
        Optional<Community> community = communityRepository.findById(community_id);
        return community.orElseThrow(()-> new IllegalArgumentException("해당 문의사항이 존재하지 않습니다."));
    }

    // 커뮤니티 수정하기


    // 커뮤니티 삭제하기

}
