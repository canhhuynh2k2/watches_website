
function gender(n){
    return n ? `Nam` : `Nữ`
}
function isStatus(n){
    if (n === undefined){
        return "N/A"
    }
    return n ? 'New 100%' : `Đã qua sử dụng`
}
function renderDetail(product, detail){
    return `<tr>
    <td><strong>Mã sản phẩm</strong></td>
    <td>${product.code}</td>
    </tr>
    <tr>
        <td><strong>Thương hiệu</strong></td>
        <td>${product.categoryOutputDto.name}</td>
    </tr>
    <tr>
        <td><strong>Giới tính</strong></td>
        <td>${gender(product.gender)}</td>
    </tr>
    <tr>
        <td><strong>Tình trạng</strong></td>
        <td>${isStatus(detail.status)}</td>
    </tr>
    <tr>
        <td><strong>Xuất xứ</strong></td>
        <td>${detail.origin}</td>
    </tr>
    <tr>
        <td><strong>Bộ sưu tập</strong></td>
        <td>${detail.collection}</td>
    </tr>
    <tr>
        <td><strong>Kích thước</strong></td>
        <td>${detail.size}</td>
    </tr>
    <tr>
        <td><strong>Phong cách</strong></td>
        <td>${detail.style}</td>
    </tr>
    <tr>
        <td><strong>Bộ máy</strong></td>
        <td>${detail.machineType}</td>
    </tr>
    <tr>
        <td><strong>Mặt đồng hồ</strong></td>
        <td>${detail.dial}</td>
    </tr>
    <tr>
        <td><strong>Chất liệu mặt</strong></td>
        <td>${detail.glassMaterial}</td>
    </tr>
    <tr>
        <td><strong>Chất liệu vỏ</strong></td>
        <td>${detail.caseMaterial}</td>
    </tr>
    <tr>
        <td><strong>Dây đeo</strong></td>
        <td>${detail.strapMaterial}</td>
    </tr>
    <tr>
        <td><strong>Hình dáng</strong></td>
        <td>${detail.shape}</td>
    </tr>
    <tr>
        <td><strong>Độ dày</strong></td>
        <td>${detail.thickness}</td>
    </tr>
    <tr>
        <td><strong>Kháng nước</strong></td>
        <td>${detail.waterResistance}</td>
    </tr>
    <tr>
        <td><strong>Bezel</strong></td>
        <td>${detail.benzel}</td>
    </tr>
    <tr>
        <td><strong>Dự trữ năng lượng</strong></td>
        <td>${detail.energyStorage}</td>
    </tr>
    <tr>
        <td><strong>Cân nặng</strong></td>
        <td>${detail.weight}</td>
    </tr>
    <tr>
        <td><strong>Chức năng</strong></td>
        <td>${detail.feature}</td>
    </tr>
    <tr>
        <td><strong>Bảo hành trong nước</strong></td>
        <td>${detail.domesticWarranty}</td>
    </tr>
    <tr>
        <td><strong>Bảo hành quốc tế</strong></td>
        <td>${detail.internationalWarranty}</td>
    </tr>`
}
async function load_product_detail(){

    var url_string = window.location.href
    var url = new URL(url_string);
    var id = url.searchParams.get("id");
    console.log(id)
    const productFetchAPI = await fetch(`http://localhost:8090/api/product/get/${id}`, {
    headers:{
        authorization: `Bearer ${localStorage.getItem("token")}`
    }
    });
    var product = await productFetchAPI.json()
    
    const detailFetchAPI = await fetch(`http://localhost:8090/api/product/detail/get/${id}`, {
    headers:{
        authorization: `Bearer ${localStorage.getItem("token")}`
    }
    });
    var detail = await detailFetchAPI.json()
    
    var productTitle = document.querySelector(".product-title")
    var originPrice = document.querySelector(".origin-price")
    var salePrice = document.querySelector(".sale-price")
    var productImage = document.querySelector("#product-img")
    var maxQuantity = document.querySelector("#max-quantity")

    productTitle.innerText = product.title
    originPrice.innerText = `${product.price} VNĐ` 
    salePrice.innerText = product.discount
    productImage.setAttribute("src", product.thumbnail)
    maxQuantity.innerText = product.quantity
    var detailElement = document.querySelector("#detail-table")
    
    detailElement.innerHTML = renderDetail(product, detail)
  
}

load_product_detail()




// xử lý nút +, - số lượng
var quantity = document.querySelector(".input-qty");
var plus_btn = document.querySelector(".plus");
var minus_btn = document.querySelector(".minus");
var max_quantity = document.querySelector("#max-quantity");
let add_to_cart = document.querySelector(".add-to-card-btn");


plus_btn.addEventListener("click", function(){
    if(quantity.value < parseInt(max_quantity.textContent)){
        quantity.value = parseInt(quantity.value) + 1;
        var hrefLinkAddToCart = add_to_cart.getAttribute("href").split("&");
        var hrefAddToCart = hrefLinkAddToCart[0] + "&quantity=" + quantity.value
        add_to_cart.setAttribute("href", hrefAddToCart);

    }
});
minus_btn.addEventListener("click", function(){
    if(quantity.value > 1){
        quantity.value = parseInt(quantity.value) - 1;
        var hrefLinkAddToCart = add_to_cart.getAttribute("href").split("&");
        var hrefAddToCart = hrefLinkAddToCart[0] + "&quantity=" + quantity.value
        add_to_cart.setAttribute("href", hrefAddToCart);
		
    }
});

// Thêm sản phẩm vào giỏ hàng

function addToCartBody(productId, q){
    return {
        "productId" : productId,
        "quantity" : q
    }
}
add_to_cart.addEventListener("click", async function(e){
    e.preventDefault()
    var url_string = window.location.href
    var url = new URL(url_string);
    var id = url.searchParams.get("id");
    const addToCartFetchAPI = await fetch("http://localhost:8090/api/cart/add", {
        method : "POST",
        body : JSON.stringify(addToCartBody(id, quantity.value)),
        headers:{
            authorization: `${localStorage.getItem("token")}`,
            "Content-type": "application/json; charset=UTF-8"
        }
    })
    const addToCartAPI = await addToCartFetchAPI.json()
    
    if(addToCartAPI.success != false){
        alert("Thêm sản phẩm vào giỏ hàng thành công!")
        loadCart()
    }
})
