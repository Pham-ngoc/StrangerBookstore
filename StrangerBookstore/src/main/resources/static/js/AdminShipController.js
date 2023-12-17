 app.controller("AdminShipController", function ($scope, $http) {
 var shipUrl = 'http://localhost:8080/admin/shipInformation';


    $scope.list = [];
    $scope.form = {};
    $scope.searchKeyword = '';
    $scope.isEditing = false;

    $http.get(shipUrl)
        .then(response=> {
                    $scope.list = response.data;
                    console.log(response.data);
                })
                .catch(function (error) {
                    console.error("Error fetching categories:", error);
                });



    $scope.updateShip = function () {
            // Sử dụng dữ liệu từ $scope.form để thực hiện cập nhật
            $http.put(shipUrl + '/' + $scope.form.shipId, $scope.form)
                .then(function (response) {
                    console.log('Ship updated successfully:', response.data);

                    // Cập nhật danh sách vận chuyển sau khi cập nhật
                    $scope.list = $scope.list.map(function (ship) {
                        if (ship.shipId === $scope.form.shipId) {
                            return $scope.form;
                        }
                        return ship;
                    });

                    // Đặt lại giá trị của $scope.form về trạng thái ban đầu
                    $scope.resetForm();
                })
                .catch(function (error) {
                    console.error('Error updating ship:', error);
                });
        };





    $scope.editShip = function (item) {
            // Gán dữ liệu từ hàng đã click vào biến $scope.form
            $scope.form = {
                        shipId: item.shipId,
                        order: {
                            orderId: item.order.orderId,
                            customer: {
                                customerId: item.order.customer.customerId,
                                email: item.order.customer.email
                            },
                            statusOrders: {
                                statusId: item.order.statusOrders.statusId,
                                statusName: item.order.statusOrders.statusName

                            },
                            paymentMethod: item.order.paymentMethod,
                            totalAmount: item.order.totalAmount
                        },
                        address: {
                            addressId: item.address.addressId,
                            recipientFullName: item.address.recipientFullName,
                            recipientPhoneNumber: item.address.recipientPhoneNumber,
                            addressDetail: item.address.addressDetail,
//                            addressType: item.address.addressType,
//                            customer: {
//                                customerId: item.address.customer.customerId
//                            }
                        },
                        status: item.status,
                        note: item.note
                    };
            $scope.isEditing = true;

        };


    $scope.searchShip = function () {
                if (!$scope.searchKeyword || $scope.searchKeyword.trim() === '') {
                    // Nếu ô tìm kiếm trống, hiển thị toàn bộ dữ liệu
                    $http.get(shipUrl)
                        .then(function (response) {
                            $scope.list = response.data;
                        })
                        .catch(function (error) {
                            console.error("Error fetching products:", error);
                        });
                } else {
                    // Ngược lại, lọc theo từ khóa tìm kiếm
                    $scope.list = $scope.list.filter(function (shipInfor) {
                        return shipInfor.shipId.toString().includes($scope.searchKeyword) ||
                            shipInfor.order.orderId.toString().includes($scope.searchKeyword) ||
                            shipInfor.address.addressDetail.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                            shipInfor.status.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                            shipInfor.note.toLowerCase().includes($scope.searchKeyword.toLowerCase());

                    });
                }
            };

        $scope.resetForm = function () {
                $scope.form = {};
                $scope.isEditing = false;
                $scope.searchKeyword = '';
            };

});