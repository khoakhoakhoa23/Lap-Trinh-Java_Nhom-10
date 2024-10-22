const details = document.querySelector(".boxs");
const getDatas = async () => {
  const responses = await fetch("data1.json");
  const datas = await responses.json();
  if (datas) {
    details.innerHTML = datas
      .map((item) => {
        return `
      <div class="boxs">
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
              <p> 
                ${item.description}
              </p>
              <h3>300.000<sup>đ</sup></h3>
              <div class="product-star">
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
              </div>
              <a href="details.html?id=${item.id}" class="btn">View</a>
            </div>
          </div>
        </div>
      
      `;
      })
      .join("");
  }
};
getDatas();

window.addEventListener("DOMContentLoaded", () => {
  const loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));
  if (loggedInUser) {
    document.querySelector(".header_account i").className =
      "fa-solid fa-right-from-bracket";
  }
});
