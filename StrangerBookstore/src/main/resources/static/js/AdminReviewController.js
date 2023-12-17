app.controller("AdminReviewController", function($scope, $http) {
    var reviewsUrl = 'http://localhost:8080/admin/reviews';
     $scope.form = {};
     $scope.reviewsList = [];

     $scope.load = function() {
         $http.get(reviewsUrl).then(resp => {
               console.log(resp.data);
               $scope.reviewsList = resp.data;
         });
     }

     $scope.edit = function(id) {
        console.log("ID to fetch:", id);
         $http.get(`${reviewsUrl}/${id}`).then(resp => {
              console.log("API Response:", resp.data);
              $scope.form = resp.data;
              console.log("Form Data:", $scope.form);
              $('#scrollmodal').modal('show');
         }).catch(function(error) {
               console.error('Error fetching review data:', error);
         });
     };

     $scope.load();
});