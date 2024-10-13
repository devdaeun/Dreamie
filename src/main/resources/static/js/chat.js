function deleteRoom(roomId){
    if (confirm('정말 삭제하시겠습니까?')) {
        fetch('/chat/deleteRoom', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                roomId: roomId
            })
        })
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error('삭제 실패: ' + response.statusText);
            }
        })
        .then(responseText => {
            alert('삭제되었습니다.');
            window.location.href = '/chat/rooms';
        })
        .catch(error => {
            alert('오류: ' + error.message);
        });
    }
}