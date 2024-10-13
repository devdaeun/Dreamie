/* 포도알 색칠하기 */
document.addEventListener('DOMContentLoaded', function() {
    // 성공한 미션 개수를 가져옴
    const stickersContainer = document.querySelector('.stickers');
    const completedMissions = parseInt(stickersContainer.getAttribute('data-completed-missions'), 10);

    // 모든 스티커들을 가져옴
    const stickers = document.querySelectorAll('.sticker');

    // 성공한 미션 개수만큼 스티커 채우기
    for (let i = 0; i < completedMissions; i++) {
        if (stickers[i]) {
            stickers[i].src = '../assets/img/fill_sticker.svg';  // 스티커 이미지를 색칠된 것으로 변경
        }
    }
})