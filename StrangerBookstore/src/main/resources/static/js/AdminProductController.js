app.controller("AdminProductController", function ($scope, $http, $window, $timeout) {
    var productUrl = 'http://localhost:8080/admin/product';
    var categoriesUrl = 'http://localhost:8080/admin/categories';

    $scope.category = [];
    $scope.list = [];
    $scope.form = {};
    $scope.searchKeyword = '';
    $scope.isEditing = false;


    // Thêm các biến cho phân trang
    $scope.pageSize = 9; // Số lượng mục trên mỗi trang
    $scope.currentPage = 1; // Trang hiện tại

    // Hàm để tính số lượng trang
    $scope.numberOfPages = function () {
        return Math.ceil($scope.list.length / $scope.pageSize);
    };

    // Hàm để load dữ liệu theo trang
    $scope.loadPage = function (page) {
        var start = (page - 1) * $scope.pageSize;
        var end = start + $scope.pageSize;
        $scope.displayedItems = $scope.list.slice(start, end);
    };

    // Fetch danh sách sản phẩm và danh mục từ server khi trang được load

    $scope.load = function(){
        $http.get(productUrl)
                .then(function (response) {
                    $scope.list = response.data;
                    $scope.loadPage($scope.currentPage);
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
    }

    $scope.load();

    $scope.createProduct = function () {
        $http.post(productUrl, $scope.form)
            .then(function (response) {
                console.log('Product created successfully:', response.data);
                // Thêm sản phẩm mới vào danh sách hiện tại
//                alert("Create successfully");
//               $scope.list.push(response.data);
               Swal.fire({
                   position: "center",
                   icon: "success",
                   title: "Product created successfully!",
                   showConfirmButton: true,
                   timer: 150000
               });
               $scope.load();
               $scope.resetForm();
            })
            .catch(function (error) {
               console.error('Error creating product:', error);
               alert("Create fail")
            });
    };

    //
    $scope.updateProduct = function () {
        $http.put(productUrl + '/' + $scope.form.productId, $scope.form)
            .then(function (response) {
                console.log('Product updated successfully:', response.data);
                // Cập nhật danh sách sản phẩm sau khi cập nhật
//                alert("Update Successfully");
                $scope.list = $scope.list.map(function (product) {
                    if (product.productId === $scope.form.productId) {
                        return $scope.form;
                        // $window.location.reload();
                    }
                    return product;
                });
                Swal.fire({
                   position: "center",
                   icon: "success",
                   title: "Product updated successfully!",
                   showConfirmButton: true,
                   timer: 150000
                });
                $scope.resetForm();
                $scope.load();
            })
            .catch(function (error) {
                console.error('Error updating product:', error);
                alert("Update fail");
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
                    Swal.fire({
                       position: "center",
                       icon: "success",
                       title: "Product deleted successfully!",
                       showConfirmButton: true,
                       timer: 150000
                    });
                    $scope.resetForm();
                    $scope.load();
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

                    Swal.fire({
                       position: "center",
                       icon: "success",
                       title: "Product deleted successfully!",
                       showConfirmButton: true,
                       timer: 150000
                    });
                    $scope.resetForm();
                    $scope.load();
                })
                .catch(function (error) {
                    console.error('Error deleting product:', error);
                });
        }
    };

    $scope.resetForm = function () {
        event.preventDefault();
        // Đặt lại giá trị của $scope.form về trạng thái ban đầu hoặc giá trị mặc định
        var fileInput = document.getElementById('product_img');
        if (fileInput) {
            fileInput.value = ''; // Xóa giá trị của input file
        }
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
                    $scope.loadPage($scope.currentPage);
                })
                .catch(function (error) {
                    console.error("Error fetching products:", error);
                });
        } else {
            // Ngược lại, lọc theo từ khóa tìm kiếm
            $scope.displayedItems = $scope.list = $scope.list.filter(function (product) {
                return product.productId.toString().includes($scope.searchKeyword) ||
                    product.productName?.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    product.author?.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    product.publisher?.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    product.language?.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    product.condition?.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    product.quantityInStock.toString().includes($scope.searchKeyword) ||
                    product.isbn?.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    product.description?.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                    product.price.toString().includes($scope.searchKeyword) ||
                    product.categories?.categoryName.toLowerCase().includes($scope.searchKeyword.toLowerCase())||
                    (product.product_img && product.product_img?.toLowerCase().includes($scope.searchKeyword.toLowerCase()));
            });
        }
    };

});
