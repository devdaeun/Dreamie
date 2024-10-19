<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>공지사항</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap Icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic" rel="stylesheet" type="text/css" />
    <!-- SimpleLightbox plugin CSS-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <!-- 나의 스타일 추가 -->
    <link href="css/styles.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/noticeList.css?v=1234">

</head>
<body  id="page-top">
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="/">Dreamie</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto my-2 my-lg-0">
                <li class="nav-item"><a class="nav-link" href="/notice">Notice</a></li>
                <li class="nav-item"><a class="nav-link" href="/challenges">challenges</a></li>
                <li class="nav-item"><a class="nav-link" href="/qna">Q&A</a></li>
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
<div id="container-notice" class="container">
    <div class="title">
        <h1>공지사항</h1>
    </div>

    <div class="grid text-center" style="--bs-columns: 10; --bs-gap: 1rem;">

        <div class="category">
            <h3>카테고리</h3>
            <div class="list-group list-group-flush">
                <a href="/community" class="list-group-item">커뮤니티</a>
                <a href="/notice" class="list-group-item active" aria-current="true">공지사항</a>
                <a href="/qna" class="list-group-item">문의사항</a>
            </div>
        </div>

        <div class="board">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>내용</th>
                    <th>작성일</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="notice" items="${noticeList}">
                    <tr>
                        <td><a href="/notice/${notice.notice_id}">${notice.title}</a></td>
                        <td>${notice.user.name}</td>
                        <td>${notice.content}</td>
                        <td>${notice.formattedWriteAt}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:if test="${sessionScope.user != null && sessionScope.user.role == '관리자'}">
                <a href="/noticeForm" class="btn btn-outline-secondary">공지사항 작성하기</a>
            </c:if>


        </div>
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
</body>
</html>
