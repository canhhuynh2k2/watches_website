

async function loadBranch(){
    const fetchAPI = await fetch("http://localhost:8090/api/category", {
      headers:{
          authorization: `${localStorage.getItem("token")}`
      }
    });
    var branchs = await fetchAPI.json()
    let branch = document.querySelector('.footer__brand-list')
    branch.innerHTML = ""
    for(var i = 0; i < branchs.length; i++){
      branch.innerHTML += `<li><a href="product.html?branch=${branchs[i].id}">`+ branchs[i].name + '</a></li>'
    }
  }
  
loadBranch()
