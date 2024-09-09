<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 작성 폼</title>
    <!-- Bootstrap CSS -->
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">--%>
<%--    --%>
    <!-- 나의 스타일 추가 -->
    <link rel="stylesheet" href="css/notice.css?v=1234">
</head>
<body class="text-center">
    <div class="notice-form">
        <div class="notice-title">
            <h1>공지사항 작성하기</h1>
            <form action="/notices" method="post">
                <label for="title">제목:</label>
                <input type="text" id="title" name="title" required />
                <br/>
                <label for="content">내용:</label>
                <textarea id="content" name="content" required></textarea>
                <br/>
                <button type="submit" class="btn btn-primary">저장</button>
            </form>
        </div>
        <a href="/notices" class="btn btn-primary">리스트로 돌아가기</a>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>