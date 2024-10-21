const Products = document.querySelector(".box");

const getData = async () => {
  const response = await fetch("data.json");
  const data = await response.json();
  if (data) {
    Products.innerHTML = data
      .map((item) => {
        return `
       <div class="product">
         <div class="box">
          <div class="card">
            <div class="small-cart">
              <i class="fa-solid fa-heart"></i>
              <i class="fa-solid fa-share"></i>
            </div>
            <div class="img">
              <img src="${item.img}" alt="" />
            </div>
            <div class="product-info">
              <h2>${item.title}</h2>
              <p>${item.description}}</p>
              <h3>300.000<sup>đ</sup>-500.000<sup>đ</sup></h3>
              <div class="product-star">
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
              </div>
              <a href="product_details.html" class="btn">View</a>
            </div>
          </div>
        </div>
      </div>
        `;
      })
      .join("");
  }
};

getData();

function functio(small) {
  var full = document.getElementById("imgbox");
  full.src = small.src;
}