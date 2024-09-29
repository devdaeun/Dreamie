function check_id() {
    var id = document.querySelector('input[name="id"]').value;

    fetch('/check-id', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({id: id})
    })
        .then(response => response.json())
        .then(data => {
            var messageElement = document.getElementById('idCheckMessage');
            if (data.exists) {
                messageElement.style.color = "red";
                messageElement.textContent = "이미 존재하는 아이디 입니다.";
            }
            else {
                messageElement.style.color = "green";
                messageElement.textContent = "사용 가능한 아이디 입니다.";
            }
        });
}
// 이메일 중복 확인
function check_email() {
    var email = document.querySelector('input[name="email"]').value;

    fetch('/check-email', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email: email })
    })
        .then(response => response.json())
        .then(data => {
            var messageElement = document.getElementById('emailCheckMessage');
            if (data.exists) {
                messageElement.style.color = "red";
                messageElement.textContent = "이미 존재하는 이메일 입니다.";
            } else {
                messageElement.style.color = "green";
                messageElement.textContent = "사용 가능한 이메일 입니다.";
            }
        });
}