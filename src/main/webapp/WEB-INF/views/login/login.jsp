<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- 나의 스타일 추가 -->
    <link rel="stylesheet" href="css/login.css?v=1234">

</head>
<body class="text-center">

<!--  html 전체 영역을 지정하는 container -->
<div id="container">
    <!--  login 폼 영역을 : loginBox -->
    <div id="loginBox">

        <!-- 로그인 페이지 타이틀 -->
        <div id="loginBoxTitle">Login</div>
        <!-- 아이디, 비번, 버튼 박스 -->
        <form action="/login" method="post">
            <div id="inputBox">
                <div class="input-form-box"><span>아이디 </span><input type="text" name="id" class="form-control"></div>
                <div class="input-form-box"><span>비밀번호 </span><input type="password" name="pwd" class="form-control"></div>
                <div class="button-login-box" >
                    <button type="submit" class="btn btn-primary btn-xs" style="width:100%" >로그인</button>
                    <button type="button" class="btn btn-primary btn-xs" style="width:100%" onclick="location.href='terms'">회원가입</button>
                </div>
            </div>
        </form>


        <c:if test="${param.error == 'true'}">
            <div class="alert alert-danger" role="alert">
                로그인 실패: 잘못된 아이디 또는 비밀번호입니다.
            </div>
        </c:if>

        <c:if test="${param.error == 'deactivated'}">
            <div class="alert alert-danger" role="alert">
                탈퇴한 회원입니다. 로그인이 불가능합니다.
            </div>
        </c:if>


        <script>
            // 페이지 로드 시 URL에서 error 파라미터 제거
            if (window.location.search.includes('error=true')) {
                const url = new URL(window.location);
                url.searchParams.delete('error');
                window.history.replaceState({}, '', url);
            }
        </script>

    </div>
</div>

<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
