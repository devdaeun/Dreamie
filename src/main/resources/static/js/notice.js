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

            fetch('/notice/update', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'X-Requested-With': 'XMLHttpRequest'
                },
                body: new URLSearchParams({
                    'notice_id': document.getElementById('noticeId').value,
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
                fetch('/notice/delete', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                        'X-Requested-With': 'XMLHttpRequest'
                    },
                    body: new URLSearchParams({
                        'notice_id': document.getElementById('noticeId').value
                    })
                })
                    .then(response => response.text())
                    .then(result => {
                        if (result === 'OK') {
                            alert('삭제되었습니다.');
                            window.location.href = '/notice';
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
