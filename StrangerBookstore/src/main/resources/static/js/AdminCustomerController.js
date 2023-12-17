app.controller("AdminCustomerController", function($scope, $http) {
        var customerUrl = 'http://localhost:8080/admin/customer';
        var rolesUrl = 'http://localhost:8080/admin/role';

        $scope.formCreate = {};
        $scope.form = {};
        $scope.customerList = {};
        $scope.currentPage = 0;
        $scope.roles = [];
        $scope.searchKeyword = '';
        $scope.isEditing = false;

         // Thêm các biến cho phân trang
            $scope.pageSize = 3; // Số lượng mục trên mỗi trang
            $scope.currentPage = 1; // Trang hiện tại

            // Hàm để tính số lượng trang
            $scope.numberOfPages = function () {
                return Math.ceil($scope.customerList.length / $scope.pageSize);
            };

            // Hàm để load dữ liệu theo trang
            $scope.loadPage = function (page) {
                var start = (page - 1) * $scope.pageSize;
                var end = start + $scope.pageSize;
                $scope.displayedItems = $scope.customerList.slice(start, end);
            };


        $scope.load = function () {
                $http.get(customerUrl).then(resp => {
                    console.log(resp.data);
                    $scope.customerList = resp.data;
                    $scope.loadPage($scope.currentPage);
                });
                $http.get(rolesUrl).then(resp => {
                    $scope.roles = resp.data;
                });
            };

        $scope.load();

        $scope.edit = function(id) {
            $http.get(`${customerUrl}/${id}`).then(resp => {
                console.log(resp.data);
                $scope.form = resp.data;
        });

        $scope.createCustomer = function () {

            $http.post(customerUrl, $scope.formCreate).then(function (response) {
                console.log('Customer created successfully:', response.data);
                        // Thêm sản phẩm mới vào danh sách hiện tại
                $scope.load();
            })
                .catch(function (error) {
                    $scope.successMessage = 'Create Fail!';
                });
            };
        };

        $scope.updateFileName = function () {
             var fileInput = document.getElementById('picture');
             if (fileInput && fileInput.files.length > 0) {
                    $scope.formCreate.picture = fileInput.files[0].name;
             } else if (!$scope.isEditing) {
                    $scope.formCreate.picture = '';
             }
        };
});