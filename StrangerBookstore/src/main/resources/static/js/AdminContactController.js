app.controller("AdminContactController", function ($scope, $route, $timeout, $http) {
    var url = 'http://localhost:8080/admin/contact';
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

    $scope.deleteCategory = function(contactId) {
        console.log('Deleting category with ID:', contactId);

        if (confirm("Bạn có muốn xóa loại sản phẩm này không?")) {
            $http.delete("http://localhost:8080/admin/contact/"+ contactId)
                .then(function(response) {
                    console.log('Category deleted successfully:', response);

                    // Check if $scope.categories is defined before using filter
                    if ($scope.contact) {
                        $scope.contact = $scope.contact.filter(function(cat) {
                            return cat.id !== contactId;

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
        }}

})