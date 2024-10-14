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
    <link rel="stylesheet" href="../css/modify.css"/>
</head>
<body class="text-center" id="page-top">
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
                <a href="/mypage/grape" class="list-group-item">포도알 스티커</a>
            </div>
            <div class="list-group list-group-flush">
                <label>고객센터</label>
                <a href="/notice" class="list-group-item">공지사항</a>
                <a href="/qna" class="list-group-item">문의사항</a>
            </div>
            <div class="list-group list-group-flush">
                <label>설정</label>
                <a href="/modifyForm" class="list-group-item">프로필 수정</a>
                <a href="/logout" class="list-group-item">로그아웃</a>
                <%--                    <a href="#" class="list-group-item" onclick="drop_check(event, '/admin/delMember.do?id=${User.user_id}')" >회원탈퇴</a>--%>
                <button  class="list-group-item" onclick="confirmDeactivation(${user.user_id})">회원탈퇴</button>

            </div>
        </div>

        <div class="modifyForm">
            <div class="row justify-content-center">
                <div class="updateForm col-lg-6">
                    <h1 class="text-center mt-5">회원정보 수정</h1>
                    <form action="/modify" method="POST" onsubmit="return validateForm()">
                        <!-- 아이디는 수정 불가 -->
                        <div class="input-form-box">
                            <div class="inputForm">
                                <span>아이디 </span>
                                <input type="text" id="id" name="id" class="form-control" value="${user.id}" readonly>
                            </div>
                        </div>

                        <!-- 비밀번호 수정 가능 -->
                        <div class="input-form-box">
                            <div class="inputForm">
                                <span>비밀번호 </span>
                                <input type="password" name="password" class="form-control" placeholder="비밀번호" value="${user.password}" id="password">
                            </div>
                        </div>

                        <!-- 비밀번호 확인 필드 -->
                        <div class="input-form-box">
                            <div class="inputForm">
                                <span>비밀번호 확인 </span>
                                <input type="password" name="confirmPassword" class="form-control" placeholder="비밀번호 확인" id="confirmPassword">
                                <button type="button" class="btn btn-primary" onclick="checkPassword()">확인</button> <!-- 비밀번호 확인 버튼 -->
                            </div>
                            <div id="confirmPasswordErrorMessage" class="error-message"></div>
                        </div>

                        <!-- 이메일 수정 가능 -->
                        <div class="input-form-box">
                            <div class="inputForm">
                                <span>이메일 </span>
                                <input type="email" id="email" name="email" class="form-control" placeholder="이메일" value="${user.email}">
                                <input type="hidden" id="currentEmail" value="${user.email}">
                                <button type="button" class="btn btn-primary" onclick="email_check()">중복 확인</button>
                            </div>
                            <div id="emailMessage" class="error-message"></div>
                        </div>

                        <!-- 이름 수정 가능 -->
                        <div class="input-form-box">
                            <div class="inputForm">
                                <span>이름 </span>
                                <input type="text" id="name" name="name" class="form-control" placeholder="이름" value="${user.name}">
                            </div>
                        </div>
                        <div class="input-form-box">
                            <div class="inputForm">
                                <span>직업 </span>
                                <select id="work" name="work" class="form-control" value="${user.work}">
                                    <option value="기획·전략" ${user.work == '기획·전략' ? 'selected' : ''}>기획·전략</option>
                                    <option value="마케팅·홍보·조사" ${user.work == '마케팅·홍보·조사' ? 'selected' : ''}>마케팅·홍보·조사</option>
                                    <option value="회계·세무·재무" ${user.work == '회계·세무·재무' ? 'selected' : ''}>회계·세무·재무</option>
                                    <option value="인사·노무·HRD" ${user.work == '인사·노무·HRD' ? 'selected' : ''}>인사·노무·HRD</option>
                                    <option value="총무·법무·사무" ${user.work == '총무·법무·사무' ? 'selected' : ''}>총무·법무·사무</option>
                                    <option value="IT개발·데이터" ${user.work == 'IT개발·데이터' ? 'selected' : ''}>IT개발·데이터</option>
                                    <option value="디자인" ${user.work == '디자인' ? 'selected' : ''}>디자인</option>
                                    <option value="서비스" ${user.work == '서비스' ? 'selected' : ''}>서비스</option>
                                    <option value="건설·건축" ${user.work == '건설·건축' ? 'selected' : ''}>건설·건축</option>
                                    <option value="의료" ${user.work == '의료' ? 'selected' : ''}>의료</option>
                                    <option value="교육" ${user.work == '교육' ? 'selected' : ''}>교육</option>
                                    <option value="미디어·문화·스포츠" ${user.work == '미디어·문화·스포츠' ? 'selected' : ''}>미디어·문화·스포츠</option>
                                    <option value="금융·보험" ${user.work == '금융·보험' ? 'selected' : ''}>금융·보험</option>
                                    <option value="공공·복지" ${user.work == '공공·복지' ? 'selected' : ''}>공공·복지</option>
                                    <option value="학생" ${user.work == '학생' ? 'selected' : ''}>학생</option>
                                    <option value="무직" ${user.work == '무직' ? 'selected' : ''}>무직</option>
                                    <option value="기타" ${user.work == '기타' ? 'selected' : ''}>기타</option>
                                </select>
                            </div>
                        </div>

                        <div class="button-login-box mt-4">
                            <button type="submit" class="btn btn-primary btn-block">수정하기</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>
<footer class="bg-light py-5">
    <div class="container px-4 px-lg-5"><div class="small text-center text-muted">Copyright &copy; 2023 - Company Name</div></div>
</footer>
<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="../js/mypage.js"></script>
<script src="../js/memberJoin.js"></script>
</body>
</html>
