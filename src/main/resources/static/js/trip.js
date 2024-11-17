const trips = document.querySelector(".about");
document.querySelector('.header_account_icon').addEventListener('click', function(event) {
  const dropdown = document.querySelector('.dropdown');
  // Toggle display of the dropdown menu
  dropdown.style.display = (dropdown.style.display === 'block') ? 'none' : 'block';
});

// main.js
const imgbox = document.getElementById("imgbox");
const smallImages = document.querySelectorAll(".about_small_img img");
let currentIndex = 0;

// Hàm để tự động thay đổi hình ảnh
function autoChangeImage() {
  // Tăng chỉ số hình ảnh hiện tại
  currentIndex = (currentIndex + 1) % smallImages.length; // Quay lại đầu nếu vượt quá
  imgbox.src = smallImages[currentIndex].src; // Cập nhật src cho imgbox

  // Thêm lớp 'moving' để kích hoạt hiệu ứng
  imgbox.classList.add("moving");

  // Sau một thời gian, xóa lớp 'moving'
  setTimeout(() => {
    imgbox.classList.remove("moving");
  }, 1000); // Thời gian khớp với thời gian chuyển động
}

// Gọi hàm autoChangeImage mỗi 3 giây
setInterval(autoChangeImage, 3000); // Thay đổi hình ảnh mỗi 3 giây

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
              <img src="../img/traicakoi.jpg" alt="" onclick="functio(this)" />
              <img src="../img/traicakoi1.jpg" alt="" onclick="functio(this)" />
              <img src="../img/traicakoi2.jpg" alt="" onclick="functio(this)" />
              <img src="../img/traicakoi3.jpg" alt="" onclick="functio(this)" />
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
