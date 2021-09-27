angular.module('front-shop').controller('editProductController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://localhost:8189/shop';

    $scope.prepareProductForUpdate = function () {
        $http.get(contextPath + '/api/v1/products/' + $routeParams.productId)
            .then(function successCallback (response) {
                $scope.updated_product = response.data;
            }, function failureCallback (response) {
                console.log(response);
                alert(response.data.messages);
                $location.path('/catalog');
            });
    }

    $scope.updateProduct = function () {
        $http.put(contextPath + '/api/v1/products', $scope.updated_product)
            .then(function successCallback (response) {
                $scope.updated_product = null;
                alert('Product successfully saved');
                $location.path('/catalog');
            }, function failureCallback (response) {
                alert(response.data.messages);
            });
    }

    $scope.prepareProductForUpdate();
});