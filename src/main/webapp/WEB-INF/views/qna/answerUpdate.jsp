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
    <title>문의사항 상세보기</title>

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
    <link href="../../../css/styles.css" rel="stylesheet" />
    <link rel="stylesheet" href="../../../css/qnaDetail.css"/>
</head>
<body id="page-top">
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="/">Dreamie</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto my-2 my-lg-0">
                <li class="nav-item"><a class="nav-link" href="#about">About</a></li>
                <li class="nav-item"><a class="nav-link" href="#services">Services</a></li>
                <li class="nav-item"><a class="nav-link" href="#portfolio">Portfolio</a>
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
    </div>
</nav>
<div id="container-qna" class="container">
    <div class="title">
        <h1>답변 수정하기</h1>
    </div>

    <div class="grid" style="--bs-columns: 10; --bs-gap: 1rem;">

        <div class="category text-center">
            <h3>카테고리</h3>
            <div class="list-group list-group-flush">
                <a href="/notice" class="list-group-item">공지사항</a>
                <a href="/qna" class="list-group-item active" aria-current="true">문의사항</a>
                <a href="#" class="list-group-item">설정</a>
            </div>
        </div>

        <div class="board">
            <div class="content row-gap-3">
                <div class="contentTitle"><h4>${qna.title}</h4></div>
                <div class="contentId">
                    <h6>작성자</h6>
                    <p>${qna.user_id}</p>
                </div>
                <div class="contentAt">
                    <h6>작성일</h6>
                    <p>${qna.write_at}</p>
                </div>
                <div class="realContent"><p>${qna.content}</p></div>

                <h6>답변 수정</h6>
                <div id="answers-section" class="mt-5">
                    <!-- 관리자만 답변 수정 폼을 볼 수 있도록 -->
                    <c:if test="${user != null && user.role == '관리자'}">
                        <form action="/qna/${qna.question_id}/answer/${answer.answer_id}/update" method="post">
                            <div class="comment mb-3">
                                <label for="answerContent"></label>
                                <textarea name="content" class="form-control" id="answerContent" rows="3" required>${answer.content}</textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">수정 완료</button>
                            <a href="/qna/${answer.question_id}" class="btn btn-secondary">취소</a>
                        </form>
                    </c:if>
                </div>
            </div>
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
<script src="../js/scripts.js"></script>
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<!-- * *                               SB Forms JS                               * *-->
<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>