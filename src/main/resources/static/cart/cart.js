angular.module('front-shop').controller('cartController', function($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/shop';

    $scope.pageIndex = 1;

    $scope.loadItems = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.cartItems = response.data;
        });
    }

    $scope.deleteItem = function (id) {
        $http({
            url: contextPath + '/' + id,
            method: 'DELETE'
        }).then(function (response) {
            $scope.loadProducts()
        })
    }

    $scope.loadItems();
});