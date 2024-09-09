<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>문의사항</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1>문의사항</h1>
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