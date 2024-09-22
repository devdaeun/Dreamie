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

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<div class="container">
    <div class="col-6">
        <label><b>채팅방</b></label>
    </div>
    <div>
        <div id="msgArea" class="col"></div>
        <div class="col-6">
            <div class="input-group mb-3">
                <input type="text" id="msg" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2">
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" id="button-send">전송</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function(){
        const id = "${id}";  // JSP에서 전달된 id 변수
        console.log("JSP에서 받은 ID:", id);  // ID 값이 출력되는지 확인

        $("#button-send").on("click", function(e) {
            send();
        });

        const websocket = new WebSocket("ws://localhost:8090/ws/chat");

        websocket.onmessage = onMessage;
        websocket.onopen = onOpen;
        websocket.onclose = onClose;

        function send() {
            let msg = document.getElementById("msg").value;
            if (msg.trim() !== "") {
                let formattedMessage = id + ":" + msg;
                console.log("Sending message:", formattedMessage);
                websocket.send(formattedMessage);
                document.getElementById("msg").value = '';
            }
        }

        function onClose(evt) {
            var str = id + ": 님이 방을 나가셨습니다.";
            console.log("Sending close message:", str);
            websocket.send(str);
        }

        function onOpen(evt) {
            var str = id + ": 님이 입장하셨습니다.";
            console.log("Sending open message:", str);
            websocket.send(str);
        }

        function onMessage(msg) {
            var data = msg.data;
            console.log("Received message:", data);
            var arr = data.split(":", 2); // ":"로 데이터를 나누되, 최대 2개 요소로 분리

            if (arr.length === 2) {
                var sessionId = arr[0];
                var message = arr[1];

                var str;
                if (sessionId === id) {
                    str = "<div class='col-6'>";
                    str += "<div class='alert alert-secondary'>";
                    str += "<b>" + sessionId + " : " + message + "</b>";
                    str += "</div></div>";
                } else {
                    str = "<div class='col-6'>";
                    str += "<div class='alert alert-warning'>";
                    str += "<b>" + sessionId + " : " + message + "</b>";
                    str += "</div></div>";
                }
                $("#msgArea").append(str);
            } else {
                console.error("Received malformed message:", data);
            }
        }
    });
</script>


</body>
</html>
