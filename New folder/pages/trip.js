const trips = document.querySelector(".about");

window.addEventListener("DOMContentLoaded", () => {
  const loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));
  if (loggedInUser) {
    document.querySelector(".header_account i").className =
      "fa-solid fa-right-from-bracket";
  } else {
    document.querySelector(".header_account").innerHTML =
      '<i class="fa-regular fa-user"></i>';
  }
});

const getData = async () => {
  const response = await fetch("trip.json");
  const data = await response.json();
  if (data) {
    trips.innerHTML = data
      .map((item) => {
        return `
    <div class="about">
        <div class="about_main">
          <div class="about_img">
            <div class="about_small_img">
              <img src="../img/koi4.jpg" alt="" onclick="functio(this)" />
              <img src="../img/koi5.jpg" alt="" onclick="functio(this)" />
              <img src="../img/koi6.jpg" alt="" onclick="functio(this)" />
              <img src="../img/koi7.jpg" alt="" onclick="functio(this)" />
            </div>
            <div class="img_container">
              <img src="../${item.img}" alt="" id="imgbox" />
            </div>
          </div>
          <div class="about_text">
            <p>
              <strong>${item.title}</strong> <br />
                ${item.description}
            </p>
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
const Account = document.querySelector(".header_account");

function logout() {
  localStorage.removeItem("loggedInUser");
  document.querySelector(
    ".header_account"
  ).innerHTML = `<i class="fa-regular fa-user"></i>`;

  window.location.href = "login.html";
}

Account.addEventListener("click", logout);
