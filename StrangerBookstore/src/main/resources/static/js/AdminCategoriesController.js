app.controller("AdminCategoriesController", function ($scope, $http) {
    var url = 'http://localhost:8080/admin/categories';
    $scope.list = [];
    $http
        .get(url)
        .then(response => {
            $scope.list = response.data;
            console.log(response.data);
        })
        .catch(function (error) {
            console.error("Error fetching categories:", error);
        });
    $scope.createCategory = function() {
        if ($scope.newcategoryName) {
            $http.post("/admin/categories", { categoryName: $scope.newcategoryName })
                .then(function(response) {
                    $scope.categories.push(response.data);
                    $scope.newcategoryName = '';
                })
                .catch(function(error) {
                    console.error("Lỗi khi tạo mới danh mục:", error);
                });
        }
    };
    $scope.updateCategory = function(category) {
        $http.put("/admin/categories/" + category.id, category)
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
    $scope.deleteCategory = function(category) {
        if (confirm("Bạn có muốn xóa loại sản phẩm này không?")) {
            $http.delete("/admin/categories/" + category.id)
                .then(function(response) {
                    // Remove the deleted category from the local array
                    $scope.categories = $scope.categories.filter(function(cat) {
                        return cat.id !== category.id;
                    });
                })
                .catch(function(error) {
                    console.error("Error deleting category:", error);
                });
        }
    };
});
