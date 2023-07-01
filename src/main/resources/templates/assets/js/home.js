

// slide
window.addEventListener("load", function(){
    var sliderWrapper = document.querySelector(".slider-wrapper");
    var sliderMain = document.querySelector(".slider-main");
    var sliderItems = document.querySelectorAll(".slider-item");
    var nextBtn = document.querySelector(".next-btn");
    var prevBtn = document.querySelector(".prev-btn");
    var dotItems = document.querySelectorAll(".slider-dot-item");
    var sliderItemWidth = sliderItems[0].offsetWidth;
    var positionX = 0;
    let index = 0;
    nextBtn.addEventListener("click", function(){
      handleChangeSlide(1);
    });
  
    prevBtn.addEventListener("click", function(){
      handleChangeSlide(-1);
      
    });
    [...dotItems].forEach((item) => 
    item.addEventListener("click", function(e){
      
      [...dotItems].forEach((el) => el.classList.remove("active"));
      e.target.classList.add("active");
      const slideIndex = parseInt(e.target.dataset.index);
      index = slideIndex;
      positionX = -1 * index * sliderItemWidth;
      sliderMain.style = `transform: translateX(${positionX}px)`;
    }));
    function handleChangeSlide(direction){
      if(direction === 1){
        if(index >= sliderItems.length - 1){
          index = sliderItems.length - 1;
          return;
        }
        positionX -= sliderItemWidth;
        sliderMain.style = `transform: translateX(${positionX}px)`;
        index++;
      }
      else if(direction === -1){
        if(index <= 0){
          index = 0;
          return;
        }
        positionX += sliderItemWidth;
        sliderMain.style = `transform: translateX(${positionX}px)`;
        index--;
      }
      
      [...dotItems].forEach((el) => el.classList.remove("active"));
      dotItems[index].classList.add("active");
    }
  })


  // hiện thị sản phẩm




function product_item(id, title, price, discount, url){
  return `<a class="product-item" href = "productdetail.html?id=${id}">
        <div class="product-img">
            <img src="${url}" alt="">
        </div>
        <h2>${title}</h2>
        <p><strong>Giá gốc: </strong><del>${price} đ</del></p>
        <p><strong>Giá bán: </strong>${discount} đ</p>
      </a>`
}

const featured_branch_list = document.querySelector('#main')
function list_branch(name, branch, id){
  return `<div class="product-introduction">
    <div class="branch">
        <h1 class="branch-heading">${name}</h1>
        <div class="is-devider"></div>
        <div class="view-all">
            <i class="fa-solid fa-angles-right"></i>
            <a class = "link-to-list-product" href = "product.html?branch=${id}">Xem tất cả </a> 
        </div>
        <div class="list-products ${branch}">
            
            
        </div>
    </div>
  </div>`
}
async function loadProduct() {
  featured_branch_list.innerHTML = "";
  const fetchAPI = await fetch("http://localhost:8090/api/category", {
    headers:{
        authorization: `${localStorage.getItem("token")}`
    }
  });
  var branchs = await fetchAPI.json()
  for(var i = 0; i < Math.min(5, branchs.length); i++){
    featured_branch_list.innerHTML += list_branch(branchs[i].name, "branch_" + branchs[i].id, branchs[i].id);
    var branch_id = ".branch_" + branchs[i].id
    const listProducts = document.querySelector(".list-products"  + branch_id);
    
    await productloading(listProducts, branchs[i].id);
  }

}
async function productloading(listProducts, id){
  listProducts.innerHTML="";
  const products = await fetch("http://localhost:8090/api/product/getbyCategory/" + id, {
    headers:{
        authorization: `Bearer ${localStorage.getItem("token")}`
    }
    }).then((res) => res.json());
    products.map((product,idx) => {
      if (idx < 4){
        listProducts.innerHTML += product_item(
          product.id,
          product.title,
          product.price,
          product.discount,
          product.thumbnail
        );
      }
    });
}

loadProduct()

