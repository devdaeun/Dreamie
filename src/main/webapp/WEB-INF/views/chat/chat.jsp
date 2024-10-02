<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>채팅방</title>

    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/styles.css"/>
    <link rel="stylesheet" href="../css/chat.css"/>
</head>
<body class="text-center" id="page-top">
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
    <div class="container containers">
        <div class="col-6 room_info">
            <label><b>채팅방</b></label>
            <c:forEach var="room" items="${room}">
                <p>${room.room_name}</p>
            </c:forEach>
        </div>
        <div>
            <div id="msgArea" class="col"></div>
            <div>
                <div class="input-group mb-3">
                    <input type="text" id="msg" class="form-control">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button" id="button-send">전송</button>
                    </div>
                </div>
            </div>
            <div class="col-6"></div>
        </div>
    </div>
<!-- Footer-->
    <footer class="bg-light py-5">
        <div class="container px-4 px-lg-5"><div class="small text-center text-muted">Copyright &copy; 2023 - Company Name</div></div>
    </footer>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script>
        $(document).ready(function(){
            const roomName = "${room.room_name}"
            const roomId = "${room.roomId}"
            const id = "${id}";  // JSP에서 전달된 id 변수

            const socket = new SockJS('/stomp/chat'); // SockJS 엔드포인트
            const stomp = Stomp.over(socket); // STOMP

             //2. connection이 맺어지면 실행
            stomp.connect({}, function (){
               console.log("STOMP Connection")

               //4. subscribe(path, callback)으로 메세지를 받을 수 있음
               stomp.subscribe("/sub/chat/room/" + roomId, function (message) {
                   var content = JSON.parse(message.body);
                   console.log("Received message: ", content);
                   var sender = content.sender;
                   var str = '';
                   if(sender === id){
                       str = "<div class= col-6>";
                       str += "<div class='alert alert-secondary'>";
                       str += "<b>" + sender + " : " + content.message + "</b>";
                       str += "</div></div>";
                   }
                   else{
                       str = "<div class= 'col-6 enters'>";
                       str += "<div class='alert alert-warning'>";
                       str += "<b>" + sender + " : " + content.message + "</b>";
                       str += "</div></div>";
                   }

                   $("#msgArea").append(str);
               });

               //3. send(path, header, message)로 메세지를 보낼 수 있음
               stomp.send('/pub/chat/enter', {}, JSON.stringify({chat_room_id: roomId, sender: id}))
            });

            $("#button-send").on("click", function(e){
                var msg = document.getElementById("msg");
                if (msg.value.trim() === "") {
                    alert("메시지를 입력하세요.");
                    return;
                }
                console.log(id + ":" + msg.value);
                stomp.send('/pub/chat/message', {}, JSON.stringify({chat_room_id: roomId, message: msg.value, sender: id}));
                msg.value = '';
            });
        });
    </script>
</body>
</html>
