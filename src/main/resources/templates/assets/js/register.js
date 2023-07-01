
function myBody(fullname, email, phone, password){
    return {
        "fullname" : fullname,
        "email" : email,
        "phoneNumber" : phone,
        "password" : password
    }
}
async function signUp(){
    let fullname = document.querySelector("#fullname").value
    let email = document.querySelector("#email").value
    let phone = document.querySelector("#phone-number").value
    let password = document.querySelector("#password").value
    const signUpApi = await fetch("http://localhost:8090/signup", {
        method : "POST",
        body : JSON.stringify(myBody(fullname, email, phone, password)),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })
    let res = await signUpApi.json()
    if(res.success == false){
        alert(res.message)
        window.location.href = "register.html";
    }else{
        alert(res.message)
        window.location.href = "login.html";
    }
}

function checkLog(){
    let isLogin = window.localStorage.getItem("login")
    console.log(typeof isLogin)
    if(isLogin === "true"){
        window.location.href = "index.html";
    }
}
checkLog()