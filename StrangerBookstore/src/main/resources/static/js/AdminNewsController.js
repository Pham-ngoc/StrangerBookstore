app.controller("AdminNewsController", function ($scope, $http) {
    var newsUrl = 'http://localhost:8080/admin/news';
    $scope.list = [];
    $scope.form = {};

    $http
        .get(newsUrl)
        .then(response=> {
            $scope.list = response.data;
            console.log(response.data);
        })
        .catch(function (error) {
            console.error("Error fetching categories:", error);
        });
 });

