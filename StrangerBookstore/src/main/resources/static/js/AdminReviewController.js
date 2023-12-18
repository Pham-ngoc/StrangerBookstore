app.controller("AdminReviewController", function ($scope, $http) {
    var reviewsUrl = 'http://localhost:8080/admin/reviews';
    $scope.list = [];
    $scope.form = {};
    $scope.searchKeyword = '';
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
        $http
        .get(reviewsUrl)
        .then(response=> {
            $scope.list = response.data;
            $scope.loadPage($scope.currentPage);
            console.log(response.data);
        })
        .catch(function (error) {
            console.error("Error fetching categories:", error);
        });
    }

    $scope.load();

    $scope.editReviews = function (item) {
        // Gán dữ liệu từ hàng đã click vào biến $scope.form
        $scope.form.reviewsId = item.reviewsId;
        $scope.form.product ={
            productId: item.product.productId,
            productName: item.product.productName
        };
        $scope.form.customer = {
            customerId: item.customer.customerId,
            email: item.customer.email
        };
        $scope.form.reviewContent = item.reviewContent;
        $scope.form.starForProduct = item.starForProduct;
        $scope.isEditing = true;
    };

    $scope.searchReviews = function () {
        if (!$scope.searchKeyword || $scope.searchKeyword.trim() === '') {
            // Nếu ô tìm kiếm trống, hiển thị toàn bộ dữ liệu
            $http.get(reviewsUrl)
                .then(function (response) {
                    $scope.list = response.data;
                })
                .catch(function (error) {
                    console.error("Error fetching products:", error);
                });
        } else {
            // Ngược lại, lọc theo từ khóa tìm kiếm
            $scope.displayedItems = $scope.list = $scope.list.filter(function (productReviews) {
                return productReviews.reviewsId.toString().includes($scope.searchKeyword) ||
                    productReviews.product.productName.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    productReviews.customer.email.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    productReviews.reviewContent.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    productReviews.starForProduct.toString().includes($scope.searchKeyword);

            });
        }
    };

});