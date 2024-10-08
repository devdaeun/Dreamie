<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>회원가입</title>

    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap Icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic" rel="stylesheet" type="text/css" />
    <!-- SimpleLightbox plugin CSS-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.css" rel="stylesheet" />
    <!-- Bootstrap CSS -->
    <%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">--%>

    <!-- 나의 스타일 추가 -->
    <link rel="stylesheet" href="css/styles.css" />
    <link rel="stylesheet" href="css/terms.css" />

</head>
<body id="page-top">
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="/">Dreamie</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto my-2 my-lg-0">
                <li class="nav-item"><a class="nav-link" href="/notice">Notice</a>
                <li class="nav-item"><a class="nav-link" href="/challenges">challenges</a>
                    <!-- 문의사항 페이지로 이동하는 링크 -->
                <li class="nav-item"><a class="nav-link" href="/qna">문의사항</a></li>
                <% if (session.getAttribute("user") != null) { %>

                <!--로그인 하면 로그아웃 옆에 마이페이지-->
                <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
                <li class="nav-item"><a class="nav-link" href="/mypage">Mypage</a></li>
                <% } else { %>
                <li class="nav-item"><a class="nav-link" href="/loginForm">Login</a></li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>
<!--  html 전체 영역을 지정하는 container -->
<div id="container" class="text-center">

    <div class="title">
        <h1>회원가입</h1>
    </div>

    <div class="step text-center">
        <div class="step1">
            <p>1. 약관 동의</p>
        </div>
        <div class="step2">
            <p>2. 회원 가입</p>
        </div>
    </div>

    <div class="terms-container">
        <form id="termsForm" onsubmit="return validateTerms()">
            <!-- 서비스 이용 약관 -->
            <h4>서비스 이용 약관</h4>
            <div class="terms-content">
                <p>
                    여기에 서비스 이용 약관 내용을 입력하세요. 이용 약관은 사용자가 사이트 또는 서비스를 이용할 때 준수해야 할 규칙과 규정을 설명합니다.
                    예시: 본 서비스는 모든 사용자에게 동일한 조건을 적용하며, 불법적인 행위는 금지됩니다.
                </p>
            </div>

            <!-- 개인정보 처리 방침 -->
            <h4>개인정보 처리 방침</h4>
            <div class="terms-content">
                <p>
                    여기에 개인정보 처리 방침 내용을 입력하세요. 사용자의 개인정보가 어떻게 수집되고 보호되는지 설명합니다.
                    예시: 본 서비스는 사용자의 개인정보를 보호하기 위해 적절한 보안 조치를 취하고 있으며, 제3자에게 제공되지 않습니다.
                </p>
            </div>

            <!-- 동의 체크박스 -->
            <div class="form-check mb-3">
                <input class="form-check-input" type="checkbox" id="agreeTerms" name="agreeTerms">
                <label class="form-check-label" for="agreeTerms">
                    서비스 이용 약관 및 개인정보 처리 방침에 동의합니다.
                </label>
            </div>

            <!-- 버튼 -->
            <button type="submit" class="btn btn-primary w-100">동의하고 회원가입</button>
        </form>
    </div>
</div>

<!-- Footer-->
<footer class="bg-light py-5">
    <div class="container px-4 px-lg-5"><div class="small text-center text-muted">Copyright &copy; 2023 - Company Name</div></div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- SimpleLightbox plugin JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<!-- * *                               SB Forms JS                               * *-->
<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- 부트스트랩 JS 및 Popper.js -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

<script src="../js/terms.js"></script>
</body>
</html>
