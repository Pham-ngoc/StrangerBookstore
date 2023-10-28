var app = angular.module("myApp",["ngRoute"]);
app.config(function($routeProvider){
    $routeProvider
    .when("/", {
        templateUrl : "Admin-view/admin-dashboard.html",
        controller: "AdminHomeController"
    })
    .when("/product",{
        templateUrl: "Admin-view/admin-product.html",
        controller: "AdminProductController"
    })
    .when("/categories", {
        templateUrl: "Admin-view/admin-categories.html",
        controller: "AdminCategoriesController"
    })
    .when("/customer", {
       templateUrl: "Admin-view/admin-customer.html",
       controller: "AdminCustomerController"
    })
    .when("/order",{
        templateUrl: "Admin-view/admin-order.html",
        controller : "AdminOrderController"
    })
    .when("/orderDetail",{
        templateUrl: "Admin-view/admin-orderdetail.html",
        controller: "AdminOrderDetailController"
    })
    .when("/news",{
        templateUrl: "Admin-view/admin-news.html",
        controller: "AdminNewsController"
    })
    .when("/pictureProduct",{
        templateUrl: "Admin-view/admin-pictureProduct.html",
        controller: "AdminPicProductController"
    })
    .when("/shipInformation",{
        templateUrl: "Admin-view/admin-shipInfor.html",
        controller: "AdminShipController"
    })
    .when("/contact",{
        templateUrl: "Admin-view/admin-contact.html",
        controller: "AdminContactController"
    })
    .when("/reviews",{
        templateUrl: "Admin-view/admin-review.html",
        controller: "AdminReviewController"
    })
    .when("/report",{
        templateUrl: "Admin-view/admin-report.html",
        controller: "AdminReportController"
    })
    .otherwise({
        redirectTo: "/"
    });
});