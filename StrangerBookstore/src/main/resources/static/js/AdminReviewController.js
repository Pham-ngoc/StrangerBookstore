app.controller("AdminReviewController", function ($scope, $http) {
    var reviewsUrl = 'http://localhost:8080/admin/reviews';
    $scope.list = [];
    $scope.form = {};

       $http
           .get(reviewsUrl)
           .then(response=> {
               $scope.list = response.data;
               console.log(response.data);
           })
           .catch(function (error) {
               console.error("Error fetching categories:", error);
           });
 });