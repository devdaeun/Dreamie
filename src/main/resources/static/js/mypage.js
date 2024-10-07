function missionComplete(mission_id){
    if (confirm('미션을 완료하시겠습니까?')) {
        fetch('/mission/complete', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                mission_id: mission_id
            })
        })
        .then(response => {
            if (response.ok) {
                alert('완료되었습니다');
                window.location.href= '/mypage';
            } else {
                throw new Error('등록 실패.');
            }
        })
    }
}

//회원탈퇴
function confirmDeactivation(user_id) {
    if (confirm("정말로 회원탈퇴를 하시겠습니까?")) {
        deactivateUser(user_id);
    }
}

function deactivateUser(user_id) {
    fetch(`/deactivate`, { // @RequestMapping("/user")가 없으면 `/deactivate`로 수정
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ user_id: user_id })
    })
        .then(response => {
            if (response.ok) {
                alert("회원탈퇴가 완료되었습니다.");
                window.location.href = "/logout"; // 탈퇴 후 로그아웃
            } else {
                alert("회원탈퇴에 실패했습니다. 다시 시도해주세요.");
            }
        })
        .catch(error => console.error("Error:", error));
}


