
document.getElementById("naver-login").onclick = function() {
    const url = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=NU4ciXZlz_FhRZKPNN5g&state=1234&redirect_uri=http://127.0.0.1:8090/naver-login"
    const width = 400
    const height = 500
    window.open(url, "popupWindow", `width=${width}, height=${height}`);
}