app.controller("AdminProductController", function ($scope, $http, $window) {
    var productUrl = 'http://localhost:8080/admin/product';
    var categoriesUrl = 'http://localhost:8080/admin/categories';

    $scope.category = [];
    $scope.list = [];
    $scope.form = {};
    $scope.searchKeyword = '';
    $scope.isEditing = false;

    // Fetch danh sách sản phẩm và danh mục từ server khi trang được load
    $http.get(productUrl)
        .then(function (response) {
            $scope.list = response.data;
        })
        .catch(function (error) {
            console.error("Error fetching products:", error);
        });

    $http.get(categoriesUrl)
        .then(function (response) {
            $scope.categories = response.data;
        })
        .catch(function (error) {
            console.error("Error fetching categories:", error);
        });

    $scope.createProduct = function () {
        var fileInput = document.getElementById('product_img');
        var fileName = fileInput ? fileInput.value : '';
        $http.post(productUrl, $scope.form)
            .then(function (response) {
                console.log('Product created successfully:', response.data);
                 $window.location.reload();
            })
            .catch(function (error) {
                console.error('Error creating product:', error);
            });
    };


//        $scope.updateProduct = function () {
//            // Gửi yêu cầu PUT tới server để cập nhật sản phẩm
//            $http.put(productUrl + '/' + $scope.form.productId, $scope.form)
//                .then(function (response) {
//                    console.log('Product updated successfully:', response.data);
//                    $scope.list = $scope.list.map(function (product) {
//                        if (product.productId === $scope.form.productId) {
//                            return $scope.form;
//                        }
//                        return product;
//                    });
//                    // Di chuyển location.reload() vào đây nếu cần
//                    $scope.resetForm();  // Đặt lại form sau khi cập nhật thành công
//                })
//                .catch(function (error) {
//                    console.error('Error updating product:', error);
//                });
//        };

        //
        $scope.updateProduct = function () {
            // Gửi yêu cầu PUT tới server để cập nhật sản phẩm
            $http.put(productUrl + '/' + $scope.form.productId, $scope.form)
                .then(function (response) {
                    console.log('Product updated successfully:', response.data);
                    // Cập nhật danh sách sản phẩm sau khi cập nhật
                    $scope.list = $scope.list.map(function (product) {
                        if (product.productId === $scope.form.productId) {
                            return $scope.form;
                        }
                        return product;
                    });
                    $scope.resetForm();
                })
                .catch(function (error) {
                    console.error('Error updating product:', error);
                });
        };



//

    $scope.editProduct = function (item) {
        // Gán dữ liệu từ hàng đã click vào biến $scope.form
        $scope.form.productId = item.productId;
        $scope.form.productName = item.productName;
        $scope.form.author = item.author;
        $scope.form.publisher = item.publisher;
        $scope.form.language = item.language;
        $scope.form.condition = item.condition;
        $scope.form.quantityInStock = item.quantityInStock;
        $scope.form.isbn = item.isbn;
        $scope.form.description = item.description;
        $scope.form.price = item.price;
        $scope.form.categories = {
                    categoryId: item.categories.categoryId,
                    categoryName: item.categories.categoryName
                };
        $scope.form.product_img = item.product_img;
        $scope.isEditing = true;
        $scope.updateFileName();
        // Các trường dữ liệu khác nếu cần
    };

    $scope.deleteProduct = function (event, item) {
        event.stopPropagation();

        if (item && item.productId) {
            // Xử lý delete 2: Gửi yêu cầu DELETE tới server để xóa sản phẩm
            $http.delete(productUrl + '/' + item.productId)
                .then(function (response) {
                    console.log('Product deleted successfully:', response.data);

                    // Cập nhật danh sách sản phẩm sau khi xóa
                    $scope.list = $scope.list.filter(function (product) {
                        return product.productId !== item.productId;
                    });

                    // Đặt lại giá trị của $scope.form về trạng thái ban đầu
                    $scope.resetForm();
                })
                .catch(function (error) {
                    console.error('Error deleting product:', error);
                });
        } else {
            // Xử lý delete 1: Gửi yêu cầu DELETE tới server dựa trên $scope.form.productId
            if (!$scope.form.productId) {
                console.error('Cannot delete. No productId specified.');
                return;
            }

            $http.delete(productUrl + '/' + $scope.form.productId)
                .then(function (response) {
                    console.log('Product deleted successfully:', response.data);

                    // Cập nhật danh sách sản phẩm sau khi xóa
                    $scope.list = $scope.list.filter(function (product) {
                        return product.productId !== $scope.form.productId;
                    });

                    // Đặt lại giá trị của $scope.form về trạng thái ban đầu
                    $scope.resetForm();
                })
                .catch(function (error) {
                    console.error('Error deleting product:', error);
                });
        }
    };

    $scope.resetForm = function () {
        event.preventDefault();
        // Đặt lại giá trị của $scope.form về trạng thái ban đầu hoặc giá trị mặc định
        $scope.form = {};
        $scope.isEditing = false;
        $scope.searchKeyword = '';
    };



    $scope.updateFileName = function () {
        var fileInput = document.getElementById('product_img');
        if (fileInput && fileInput.files.length > 0) {
            $scope.form.product_img = fileInput.files[0].name;
        } else if (!$scope.isEditing) {
            $scope.form.product_img = '';
        }
    };





    $scope.searchProduct = function () {
        if (!$scope.searchKeyword || $scope.searchKeyword.trim() === '') {
            // Nếu ô tìm kiếm trống, hiển thị toàn bộ dữ liệu
            $http.get(productUrl)
                .then(function (response) {
                    $scope.list = response.data;
                })
                .catch(function (error) {
                    console.error("Error fetching products:", error);
                });
        } else {
            // Ngược lại, lọc theo từ khóa tìm kiếm
            $scope.list = $scope.list.filter(function (product) {
                return product.productId.toString().includes($scope.searchKeyword) ||
                    product.productName.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    product.author.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    product.publisher.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    product.language.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    product.condition.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    product.quantityInStock.toString().includes($scope.searchKeyword) ||
                    product.isbn.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    product.description.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    product.price.toString().includes($scope.searchKeyword) ||
                    product.categories.categoryName.toLowerCase().includes($scope.searchKeyword.toLowerCase())||
                    (product.product_img && product.product_img.toLowerCase().includes($scope.searchKeyword.toLowerCase()));
            });
        }
    };

});
