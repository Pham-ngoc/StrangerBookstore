 app.controller("AdminOrderController", function ($scope, $http) {
 var orderUrl = 'http://localhost:8080/admin/order';
    $scope.list = [];
    $scope.form = {};
    $scope.load = function(){
            $http.get(orderUrl).then(response=> {
                $scope.list = response.data;
                console.log(response.data);
            })
            .catch(function (error) {
                console.error("Error fetching categories:", error);
            });
    };
    $scope.load();
});