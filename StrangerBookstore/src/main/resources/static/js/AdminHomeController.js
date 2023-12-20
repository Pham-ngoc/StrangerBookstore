app.controller("AdminHomeController", function ($scope, $route, $timeout, $http) {
    var reportUrl = 'http://localhost:8080/report';

    $scope.listReport = [];


    // Thêm các biến cho phân trang
        $scope.pageSize = 5; // Số lượng mục trên mỗi trang
        $scope.currentPage = 1; // Trang hiện tại

        // Hàm để tính số lượng trang
        $scope.numberOfPages = function () {
            return Math.ceil($scope.listReport.length / $scope.pageSize);
        };

        // Hàm để load dữ liệu theo trang
        $scope.loadPage = function (page) {
            var start = (page - 1) * $scope.pageSize;
            var end = start + $scope.pageSize;
            $scope.displayedItems = $scope.listReport.slice(start, end);
        };

        $scope.load = function (){
            $http.get(reportUrl).then(response=> {
                        $scope.listReport = response.data;
                        $scope.loadPage($scope.currentPage);
                        $scope.account = response.data.count;
                        console.log(response.data);
                    }).catch(function (error) {
                        console.error("Error fetching categories:", error);
                    });
        }

        $scope.load();


});