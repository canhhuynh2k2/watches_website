function myBody(email, password){
    return {
        "email" : email,
        "password" : password
    }
}
async function login(){
    let email = document.querySelector("#email")
    let password = document.querySelector("#password")
    const loginApi = await fetch("http://localhost:8090/signin", {
        method : "POST",
        body : JSON.stringify(myBody(email.value, password.value)),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })
    let res = await loginApi.json()
    console.log(res)
    if(res.success === false){
    
        let message = email.parentElement.querySelector(".form-message")
        alert(res.message)
    }
    else{
        window.localStorage.setItem("login", true)
        window.localStorage.setItem("token", res.token)
        window.location.href = "index.html";
    }
}

window.onload = async function checkLog(){
    let isLogin = window.localStorage.getItem("login")
    if(isLogin === "true"){
        window.location.href = "views/index.html";
    }
}
