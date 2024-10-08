package flower.dreamie.community.service;

import flower.dreamie.community.entity.Community;
import flower.dreamie.community.entity.UploadFile;
import flower.dreamie.community.exception.FileDownloadException;
import flower.dreamie.community.repository.CommunityRepository;
import flower.dreamie.community.repository.UploadFileRepository;
import lombok.Value;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final UploadFileRepository uploadFileRepository;

    public CommunityService(CommunityRepository communityRepository, UploadFileRepository uploadFileRepository) {
        this.communityRepository = communityRepository;
        this.uploadFileRepository = uploadFileRepository;
    }

    // 커뮤니티조회
    public List<Community> getAllCommunity() {return communityRepository.findAll();}

    // 커뮤니티 저장
    public void saveCommunity(Community community) {communityRepository.save(community);}

    // 커뮤니티 상세 조회
    public Community getCommunityById(Long community_id) {
        Optional<Community> community = communityRepository.findById(community_id);
        return community.orElseThrow(()-> new IllegalArgumentException("해당 문의사항이 존재하지 않습니다."));
    }

    //커뮤니티 파일 저장
    private String uploadDir;  // 파일 저장 경로 설정

    public UploadFile uploadFile(MultipartFile file) throws FileUploadException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileUploadException("파일명에 부적합 문자가 포함되어 있습니다: " + fileName);
            }

            Path targetLocation = Paths.get(uploadDir).resolve(fileName); // uploadDir로 변경
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            UploadFile uploadFile = new UploadFile();
            uploadFile.setFileName(fileName); // 파일 이름 설정
            uploadFile.setSize(file.getSize()); // 파일 크기 설정
            uploadFile.setInsert_date(LocalDateTime.now()); // 현재 시간 설정

            uploadFileRepository.save(uploadFile); // 데이터베이스에 저장

            return uploadFile;
        } catch (IOException e) {
            throw new FileUploadException("[" + fileName + "] 파일 업로드에 실패했습니다. 다시 시도하십시오.", e);
        }
    }

    public Iterable<UploadFile> getFileList() {
        Iterable<UploadFile> iterable = uploadFileRepository.findAll();

        if (!iterable.iterator().hasNext()) {
            throw new FileDownloadException("업로드 된 파일이 존재하지 않습니다.");
        }
        return iterable;
    }

    public UploadFile getUploadFile(Long communityId) {
        return uploadFileRepository.findById(communityId)
                .orElseThrow(() -> new FileDownloadException("해당 아이디[" + communityId + "]로 업로드 된 파일이 존재하지 않습니다."));
    }



    // 커뮤니티 수정하기


    // 커뮤니티 삭제하기

}
