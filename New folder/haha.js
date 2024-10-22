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
            <button class="normal">Proceed to checkout</button>
          </div>
      `;
  } else return;
};
