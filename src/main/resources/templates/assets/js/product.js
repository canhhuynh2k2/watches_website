let productList = document.querySelector(".product-list")
var url_string = window.location.href
var url = new URL(url_string);
var branch = url.searchParams.get("branch")
var search = url.searchParams.get("search")
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
async function getProduct(option, value = 0){
    var urlAPI
    if(option == -1){
        urlAPI = "http://localhost:8090/api/product"
    }
    else if(option == 1){
        urlAPI =  `http://localhost:8090/api/product/find?key=${value}`
    }
    else if(option == 2){
        urlAPI = `http://localhost:8090/api/product/getbyCategory/${value}`
    }
    const productFetchAPI = await fetch(urlAPI, {
        method : "GET",
        headers:{
            "Content-type": "application/json; charset=UTF-8"
        }
    })

    const products = await productFetchAPI.json()
    if(option == 1){
        let numberSearchResult = document.querySelector(".number-search-result")
        numberSearchResult.textContent = `Có ${products.length} kết quả tìm kiếm phù hợp với từ khóa "${value}"`
    }
    console.log(products)
    productList.innerHTML = renderProduct(products)
}

async function getBranchById(id){
    const branchFetchAPI = await fetch(`http://localhost:8090/api/category/get/${id}`, {
        method : "GET",
        headers:{
            "Content-type": "application/json; charset=UTF-8"
        }
    });
    const branch = await branchFetchAPI.json()
    return branch.name
}

window.onload = async function(){
    let headingProduct = document.querySelector(".heading-product")
    
    if(search != null){
        headingProduct.textContent = "TÌM KIẾM"
        await getProduct(1, search)
    }
    else if(branch != null && branch != ""){
        headingProduct.textContent = `${await getBranchById(parseInt(branch))}`
        await getProduct(2, branch)
    }
    else{
        headingProduct.textContent = "TÂT CẢ SẢN PHẨM"
        await getProduct(-1)
    }
}