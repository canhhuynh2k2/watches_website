
function renderDetail(order){
    var res= ""
    for(var i = 0; i < order.listOrderDetails.length; i++){

        res += `<tr class = "tbody">
    <td class = "tbody__orderdetail-id">${order.listOrderDetails[i].id}</td>
    <td class = "tbody__product-name">
        <a class = "product-link" href="productdetail.html?id=${order.listOrderDetails[i].productOutputDto.id}">
            <img class = "product-image" src="${order.listOrderDetails[i].productOutputDto.thumbnail}" alt="">
            <p class = "tbody__product-name">${order.listOrderDetails[i].productOutputDto.title}</p>
        </a>
    </td>
    <td class = "tbody__quantity">${order.listOrderDetails[i].quantity}</td>

    <td class = "tbody__price">${order.listOrderDetails[i].discount}<span> VNĐ</span></td>
    <td class = "tbody__total-price">${order.listOrderDetails[i].discount * order.listOrderDetails[i].quantity}<span> VNĐ</span></td>

</tr>
    `
    }
    let listOrderDetail = document.querySelector(".list-orderdetail") 
    listOrderDetail.innerHTML = res
}
async function getOrderDetail(id){
    const orderFetch  = await fetch( `http://localhost:8090/api/order/${id}`, {
        method : "GET",
        headers:{
            authorization: `${localStorage.getItem("token")}`,
            "Content-type": "application/json; charset=UTF-8"
        }
    });
    const order = await orderFetch.json()
    renderDetail(order)
}
window.onload = async function(e){
    e.preventDefault()
    var url_string = window.location.href
    var url = new URL(url_string);
    var id = url.searchParams.get("id")

    var res = ""
    await getOrderDetail(parseInt(id))
}