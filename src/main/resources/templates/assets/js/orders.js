
function renderOrder(orders){
    var res = ""
    console.log(orders)
    for(var i = 0; i < orders.length; i++){
        var priceTotal = 0;
        
        for(var j = 0; j < orders[i].listOrderDetails.length; j++){
            priceTotal += orders[i].listOrderDetails[j].discount
        }
        var status = ""
        if(orders[i].status == 1){
            status = "Đang chờ xác nhận"
        }
        else if(orders[i].status == -1){
            status = "Đã hủy"
        }
        else if(orders[i].status == 2){
            status = "Đã xác nhận"
        }
        else if(orders[i].status == 3){
            status = "Đã giao"
        }
        var cancelBtn = "";
        if(orders[i].status != 1){
            cancelBtn = "invalid"
        }
        res += `<tr class = "tbody">
            <td class = "tbody__order-id"><a href="orderdetail.html?id=${orders[i].id}">${orders[i].id}</a></td>
            <td class = "tbody__customer-name">${orders[i].fullname}</td>
            <td class = "tbody__phonenumber">${orders[i].phoneNumber}</td>
            <td class = "tbody__address">${orders[i].address}</td>
            
            <td class = "tbody__total-money">${priceTotal}</td>
            <td class ="tbody__order-date">${orders[i].orderDate}</td>
            <td class = "tbody__note">${orders[i].note}</td>
            <td class ="tbody__status">
                <p>${status}</p>
                <form action ="orders" method ="POST">
                    <input type="text" name ="orderid" value="<%= order.getId()%>" hidden>
                    <input type="submit" class = "cancel-btn ${cancelBtn}" onclick = "cancelOrder(${orders[i].id})" value = "Hủy đơn hàng">
                </form>
        
            </td>
            </tr>`
    }
    return res;
}
async function getOrders(){
    const orderFetchAPI = await fetch("http://localhost:8090/api/order/getall", {
        method : "GET",
        headers:{
            authorization: `${localStorage.getItem("token")}`,
            "Content-type": "application/json; charset=UTF-8"
        }
    });
    const orders = await orderFetchAPI.json()
    localStorage.setItem("orders", orders)
    console.log(orders)
    let listOrders = document.querySelector(".list-orders")
    console.log(listOrders)
    listOrders.innerHTML = renderOrder(orders)
    
}

window.onload = async function(){
    await getOrders()
}

async function cancelOrder(id){
    const cancelOrderFetch = await fetch(`http://localhost:8090/api/update/orderstatus/${id}/-1`, {
        method : "PUT",
        headers:{
            authorization: `${localStorage.getItem("token")}`,
            "Content-type": "application/json; charset=UTF-8"
        }
    });
    window.location.href = "orders.html"
}