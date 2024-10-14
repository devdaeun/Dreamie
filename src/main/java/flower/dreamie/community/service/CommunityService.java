package flower.dreamie.community.service;

import flower.dreamie.community.entity.Community;
import flower.dreamie.community.entity.UploadFile;
import flower.dreamie.community.exception.FileUploadException;
import flower.dreamie.community.repository.CommunityRepository;
import flower.dreamie.community.repository.UploadFileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final UploadFileRepository uploadFileRepository; // 추가

    public CommunityService(CommunityRepository communityRepository, UploadFileRepository uploadFileRepository) {
        this.communityRepository = communityRepository;
        this.uploadFileRepository = uploadFileRepository; // 주입
    }

    // 커뮤니티 조회
    public List<Community> getAllCommunity() {
        return communityRepository.findAll();
    }

    // 커뮤니티 저장
    public void saveCommunity(Community community) {
        communityRepository.save(community);
    }

    // 커뮤니티 상세 조회
    public Community getCommunityById(Long communityId) {
        return communityRepository.findById(communityId)
                .orElseThrow(() -> new EntityNotFoundException("Community not found with id " + communityId));
    }

    // 파일 저장 메서드
    public UploadFile saveUploadedFile(MultipartFile file, Long community_id) throws FileUploadException {
        try {
            // 커뮤니티 객체 조회
            Community community = communityRepository.findById(community_id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 커뮤니티가 존재하지 않습니다."));

            // 파일 이름과 크기 설정
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            long fileSize = file.getSize();

            // 파일을 지정된 경로에 저장
            Path uploadPath = Paths.get("D:\\Yoni\\fileupload3\\" + fileName);
            Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);

            // UploadFile 객체 생성
            UploadFile uploadFile = new UploadFile();
            uploadFile.setFileName(fileName); // 파일 이름 설정
            uploadFile.setFilePath(uploadPath.toString()); // 파일 경로 설정 추가
            uploadFile.setSize(fileSize); // 파일 크기 설정
            uploadFile.setCommunity(community); // community 객체 설정

            // UploadFile 저장
            return uploadFileRepository.save(uploadFile); // DB에 저장

        } catch (IOException e) {
            throw new FileUploadException("파일 업로드 중 오류가 발생했습니다.", e);
        }
    }

    //파일 다운로드
    public UploadFile downloadFile(long community_id) {
        return uploadFileRepository.findByCommunityId(community_id);  // 데이터베이스에서 커뮤니티 ID를 사용하여 UploadFile 객체를 가져오는 로직
    }

    //커뮤니티 수정하기
    public Community updateCommunity(Long community_id, String content) {
        Community community = getCommunityById(community_id);
        if (community != null) {
            community.setContent(content);
        }
        return community;
    }

    // 커뮤니티 삭제
    public void deleteCommunity(Long community_id) throws Exception{
        try{
            communityRepository.deleteById(community_id);
        }catch (Exception e) {
            throw new Exception("삭제 중 오류 발생", e);
        }
    }

    // 커뮤니티 파일 수정
    public void updateUploadFile(Long uploadFileId, MultipartFile file) throws Exception {
        UploadFile uploadFile = uploadFileRepository.findById(uploadFileId)
                .orElseThrow(() -> new Exception("파일을 찾을 수 없습니다."));

        // 새로운 파일 정보 설정 (파일 이름 및 경로)
        String newFileName = file.getOriginalFilename();
        Path newFilePath = Paths.get("D:\\Yoni\\fileupload3\\" + newFileName); // 파일 저장 경로 설정

        try {
            // 파일이 이미 존재하는지 확인하고 삭제 (옵션에 따라)
            if (Files.exists(newFilePath)) {
                Files.delete(newFilePath); // 기존 파일 삭제 (필요시)
            }

            // 파일 저장 로직 (파일 시스템에 저장하는 로직 구현)
            file.transferTo(newFilePath.toFile()); // 파일 저장

            uploadFile.setFileName(newFileName);
            uploadFile.setFilePath(newFilePath.toString()); // 저장된 파일 경로를 문자열로 설정
            uploadFileRepository.save(uploadFile); // 변경사항 저장
        } catch (IOException e) {
            throw new Exception("파일 저장 중 오류 발생", e);
        }
    }

}
