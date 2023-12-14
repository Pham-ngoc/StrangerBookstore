app.controller("AdminNewsController", function ($scope, $http) {
    var newsUrl = 'http://localhost:8080/admin/news';
    $scope.list = [];
    $scope.form = {};
    $scope.searchKeyword = '';
    $scope.filteredNews = [];
    $scope.isEditing = false;

    $http
        .get(newsUrl)
        .then(response=> {
            $scope.list = response.data;
            console.log(response.data);
        })
        .catch(function (error) {
            console.error("Error fetching categories:", error);
        });

    $scope.createNews = function () {
            // Gửi dữ liệu tin tức lên server để tạo mới
            $http.post(newsUrl, $scope.form)
                .then(function (response) {
                    console.log('News created successfully:', response.data);
                    // Gán giá trị newsId từ server response cho $scope.form.newsId
                    $scope.form.newsId = response.data.newsId;
                    // Các bước khác sau khi tạo tin tức
                })
                .catch(function (error) {
                    console.error('Error creating news:', error);
                });
        };

    $scope.updateNews = function () {
            // Gửi yêu cầu PUT tới server để cập nhật tin tức
            $http.put(newsUrl + '/' + $scope.form.newsId, $scope.form)
                .then(function (response) {
                    console.log('News updated successfully:', response.data);
                    // Cập nhật danh sách tin tức sau khi cập nhật
                    $scope.list = $scope.list.map(function (news) {
                        if (news.newsId === $scope.form.newsId) {
                            return $scope.form;
                        }
                        return news;
                    });
                    // Các bước khác sau khi cập nhật tin tức
                })
                .catch(function (error) {
                    console.error('Error updating news:', error);
                });
        };

     $scope.editNews = function (item) {
            // Gán dữ liệu từ hàng đã click vào biến $scope.form
            $scope.form.newsId = item.newsId;
            $scope.form.newsTitle = item.newsTitle;
            $scope.form.newsContent = item.newsContent;
            $scope.form.newsPicture = item.newsPicture;
            $scope.isEditing = true;
            $scope.updateFileName();
            // Các trường dữ liệu khác nếu cần
        };



        $scope.deleteNews = function (event, item) {
            event.stopPropagation(); // Ngăn chặn sự kiện click từ lan ra các phần tử cha

            if (item && item.newsId) {
                // Xử lý delete 2: Gửi yêu cầu DELETE tới server để xóa tin tức
                $http.delete(newsUrl + '/' + item.newsId)
                    .then(function (response) {
                        console.log('News deleted successfully:', response.data);

                        // Cập nhật danh sách tin tức sau khi xóa
                        $scope.list = $scope.list.filter(function (news) {
                            return news.newsId !== item.newsId;
                        });

                        // Đặt lại giá trị của $scope.form về trạng thái ban đầu
                        $scope.resetForm();
                    })
                    .catch(function (error) {
                        console.error('Error deleting news:', error);
                    });
            } else {
                // Xử lý delete 1: Gửi yêu cầu DELETE tới server dựa trên $scope.form.newsId
                if (!$scope.form.newsId) {
                    console.error('Cannot delete. No newsId specified.');
                    return;
                }

                $http.delete(newsUrl + '/' + $scope.form.newsId)
                    .then(function (response) {
                        console.log('News deleted successfully:', response.data);

                        // Cập nhật danh sách tin tức sau khi xóa
                        $scope.list = $scope.list.filter(function (news) {
                            return news.newsId !== $scope.form.newsId;
                        });

                        // Đặt lại giá trị của $scope.form về trạng thái ban đầu
                        $scope.resetForm();
                    })
                    .catch(function (error) {
                        console.error('Error deleting news:', error);
                    });
            }
        };



      $scope.resetForm = function () {
              event.preventDefault();
             // Đặt lại giá trị của $scope.form về trạng thái ban đầu hoặc giá trị mặc định
             $scope.form = {};
             $scope.isEditing = false;
             $scope.searchKeyword = '';
         };



      $scope.updateFileName = function () {
              var fileInput = document.getElementById('file-input');
              if (fileInput.files.length > 0) {
                  $scope.form.newsPicture = fileInput.files[0].name;
              } else if (!$scope.isEditing) { // Thêm điều kiện kiểm tra isEditing
                  $scope.form.newsPicture = ''; // Đặt lại giá trị nếu không có tệp nào được chọn
              }
          };

       $scope.searchNews = function () {
           if (!$scope.searchKeyword || $scope.searchKeyword.trim() === '') {
               // Nếu ô tìm kiếm trống, hiển thị toàn bộ dữ liệu
               $http.get(newsUrl)
                   .then(function (response) {
                       $scope.list = response.data;
                   })
                   .catch(function (error) {
                       console.error("Error fetching news:", error);
                   });
           } else {
               // Ngược lại, lọc theo từ khóa tìm kiếm
               $scope.list = $scope.list.filter(function (news) {
                   return news.newsId.toString().includes($scope.searchKeyword) ||
                       news.newsTitle.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                       news.newsContent.toLowerCase().includes($scope.searchKeyword.toLowerCase())||
                       (news.newsPicture && news.newsPicture.toLowerCase().includes($scope.searchKeyword.toLowerCase()));

               });
           }
       };




 });

