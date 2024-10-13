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
    <link rel="stylesheet" href="../css/qnaList.css"/>
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
    <div id="container-qna" class="container">
        <div class="title">
            <h1>문의사항</h1>
        </div>

        <div class="grid text-center" style="--bs-columns: 10; --bs-gap: 1rem;">

            <div class="category">
                <h3>카테고리</h3>
                <div class="list-group list-group-flush">
                    <a href="/notice" class="list-group-item">공지사항</a>
                    <a href="#" class="list-group-item active" aria-current="true">문의사항</a>
                </div>
            </div>

            <div class="board">
<%--                <h3>문의사항</h3>--%>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>글번호</th>
                        <th>제목</th>
                        <th>회원</th>
                        <th>작성일</th>
                    </tr>
                    </thead>
                    <tbody>
                        <!-- qnaList를 반복하며 각 문의사항을 출력 -->
                        <c:forEach var="qna" items="${qnaList}">
                            <tr>
                                <td>${qna.question_id}</td> <!-- 문의 번호 -->
                                <!-- 비밀글인지 확인 -->
                                <!-- 작성자거나 관리자인 경우 글 제목을 보여줌 -->
                                <!-- 작성자도 아니고 관리자가 아닐 때는 '비밀글입니다' 표시 -->
                                <c:choose>
                                    <c:when test="${qna.show_type == 'False'}">

                                        <c:if test="${sessionScope.user.user_id == qna.user_id || sessionScope.user.role == '관리자'}">
                                            <td><a href="/qna/${qna.question_id}"><i class="bi bi-lock-fill"></i> ${qna.title}</a></td>
                                            <td>${qna.user_id}</td>
                                            <td>${qna.write_at}</td>
                                        </c:if>

                                        <c:if test="${sessionScope.user.user_id != qna.user_id && sessionScope.user.role != '관리자'}">
                                            <td><i class="bi bi-lock-fill"></i> 비밀글입니다 </td>
                                            <td></td>
                                            <td></td>
                                        </c:if>

                                    </c:when>

                                    <c:otherwise>
                                        <td><a href="/qna/${qna.question_id}">${qna.title}</a></td>
                                        <td>${qna.user_id}</td>
                                        <td>${qna.write_at}</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <a href="/qnaForm">
                    <button type="button" class="btn btn-outline-secondary">문의하기</button>
                </a>
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