app.controller("AdminCustomerController", function ($scope, $http, $window) {
    var url = 'http://localhost:8080/admin/customer';
    var roleurl="http://localhost:8080/admin/roles"
    $scope.list = [];
    $scope.form = {};
    $scope.formCreate = {};
    $scope.isEditing = false;

    // Thêm các biến cho phân trang
    $scope.pageSize = 3; // Số lượng mục trên mỗi trang
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

    $scope.load = function (){
        $http
            .get(url)
            .then(response => {
                $scope.list = response.data;
                console.log(response.data);
                $scope.loadPage($scope.currentPage);
            })
            .catch(function (error) {
                console.error("Error fetching categories:", error);
            });
        $http.get(roleurl)
            .then(function(response){
                $scope.roles = response.data;
                console.log(response.data);
            })
            .catch(function (error) {
                console.error("Error fetching categories:", error);
            });
    }

    $scope.load();

    $scope.createCustomer = function () {
        var data = angular.copy($scope.formCreate);
        data.picture = document.getElementById('file-input1').files[0].name;
        $http.post(url, data)
            .then(function(response){
                console.log('Customer created successfully:', response.data);
                console.log(data.picture);
                console.log($scope.formCreate.roles.roleId);
                console.log($scope.formCreate.password);
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Customer created successfully!",
                    showConfirmButton: true,
                    timer: 150000
                }).then((result) => {
                    if (result.isConfirmed) {
                        $('#scrollModal').modal('hide');
                    }
                });
                $scope.load();
                $scope.resetform();

            })
            .catch(function(error){
                Swal.fire({
                    icon: "error",
                    title: "Customer created failed!"
                });
                console.error('Error creating customer:', error);
            });
    };


    $scope.deleteCustomer= function(event, item) {
        if (item && item.customerId) {
            $http.delete(url + '/'+ item.customerId)
                .then(function(response) {
                    console.log('Category deleted successfully:', response);
                    // Check if $scope.categories is defined before using filter
                    $scope.list = $scope.list.filter(function (news) {
                        return news.customerId !== item.customerId;
                    });
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Customer deleted successfully!",
                        showConfirmButton: false,
                        timer: 1500
                    });
                    $scope.resetform();
                    $scope.load();
                })
                .catch(function(error) {
                    console.error("Error deleting category:", error);
                    Swal.fire({
                        icon: "error",
                        title: "Customer deleted failed!"
                    });
                });
        }else{
            if (!$scope.form.customerId) {
                console.error('Cannot delete. No newsId specified.');
                Swal.fire({
                    icon: "error",
                    title: "Cannot delete. No customerId specified!"
                });
                return;
            }

            $http.delete(url + '/' + $scope.form.customerId)
                .then(function (response) {
                    console.log('Category deleted successfully:', response.data);
                    $scope.list = $scope.list.filter(function (news) {
                        return news.customerId !== $scope.form.customerId;
                    });
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Customer deleted successfully!",
                        showConfirmButton: false,
                        timer: 1500
                    });
                    $scope.resetform();
                    $scope.load();
                })
                .catch(function (error) {
                    console.error('Error deleting news:', error);
                    Swal.fire({
                        icon: "error",
                        title: "Customer deleted failed!"
                    });
                });
        }
    };

    $scope.edit = function(id) {
        console.log(id);
        $http.get(`${url}/${id}`).then(resp => {
            console.log(id);
            console.log(resp.data);
            $scope.form = resp.data;
            $scope.isEditing = true;
        });
    };

    $scope.resetform = function (item) {
        $scope.form = {};
        $scope.formCreate = {};
        $scope.isEditing = false;

    };

    $scope.updatecustomer = function () {
        var data = angular.copy($scope.form);
        var fileCustomer = document.getElementById('file-input').files[0];
        console.log($scope.form.picture);
        if(fileCustomer){
            data.picture = fileCustomer.name;
        } else {
            data.picture = $scope.form.picture;
        }
        $http.put(`${url}/${data.customerId}`, data).then(resp => {
            console.log(data.picture);
            console.log("success");
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Customer updated successfully!",
                showConfirmButton: false,
                timer: 1500
            });
            $scope.resetform();
            $scope.load();
        }).catch(error => {
            Swal.fire({
                icon: "error",
                title: "Customer updated failed!"
            });
            console.log(error);
        });
    };
})