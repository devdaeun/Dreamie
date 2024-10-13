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