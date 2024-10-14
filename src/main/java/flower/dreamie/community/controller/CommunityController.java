package flower.dreamie.community.controller;

import flower.dreamie.community.entity.Comment;
import flower.dreamie.community.entity.Community;
import flower.dreamie.community.entity.UploadFile;
import flower.dreamie.community.repository.UploadFileRepository;
import flower.dreamie.community.service.CommentService;
import flower.dreamie.community.service.CommunityService;
import flower.dreamie.login.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;


@Controller
public class CommunityController {

    private final CommunityService communityService;
    private final UploadFileRepository uploadFileRepository;
    private final CommentService commentService; // CommentService 주입

    public CommunityController(CommunityService communityService, UploadFileRepository uploadFileRepository, CommentService commentService) {
        this.communityService = communityService;
        this.uploadFileRepository = uploadFileRepository;
        this.commentService = commentService;
    }

    // 커뮤니티조회
    @RequestMapping("/community")
    public String List(Model model) {
        System.out.println("커뮤니티 조회 메서드 호출됨");
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
                                @RequestParam("file") MultipartFile file,
                                HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println("User from session: " + user);

        community.setUser_id(user.getUser_id());

        try {
            // community 객체를 먼저 저장하고 ID를 가져옴
            communityService.saveCommunity(community); // Community 저장

            // 저장된 커뮤니티의 ID를 사용하여 파일 업로드 메서드 호출
            if (!file.isEmpty()) {
                UploadFile uploadFile = communityService.saveUploadedFile(file, community.getCommunity_id());
                community.setUploadFile(uploadFile); // Community 객체에 UploadFile 설정
                communityService.saveCommunity(community); // 변경된 Community 저장
            }
        } catch (Exception e) {
            return "redirect:/communityForm?error=file upload fail"; // 에러 처리
        }

        return "redirect:/community?success=true"; // 성공 시 리다이렉트
    }

//    // 커뮤니티 상세 조회
//    @RequestMapping("/community/{community_id}")
//    public String detail(@PathVariable("community_id") Long community_id, Model model, HttpSession session) {
//        System.out.println("요청된 community_id: " + community_id); // 추가된 로그
//        Community community = communityService.getCommunityById(community_id);
//        User user = (User) session.getAttribute("user");
//        model.addAttribute("community", community);
//        model.addAttribute("user", user);
//        return "community/communityDetail";
//    }

    // 커뮤니티 상세 조회 및 댓글 조회
    @RequestMapping("/community/{community_id}")
    public String detail(@PathVariable("community_id") Long community_id, Model model, HttpSession session) {
        Community community = communityService.getCommunityById(community_id);
        User user = (User) session.getAttribute("user");

        // 댓글 목록 가져오기
        List<Comment> comments = commentService.getCommentsByCommunityId(community_id);

        model.addAttribute("community", community);
        model.addAttribute("user", user);
        model.addAttribute("comments", comments);  // 댓글 목록 추가
        return "community/communityDetail";
    }

    // 파일 다운로드
    @GetMapping("/community/download/{uploadFileId}")
    public ResponseEntity<FileSystemResource> downloadFile(@PathVariable Long uploadFileId) {
        UploadFile uploadFile = uploadFileRepository.findById(uploadFileId)
                .orElseThrow(() -> new RuntimeException("파일을 찾을 수 없습니다."));

        if (uploadFile == null || uploadFile.getFilePath() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        FileSystemResource resource = new FileSystemResource(uploadFile.getFilePath());

        HttpHeaders headers = new HttpHeaders();
        String encodedFileName = UriUtils.encode(uploadFile.getFileName(), StandardCharsets.UTF_8);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"");

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    // 커뮤니티 수정하기
    @PostMapping("/community/update")
    @ResponseBody
    public String updateCommunity(@RequestParam("community_id") Long community_id,
                                  @RequestParam("content") String content) {

        Community updatedCommunity = communityService.updateCommunity(community_id, content);
        if (updatedCommunity != null) {
            return "OK";
        }
        return "FAIL";
    }

    // 커뮤니티 삭제하기
    @PostMapping("/community/delete")
    @ResponseBody
    public String deleteCommunity(@RequestParam("community_id") Long community_id) {
        try {
            communityService.deleteCommunity(community_id);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }


    //커뮤니티 파일 수정
    @PostMapping("/community/uploadFile/update")
    @ResponseBody
    public ResponseEntity<String> updateUploadFile(@RequestParam("uploadFileId") Long uploadFileId,
                                                   @RequestParam("file") MultipartFile file) {
        try {
            communityService.updateUploadFile(uploadFileId, file);
            return ResponseEntity.ok("파일이 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            e.printStackTrace(); // 예외 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 수정에 실패했습니다.");
        }
    }

    // 댓글 저장
    @PostMapping("/community/{community_id}/comment")
    public String saveComment(@PathVariable("community_id") Long community_id,
                              @RequestParam("content") String content, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/loginForm"; // 로그인 필요 시 로그인 페이지로 리다이렉트
        }

        Comment comment = new Comment();
        comment.setCommunity(communityService.getCommunityById(community_id));
        comment.setUser(user);
        comment.setContent(content);

        commentService.saveComment(comment);

        // 댓글 저장 후 communityDetail 페이지로 리다이렉트
        return "redirect:/community/" + community_id; // 수정된 URL
    }


    // 댓글 삭제
    @PostMapping("/community/comment/delete")
    @ResponseBody
    public String deleteComment(@RequestParam("comment_id") Long commentId) {
        try {
            commentService.deleteComment(commentId);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

}


