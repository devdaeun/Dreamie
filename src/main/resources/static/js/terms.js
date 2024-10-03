// 약관 동의 유효성 검사
function validateTerms() {
    var agreeTerms = document.getElementById('agreeTerms');

    // 체크박스가 선택되지 않은 경우 경고 메시지 표시
    if (!agreeTerms.checked) {
        alert("서비스 이용 약관 및 개인정보 처리 방침에 동의해야 합니다.");
        return false; // 폼 제출 중단
    }

    // 동의했으면 회원가입 페이지로 이동
    window.location.href = "/memberForm"; // 회원가입 페이지 경로 설정
    return false; // 실제로 폼을 제출하지 않고, 페이지 이동만 실행
}