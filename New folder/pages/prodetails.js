const Productss = document.querySelector(".prodetails");
const cartIcon = document.querySelector(".cart");
window.addEventListener("DOMContentLoaded", () => {
  const loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));
  if (loggedInUser) {
    document.querySelector(".header_account i").className =
      "fa-solid fa-right-from-bracket";
  }
});

const getProduct = async () => {
  const path = new URLSearchParams(window.location.search);

  const productId = path.get("id");
  const response = await fetch("data1.json");
  const data = await response.json();

  const findProductId = data.find(
    (item) => item.id.toString() === productId.toString()
  );
  Productss.innerHTML = `
    <div class="s-p-image">
        <img src="../${findProductId.img}" width="100%" id="MainImg" alt="" />
          
    </div>

    <div class="single-pro-details">
        <h6>Shop/Koi Kohaku/Tancho Kohaku</h6>
        <h4>${findProductId.title}</h4>
        <h2>300.000</h2><sup>đ</sup></h2>
        <select>
          <option>Select color</option>
          <option>White</option>
          <option>Black</option>
        </select>

        <input type="number" value="1" />
        <button class="normal" id="btn-cart">Add to cart</button>
            <h4>Product detail</h4>
            <span>
                ${findProductId.description}
            </span>
    </div>
  
  `;

  const btnAddCart = document.getElementById("btn-cart");

  btnAddCart.addEventListener("click", () => {
    const cart = JSON.parse(localStorage.getItem("cart"));
    if (cart) {
      // const item = cart.findIndex((item) => item.id === findProductId.id);
      const item = cart.findIndex((item) => item.id === findProductId.id);
      if (item !== -1) {
        cart[item].count += 1;
      } else {
        cart.push({ id: findProductId.id, count: 1 });
      }
      localStorage.setItem("cart", JSON.stringify(cart));
    } else {
      const cart = [
        {
          id: findProductId.id,
          count: 1,
        },
      ];
      localStorage.setItem("cart", JSON.stringify(cart));
    }
  });
};

getProduct();
