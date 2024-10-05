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
    <link rel="stylesheet" href="css/memberForm.css" />

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

        <div class="step">
            <div class="step1">
                <p>1. 약관 동의</p>
            </div>
            <div class="step2">
                <p>2. 회원 가입</p>
            </div>
        </div>

        <!--  회원가입 폼 영역을 : signUpBox -->
        <div id="signUpBox">
            <!-- 입력 폼 박스 -->
            <div id="inputBox">
                <form action="/addmember" method="POST" onsubmit="return validateForm()">
                    <div class="input-form-box">
                        <div class="inputForm">
                            <span>아이디 </span>
                            <input type="text" id="id" name="id" class="form-control" placeholder="아이디">
                            <button type="button" class="btn btn-primary" onclick="check_id()">중복 확인</button>
                        </div>
                        <div id="idMessage" class="error-message"></div>
                    </div>

                    <div class="input-form-box">
                        <div class="inputForm">
                            <span>비밀번호 </span>
                            <input type="password" name="password" class="form-control" placeholder="비밀번호" id="password">
                        </div>
                        <div id="passwordErrorMessage" class="error-message"></div>
                    </div>

                    <div class="input-form-box">
                        <div class="inputForm">
                            <span>비밀번호 확인 </span>
                            <input type="password" name="confirmPassword" class="form-control" placeholder="비밀번호 확인" id="confirmPassword">
                            <button type="button" class="btn btn-primary" onclick="checkPassword()">비밀번호 확인</button> <!-- 비밀번호 확인 버튼 -->
                        </div>
                        <div id="confirmPasswordErrorMessage" class="error-message"></div>
                    </div>

                    <div class="input-form-box">
                        <div class="inputForm">
                            <span>이메일 </span>
                            <input type="email" id="email" name="email" class="form-control" placeholder="이메일">
                            <button type="button" class="btn btn-primary" onclick="check_email()">중복 확인</button>
                        </div>
                        <div id="emailMessage" class="error-message"></div>
                    </div>
                    <div class="input-form-box">
                        <div class="inputForm">
                            <span>이름 </span>
                            <input type="text" id="name" name="name" class="form-control" placeholder="이름">
                        </div>
                        <div id="nameErrorMessage" class="error-message"></div>
                    </div>
                    <div class="input-form-box">
                        <div class="inputForm">
                            <span>직업 </span>
                            <div class="dropdown">
                                <button class="workBtn btn btn-outline-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                                    직업을 선택하세요
                                </button>
                                <ul class="dropdown-menu custom-dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <li><a class="dropdown-item custom-dropdown-item" href="#" onclick="selectWork('기획·전략')">기획·전략</a></li>
                                    <li><a class="dropdown-item custom-dropdown-item" href="#" onclick="selectWork('마케팅·홍보·조사')">마케팅·홍보·조사</a></li>
                                    <li><a class="dropdown-item custom-dropdown-item" href="#" onclick="selectWork('회계·세무·재무')">회계·세무·재무</a></li>
                                    <li><a class="dropdown-item custom-dropdown-item" href="#" onclick="selectWork('인사·노무·HRD')">인사·노무·HRD</a></li>
                                    <li><a class="dropdown-item custom-dropdown-item" href="#" onclick="selectWork('총무·법무·사무')">총무·법무·사무</a></li>
                                    <li><a class="dropdown-item custom-dropdown-item" href="#" onclick="selectWork('IT개발·데이터')">IT개발·데이터</a></li>
                                    <li><a class="dropdown-item custom-dropdown-item" href="#" onclick="selectWork('디자인')">디자인</a></li>
                                    <li><a class="dropdown-item custom-dropdown-item" href="#" onclick="selectWork('서비스')">서비스</a></li>
                                    <li><a class="dropdown-item custom-dropdown-item" href="#" onclick="selectWork('건설·건축')">건설·건축</a></li>
                                    <li><a class="dropdown-item custom-dropdown-item" href="#" onclick="selectWork('의료')">의료</a></li>
                                    <li><a class="dropdown-item custom-dropdown-item" href="#" onclick="selectWork('교육')">교육</a></li>
                                    <li><a class="dropdown-item custom-dropdown-item" href="#" onclick="selectWork('미디어·문화·스포츠')">미디어·문화·스포츠</a></li>
                                    <li><a class="dropdown-item custom-dropdown-item" href="#" onclick="selectWork('금융·보험')">금융·보험</a></li>
                                    <li><a class="dropdown-item custom-dropdown-item" href="#" onclick="selectWork('공공·복지')">공공·복지</a></li>
                                </ul>
                            </div>
                            <input type="hidden" id="work" name="work" class="form-control">
                        </div>
                        <div id="workErrorMessage" class="error-message"></div>
<%--                        /input type="text" name="work" class="form-control" placeholder="직업">--%>
                    </div>
                    <div class="button-login-box">
                        <button type="submit" class="btn btn-primary btn-xs">회원가입</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- 회원가입 성공 시 알림 -->
    <%
        // 회원가입 성공 여부를 파라미터로 전달받음
        String success = request.getParameter("success");
        if ("true".equals(success)) {
    %>
    <script>
        // 회원가입 성공 시 팝업 알림
        alert("회원가입이 완료되었습니다!");
        // 로그인 페이지로 리다이렉트
        window.location.href = "loginForm";
    </script>
    <%
        }
    %>

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

    <script src="../js/memberJoin.js"></script>
</body>
</html>
