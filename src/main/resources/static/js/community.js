//수정 삭제
document.addEventListener('DOMContentLoaded', function() {
    var editButton = document.getElementById('editButton');
    var saveButton = document.getElementById('saveButton');
    var deleteButton = document.getElementById('deleteButton'); // 삭제 버튼
    var communityContent = document.getElementById('communityContent');
    var editContent = document.getElementById('editContent');
    var editContentText = document.getElementById('editContentText');

    // 수정 버튼 클릭 시 수정 모드로 전환
    if (editButton) {
        editButton.addEventListener('click', function() {
            communityContent.style.display = 'none'; // 기존 내용 숨기기
            editContent.style.display = 'block'; // 수정 영역 보이기
            saveButton.style.display = 'inline-block'; // 저장 버튼 보이기
            editButton.style.display = 'none'; // 수정 버튼 숨기기
        });
    }

    // 저장 버튼 클릭 시 내용 수정
    if (saveButton) {
        saveButton.addEventListener('click', function() {
            var updatedContent = editContentText.value;

            fetch('/community/update', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'X-Requested-With': 'XMLHttpRequest'
                },
                body: new URLSearchParams({
                    'community_id': document.getElementById('communityId').value,
                    'content': updatedContent
                })
            })
                .then(response => {
                    if (response.ok) {
                        communityContent.textContent = updatedContent;
                        communityContent.style.display = 'block';
                        editContent.style.display = 'none'; // 수정 영역 숨기기
                        saveButton.style.display = 'none'; // 저장 버튼 숨기기
                        editButton.style.display = 'inline-block'; // 수정 버튼 다시 보이기
                    } else {
                        alert('수정에 실패했습니다. 다시 시도해 주세요.');
                    }
                });
        });
    }

    // 삭제 버튼 클릭 시 삭제 요청
    if (deleteButton) {
        deleteButton.addEventListener('click', function() {
            if (confirm('정말 삭제하시겠습니까?')) {
                fetch('/community/delete', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                        'X-Requested-With': 'XMLHttpRequest'
                    },
                    body: new URLSearchParams({
                        'community_id': document.getElementById('communityId').value
                    })
                })
                    .then(response => response.text())
                    .then(result => {
                        if (result === 'OK') {
                            alert('삭제되었습니다.');
                            window.location.href = '/community';
                        } else {
                            alert('삭제에 실패했습니다. 다시 시도해 주세요.');
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('오류가 발생했습니다. 다시 시도해 주세요.');
                    });
            }
        });
    }
});

//파일수정
function showEditFileContent() {
    console.log('파일 수정 버튼 클릭됨'); // 추가
    document.getElementById('editFileContent').style.display = 'block';
}

function updateFile() {
    var uploadFileId = document.getElementById('uploadFileId').value; // ID 가져오기
    var newFile = document.getElementById('fileInput').files[0]; // 선택된 파일
    var formData = new FormData();
    formData.append('uploadFileId', uploadFileId); // uploadFileId 추가
    formData.append('file', newFile); // 파일 추가

    fetch('/community/uploadFile/update', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('파일 수정 실패했습니다.');
            }
            return response.text();
        })
        .then(data => {
            alert(data);
            window.location.href = '/community'; //
        })
        .catch(error => {
            console.error('오류:', error);
        });
}

// 내용 수정 버튼 클릭 시 텍스트 에디터 보이기
function showEditContent() {
    document.getElementById('communityContent').style.display = 'none'; // 기존 내용 숨김
    document.getElementById('editContent').style.display = 'block'; // 수정 모드 보이기
    document.getElementById('saveButton').style.display = 'inline-block'; // 저장 버튼 보이기
}

//댓글삭제
function deleteComment(commentId) {
    if (confirm("댓글을 삭제하시겠습니까?")) {
        const csrfToken = document.querySelector('input[name="csrfToken"]').value; // CSRF 토큰 가져오기

        fetch(`/community/${community.community_id}/comment/${commentId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-Token': csrfToken // CSRF 토큰 추가
            }
        })
            .then(response => {
                if (response.ok) {
                    location.reload(); // 성공적으로 삭제된 경우 페이지 새로고침
                } else {
                    alert("댓글 삭제에 실패했습니다.");
                }
            })
            .catch(error => console.error('Error:', error)); // 오류 로그
    }
}
