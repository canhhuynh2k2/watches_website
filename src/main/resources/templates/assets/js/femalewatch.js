let productList = document.querySelector(".product-list")
var url_string = window.location.href
var url = new URL(url_string);
var gender = url.searchParams.get("gender");
var branch = url.searchParams.get("branch")
function renderProduct(products){
    var res = ""
    for(var i = 0; i < products.length; i++){
        res += `<a href="productdetail.html?id=${products[i].id}" class = "product-item-wrap">
                    <div class="product-item">
                        <div class="product-img">
                            <img src="${products[i].thumbnail}" alt="" class = "">
                        </div>
                        <h2>${products[i].title}</h2>
                        <p><strong>Giá gốc: </strong><del>${products[i].price} VNĐ</del></p>
                        <p><strong>Giá bán: </strong>${products[i].discount} VNĐ</p>
                    </div>
                </a>`
    }
    return res
}
async function getProduct(){
    
    var urlAPI = `http://localhost:8090/api/product/get/female`
    
    const productFetchAPI = await fetch(urlAPI, {
        method : "GET",
        headers:{
            "Content-type": "application/json; charset=UTF-8"
        }
    })

    const products = await productFetchAPI.json()
    console.log(products)
    let headingProduct = document.querySelector(".heading-product")
    headingProduct.textContent = "Đồng hồ nữ"
    let numberSearchResult = document.querySelector(".number-search-result")
    numberSearchResult.textContent = `Sản phẩm: ${products.length} sản phẩm`
    productList.innerHTML = renderProduct(products)
}

window.onload = async function(){
    await getProduct()
}
