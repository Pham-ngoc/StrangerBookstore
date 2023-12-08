 app.controller("AdminShipController", function ($scope, $http) {
 var shipUrl = 'http://localhost:8080/admin/shipInformation';
    $scope.list = [];
    $scope.form = {};
    $http
        .get(shipUrl)
        .then(response=> {
                    $scope.list = response.data;
                    console.log(response.data);
                })
                .catch(function (error) {
                    console.error("Error fetching categories:", error);
                });
});