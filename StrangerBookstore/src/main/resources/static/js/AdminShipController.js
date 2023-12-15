 app.controller("AdminShipController", function ($scope, $http) {
 var shipUrl = 'http://localhost:8080/admin/shipInformation';
    $scope.list = [];
    $scope.form = {};
    $scope.searchKeyword = '';
    $scope.isEditing = false;

    $http
        .get(shipUrl)
        .then(response=> {
                    $scope.list = response.data;
                    console.log(response.data);
                })
                .catch(function (error) {
                    console.error("Error fetching categories:", error);
                });

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