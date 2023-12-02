app.controller("AdminReviewController", function ($scope, $http) {
    var url = 'http://localhost:8080/admin/reviews';
    $scope.list = [];
       $http
           .get(url)
           .then(response=> {
               $scope.list = response.data;
               console.log(response.data);
           })
           .catch(function (error) {
               console.error("Error fetching categories:", error);
           });
 });