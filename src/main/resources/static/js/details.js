
function AddToCart() {
    let userId = document.getElementById("userid").value;
    console.log("User ID:", userId);

  // Lấy thông tin sản phẩm từ trang
  const productId = new URLSearchParams(window.location.search).get("id");
  const quantityInput = document.querySelector('input[type="number"]');
  const quantity = parseInt(quantityInput.value);
  const productName = document.getElementById('productName').textContent;
  const productImage = document.getElementById('productImage').src;
  const productPrice = document.getElementById('productPrice').textContent.replace(" đ", "").trim();

  // Tạo đối tượng cartItem để gửi lên server
  const cartItem = {
    productId: productId,
    quantity: quantity,
    price: productPrice,
    productName: productName,
    productImageUrl: productImage,
  };

  // Gửi yêu cầu POST đến server để thêm sản phẩm vào giỏ hàng
  fetch(`/Cart/add/${userId}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(cartItem),
  })
      .then(response => {
        if (response.status === 401 || response.status === 400) {
          alert("Please log in to add items to the cart.");
          return;
        }
        return response.json();
      })
      .then(data => {
        if (data) {
          alert('Item added to cart successfully!');
          console.log('Cart item added:', data);
        }
      })
      .catch(error => {
        console.error('Error adding item to cart:', error);
      });
}
// Lấy ID sản phẩm từ URL (giả sử bạn lấy ID từ đường dẫn như: /Product_details/{id})
const productId = window.location.pathname.split('/').pop();  // Lấy ID từ URL

// Gọi API để lấy thông tin sản phẩm
fetch(`/products/${productId}`)
    .then(response => response.json())
    .then(product => {
      // Cập nhật nội dung trang với dữ liệu sản phẩm
      document.getElementById('productImage').src = product.imageUrl;
      document.getElementById('productName').textContent = product.name;
      document.getElementById('productPrice').innerHTML = `${product.price} <sup>đ</sup>`;
      document.getElementById('productDescription').textContent = product.description;
    })
    .catch(error => console.error('Error fetching product data:', error));
//
document.querySelector('.header_account_icon').addEventListener('click', function(event) {
    const dropdown = document.querySelector('.dropdown');
    // Toggle display of the dropdown menu
    dropdown.style.display = (dropdown.style.display === 'block') ? 'none' : 'block';
});



