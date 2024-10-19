package flower.dreamie.community.exception;

public class FileUploadException extends RuntimeException {
    // 기본 생성자
    public FileUploadException() {
        super("파일 업로드 오류가 발생했습니다.");
    }

    // 메시지 포함 생성자
    public FileUploadException(String message) {
        super(message);
    }

    // 메시지와 원인 포함 생성자
    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    // 원인 포함 생성자
    public FileUploadException(Throwable cause) {
        super("파일 업로드 오류가 발생했습니다.", cause);
    }
}
