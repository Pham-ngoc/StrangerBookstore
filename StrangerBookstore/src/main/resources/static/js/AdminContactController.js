app.controller("AdminContactController", function ($scope, $route, $timeout, $http) {
    var url = 'http://localhost:8080/admin/contact';
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

})