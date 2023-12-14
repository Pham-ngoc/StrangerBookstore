

app.controller("AdminCategoriesController", function ($scope, $route, $timeout, $http) {
    var url = 'http://localhost:8080/admin/categories';
    $scope.list = [];
    var key = null;
    $scope.editMode = false;
    $scope.selectedItem = null;
    $scope.query = '';
    $scope.categories = [];
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
            $http.post(url, { categoryName: $scope.newcategoryName })
                .then(function(response) {
                    if (!$scope.categories) {
                        $scope.categories = [];
                    }
                    $scope.categories.push(response.data);
                    $scope.newcategoryName = '';
                    $scope.successMessage = 'Thêm mới thành công!';
                    $timeout(function() {
                        $route.reload();
                    }, 1000);

                })
                .catch(function(error) {
                    console.error("Lỗi khi tạo mới danh mục:", error);
                });
        }
    };
    $scope.updateCategory = function(category) {
        $http.put("http://localhost:8080/admin/categories/" + category.id, category)
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
    $scope.deleteCategory = function(categoryId) {
        console.log('Deleting category with ID:', categoryId);

        if (confirm("Bạn có muốn xóa loại sản phẩm này không?")) {
            $http.delete("http://localhost:8080/admin/categories/"+ categoryId)
                .then(function(response) {
                    console.log('Category deleted successfully:', response);

                    // Check if $scope.categories is defined before using filter
                    if ($scope.categories) {
                        $scope.categories = $scope.categories.filter(function(cat) {
                            return cat.id !== categoryId;

                        });

                    } else {
                        console.warn('$scope.categories is undefined.');
                    }
                    $timeout(function() {
                        $route.reload();
                        alert('Xóa thành công loại sản phẩm!');
                    }, 1000);
                })
                .catch(function(error) {
                    console.error("Error deleting category:", error);
                });
        }
    };



    $scope.editCategory = function(categoryId) {
        $http.get("http://localhost:8080/admin/categories/" + categoryId, {
            transformResponse: function(data, headersGetter, status) {
                try {
                    return JSON.parse(data);
                } catch (error) {
                    console.error('Error parsing JSON:', error);
                    return { error: 'Invalid JSON response' };
                }
            }
        })
            .then(function(response) {
                // Check for an error in the response
                if (response && response.data && response.data.error) {
                    console.error('Error in server response:', response.data.error);
                    return;
                }

                // Continue processing the response as needed
                console.log('Response:', response);

                if (response && response.data) {
                    $scope.category_Id = response.data.id;
                    $scope.newcategoryName = response.data.categoryName;
                    // Add more assignments for other properties if needed
                } else {
                    console.error('Invalid response or missing data:', response);
                }
            })
            .catch(function(error) {
                console.error('Error loading category:', error);
            });
    };



    $scope.resetForm = function(){
        $scope.newcategoryName=null;
        $scope.category_Id=null;
    }
    $scope.searchCategories = function() {
        $http.get('http://localhost:8080/admin/categories/sreach', {
            params: { query: $scope.query }
        })
            .then(function(response) {
                $scope.categories = response.data;

            })
            .catch(function(error) {
                console.error('Error searching categories:', error);
            });
    };
})
