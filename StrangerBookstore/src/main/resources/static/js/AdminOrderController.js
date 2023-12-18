app.controller("AdminOrderController", function ($scope, $http, $window) {
    var orderUrl = 'http://localhost:8080/admin/order';
    var statusOrderUrl = 'http://localhost:8080/admin/status';
    $scope.list = [];
    $scope.form = {};
    $scope.searchKeyword = '';
    $scope.isEditing = false;
    $scope.status = [];

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
        $http.get(orderUrl).then(response=> {
                $scope.list = response.data;
                $scope.loadPage($scope.currentPage);
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
    }

    $scope.load();


    $scope.updateOrders = function () {
        $http.put(orderUrl + '/' + $scope.form.orderId, $scope.form)
            .then(function (response) {
                console.log('Updated successfully:', response.data);
                Swal.fire({
                   position: "center",
                   icon: "success",
                   title: "Order updated successfully!",
                   showConfirmButton: false,
                   timer: 1500
                });
                // Cập nhật danh sách sản phẩm sau khi cập nhật
                $scope.list = $scope.list.map(function (order) {
                    if (order.orderId === $scope.form.orderId) {
                        return $scope.form;
                    }

                $scope.load();
                 $scope.resetForm
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
            $scope.displayedItems = $scope.list = $scope.list.filter(function (order) {
                return order.orderId.toString().includes($scope.searchKeyword) ||
                    order.customer.email.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    order.statusOrders.statusName.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    order.paymentMethod.toString().includes($scope.searchKeyword) ||
                    order.totalAmount.toString().includes($scope.searchKeyword);

            });
        }
    };

});

