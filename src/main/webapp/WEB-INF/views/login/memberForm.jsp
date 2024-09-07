<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- 나의 스타일 추가 -->
    <link rel="stylesheet" href="css/login.css?v=1234">

</head>
<body class="text-center">

<!--  html 전체 영역을 지정하는 container -->
<div id="container">
    <!--  회원가입 폼 영역을 : signUpBox -->
    <div id="signUpBox">

        <!-- 회원가입 페이지 타이틀 -->
        <div id="signUpBoxTitle">회원가입</div>
        <!-- 입력 폼 박스 -->
        <div id="inputBox">
            <form action="/addmember" method="POST">
                <div class="input-form-box">
                    <span>아이디 </span>
                    <input type="text" name="id" class="form-control" placeholder="아이디">
                </div>
                <div class="input-form-box">
                    <span>비밀번호 </span>
                    <input type="password" name="password" class="form-control" placeholder="비밀번호">
                </div>
<%--                <div class="input-form-box">--%>
<%--                    <span>비밀번호 확인 </span>--%>
<%--                    <input type="password" name="confirmPassword" class="form-control" placeholder="비밀번호 확인">--%>
<%--                </div>--%>
                <div class="input-form-box">
                    <span>이메일 </span>
                    <input type="email" name="email" class="form-control" placeholder="이메일">
                </div>
                <div class="input-form-box">
                    <span>이름 </span>
                    <input type="text" name="name" class="form-control" placeholder="이름">
                </div>
                <div class="input-form-box">
                    <span>직업 </span>
                    <input type="text" name="work" class="form-control" placeholder="직업">
                </div>
                <div class="button-login-box">
                    <button type="submit" class="btn btn-primary btn-xs" style="width:100%">회원가입</button>
                </div>
            </form>
        </div>

    </div>
</div>

<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
