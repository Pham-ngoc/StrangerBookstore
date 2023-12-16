

app.controller("AdminCustomerController", function ($scope, $http) {
    var url = 'http://localhost:8080/admin/customer';
    $scope.list = [];
    $scope.form = {};
    $scope.isEditing = false;
    $http
        .get(url)
        .then(response => {
            $scope.list = response.data;
            console.log(response.data);
        })
        .catch(function (error) {
            console.error("Error fetching categories:", error);
        });
    $scope.createCustormer = function () {
        $http.post(url, $scope.form)
            .then(function (response) {
                console.log('Custormer created successfully:', response.data);
                console.log($scope.form);
            })
            .catch(function (error) {
                console.error('Error creating custormer:', error);
            });
    };


    $scope.deleteCusstormer= function(event, item) {


        if (item && item.customerId) {
            $http.delete(url + '/'+ item.customerId)
                .then(function(response) {
                    console.log('Category deleted successfully:', response);

                    // Check if $scope.categories is defined before using filter
                    $scope.list = $scope.list.filter(function (news) {
                        return news.customerId !== item.customerId;
                    });
                    $scope.resetForm();
                })
                .catch(function(error) {
                    console.error("Error deleting category:", error);
                });
        }else{
            if (!$scope.form.customerId) {
                console.error('Cannot delete. No newsId specified.');
                return;
            }

            $http.delete(url + '/' + $scope.form.customerId)
                .then(function (response) {
                    console.log('Category deleted successfully:', response.data);
                    $scope.list = $scope.list.filter(function (news) {
                        return news.customerId !== $scope.form.customerId;
                    });


                    $scope.resetForm();
                })
                .catch(function (error) {
                    console.error('Error deleting news:', error);
                });
        }
    };


    $scope.updateFileName = function () {
        var fileInput = document.getElementById('file-input1');
        if (fileInput.files.length > 0) {
            $scope.form.picture = fileInput.files[0].name;
        } else if (!$scope.isEditing) {
            $scope.form.picture = '';
        }
    };

    $scope.editNews = function (item) {
        // Gán dữ liệu từ hàng đã click vào biến $scope.form
        $scope.form.customerId = item.customerId;
        $scope.form.customerName = item.customerName;
        $scope.form.email = item.email;
        $scope.form.phoneNumber = item.phoneNumber;
        $scope.form.status = item.status;
        $scope.form.roles= item.roles;
        $scope.form.picture = item.picture;
        $scope.isEditing = true;
        $scope.updateFileName();

    };
    $scope.resetform = function (item) {
        $scope.form = {};
        $scope.isEditing = false;

    };

    $scope.updatecustormer = function () {
        $http.put(url + '/' + $scope.form.customerId, $scope.form)
            .then(function (response) {
                console.log('News updated successfully:', response.data);
                $scope.list = $scope.list.map(function (news) {
                    if (news.customerId === $scope.form.customerId) {
                        return $scope.form;
                    }
                    return news;
                });
            })
            .catch(function (error) {
                console.error('Error updating news:', error);
            });
    };
})