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