package flower.dreamie.community.service;

import flower.dreamie.community.entity.Comment;
import flower.dreamie.community.entity.Community;
import flower.dreamie.community.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // 커뮤니티에 속한 댓글 목록 조회
    public List<Comment> getCommentsByCommunityId(Long communityId) {
        Community community = new Community(); // Community 객체 생성
        community.setCommunity_id(communityId); // ID 설정

        return commentRepository.findByCommunity(community);  // community 객체를 기준으로 댓글 필터링
    }

    // 댓글 저장
    public Comment saveComment(Comment comment) {
        comment.setCreated_at(LocalDateTime.now()); // 현재 시간으로 설정
        comment.setUpdated_at(LocalDateTime.now());
        return commentRepository.save(comment);  // 댓글 저장
    }



    // 댓글 삭제
    public void deleteComment(Long comment_id) {
        commentRepository.deleteById(comment_id);  // 댓글 삭제
    }
}
