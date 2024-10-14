function previewFile(uploadFileId) {
    // AJAX 요청을 통해 미리보기 내용을 가져옴
    fetch(`/community/preview/${uploadFileId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('파일을 미리 볼 수 없습니다.');
            }
            return response.text();
        })
        .then(data => {
            // 가져온 데이터를 미리보기 영역에 삽입
            document.getElementById('previewArea').innerHTML = data;
        })
        .catch(error => {
            console.error('Error:', error);
            alert('미리보기 실패: ' + error.message);
        });
}


// 수정삭제
document.addEventListener('DOMContentLoaded', function() {
    var editButton = document.getElementById('editButton');
    var saveButton = document.getElementById('saveButton');
    var deleteButton = document.getElementById('deleteButton'); // 삭제 버튼
    var noticeContent = document.getElementById('noticeContent');
    var editContent = document.getElementById('editContent');
    var editContentText = document.getElementById('editContentText');

    if (editButton) {
        editButton.addEventListener('click', function() {
            noticeContent.style.display = 'none';
            editContent.classList.add('active');
            saveButton.classList.add('active'); // 저장 버튼을 보이게 함
        });
    }

    if (saveButton) {
        saveButton.addEventListener('click', function() {
            var updatedContent = editContentText.value;

            fetch('/community/update', { // 수정된 경로
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'X-Requested-With': 'XMLHttpRequest'
                },
                body: new URLSearchParams({
                    'community_id': document.getElementById('noticeId').value, // 수정된 파라미터 이름
                    'content': updatedContent
                })
            })
                .then(response => {
                    if (response.ok) {
                        noticeContent.textContent = updatedContent;
                        noticeContent.style.display = 'block';
                        editContent.classList.remove('active');
                        saveButton.classList.remove('active'); // 저장 버튼 숨기기
                    } else {
                        alert('수정에 실패했습니다. 다시 시도해 주세요.');
                    }
                });
        });
    }

    if (deleteButton) {
        deleteButton.addEventListener('click', function() {
            if (confirm('정말 삭제하시겠습니까?')) {
                fetch('/community/delete', { // 수정된 경로
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                        'X-Requested-With': 'XMLHttpRequest'
                    },
                    body: new URLSearchParams({
                        'community_id': document.getElementById('noticeId').value // 수정된 파라미터 이름
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
