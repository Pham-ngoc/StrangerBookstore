app.controller("AdminProductController", function($scope, $http) {
    var productUrl = 'http://localhost:8080/products';
        var categoryUrl = 'http://localhost:8080/categories';

        $scope.form = {};
        $scope.productList = {};
        $scope.currentPage = 0;
        $scope.categories = [];

        $scope.load = function(pageNumber) {
//                $http.get(productUrl, { params: { pageNumber: pageNumber } }).then(resp => {
//                    console.log(resp.data);
//                    $scope.productList = resp.data;
//                    console.log("After API Call - Updated Page:", pageNumber);
//                });
                $http.get(productUrl).then(resp => {
                            console.log(resp.data);
                            $scope.productList = resp.data;
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
                // Tạo đối tượng chứa dữ liệu để gửi đến API
                        var data = {
                            productId: $scope.form.productId,
                            productName: $scope.form.productName,
                            author: $scope.form.author,
                            publisher: $scope.form.publisher,
                            language: $scope.form.language,
                            condition: $scope.form.condition,
                            quantityInStock: $scope.form.quantityInStock,
                            isbn: $scope.form.isbn,
                            description: $scope.form.description,
                            price: $scope.form.price,
                            categories: {
                                categoryId: $scope.form.categories.categoryId
                            },
                            product_img: $scope.form.product_img
                        };

                        // Gửi yêu cầu POST đến API
                        $http.post(productUrl, data)
                            .then(function(response) {
                                // Xử lý kết quả thành công
                                console.log('Create successful:', response.data);

                                // Sau khi tạo thành công, bạn có thể thực hiện các bước khác như làm mới form hoặc chuyển hướng trang
                                $scope.reset();
                            })
                            .catch(function(error) {
                                // Xử lý lỗi
                                console.error('Error creating product:', error);
                            });
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
