<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>문의사항 수정</title>

    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
    <link href="../../css/styles.css" rel="stylesheet" />
    <link rel="stylesheet" href="../../css/qnaUpdate.css"/>
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
                <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
                <% } else { %>
                <li class="nav-item"><a class="nav-link" href="/loginForm">Login</a></li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>
<div id="container-qna" class="container">
    <div class="title">
        <h1>문의사항 수정하기</h1>
    </div>

    <div class="grid" style="--bs-columns: 10; --bs-gap: 1rem;">

        <div class="category text-center">
            <h3>카테고리</h3>
            <div class="list-group list-group-flush">
                <a href="/community" class="list-group-item">커뮤니티</a>
                <a href="/notice" class="list-group-item">공지사항</a>
                <a href="#" class="list-group-item active" aria-current="true">문의사항</a>
            </div>
        </div>

        <form class="qnaForm" action="/qna/${qna.question_id}/update" method="post">
            <div class="board">
                <div class="mb-3 row">
                    <label for="user_id" class="userId col-sm-1 col-form-label">아이디</label>
                    <div class="col-sm-3">
                        <input type="text" readonly class="form-control-plaintext" id="user_id" value="${sessionScope.user.name}">
                    </div>
                </div>
                <!-- show_type을 사용자가 선택하도록 할 경우 (기본 값 True로 설정됨) -->
                <div class="mb-3">
                    <label for="show_type" class="form-label">공개 여부</label>
                    <select class="form-control" id="show_type" name="show_type">
                        <option value="True" <c:if test="${qna.show_type == 'True'}">selected</c:if>>공개</option>
                        <option value="False" <c:if test="${qna.show_type == 'False'}">selected</c:if>>비공개</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="title" class="form-label">제목</label>
                    <input type="text" class="form-control" id="title" name="title" value="${qna.title}">
                </div>
                <div class="mb-3">
                    <label for="content" class="form-label">문의 내용</label>
                    <textarea class="form-control" id="content" name="content" rows="3">${qna.content}</textarea>
                </div>
                <div class="btn_area">
                    <button type="submit" class="btn btn-primary">수정 완료</button>
                    <a href="/qna/${qna.question_id}">
                        <button type="button" class="btn btn-outline-primary">취소</button>
                    </a>
                </div>
            </div>
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
</body>
</html>