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
document.addEventListener('DOMContentLoaded', function() {
    function validateForm() {
        console.log("validateForm 함수 실행 확인");

        // 필드 값 가져오기
        var id = document.getElementById('id').value;
        var password = document.getElementById('password').value;
        var email = document.getElementById('email').value;
        var name = document.getElementById('name').value;
        // var work = document.getElementById('work').value;

        // 오류 메시지를 표시할 엘리먼트 가져오기
        var idErrorMessage = document.getElementById('idErrorMessage');
        var passwordErrorMessage = document.getElementById('passwordErrorMessage');
        var emailErrorMessage = document.getElementById('emailErrorMessage');
        var nameErrorMessage = document.getElementById('nameErrorMessage');
        // var workErrorMessage = document.getElementById('workErrorMessage');

        // 오류 메시지를 초기화
        idErrorMessage.textContent = "";
        passwordErrorMessage.textContent = "";
        emailErrorMessage.textContent = "";
        nameErrorMessage.textContent = "";
        // workErrorMessage.textContent = "";

        var isValid = true;

        // 빈 값 확인 및 오류 메시지 표시
        if (id === "") {
            idErrorMessage.textContent = "아이디를 입력해주세요.";
            isValid = false;
        }
        if (password === "") {
            passwordErrorMessage.textContent = "비밀번호를 입력해주세요.";
            isValid = false;
        }
        if (email === "") {
            emailErrorMessage.textContent = "이메일을 입력해주세요.";
            isValid = false;
        }
        if (name === "") {
            nameErrorMessage.textContent = "이름을 입력해주세요.";
            isValid = false;
        }
        // if (work === "") {
        //     workErrorMessage.textContent = "직업을 입력해주세요.";
        //     isValid = false;
        // }

        console.log("isValid:", isValid);

        // 모든 필드가 올바르게 입력된 경우 true 반환, 그렇지 않으면 false 반환
        return isValid;
    }
})

function checkPassword() {
    var password = document.getElementById('password').value;
    var confirmPassword = document.getElementById('confirmPassword').value;
    var confirmPasswordErrorMessage = document.getElementById('confirmPasswordErrorMessage');

    // 메시지 초기화
    confirmPasswordErrorMessage.textContent = "";

    // 비밀번호가 비어있는지 확인
    if (password === "") {
        confirmPasswordErrorMessage.textContent = "먼저 비밀번호를 입력하세요.";
        return;
    }

    // 비밀번호 일치 여부 확인
    if (password === confirmPassword) {
        confirmPasswordErrorMessage.textContent = "비밀번호가 일치합니다.";
        confirmPasswordErrorMessage.style.color = "green"; // 일치하면 녹색 메시지
    } else {
        confirmPasswordErrorMessage.textContent = "비밀번호가 일치하지 않습니다.";
        confirmPasswordErrorMessage.style.color = "red"; // 불일치하면 빨간 메시지
    }
}