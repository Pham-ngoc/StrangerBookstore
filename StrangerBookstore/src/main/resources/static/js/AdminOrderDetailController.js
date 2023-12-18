app.controller("AdminOrderDetailController", function ($scope, $http, $timeout,$route) {
    var url = 'http://localhost:8080/admin/orderDetail';
    $scope.items = [];
    $scope.form = {};
    $scope.searchKeyword='';

    // Thêm các biến cho phân trang
        $scope.pageSize = 3; // Số lượng mục trên mỗi trang
        $scope.currentPage = 1; // Trang hiện tại

        // Hàm để tính số lượng trang
        $scope.numberOfPages = function () {
            return Math.ceil($scope.items.length / $scope.pageSize);
        };

        // Hàm để load dữ liệu theo trang
        $scope.loadPage = function (page) {
            var start = (page - 1) * $scope.pageSize;
            var end = start + $scope.pageSize;
            $scope.displayedItems = $scope.items.slice(start, end);
        };

    $scope.getAll=function (){
        $http.get(url).then(resp=>{
            $scope.items=resp.data;
            $scope.loadPage($scope.currentPage);
        });
    }
    $scope.getAll();

    $scope.searchOrderDetail = function () {
        if (!$scope.searchKeyword || $scope.searchKeyword.trim() === '') {
            // Nếu ô tìm kiếm trống, hiển thị toàn bộ dữ liệu
            $http.get(url)
                .then(function (response) {
                    $scope.items = response.data;
                })
                .catch(function (error) {
                    console.error("Error fetching news:", error);
                });
        } else {
            // Ngược lại, lọc theo từ khóa tìm kiếm
            $scope.displayedItems = $scope.items = $scope.items.filter(function (orderDetail) {
                return orderDetail.orderDetailsId.toString().includes($scope.searchKeyword) ||
                    orderDetail.order.orderId.toString().includes($scope.searchKeyword) ||
                    orderDetail.product.productName?.toLowerCase().includes($scope.searchKeyword.toLowerCase())||
                    orderDetail.quantity.toString().includes($scope.searchKeyword)||
                    orderDetail.amount.toString().includes($scope.searchKeyword);
            });
        }
    };

})