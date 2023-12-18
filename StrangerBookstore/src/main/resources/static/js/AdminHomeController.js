app.controller("AdminHomeController", function ($scope, $http) {
    var url = 'http://localhost:8080/admin/customer';

    $http.get(url).then(response => {
        console.log(response.data);
         $scope.customerImage = "/customer_pic/" + response.data.loggingCustomer.picture;
    }).catch(function (error) {
        console.error("Error:", error);
    });
});