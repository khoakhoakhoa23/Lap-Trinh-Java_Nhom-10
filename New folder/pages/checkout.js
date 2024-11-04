let container = document.querySelector(".container");

let cart = document.querySelector(".cart");

let ca = JSON.parse(localStorage.getItem("cart")) || [];

let carttotal = document.querySelector(".cart-add");
const renderCartItem = async () => {
  const response = await fetch("data1.json");
  const data = await response.json();

  if (ca.length != 0) {
    return (cart.innerHTML = ca
      .map((itemCart) => {
        let search = data.find((itemData) => itemData.id === itemCart.id) || {};
        return `
      <div class="cart" >
        <table width="100%">    
          <thead>
            <tr>
              <td>Remove</td>
              <td>Image</td>
              <td>Product</td>
              <td>Price</td>
              <td>Quantity</td>
              <td>Total</td>
            </tr>
          </thead>  
          <tbody>
            <tr>
              <td>
                <a onclick="removeItem(${
                  search.id
                })"  href=""><i class="fa-regular fa-circle-xmark"></i></a>
              </td>
              <td>
                <img src="../img/${search.img}" alt="" />
              </td>
              <td>${search.title}</td>
              <td>${search.price}<sup>đ</sup></td>
              <td><input onchange="update(${
                search.id
              })" type="number"  value="${itemCart.count}" name="" id="${
          search.id
        }"   /></td>
              <td>${search.price * itemCart.count}</td>
            </tr>
          </tbody>
        </table>
      </div>
      `;
      })
      .join(""));
  } else {
    return (container.innerHTML = `
      <div class="cart-empty">
      <h2>Cart is empty</h2>
      <a href ="../index.html">
      <button class="back">Back Home</button>
      </a>
      <div>
    `);
  }
};

let update = (id) => {
  if (ca.length !== 0) {
    let searchIndex = ca.findIndex((itemCart) => itemCart.id === id);
    if (searchIndex !== -1) {
      let quantityElement = document.getElementById(id);
      if (quantityElement) {
        ca[searchIndex].count = parseInt(quantityElement.value, 10) || 0;

        localStorage.setItem("cart", JSON.stringify(ca));

        renderCartItem();
        totalPro();
      }
    }
  }
};

let totalPro = async () => {
  let response = await fetch("data1.json");

  let data = await response.json();

  if (ca.length != 0) {
    let total = ca
      .map((item) => {
        let search = data.find((itemData) => itemData.id === item.id) || {};
        return item.count * search.price;
      })
      .reduce((x, y) => x + y, 0); //tinh toan gia tr duy nhat tu 1 mang, nhan 1 ham comback, 1 gtri khoi tao, lap lai qua tung phan tu thuc hien phep toan hoac tong hop gia tri
    carttotal.innerHTML = `<div class="coupon">
            <h3>Apply coupon</h3>
            <div>
              <input type="text" placeholder="Enter you coupon" />
              <button class="normal">Apply</button>
            </div>
          </div>
    
          <div class="total">
            <h3>Cart total</h3>
            <table>
              <tr>
                <td>Cart total:</td>
                <td>${total}<sup>đ</sup></td>
              </tr>
              <tr>
                <td>Shipping:</td>
                <td>Free</td>
              </tr>
              <tr>
                <td><strong>Total:</strong></td>
                <td>
                  <strong>${total}<sup>đ</sup></strong>
                </td>
              </tr>
            </table>
            <a href="../checkout/end.html"><button class="normal">Proceed to checkout</button></a>      
          </div>
      `;
  } else return;
};

let removeItem = (id) => {
  let removeId = id;
  ca = ca.filter((item) => item.id !== removeId);
  renderCartItem();
  totalPro();
  localStorage.setItem("cart", JSON.stringify(ca));
};

renderCartItem();

totalPro();