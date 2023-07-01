let listItems = document.querySelector(".list-items")
let totalMoney = 0
let currentCart;
let deleteBtns
function renderItem(cart){
    
    var res = ""
    for(var i = 0; i < cart.listOrderDetails.length; i++){
        totalMoney += cart.listOrderDetails[i].productOutputDto.discount * cart.listOrderDetails[i].quantity
        res += `<tr class = "product-item">
                    <td class = "item-id" value = "${cart.listOrderDetails[i].productOutputDto.id}" hidden></td>
                    <td class = "product-title">
                        <i class="fa-sharp fa-solid fa-trash delete-btn" onclick= "deleteCartItem(${cart.listOrderDetails[i].id})"></i>
                        <a class = "product-link" href="productdetail.html?id=${cart.listOrderDetails[i].productOutputDto.id}">
                            <img class = "product-image" src="${cart.listOrderDetails[i].productOutputDto.thumbnail}" alt="">
                            <div class = "product-name"><p >${cart.listOrderDetails[i].productOutputDto.title}</p></div>
                        </a>
                    </td>
                    <td class = "price-product">${cart.listOrderDetails[i].productOutputDto.discount} <span>VNĐ</span></td>
                    <td class = "quantity-product">
                            <a class="minus" href = "#" onclick= "minusQuantity(${cart.listOrderDetails[i].id})">-</a>
                            <input class="input-qty" max="${cart.listOrderDetails[i].productOutputDto.quantity}" min="1" name="" type="number" value="${cart.listOrderDetails[i].quantity}" >
                            <a class="plus" href = "#" onclick= "plusQuantity(${cart.listOrderDetails[i].id})">+</a>
                        </td>
                    <td class = "temporary-price-product">${cart.listOrderDetails[i].productOutputDto.discount * cart.listOrderDetails[i].quantity} <span> VNĐ</span></td>
                </tr>`
    }
    return res
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
    currentCart = cart
    listItems.innerHTML = renderItem(cart)
    let priceTotal = document.querySelector("#price-total")
    priceTotal.innerText = totalMoney

}
loadCart()

async function deleteCartItem(id){
    var urlAPI = "http://localhost:8090/api/cart/" + currentCart.id + "/deleteitem/" + id
    let deleteFetchAPI = await fetch(urlAPI, {
        method : "POST",
        headers:{
            authorization: `${localStorage.getItem("token")}`
        }
    });

    let deleteItem = await deleteFetchAPI.json()
    console.log(deleteItem)
    if(deleteItem.success != null){
        window.location.href = "cart.html"
    }
}


// async function updateQUantity(){
//     url
// }
async function updateQuantity(itemId, quantity){
    var urlAPI = `http://localhost:8090/api/cart/${currentCart.id}/updateitem/${itemId}/${quantity}`
    const updateQuantityFetchAPI = await fetch(urlAPI, {
        method : "PUT",
        headers:{
            authorization: `${localStorage.getItem("token")}`
        }
    });
    const updateQuantity = await updateQuantityFetchAPI.json()
    console.log(updateQuantity)
    if(updateQuantity.success != null){
        window.location.href = "cart.html"
    }
}
function plusQuantity(id){
    for(var i = 0; i < currentCart.listOrderDetails.length; i++){
        if(currentCart.listOrderDetails[i].id == id){
            if(currentCart.listOrderDetails[i].productOutputDto.quantity >= currentCart.listOrderDetails[i].quantity + 1)
                updateQuantity(id, currentCart.listOrderDetails[i].quantity + 1)
            break
        }

    }
}

function minusQuantity(id){
    for(var i = 0; i < currentCart.listOrderDetails.length; i++){
        if(currentCart.listOrderDetails[i].id == id){
            if(0 < currentCart.listOrderDetails[i].quantity - 1)
                updateQuantity(id, currentCart.listOrderDetails[i].quantity - 1)
            break
        }

    }
}