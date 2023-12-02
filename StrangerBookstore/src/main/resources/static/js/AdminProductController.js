app.controller("AdminProductController", function ($scope, $http) {
    var url = 'http://localhost:8080/admin/product';
    $scope.list = [];
    $scope.form = {};
    $http
        .get(url)
        .then(response=> {
            $scope.list = response.data;
            console.log(response.data);
        })
        .catch(function (error) {
            console.error("Error fetching categories:", error);
        });
    $scope.createproduct = function() {
        /*if ($scope.newproductname && $scope.newauthor && $scope.newpublisher) {
            // Assuming you have the correct properties for the product
            var productData = {
                productName: $scope.newproductname,
                author: $scope.newauthor,
                publisher: $scope.newpublisher
                // Add other properties as needed
            };

            $http.post(url, productData)
                .then(function(response) {
                    $scope.products.push(response.data); // Assuming you have a products array
                    // Reset input fields after successful creation
                    $scope.newproductname = '';
                    $scope.newauthor = '';
                    $scope.newpublisher = '';
                })
                .catch(function(error) {
                    console.error("Error creating product:", error);
                });
        } else {
            console.error("Incomplete information for creating a product");
        }*/

        var item = angular.copy($scope.form);
                $http.post(url,item).then(resp => {
                    resp.data.create_at='';
                    resp.data.create_by='';

                    $scope.items.push(resp.data);

                    alert("Thêm mới thành công!");
                }).catch(error => {
                    alert("Lỗi thêm mới sản phẩm!");
                    console.log("Error",error);
                });
    };

          $scope.updateproduct = function(category) {
              $http.put(url + category.id, category)
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

           $scope.deleteCategory = function(productId) {
                  console.log('Deleting category with ID:', productId);

                  if (confirm("Bạn có muốn xóa loại sản phẩm này không?")) {
                      $http.delete("http://localhost:8080/admin/product/"+ productId)
                          .then(function(response) {
                              console.log('Category deleted successfully:', response);
                              if ($scope.product) {
                                  $scope.product = $scope.product.filter(function(pro) {
                                      return pro.id !== productId;
                                  });
                                  console.log('Updated categories:', $scope.pro);
                                  alert("Bạn đã xóa thành công?");
                              } else {
                                  console.warn('$scope.categories is undefined.');
                              }
                          })
                          .catch(function(error) {
                              console.error("Error deleting category:", error);
                          });
                  }
              };


         });