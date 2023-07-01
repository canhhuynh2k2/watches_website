var customer_name = document.getElementById("customer-name")
var customer_email = document.querySelector("#customer-email")
var customer_phonenumber = document.querySelector("#customer-phonenumber")
var customer_address = document.querySelector("#customer-address")

customer_name.addEventListener("blur", handleNameBlur)
customer_phonenumber.addEventListener("blur", handlePhoneNumberBlur)
customer_email.addEventListener("blur", handleEmailBlur)
customer_address.addEventListener("blur", handleAddressBlur)
customer_name.addEventListener("input", handleNameInput)
customer_email.addEventListener("input", handleEmailInput)
customer_phonenumber.addEventListener("input", handlePhoneNumberInput)
customer_address.addEventListener("input", handleAddressInput)

 function handleNameBlur(){
    var form_message = customer_name.parentElement.querySelector('.form-message')
    if(customer_name.value.trim() == ''){       
        form_message.textContent = "Vui lòng nhập tên đầy đủ của bạn"
    }
    else{
        form_message.textContent = ''
    }
}

function handlePhoneNumberBlur(){
    var form_message = customer_phonenumber.parentElement.querySelector('.form-message')
    if(customer_phonenumber.value.trim() == ''){       
        form_message.textContent = "Vui lòng nhập chính xác số điện thoại của bạn"
    }
    else{
        form_message.textContent = ''
    }
}

function handleEmailBlur(){
    var form_message = customer_email.parentElement.querySelector('.form-message')
    var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if(customer_email.value.trim() == ''){      
        form_message.textContent = "Vui lòng nhập địa chỉ email"
    }
    else if(!regex.test(customer_email.value)){
        form_message.textContent = "Trường này phải là email"
    }
    else{
        form_message.textContent = ''
    }
}

function handleAddressBlur(){
    var form_message = customer_address.parentElement.querySelector('.form-message')
    if(customer_address.value.trim() == ''){       
        form_message.textContent = "Vui lòng nhập địa chỉ của bạn"
    }
    else{
        form_message.textContent = ''
    }
}


function handleNameInput(){
    var form_message = customer_name.parentElement.querySelector('.form-message')
    form_message.textContent = ''
}

function handlePhoneNumberInput(){
    var form_message = customer_phonenumber.parentElement.querySelector('.form-message')
    form_message.textContent = ''
}

function handleAddressInput(){
    var form_message = customer_address.parentElement.querySelector('.form-message')
    form_message.textContent = ''
}
function handleEmailInput(){
    var form_message = customer_email.parentElement.querySelector('.form-message')
    form_message.textContent = ''
}

// Xử lý load cart
let priceTotal = 0
let listItems = document.querySelector(".tbody__cart")
let priceTotalCart = document.querySelector(".tfoot__cart")
function renderItem(cart){
    var res = ""
    for(var i = 0; i < cart.listOrderDetails.length; i++){
        priceTotal += cart.listOrderDetails[i].quantity * cart.listOrderDetails[i].productOutputDto.discount
        res += `<tr class="cart-item">
                    <td class="product-name">
                        ${cart.listOrderDetails[i].productOutputDto.title}
                        <strong class = "product-quantity"> x ${cart.listOrderDetails[i].quantity}</strong>
                    </td>
                    <td class="product-total">
                        <p>${cart.listOrderDetails[i].quantity * cart.listOrderDetails[i].productOutputDto.discount} <span>VNĐ</span></p>
                    </td>
                </tr>`
    }
    return res
}
function renderPriceTotal(){
    priceTotalCart.innerHTML = `<tr class="cart-subtotal">
                                    <th>Tạm tính</th>
                                    <td class = "provisional-price">${priceTotal}<span> VNĐ</span></td>
                                </tr>
                                <tr class="shipping">
                                    <th>Giao hàng</th>
                                    <td>Miễn phí phí vận chuyển</td>
                                </tr>
                                <tr class="order-total">
                                    <th>Tổng</th>
                                    <td><strong><span class = "amount">${priceTotal} </span><span> VNĐ</span></strong></td>
                                </tr>`
}
async function loadCart(){
    var urlAPI = "http://localhost:8090/api/cart/getall"
    let cartFetchAPI = await fetch(urlAPI, {
        method : "GET",
        headers:{
            authorization: `${localStorage.getItem("token")}`
        }
    });

    let cart = await cartFetchAPI.json()
    listItems.innerHTML = renderItem(cart)
    renderPriceTotal()

}
loadCart()

// checkout

var order = null
function paymentBody(name, address, phone, mail, note){
    return {
        "fullname" : name,
        "email" : mail,
        "phoneNumber" : phone,
        "address" : address,
        "note" : note
    }
}
async function payment(){
    let customerName = document.querySelector("#customer-name").value
    let customerAddress = document.querySelector("#customer-address").value
    let customerPhonenumber = document.querySelector("#customer-phonenumber").value
    let customerEmail = document.querySelector("#customer-email").value
    let orderNote = document.querySelector("#order-comment").value
    
    var urlAPI = "http://localhost:8090/api/checkout"
    const paymentFetchAPI = await fetch(urlAPI, {
        method : "POST",
        body : JSON.stringify(paymentBody(customerName, customerAddress, customerPhonenumber, customerEmail, orderNote)),
        headers:{
            "Content-type": "application/json; charset=UTF-8",
            authorization: `${localStorage.getItem("token")}`
        }
    });
    const payment = await paymentFetchAPI.json()
    return payment

}
let payBtn = document.querySelector("#pay-btn")
let checkoutForm = document.querySelector("#checkout-form")
payBtn.addEventListener("click", async function(e){
    e.preventDefault()
    var neworder = await payment()
    console.log(neworder)
    if(neworder.id){
        checkoutForm.submit()
        localStorage.setItem("neworder", neworder)
        window.location.href = "checkoutsuccess.html"
    }
    
})