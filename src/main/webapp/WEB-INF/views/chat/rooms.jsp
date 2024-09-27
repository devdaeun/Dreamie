<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>채팅방</title>

    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<div class="container">
    <h3>채팅방 리스트 페이지입니다!</h3>
    <div class="col-6">
        <c:forEach var="room" items="${list}">
            <a>
                <p>${room.roomId}</p>
                <p>${room.room_name}</p>
            </a>
        </c:forEach>
    </div>
    <form action="/chat/createRoom" method="post">
        <input type="text" name="room_name" class="form-control">
        <button class="btn btn-secondary">개설하기</button>
    </form>
</div>

</body>
</html>
