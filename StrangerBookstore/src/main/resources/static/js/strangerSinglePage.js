var app = angular.module("myApp",["ngRoute"]);
app.config(function($routeProvider){
    $routeProvider
    .when("/admin", {
        templateUrl : "Admin-view/admin-dashboard.html",
        controller: "AdminHomeController"
    })
    .when("/admin/product",{
        templateUrl: "Admin-view/admin-product.html",
        controller: "AdminProductController"
    })
    .when("/admin/categories", {
        templateUrl: "Admin-view/admin-categories.html",
        controller: "AdminCategoriesController"
    })
    .when("/admin/customer", {
       templateUrl: "Admin-view/admin-customer.html",
       controller: "AdminCustomerController"
    })
    .when("/admin/order",{
        templateUrl: "Admin-view/admin-order.html",
        controller : "AdminOrderController"
    })
    .when("/admin/orderDetail",{
        templateUrl: "Admin-view/admin-orderdetail.html",
        controller: "AdminOrderDetailController"
    })
    .when("/admin/news",{
        templateUrl: "Admin-view/admin-news.html",
        controller: "AdminNewsController"
    })
    .when("/admin/pictureProduct",{
        templateUrl: "Admin-view/admin-pictureProduct.html",
        controller: "AdminPicProductController"
    })
    .when("/admin/shipInformation",{
        templateUrl: "Admin-view/admin-shipInfor.html",
        controller: "AdminShipController"
    })
    .when("/admin/contact",{
        templateUrl: "Admin-view/admin-contact.html",
        controller: "AdminContactController"
    })
    .when("/admin/reviews",{
        templateUrl: "Admin-view/admin-review.html",
        controller: "AdminReviewController"
    })
    .when("/admin/report",{
        templateUrl: "Admin-view/admin-report.html",
        controller: "AdminReportController"
    })
    .otherwise({
        redirectTo: "/admin"
    });
});