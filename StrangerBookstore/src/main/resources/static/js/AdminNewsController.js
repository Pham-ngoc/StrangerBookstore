app.controller("AdminNewsController", function ($scope, $http) {
    var newsUrl = 'http://localhost:8080/admin/news';
    $scope.list = [];
    $scope.form = {};
    $scope.searchKeyword = '';
    $scope.filteredNews = [];
    $scope.isEditing = false;

    // Thêm các biến cho phân trang
        $scope.pageSize = 5; // Số lượng mục trên mỗi trang
        $scope.currentPage = 1; // Trang hiện tại

        // Hàm để tính số lượng trang
        $scope.numberOfPages = function () {
            return Math.ceil($scope.list.length / $scope.pageSize);
        };

        // Hàm để load dữ liệu theo trang
        $scope.loadPage = function (page) {
            var start = (page - 1) * $scope.pageSize;
            var end = start + $scope.pageSize;
            $scope.displayedItems = $scope.list.slice(start, end);
        };

    $scope.load = function(){
        $http.get(newsUrl).then(response=> {
            $scope.list = response.data;
            $scope.loadPage($scope.currentPage);
            console.log(response.data);
        }).catch(function (error) {
            console.error("Error fetching categories:", error);
        });
    }

    $scope.load();

    $scope.createNews = function () {
        $http.post(newsUrl, $scope.form)
            .then(function (response) {
                console.log('News created successfully:', response.data);
                Swal.fire({
                     position: "center",
                     icon: "success",
                     title: "News created successfully!",
                     showConfirmButton: false,
                     timer: 1500
                });
                $scope.resetForm();
                $scope.load();
            })
            .catch(function (error) {
                console.error('Error creating news:', error);
                    Swal.fire({
                        icon: "error",
                        title: "News created failed!"
                    });
            });
    };

    $scope.updateNews = function () {
        // Gửi yêu cầu PUT tới server để cập nhật tin tức
        $http.put(newsUrl + '/' + $scope.form.newsId, $scope.form)
            .then(function (response) {
                console.log('News updated successfully:', response.data);
                // Cập nhật danh sách tin tức sau khi cập nhật
                Swal.fire({
                   position: "center",
                   icon: "success",
                   title: "News updated successfully!",
                   showConfirmButton: false,
                   timer: 1500
                });
                $scope.resetForm();
                $scope.load();
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
                Swal.fire({
                        icon: "error",
                        title: "News updated failed!"
                    });
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
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "News deleted successfully!",
                        showConfirmButton: false,
                        timer: 1500
                    });
                    $scope.resetForm();
                    $scope.load();
                })
                .catch(function (error) {
                    console.error('Error deleting news:', error);
                    Swal.fire({
                        icon: "error",
                        title: "News deleted failed!"
                    });
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
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "News deleted successfully!",
                        showConfirmButton: false,
                        timer: 1500
                    });
                    $scope.resetForm();
                    $scope.load();
                })
                .catch(function (error) {
                    console.error('Error deleting news:', error);
                    Swal.fire({
                        icon: "error",
                        title: "News deleted failed!"
                    });
                });
        }
    };



    $scope.resetForm = function () {
        event.preventDefault();
        // Đặt lại giá trị của $scope.form về trạng thái ban đầu hoặc giá trị mặc định
        var fileInput = document.getElementById('file-input');
        if (fileInput) {
            fileInput.value = ''; // Xóa giá trị của input file
        }
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
            $scope.displayedItems = $scope.list = $scope.list.filter(function (news) {
                return news.newsId.toString().includes($scope.searchKeyword) ||
                    news.newsTitle.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    news.newsContent.toLowerCase().includes($scope.searchKeyword.toLowerCase())||
                    (news.newsPicture && news.newsPicture.toLowerCase().includes($scope.searchKeyword.toLowerCase()));

            });
        }
    };




});
