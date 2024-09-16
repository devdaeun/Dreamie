document.addEventListener('DOMContentLoaded', function() {
    var editButton = document.getElementById('editButton');
    var saveButton = document.getElementById('saveButton');
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
                    'notice_id': '${notice.notice_id}',
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
});
