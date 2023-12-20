

app.controller("AdminCategoriesController", function ($scope, $route, $timeout, $http) {
    var url = 'http://localhost:8080/admin/categories';
    $scope.list = [];
    $scope.form = { };
    $scope.isEditing = false;

    // Thêm các biến cho phân trang
        $scope.pageSize = 5; // Số lượng mục trên mỗi trang
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

    $scope.load = function(){
        $http.get(url).then(response => {
           $scope.list = response.data;
           console.log(response.data);
           $scope.loadPage($scope.currentPage);
        }).catch(function (error) {
           console.error("Error fetching categories:", error);
        });
    }

    $scope.load();

    $scope.createCategory = function() {
        $http.post(url, $scope.form)
            .then(function(response) {
                console.log('News created successfully:', response.data);
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Category created successfully!",
                    showConfirmButton: true,
                    timer: 150000
                });
                $scope.resetForm();
                $scope.load();
            })
            .catch(function(error) {
                console.error("Lỗi khi tạo mới danh mục:", error);
//                alert("Create successfully");
            Swal.fire({
                    icon: "error",
                    title: "Category created failed!"
                });
            });
    };
    $scope.updatecategory = function () {
        $http.put(url + '/' + $scope.form.categoryId, $scope.form)
            .then(function (response) {
                console.log('News updated successfully:', response.data);
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Category updated successfully!",
                    showConfirmButton: true,
                    timer: 150000
                });
                $scope.list = $scope.list.map(function (news) {
                    if (news.categoryId === $scope.form.categoryId) {
                        return $scope.form;
                    }
                    return news;
                });
                $scope.resetForm();
                $scope.load();
            })
            .catch(function (error) {
                console.error('Error updating news:', error);
                Swal.fire({
                        icon: "error",
                        title: "Category updated failed!"
                    });
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
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Category deleted successfully!",
                        showConfirmButton: true,
                        timer: 150000
                    });
                    $scope.resetForm();
                    $scope.load();
                })
                .catch(function(error) {
                    console.error("Error deleting category:", error);
                });
        }else{
            if (!$scope.form.categoryId) {
                console.error('Cannot delete. No newsId specified.');
                Swal.fire({
                        icon: "error",
                        title: "Category deleted failed!"
                    });
                return;
            }

            $http.delete(url + '/' + $scope.form.categoryId)
                .then(function (response) {
                    console.log('Category deleted successfully:', response.data);
                    $scope.list = $scope.list.filter(function (news) {
                        return news.categoryId !== $scope.form.categoryId;
                    });
                    Swal.fire({
                         position: "center",
                         icon: "success",
                         title: "Category deleted successfully!",
                         showConfirmButton: true,
                         timer: 150000
                    });
                    $scope.resetForm();
                    $scope.load();
                })
                .catch(function (error) {
                    console.error('Error deleting news:', error);
                        Swal.fire({
                            icon: "error",
                            title: "Category deleted failed!"
                        });
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
