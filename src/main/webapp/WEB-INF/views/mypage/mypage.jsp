<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>미션</title>

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
    <link href="../css/styles.css" rel="stylesheet"/>
    <link rel="stylesheet" href="../css/challenges.css"/>
    <link rel="stylesheet" href="../css/mypage.css"/>
</head>
<body class="text-center" id="page-top">
    <nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
       <div class="container px-4 px-lg-5">
           <a class="navbar-brand" href="#page-top">Study</a>
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
                   <li class="nav-item"><a class="nav-link" href="/mypage">mypage</a></li>
                   <% } else { %>
                   <li class="nav-item"><a class="nav-link" href="/loginForm">Login</a></li>
                   <% } %>
               </ul>
           </div>
       </div>
    </nav>
    <div id="container">
        <h2>마이페이지입니다!</h2>
        <p class="mypage-name"><c:out value="${sessionScope.user.name}"/>님</p>
        <div class="mypage-list">
            <div class="list-set">
                <label>미션</label>
                <ul>
                    <li><a href="/challenges">미션 추가하기</a></li>
                    <li class="list-active"><a href="/mypage">진행중 미션</a></li>
                    <li><a href="">포도알 스티커</a></li>
                </ul>
            </div>
            <div class="list-set">
                <label>고객센터</label>
                <ul>
                    <li><a href="">공지사항</a></li>
                    <li><a href="">FAQ</a></li>
                </ul>
            </div>
            <div class="list-set">
                <label>설정</label>
                <ul>
                    <li><a href="/challenges">프로필 수정</a></li>
                    <li><a href="">비밀번호 수정</a></li>
                    <li><a href="/logout">로그아웃</a></li>
                </ul>
            </div>
        </div>
        <div class="mypage-content">
            <c:choose>
                <c:when test ="${not empty sessionScope.user}">
                    <c:forEach var="mission" items="${missions}">
                        <div>
                            <span>
                                <c:out value="${mission.challenge.title}"></c:out>
                                <c:out value="${mission.challenge.content}"></c:out>
                                <c:out value="${mission.succese}"></c:out>
                            </span>
                        </div>
                    </c:forEach>
                </c:when>
            </c:choose>
        </div>
    </div>

<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
