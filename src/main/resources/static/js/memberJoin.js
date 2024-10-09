// 버튼이 눌렸는지 추적하는 플래그 변수
let isIdChecked = false;
let isEmailChecked = false;
let isPasswordChecked = false;

function check_id() {
    var id = document.querySelector('input[name="id"]').value;
    var messageElement = document.getElementById('idMessage');

    // 입력값이 없는 경우
    if (id === "") {
        messageElement.textContent = "아이디를 입력해주세요.";
        return; // 중복 체크 요청 중단
    }

    fetch('/check-id', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({id: id})
    })
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                messageElement.style.color = "red";
                messageElement.textContent = "이미 존재하는 아이디 입니다.";
            }
            else {
                messageElement.style.color = "green";
                messageElement.textContent = "사용 가능한 아이디 입니다.";
                isIdChecked = true; // 아이디 중복 확인 완료
            }
        });
}

// 이메일 형식 확인을 위한 정규 표현식
function validateEmailFormat(email) {
    var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailPattern.test(email);
}


// 이메일 중복 확인
function check_email() {
    var email = document.querySelector('input[name="email"]').value;
    var messageElement = document.getElementById('emailMessage');

    // 입력값이 없는 경우
    if (email === "") {
        messageElement.textContent = "이메일을 입력해주세요.";
        return; // 중복 체크 요청 중단
    }

    // 이메일 형식 검증
    if (!validateEmailFormat(email)) {
        messageElement.textContent = "올바른 이메일 형식이 아닙니다. 다시 입력해주세요.";
        messageElement.style.color = "red";
        return;
    }

    fetch('/check-email', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email: email })
    })
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                messageElement.style.color = "red";
                messageElement.textContent = "이미 존재하는 이메일 입니다.";
            } else {
                messageElement.style.color = "green";
                messageElement.textContent = "사용 가능한 이메일 입니다.";
                isEmailChecked = true; // 이메일 중복 확인 완료
            }
        });
}

//수정 시 이메일 확인
function email_check() {
    var email = document.querySelector('input[name="email"]').value;
    var currentEmail = document.getElementById("currentEmail").value; // 현재 사용자의 이메일
    var messageElement = document.getElementById('emailMessage');

    // 입력값이 없는 경우
    if (email === "") {
        messageElement.textContent = "이메일을 입력해주세요.";
        return;
    }

    // 이메일 형식 검증
    if (!validateEmailFormat(email)) {
        messageElement.textContent = "올바른 이메일 형식이 아닙니다. 다시 입력해주세요.";
        return;
    }

    // 이메일 중복 확인 요청
    fetch('/email-check', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email: email, currentEmail: currentEmail }) // 현재 이메일도 함께 전송
    })
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                messageElement.style.color = "red";
                messageElement.textContent = "이미 존재하는 이메일 입니다.";
            } else {
                messageElement.style.color = "green";
                messageElement.textContent = "사용 가능한 이메일 입니다.";
                isEmailChecked = true;
            }
        })
        .catch(error => {
            console.error("Error during email check:", error);
            messageElement.textContent = "이메일 확인 중 오류가 발생했습니다.";
            messageElement.style.color = "red";
        });
}

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
        isPasswordChecked = true; // 비밀번호 확인 완료
    }
    else {
        confirmPasswordErrorMessage.textContent = "비밀번호가 일치하지 않습니다.";
        confirmPasswordErrorMessage.style.color = "red"; // 불일치하면 빨간 메시지
        isPasswordChecked = false;
    }
}

// 사용자가 직업을 선택했을 때 호출되는 함수
function selectWork(work) {
    document.getElementById('work').value = work; // 숨겨진 input 필드에 값 설정
    document.getElementById('dropdownMenuButton').textContent = work; // 드롭다운 버튼 텍스트 업데이트
}

function validateForm() {
    console.log("validateForm 함수 실행 확인");

    // 필드 값 가져오기
    var id = document.getElementById('id').value;
    var password = document.getElementById('password').value;
    var confirmPassword = document.getElementById('confirmPassword').value;
    var email = document.getElementById('email').value;
    var name = document.getElementById('name').value;
    var work = document.getElementById('work').value;

    // 오류 메시지를 표시할 엘리먼트 가져오기
    var idMessage = document.getElementById('idMessage');
    var passwordErrorMessage = document.getElementById('passwordErrorMessage');
    var confirmPasswordErrorMessage = document.getElementById('confirmPasswordErrorMessage');
    var emailMessage = document.getElementById('emailMessage');
    var nameErrorMessage = document.getElementById('nameErrorMessage');
    var workErrorMessage = document.getElementById('workErrorMessage');

    // 오류 메시지를 초기화
    idMessage.textContent = "";
    passwordErrorMessage.textContent = "";
    confirmPasswordErrorMessage.textContent = "";
    emailMessage.textContent = "";
    nameErrorMessage.textContent = "";
    workErrorMessage.textContent = "";

    var isValid = true;

    // 빈 값 확인 및 오류 메시지 표시
    if (id === "") {
        idMessage.textContent = "아이디를 입력해주세요.";
        isValid = false;
    }
    else if (!isIdChecked) {
        idMessage.textContent = "아이디 중복 체크를 해주세요.";
        isValid = false;
    }
    if (password === "") {
        passwordErrorMessage.textContent = "비밀번호를 입력해주세요.";
        isValid = false;
    }
    if (confirmPassword === "") {
        confirmPasswordErrorMessage.textContent = "비밀번호를 입력해주세요.";
        isValid = false;
    } else if (password !== confirmPassword) {
        confirmPasswordErrorMessage.textContent = "비밀번호가 일치하지 않습니다.";
        isValid = false;
    }
    // 비밀번호 확인 버튼이 눌렸는지 확인
    if (!isPasswordChecked) {
        confirmPasswordErrorMessage.textContent = "비밀번호 확인을 눌러주세요.";
        isValid = false;
    }
    if (email === "") {
        emailMessage.textContent = "이메일을 입력해주세요.";
        isValid = false;
    }
    else if (!isEmailChecked) {
        emailMessage.textContent = "이메일 중복 체크를 해주세요.";
        emailMessage.style.color = "red";
        isValid = false;
    }
    if (name === "") {
        nameErrorMessage.textContent = "이름을 입력해주세요.";
        isValid = false;
    }
    if (work === "") {
        workErrorMessage.textContent = "직업을 선택해주세요.";
        isValid = false;
    }

    console.log("isValid:", isValid);
    // 모든 필드가 올바르게 입력된 경우 true 반환, 그렇지 않으면 false 반환
    if (!isValid) {
        return false; // 폼 제출을 중단
    }

    return true; // 모든 검증을 통과했을 때만 true 반환하여 폼 제출 진행
}

document.addEventListener('DOMContentLoaded', function() {
    // 폼을 제출할 때 validateForm을 실행
    document.querySelector('form').onsubmit = validateForm;
});