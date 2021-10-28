angular.module('front-shop').controller('createProductController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/shop';

    $scope.createProduct = function () {
        if ($scope.new_product == null) {
            alert('Data not filled');
            return;
        }
        $http.post(contextPath + '/api/v1/products', $scope.new_product)
            .then(function successCallback (response) {
                $scope.new_product = null;
                alert('Product successfully added');
                $location.path('/catalog');
            }, function failureCallback (response) {
                console.log(response);
                alert(response.data.messages);
            });
    }
});