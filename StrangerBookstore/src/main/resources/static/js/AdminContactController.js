app.controller("AdminContactController", function ($scope, $route, $timeout, $http) {
    var url = 'http://localhost:8080/admin/contact';
    $scope.list = [];
    $scope.form = { };
    $scope.isEditing = false;

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
    $http
            .get(url)
            .then(response => {
                $scope.list = response.data;
                $scope.loadPage($scope.currentPage);
                console.log(response.data);
            })
            .catch(function (error) {
                console.error("Error fetching categories:", error);
            });
    }

    $scope.load();

    $scope.updatecontact = function () {
        $http.put(url + '/' + $scope.form.contactId, $scope.form)
            .then(function (response) {
                console.log('ContactS updated successfully:', response.data);
                $scope.list = $scope.list.map(function (news) {
                    if (news.contactId === $scope.form.contactId) {
                        return $scope.form;
                    }
                    return news;
                });
                Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Contact updated successfully!",
                        showConfirmButton: false,
                        timer: 1500
                });

                $scope.load();

            })
            .catch(function (error) {
                console.error('Error updating news:', error);
                Swal.fire({
                        icon: "error",
                        title: "Contact updated failed!"
                    });
                });
    };

    $scope.resetFrom = function (){
        $scope.form = {};
    }

    $scope.editcontact = function (item) {
        $scope.form.contactId = item.contactId ;
        $scope.form.fullName = item.fullName;
        $scope.form.email = item.email ;
        $scope.form.phoneNumber = item.phoneNumber;
        $scope.form.subject = item.subject ;
        $scope.form.message = item.message;
        $scope.form.status = item.status;
        $scope.isEditing = true;
    };

})