app.controller("AdminProductController", function ($scope, $http) {
    var productUrl = 'http://localhost:8080/admin/product';
    var categoriesUrl = 'http://localhost:8080/admin/categories';
    $scope.category=[];
    $scope.list = [];
    $scope.form = {};

    $http
        .get(productUrl)
        .then(response=> {
            $scope.list = response.data;
            console.log(response.data);
        })
        .catch(function (error) {
            console.error("Error fetching categories:", error);
        });

        $http
                .get(categoriesUrl)
                .then(response=> {
                    $scope.category= response.data;
                    console.log(response.data);
                })
                .catch(function (error) {
                    console.error("Error fetching categories:", error);
                });



    $scope.createProduct = function () {
            // Gửi dữ liệu sản phẩm lên server để tạo sản phẩm
            $http.post('http://localhost:8080/admin/product', $scope.form)
                .then(function (response) {
                    // Xử lý kết quả từ server nếu cần
                    console.log('Product created successfully:', response.data);
                    // Có thể thực hiện các bước khác như làm mới danh sách sản phẩm, hiển thị thông báo, vv.
                })
                .catch(function (error) {
                    // Xử lý lỗi nếu có
                    console.error('Error creating product:', error);
                });
        };

//    $scope.create=function(){
//            var item=angular.copy($scope.form);
//            $http.post(productUrl,list).then(resp=>{
//
//                $scope.list.push(resp.data);
//
//                alert("Thêm mới thành công!");
//            }).catch(error=>{
//                alert("Thêm mới không thành công");
//                console.log("Error",error);
//            });
//        }


$scope.editNews = function (item) {
            // Gán dữ liệu từ hàng đã click vào biến $scope.form
            $scope.form.productId = item.productId;
            $scope.form.productName = item.productName;
            $scope.form.author = item.author;
            $scope.form.publisher = item.publisher;
            $scope.form.language = item.language;
            $scope.form.condition = item.condition;
            $scope.form.quantityInStock = item.quantityInStock;
            $scope.form.isbn = item.isbn;
            $scope.form.price = item.price;
            $scope.form.description = item.description;
            $scope.form.product_img = item.product_img;
            $scope.form.categories = item.categories;
            // Các trường dữ liệu khác nếu cần
        };

////////////////////////////////////////////////////////////////////////
          $scope.updateproduct = function(category) {
              $http.put(productUrl + category.id, category)
                  .then(function (response) {
                      // Update the category in the local array
                      var index = $scope.categories.findIndex(function (cat) {
                          return cat.id === category.id;
                      });

                      if (index !== -1) {
                          $scope.categories[index] = response.data;
                      }
                  })
                  .catch(function (error) {
                      console.error("Error updating category:", error);
                  });
          }

           $scope.deleteProduct = function (item) {
                        // Gửi yêu cầu DELETE tới server để xóa tin tức
                        $http.delete('http://localhost:8080/admin/product/' + item.productId)
                            .then(function (response) {
                                console.log('News deleted successfully:', response.data);

                                // Cập nhật danh sách tin tức sau khi xóa
                                $scope.list = $scope.list.filter(function (news) {
                                    return product.productId !== item.productId;
                                });

                                // Các bước khác sau khi xóa tin tức
                            })
                            .catch(function (error) {
                                console.error('Error deleting news:', error);
                            });
                    };


         });