<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- 나의 스타일 추가 -->
    <link rel="stylesheet" href="css/notice.css?v=1234">

</head>
<body>
<a href="/"><h1 class="page-name">Dreamie</h1></a>
    <!-- 서브 메뉴-->
    <div class="submenu-wrap">
        <section id="submenu">
            <a class="contact">
                <h2 class="active-category" >공지사항</h2>
            </a>
            <a class="contact">
                <h2>문의사항</h2>
            </a>
            <a class="contact" >
                <h2>커뮤니티</h2>
            </a>
            <a class="contact" >
                <h2>1:1매칭</h2>
            </a>
        </section>
    </div>

    <div class="container-fluid">
        <div class="row">
            <!-- 좌측 사이드바 -->
            <div class="sidebar">
                <div class="catagory">카테고리</div>
                <p>문의</p>
                <p>문의</p>
                <p>문의</p>
            </div>

            <!-- 공지사항 목록 -->
            <div class="content">
                <h1>공지사항 목록</h1>
                <table class="table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Content</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="notice" items="${notices}">
                        <tr>
                            <td>${notice.id}</td>
                            <td>${notice.title}</td>
                            <td>${notice.content}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="/notices/form" class="btn btn-primary">공지사항 작성하기</a>
            </div>
        </div>
    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>