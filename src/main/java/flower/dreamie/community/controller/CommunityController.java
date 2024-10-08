package flower.dreamie.community.controller;

import flower.dreamie.community.entity.Community;
import flower.dreamie.community.entity.UploadFile;
import flower.dreamie.community.service.CommunityService;
import flower.dreamie.login.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import flower.dreamie.community.exception.FileDownloadException;
import flower.dreamie.community.exception.FileUploadException;



@Controller
public class CommunityController {

    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) { this.communityService = communityService;
    }


    // 커뮤니티조회
    @RequestMapping("/community")
    public String List(Model model) {
        System.out.println("커뮤니티 조회 메서드 호출됨"); // 로그 추가
        model.addAttribute("community", communityService.getAllCommunity());
        return "community/community";
    }

    // 커뮤니티 작성하기
    @RequestMapping("/communityForm")
    public String communityForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
        if (user == null) {
            return "redirect:/loginForm";
        }

        model.addAttribute("community", new Community());
        return "community/communityForm";
    }


    // 커뮤니티 저장
    @RequestMapping("communitySave")
    public String saveCommunity(@ModelAttribute("community") Community community,
                                @RequestParam("file") MultipartFile file, // 파일 파라미터 추가
                                HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println("User from session: " + user);

        // 사용자 ID 설정
        community.setUser_id(user.getUser_id());

        // 커뮤니티 저장
        communityService.saveCommunity(community);

        // 파일 업로드 처리
        if (file != null && !file.isEmpty()) {
            try {
                UploadFile uploadFile = communityService.uploadFile(file); // 파일 업로드 메서드 호출
                // 추가적인 처리: uploadFile 객체를 커뮤니티와 연결할 수 있다면 여기서 설정
                community.setUploadFile(uploadFile); // 커뮤니티 객체에 파일 정보를 설정 (UploadFile 필드가 Community 클래스에 있어야 함)
            } catch (FileUploadException e) {
                // 예외 처리: 파일 업로드 실패 시 로깅 또는 사용자에게 알림
                e.printStackTrace();
            }
        }

        return "redirect:/community"; // 커뮤니티 리스트로 리다이렉트
    }

    // 커뮤니티 상세 조회
    @RequestMapping("/community/{community_id}")
    public String detail(@PathVariable("community_id") Long community_id, Model model, HttpSession session) {
        System.out.println("요청된 community_id: " + community_id); // 추가된 로그
        Community community = communityService.getCommunityById(community_id);
        User user = (User) session.getAttribute("user");

        model.addAttribute("community", community);
        model.addAttribute("user", user);

        return "community/communityDetail";
    }

    // 커뮤니티 수정하기


    // 커뮤니티 삭제하기


    // 파일 업로드 처리
    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            UploadFile uploadFile = communityService.uploadFile(file); // 메서드명 수정
            return ResponseEntity.ok(uploadFile);
        } catch (FileUploadException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // 업로드된 파일 리스트 조회 핸들러
    @GetMapping("/files")
    public ResponseEntity<?> getFileList() {
        try {
            Iterable<UploadFile> files = communityService.getFileList(); // 인스턴스 메서드로 변경
            return ResponseEntity.ok(files);
        } catch (FileDownloadException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // 특정 파일 조회 핸들러
    @GetMapping("/files/{community_id}")
    public ResponseEntity<?> getUploadFile(@PathVariable Long community_id) {
        try {
            UploadFile uploadFile = communityService.getUploadFile(community_id); // 인스턴스 메서드로 변경
            return ResponseEntity.ok(uploadFile);
        } catch (FileDownloadException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
