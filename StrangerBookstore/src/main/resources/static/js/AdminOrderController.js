app.controller("AdminOrderController", function ($scope, $http, $window) {
    var orderUrl = 'http://localhost:8080/admin/order';
    var statusOrderUrl = 'http://localhost:8080/admin/status';
    $scope.list = [];
    $scope.form = {};
    $scope.searchKeyword = '';
    $scope.isEditing = false;
    $scope.status = [];

    $http.get(orderUrl).then(response=> {
        $scope.list = response.data;
//                    console.log(response.data);
        console.log('Order data:', $scope.list);
    })
        .catch(function (error) {
            console.error("Error fetching categories:", error);
        });

    $http.get(statusOrderUrl).then(response => {
        $scope.status = response.data;
        console.log('Status data:', $scope.status);
    })
        .catch(function (error) {
            console.error("Error fetching status:", error);
        });


    $scope.updateOrders = function () {
        $http.put(orderUrl + '/' + $scope.form.orderId, $scope.form)
            .then(function (response) {
                console.log('Updated successfully:', response.data);
                alert("Update successfully");
                // Cập nhật danh sách sản phẩm sau khi cập nhật
                $scope.list = $scope.list.map(function (order) {
                    if (order.orderId === $scope.form.orderId) {
                        return $scope.form;
                    }

                    $window.location.reload();
                });
            })
            .catch(function (error) {
                console.error('Error updating product:', error);
                alert("Update fail");
            });
    };

//
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
        console.log('StatusOrders value:', item.statusOrders);
        console.log('StatusOrders value when editing:', item.statusOrders);
        console.log('StatusOrders ID when editing:', item.statusOrders.statusId);
        console.log('StatusOrders Name when editing:', item.statusOrders.statusName);
    };

    $scope.deleteOrders = function (orderId) {
        $http.delete(orderUrl + '/' + orderId)
            .then(function (response) {
                console.log('Order deleted successfully:', response.data);
                alert("Delete successfully");
                // Cập nhật danh sách đơn hàng sau khi xóa
                $scope.list = $scope.list.filter(function (order) {
                    return order.orderId !== orderId;
                });
                return $scope.resetForm();
            })
            .catch(function (error) {
                console.error('Error deleting order:', error);
                alert("Delete fail");
            });
    };



    $scope.resetForm = function () {
        $scope.form = {};
        $scope.isEditing = false;
        $scope.searchKeyword = '';
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
                    order.totalAmount.toString().includes($scope.searchKeyword);

            });
        }
    };

});

