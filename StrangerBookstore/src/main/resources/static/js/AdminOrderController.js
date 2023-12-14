 app.controller("AdminOrderController", function ($scope, $http) {
 var orderUrl = 'http://localhost:8080/admin/order';
    $scope.list = [];
    $scope.form = {};
    $scope.searchKeyword = '';
    $scope.isEditing = false;

    $http
    .get(orderUrl)
    .then(response=> {
                    $scope.list = response.data;
                    console.log(response.data);
                })
                .catch(function (error) {
                    console.error("Error fetching categories:", error);
                });

    $scope.editOrders = function (item) {
                // Gán dữ liệu từ hàng đã click vào biến $scope.form
                $scope.form.orderId = item.orderId;
                $scope.form.customer = {
                        customerId: item.customer.customerId,
                        email: item.customer.email
                };
                $scope.form.statusOrders ={
                        statusId: item.statusOrders.statusId,
                        statusName: item.statusOrders.statusName
                };
                $scope.form.paymentMethod = item.paymentMethod;
                $scope.form.totalAmount = item.totalAmount;
                $scope.isEditing = true;
                // Các trường dữ liệu khác nếu cần
            };


    $scope.searchOrders = function () {
            if (!$scope.searchKeyword || $scope.searchKeyword.trim() === '') {
                // Nếu ô tìm kiếm trống, hiển thị toàn bộ dữ liệu
                $http.get(orderUrl)
                    .then(function (response) {
                        $scope.list = response.data;
                    })
                    .catch(function (error) {
                        console.error("Error fetching products:", error);
                    });
            } else {
                // Ngược lại, lọc theo từ khóa tìm kiếm
                $scope.list = $scope.list.filter(function (order) {
                    return order.orderId.toString().includes($scope.searchKeyword) ||
                        order.customer.email.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                        order.statusOrders.statusName.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                        order.paymentMethod.toString().includes($scope.searchKeyword) ||
                        order.totalAmount.toLowerCase().includes($scope.searchKeyword.toLowerCase());

                });
            }
        };

});