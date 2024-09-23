function challenge_btn(){
    document.getElementById('modify_btn').style.display='block';
    document.getElementById('motion_btn').style.display='none';

    document.getElementById('title').removeAttribute('readonly');
    document.getElementById('content').removeAttribute('readonly');
    document.getElementById('start_at').removeAttribute('readonly');
    document.getElementById('end_at').removeAttribute('readonly');
}

function modify_challenge() {
    if (confirm('수정하시겠습니까?')) {
        var form = document.getElementById('challenge_modify_form');
        var formData = new FormData(form);

        fetch('/challenges/modify', {
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error('수정 실패.');
            }
        })
        .then(responseText => {
            alert('수정되었습니다.');
            window.location.href = '/challenges/info?challenges_id=' + document.getElementById('challenges_id').value;
        })
        .catch(error => {
            alert(error.message);
        });
    }
}

function cancel_btn() {
    window.location.href = '/challenges/info?challenges_id=' + document.getElementById('challenges_id').value;
}

function delete_challenge(challenges_id) {
    if (confirm('정말 삭제하시겠습니까?')) {
        fetch('/challenges/delete', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                challenges_id: challenges_id
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
            window.location.href = '/challenges'; // 목록 페이지로 리다이렉션
        })
        .catch(error => {
            alert('오류: ' + error.message);
        });
    }
}

function missionSelect(challenges_id){
    if (confirm('미션을 추가하시겠습니까?')) {
        fetch('/mission/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                challenges_id: challenges_id
            })
        })
        .then(response => {
            if (response.ok) {
                alert('추가되었습니다');
                window.location.href= '/challenges';
            } else {
                throw new Error('등록 실패.');
            }
        })
    }
}
