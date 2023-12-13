app.controller("AdminCustomerController", function ($scope, $http) {
    var url = 'http://localhost:8080/admin/customer';
    $scope.list = [];
    $http
        .get(url)
        .then(response => {
            $scope.list = response.data;
            console.log(response.data);
        })
        .catch(function (error) {
            console.error("Error fetching categories:", error);
        });
    $scope.createCustomer = function () {
        var file = $scope.file;

        if (!file) {
            alert('Please choose a file.');
            return;
        }

        var formData = new FormData();
        formData.append('file', file);

        var customerData = {
            customerName: $scope.fullname,
            email: $scope.email,
            phoneNumber: $scope.phonenumber,
            status: $scope.selectedOption,
            roles: $scope.selectedRole,
            picture:file,
        };


        formData.append('customerData', JSON.stringify(customerData));

        $http.post("http://localhost:8080/admin/customer", formData, {
            transformRequest: angular.identity,
            headers: {
                'Content-Type': undefined,
                'Accept': 'application/json'
            }
        })
            .then(function (response) {
                console.log('Customer created successfully:', response.data);
            })
            .catch(function (error) {
                console.error('Error creating customer:', error);
            });
    };
})