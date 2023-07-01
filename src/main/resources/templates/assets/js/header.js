// header

async function loadBranch(){
    const fetchAPI = await fetch("http://localhost:8090/api/category", {
      headers:{
          authorization: `${localStorage.getItem("token")}`
      }
    });
    var branchs = await fetchAPI.json()
    let branch = document.querySelector('.header__brand-list')
    branch.innerHTML = ""
    for(var i = 0; i < branchs.length; i++){
      branch.innerHTML += `<li><a href="product.html?branch=${branchs[i].id}" class="brand-item">`+ branchs[i].name + '</a></li>'
    }
  }
  
  function name_user(name){
    return `<strong>${name}</strong>`
  }
  async function loadUser(){
    let logined = window.localStorage.getItem("login")
    let login_register_option  = document.querySelector(".fa-user")
    let avatar_user = document.querySelector(".avatar-user")
    if(logined){
      const userAPI = await fetch("http://localhost:8090/user", {
        headers:{
          authorization: `${localStorage.getItem("token")}`
        }
      });
      let user = await userAPI.json()
  
      let avatar = document.querySelector(".avatar-user")
   
      if(user.avatar){
        avatar.setAttribute("src", user.avatar)
      }
      else{
        avatar.setAttribute("src", "assets\\images\\avatar\\default.jpg")
      }
      let nameElement = document.querySelector("#name-user")
      nameElement.innerHTML = name_user(user.fullname)
      avatar_user.classList.remove("invalid")
      login_register_option.classList.add("invalid")
      
    }
    else{
      login_register_option.classList.remove("invalid")
      avatar_user.classList.add("invalid")
    }
  }
  
  let logout = document.querySelector("#logout")
  logout.addEventListener("click", () =>{
    window.localStorage.removeItem("login")
    window.localStorage.removeItem("token")
  })

loadBranch()
loadUser()

// load giỏ hàng
async function loadCart(){
  const cartFetchAPI = await fetch(`http://localhost:8090/api/cart/getall`, {
    method : "GET",
    headers:{
      authorization: `${localStorage.getItem("token")}`,
      "Content-type": "application/json; charset=UTF-8"
    }
  });
  const cartAPI = await cartFetchAPI.json()
  var cart_count = document.querySelector("#cart-count")
  cart_count.innerText = cartAPI.listOrderDetails.length
}
loadCart()

let cartIcon = document.querySelector(".cart")
cartIcon.addEventListener("click", function(){
  window.location.href = "cart.html"
})

let searchSubmit = document.querySelector(".search-bar__search-btn")
searchSubmit.addEventListener("click", function(){
  let contentSearch = document.querySelector(".search-bar__content").value 
  window.location.href = `product.html?search=${contentSearch}`
})