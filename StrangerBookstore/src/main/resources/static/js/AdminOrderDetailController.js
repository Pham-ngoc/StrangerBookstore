app.controller("AdminOrderDetailController", function ($scope, $http, $timeout,$route) {
    var url = 'http://localhost:8080/admin/orderDetail';
    $scope.items = [];
    $scope.form = {};
    $scope.searchKeyword='';

    $scope.getAll=function (){
        $http.get(url).then(resp=>{
            $scope.items=resp.data;
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
            $scope.items = $scope.items.filter(function (orderDetail) {
                return orderDetail.orderDetailsId.toString().includes($scope.searchKeyword) ||
                    orderDetail.order.orderId.toString().includes($scope.searchKeyword) ||
                    orderDetail.product.productName.toLowerCase().includes($scope.searchKeyword.toLowerCase())||
                    orderDetail.quantity.toString().includes($scope.searchKeyword)||
                    orderDetail.amount.toString().includes($scope.searchKeyword);
            });
        }
    };

})