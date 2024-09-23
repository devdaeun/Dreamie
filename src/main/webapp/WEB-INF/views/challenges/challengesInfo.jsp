<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>미션상세</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/challenges.css"/>
    <link href="../css/styles.css" rel="stylesheet"/>
</head>
<body class="text-center">
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
        <form id="challenge_modify_form" >
            <input type="hidden" name="challenges_id" id="challenges_id" value="${challenges.challenges_id}">
            <input type="text" name="title" value="${challenges.title}" id="title" readonly>
            <textarea type="text" name="content" max-length="200" id="content" readonly>${challenges.content}</textarea>
            <input type="date" name="start_at" value="${challenges.start_at}" id="start_at" readonly>
            <input type="date" name="end_at" value="${challenges.end_at}" id="end_at" readonly>
            <div id="modify_btn">
                <input type="button" value="수정반영하기" onclick="modify_challenge()">
                <input type="button" value="취소" onclick="cancle_btn()">
            </div>
            <div id="motion_btn">
                <input type="button" value="수정하기" onclick="challenge_btn()">
                <input type="button" value="삭제하기" onclick="delete_challenge(${challenges.challenges_id})">
            </div>
        </form>
        <a href="/challenges">리스트로 돌아가기</a>
    </div>
    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="../js/challenges.js"></script>
</body>
</html>
