<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>문의사항</title>
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
    <link href="css/styles.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="#page-top">Study</a>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto my-2 my-lg-0">
                        <li class="nav-item"><a class="nav-link" href="#about">About</a></li>
                        <li class="nav-item"><a class="nav-link" href="#services">Services</a></li>
                        <li class="nav-item"><a class="nav-link" href="#portfolio">Portfolio</a>
                        <li class="nav-item"><a class="nav-link" href="/notices">Notice</a>
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
        <h1>문의사항</h1>
        <div class="list-group list-group-flush">
            <a href="#" class="list-group-item">공지사항</a>
            <a href="#" class="list-group-item active" aria-current="true">문의사항</a>
            <a href="#" class="list-group-item">설정</a>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>번호</th>
                <th>회원 번호</th>
                <th>제목</th>
                <th>작성일</th>
            </tr>
            </thead>
            <tbody>
                <!-- qnaList를 반복하며 각 문의사항을 출력 -->
                <c:forEach var="qna" items="${qnaList}">
                    <tr>
                        <td>${qna.question_id}</td>      <!-- 문의 번호 -->
                        <td>${qna.title}</td>           <!-- 제목 -->
                        <td>${qna.user_id}</td>          <!-- 작성자 -->
                        <td>${qna.write_at}</td>        <!-- 작성일 -->
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>