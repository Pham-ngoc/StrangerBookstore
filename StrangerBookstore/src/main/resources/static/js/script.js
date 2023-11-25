//HOME.HTML
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


//END HOME.HTML

//LOGIN.HTML
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
//END LOGIN.HTML

//SHOP.HTML
$(document).ready(function() {
        //jquery for toggle sub menus
        $('.sub-btn').click(function() {
          $(this).next('.sub-menu').slideToggle();
          $(this).find('.dropdown').toggleClass('rotate');
        });
        //jquery for expand and collapse the sidebar
        $('.menu-btn').click(function() {
          $('.side-bar').addClass('active');
          $('.menu-btn').css("visibility", "hidden");
        });
        $('.close-btn').click(function() {
          $('.side-bar').removeClass('active');
          $('.menu-btn').css("visibility", "visible");
        });
      });
      try {
        var arrow = $('.js-arrow');
        arrow.each(function () {
          var that = $(this);
          that.on('click', function (e) {
            e.preventDefault();
            that.find(".arrow").toggleClass("up");
            that.toggleClass("open");
            that.parent().find('.js-sub-list').slideToggle("250");
          });
        });

      } catch (error) {
        console.log(error);
      }
//END SHOP.HTML

//PROFILE-PERSONAL.HTML
// JavaScript
        function handleFileSelect() {
        const fileInput = document.getElementById('fileInput');
        const avataImage = document.getElementById('avataImage');

        const selectedFile = fileInput.files[0];

        if (selectedFile) {
            const reader = new FileReader();

            reader.onload = function (e) {
            avataImage.src = e.target.result;
            };

            reader.readAsDataURL(selectedFile);
        }
      }
//END PERSONAL.HTML
