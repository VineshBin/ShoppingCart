let cartValue = document.getElementById("cartData");
let quantity = 0;
let productArray = [];
function LoadData() {
  cartValue.innerText = productArray.length;
}

function Products() {
  fetch("./product.json")
    .then(function (responseData) {
      return responseData.json();
    })
    .then(function (formattedObject) {
      for (let item of formattedObject) {
        let tdName = document.createElement("td");
        let tdPrice = document.createElement("td");
        let tdImg = document.createElement("td");
        let tr = document.createElement("tr");
        let button = document.createElement("button");
        button.innerHTML = "Add";
        tdName.innerHTML = item.name;
        tdPrice.innerHTML = item.price;
        button.addEventListener("click", function () {
          update(item);
        });
        let img = document.createElement("img");
        img.width = "100";
        img.height = "100";
        img.src = item.pic;
        tdImg.appendChild(img);
        tr.appendChild(tdName);
        tr.appendChild(tdPrice);
        tr.appendChild(tdImg);
        tr.appendChild(button);
        document.getElementById("tableBody").appendChild(tr);
      }
    });
}

function update(receivedData) {
  productArray.push(receivedData);
  console.log(receivedData);
  LoadData();
}

function showProducts(){
  document.getElementById("cartBody").innerHTML = "";
  for(let item of productArray){
    let tr = document.createElement("tr");
    let tdTitle = document.createElement("td");
    let tdPrice = document.createElement("td");
    let tdPhoto = document.createElement("td");
    let tdRemove = document.createElement("td");
    let tdBuy = document.createElement("td");

    tdTitle.innerHTML = item.name;
    tdPrice.innerHTML = item.price;

    let img = document.createElement("img");
    img.width = "50";
    img.height = "50";
    img.src = item.pic;
    tdPhoto.appendChild(img);

    tdRemove.innerHTML = `<button class="btn btn-outline-danger" onclick="Delete(${item.id})">
                          <span class="bi bi-trash2-fill"></span>
                          <button>`;

    tdBuy.innerHTML = `<button  class="btn btn-outline-success" onclick= "Buy(${item.id})">
                       <span class="bi bi-bag-fill"></span>
                       </button>`;                      
                      
    tr.appendChild(tdTitle);                      
    tr.appendChild(tdPrice);                      
    tr.appendChild(tdPhoto);  
    tr.appendChild(tdBuy);                    
    tr.appendChild(tdRemove);
    
    document.querySelector("#cartBody").appendChild(tr);
  }
}

function Delete(unique){
  for(let i =0; i < productArray.length; i++){
    if(productArray[i].id == unique){
      productArray.splice(i,1);
      GetTotalItem();
      showProducts();
    }
  }
}
function GetTotalItem(){
   document.getElementById("cartData").innerHTML = productArray.length;
}

function Buy(unique){
  alert("Payment Successful,Your Order has been placed, delivered soon!!!");
  var myInterwal = setInterval(()=>{
    Delete(unique);
    alert("Your order has been delivered.");
    clearInterval(myInterwal);
  }, 100);
}
