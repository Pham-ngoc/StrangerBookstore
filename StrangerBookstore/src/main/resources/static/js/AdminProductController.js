app.controller("AdminProductController", function($scope, $http) {
    var productUrl = 'http://localhost:8080/products';
        var categoryUrl = 'http://localhost:8080/categories';

        $scope.form = {};
        $scope.productList = {};
        $scope.currentPage = 0;
        $scope.categories = [];

        $scope.load = function(pageNumber) {
                $http.get(productUrl, { params: { pageNumber: pageNumber } }).then(resp => {
                    console.log(resp.data);
                    $scope.productList = resp.data;
                    console.log("After API Call - Updated Page:", pageNumber);
                });
                $http.get(categoryUrl).then(resp => {
                    $scope.categories = resp.data;
                });
            };

        $scope.next = function(){
            $scope.load(++$scope.currentPage);
        }

        $scope.previous = function(){
            $scope.load(--$scope.currentPage);
        }

        $scope.edit = function(id) {
            $http.get(`${productUrl}/${id}`).then(resp => {
                console.log(resp.data);
                $scope.form = resp.data;
            });
        };

        $scope.reset = function() {
            $scope.form = {};
        };

        $scope.create = function() {
                var data = angular.copy($scope.form);
                        var imageFile = document.getElementById("imageInput").files[0];
                        data.imageFile = imageFile;

                                // Use FormData to send both form data and image file
                                var formData = new FormData();
                                formData.append("imageFile", imageFile);
                                formData.append("productName", data.productName);
                                formData.append("author", data.author);
                                formData.append("publisher", data.publisher);
                                formData.append("language", data.language);
                                formData.append("condition", data.condition);
                                formData.append("quantityInStock", data.quantityInStock);
                                formData.append("isbn", data.isbn);
                                formData.append("description", data.description);
                                formData.append("price", data.price);
                                formData.append("category", data.categories.categoryId);

                                if ($scope.form.$invalid) {
                                    $scope.showErrorMessage = true;
                                    return;
                                } else{
                                    $http.post(productUrl, formData, {
                                        headers: {
                                             "Content-Type": undefined
                                        }
                                    })
                                    .then(resp => {
                                        Swal.fire({
                                          position: "top-end",
                                          icon: "success",
                                          title: "Your work has been saved",
                                          showConfirmButton: false,
                                          timer: 1500
                                        });
                                        $scope.reset();
                                        $scope.load();
                                    }).catch(error => {
                                        Swal.fire({
                                          icon: "error",
                                          title: "Oops...",
                                          text: "Something went wrong!",
                                          footer: '<a href="#">Why do I have this issue?</a>'
                                        });
                                        console.log(error);
                                    });
                                }


        };

        $scope.update = function() {
            var data = angular.copy($scope.form);
            // Get the selected image file
                    var imageFile = document.getElementById("imageInput").files[0];
                    data.imageFile = imageFile;

                    // Use FormData to send both form data and image file
                    var formData = new FormData();
                    formData.append("imageFile", imageFile);
                    formData.append("productName", data.productName);
                    formData.append("author", data.author);
                    formData.append("publisher", data.publisher);
                    formData.append("language", data.language);
                    formData.append("condition", data.condition);
                    formData.append("quantityInStock", data.quantityInStock);
                    formData.append("isbn", data.isbn);
                    formData.append("description", data.description);
                    formData.append("price", data.price);
                    formData.append("category", data.categories.categoryId);

                    $http.put(`${productUrl}/${data.productId}`, formData, {
                        headers: {
                            "Content-Type": undefined // Let AngularJS set appropriate headers
                        }
                    }).then(resp => {
                        $scope.load();
                    }).catch(error => {
                        alert("Không tìm thấy sản phẩm để cập nhật");
                    });
        };

        $scope.delete = function(id) {
            $http.delete(`${productUrl}/${id}`).then(resp => {
                $scope.reset();
                $scope.load();
            });
        };

       $scope.load(0);
});
