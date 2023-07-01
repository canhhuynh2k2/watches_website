let submitBtn = document.querySelector("#submit-btn")


function bodyContact(name, email, phone, subject, content){
    return {
        "firstname" : name,
        "lastname" : "",
        "email" : email,
        "phoneNumber" : phone,
        "subjectName" : subject,
        "content"   : content
    }
}

console.log(submitBtn)
submitBtn.addEventListener("click", async function(e){
    e.preventDefault()
    let cname = document.querySelector("#name")
    let cemail = document.querySelector("#email")
    let cphonenumber = document.querySelector("#phonenumber")
    let subjectName = document.querySelector("#subject-name")
    let contentContact = document.querySelector("#content-contact")
    console.log(cemail.value)
    const contactFetchAPI = await fetch("http://localhost:8090/api/contact", {
        method :"POST",
        body: JSON.stringify(bodyContact(cname.value, cemail.value, cphonenumber.value, subjectName.value, contentContact.value)),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    });
    const contact = await contactFetchAPI.json()
    console.log(contact)
    if(contact.success == true){
        alert(contact.message)
        window.location.href = "index.html"
    }
})