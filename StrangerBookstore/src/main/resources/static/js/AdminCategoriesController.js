

app.controller("AdminCategoriesController", function ($scope, $route, $timeout, $http) {
    var url = 'http://localhost:8080/admin/categories';
    $scope.list = [];
    $scope.form = { };
    $scope.isEditing = false;
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
        $http.post(url, $scope.form)
            .then(function(response) {
                console.log('News created successfully:', response.data);
                alert("Create successfully");

            })
            .catch(function(error) {
                console.error("Lỗi khi tạo mới danh mục:", error);
                alert("Create successfully");
            });
    };
    $scope.updatecategory = function () {
        $http.put(url + '/' + $scope.form.categoryId, $scope.form)
            .then(function (response) {
                console.log('News updated successfully:', response.data);
                alert("Update successfully");
                $scope.list = $scope.list.map(function (news) {
                    if (news.categoryId === $scope.form.categoryId) {
                        return $scope.form;
                    }
                    return news;
                });
            })
            .catch(function (error) {
                console.error('Error updating news:', error);
                alert("Update fail");
            });
    };

    $scope.deleteCategory = function(event, item) {


        if (item && item.categoryId) {
            $http.delete("http://localhost:8080/admin/categories/"+ item.categoryId)
                .then(function(response) {
                    console.log('Category deleted successfully:', response);

                    // Check if $scope.categories is defined before using filter
                    $scope.list = $scope.list.filter(function (news) {
                        return news.categoryId !== item.categoryId;
                    });
                    alert("Delete successfully");
                    $scope.resetForm();
                })
                .catch(function(error) {
                    console.error("Error deleting category:", error);
                });
        }else{
            if (!$scope.form.categoryId) {
                console.error('Cannot delete. No newsId specified.');
                alert("Delete fail");
                return;
            }

            $http.delete(url + '/' + $scope.form.categoryId)
                .then(function (response) {
                    console.log('Category deleted successfully:', response.data);
                    $scope.list = $scope.list.filter(function (news) {
                        return news.categoryId !== $scope.form.categoryId;
                    });


                    $scope.resetForm();
                })
                .catch(function (error) {
                    console.error('Error deleting news:', error);
                });
        }
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
    $scope.editCategory = function (item) {
        $scope.form.categoryId = item.categoryId ;
        $scope.form.categoryName = item.categoryName;
        $scope.isEditing = true;
    };
    $scope.resetForm = function () {
        event.preventDefault();
        $scope.form = {};
        $scope.isEditing = false;
    };
})
