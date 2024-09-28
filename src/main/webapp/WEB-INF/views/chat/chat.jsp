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
</head>
<body>

<div class="container">
    <div class="col-6">
        <label><b>채팅방</b></label>
        <c:forEach var="room" items="${room}">
            <p>${room.room_name}</p>
        </c:forEach>
    </div>
    <div>
        <div id="msgArea" class="col"></div>
        <div class="col-6">
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
                   str = "<div class='col-6'>";
                   str += "<div class='alert alert-secondary'>";
                   str += "<b>" + sender + " : " + content.message + "</b>";
                   str += "</div></div>";
               }
               else{
                   str = "<div class='col-6'>";
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
            stomp.send('/pub/chat/message', {}, JSON.stringify({roomId: roomId, message: msg.value, sender: id}));
            msg.value = '';
        });
    });
</script>
</body>
</html>
