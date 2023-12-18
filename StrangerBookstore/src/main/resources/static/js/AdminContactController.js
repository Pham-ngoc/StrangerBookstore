app.controller("AdminContactController", function ($scope, $route, $timeout, $http) {
    var url = 'http://localhost:8080/admin/contact';
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

    $scope.updatecontact = function () {
        $http.put(url + '/' + $scope.form.contactId, $scope.form)
            .then(function (response) {
                console.log('ContactS updated successfully:', response.data);
                $scope.list = $scope.list.map(function (news) {
                    if (news.contactId === $scope.form.contactId) {
                        return $scope.form;
                    }
                    return news;
                });
            })
            .catch(function (error) {
                console.error('Error updating news:', error);
            });
    };

    $scope.editcontact = function (item) {
        $scope.form.contactId = item.contactId ;
        $scope.form.fullName = item.fullName;
        $scope.form.email = item.email ;
        $scope.form.phoneNumber = item.phoneNumber;
        $scope.form.subject = item.subject ;
        $scope.form.message = item.message;
        $scope.form.status = item.status;
        $scope.isEditing = true;
    };

})