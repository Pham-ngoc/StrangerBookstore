function dropdownLanguage() {
  var dropdownContent = document.getElementById("myDropdown");

  if (dropdownContent.style.display === "block") {
    dropdownContent.style.display = "none";
  } else {
    dropdownContent.style.display = "block";
  }
}
function dropdownAccount() {
        var dropdownContent = document.getElementById("dropdown-account");

        if (dropdownContent.style.display === "block") {
          dropdownContent.style.display = "none";
        } else {
          dropdownContent.style.display = "block";
        }
}
// Đảm bảo mã JavaScript được chạy sau khi trang đã tải xong
$(document).ready(function() {
    var slides = $(".book-introduce, .book-introduce1, .book-introduce2");
    var currentIndex = 0;

    // Hiển thị lớp đầu tiên
    showSlide(currentIndex);

    // Hàm hiển thị lớp
    function showSlide(index) {
        slides.eq(index).animate({ opacity: 1 }, 500, function() {
            setTimeout(function() {
                slides.eq(index).animate({ opacity: 0 }, 500, function() {
                    currentIndex = (currentIndex + 1) % slides.length;
                    showSlide(currentIndex);
                });
            }, 5000); // Thời gian hiển thị mỗi lớp (10 giây)
        });
    }
});
// Lắng nghe sự kiện click trên biểu tượng mắt
      function togglePasswordVisibility() {
          const input = document.getElementById('passwordInput');
          const eyeIcon = document.getElementById('eyeIcon');

          if (input.type === 'password') {
              input.type = 'text'; // Hiển thị mật khẩu khi click vào biểu tượng
              eyeIcon.src = './public/fluent_eye-off-24-filled.svg'; // Thay đổi hình ảnh
          } else {
              input.type = 'password'; // Ẩn mật khẩu khi click lại vào biểu tượng
              eyeIcon.src = './public/fluent_eye-16-filled.svg'; // Thay đổi hình ảnh
          }
      };
