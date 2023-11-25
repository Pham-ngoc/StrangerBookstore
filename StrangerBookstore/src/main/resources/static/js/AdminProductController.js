//app.controller("AdminProductController", function($scope, $http) {
//    var productUrl = 'http://localhost:8080/products';
//    var categoryUrl = 'http://localhost:8080/categories';
//
//    $scope.form = {};
//    $scope.list = [];
//    $scope.categories = [];
//
//
//    $scope.loadTable = function() {
//        $http.get(productUrl).then(resp => {
//            console.log(resp.data);
//            $scope.list = resp.data;
//        });
//    };
//
//    $scope.refreshTable = function() {
//        $scope.loadTable();
//    };
//
//    $scope.load = function() {
//        $http.get(productUrl).then(resp => {
//            console.log(resp.data);
//            $scope.list = resp.data;
//        });
//        $http.get(categoryUrl).then(resp => {
//            $scope.categories = resp.data;
//        });
//    }
//    $scope.edit = function(id) {
//            $http.get(`${productUrl}/${id}`).then(resp => {
//                console.log(resp.data);
//                $scope.form = resp.data;
//            });
//        }
//    $scope.reset = function() {
//            $scope.form = {}
//    }
//
//    $scope.create = function() {
//        var data = angular.copy($scope.form);
//        $http.post(productUrl, data).then(resp => {
//             $scope.reset();
//             $scope.load();
//
//            Swal.fire({
//              position: "top-end",
//              icon: "success",
//              title: "Your work has been saved",
//              showConfirmButton: false,
//              timer: 1500
//            });
//        })
//        .catch(error => {
//              console.log(error);
//               Swal.fire({
//                    icon: "error",
//                    title: "Oops...",
//                    text: "Something went wrong!",
//                    footer: '<a href="#">Why do I have this issue?</a>'
//               });
//            });
//    }
//    $scope.update = function() {
//            var data = angular.copy($scope.form);
//            $http.put(`${productUrl}/${data.productId}`, data).then(resp => {
//                $scope.load();
//            }).catch(error => {
//                alert("Không tìm thấy loại hàng để cập nhật");
//            });
//        }
//        $scope.delete = function(id) {
//            $http.delete(`${productUrl}/${id}`).then(resp => {
//                $scope.reset();
//                $scope.load();
//            });
//        }
//
//        $scope.init = function() {}
//
//        $scope.load();
//});
