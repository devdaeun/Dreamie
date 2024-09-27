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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"/>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap Icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic" rel="stylesheet" type="text/css" />
    <!-- SimpleLightbox plugin CSS-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="../css/styles.css"/>
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
    <div id="container-mypage" class="container">
        <div class="mypage-name">
            <p><c:out value="${sessionScope.user.name}"/>님</p>
        </div>

        <div class="grid text-center" style="--bs-columns: 10; --bs-gap: 1rem;">
            <!-- Left Sidebar (Categories) -->
            <div class="category">
                <h3>마이페이지</h3>
                <div class="list-group list-group-flush">
                    <label>미션</label>
                    <a href="/challenges" class="list-group-item">미션 추가하기</a>
                    <a href="/mypage" class="list-group-item list-active">진행중 미션</a>
                    <a href="#" class="list-group-item">포도알 스티커</a>
                </div>
                <div class="list-group list-group-flush">
                    <label>고객센터</label>
                    <a href="/notice" class="list-group-item">공지사항</a>
                    <a href="/qna" class="list-group-item">문의사항</a>
                </div>
                <div class="list-group list-group-flush">
                    <label>설정</label>
                    <a href="/challenges" class="list-group-item">프로필 수정</a>
                    <a href="#" class="list-group-item">비밀번호 수정</a>
                    <a href="/logout" class="list-group-item">로그아웃</a>
                    <a href="#" class="list-group-item">회원탈퇴</a>
                </div>
            </div>

            <div class="missions board">
                <h1>진행중 미션</h1>
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <c:forEach var="mission" items="${missions}">
                            <div class="mission-contents">
                                <a class="mission-info" href="/challenges/info?challenges_id=${mission.challenge.challenges_id}">
                                    <p><c:out value="${mission.challenge.title}"/></p>
                                    <div class="challenge-period">
                                        <p><c:out value="${mission.challenge.start_at}"/></p>
                                        <p>~</p>
                                        <p><c:out value="${mission.challenge.end_at}"/></p>
                                    </div>
                                </a>
                                <a class="btn btn-primary btn-xl" onclick="missionComplete(${mission.mission_id})">미션 완료</a>
                            </div>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>

    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="../js/mypage.js"></script>
</body>
</html>
