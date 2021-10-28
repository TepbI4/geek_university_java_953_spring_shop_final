angular.module('front-shop').controller('cartController', function($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/cart';

    $scope.loadCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/' + $localStorage.cartId,
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.removeItem = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/' + $localStorage.cartId + '/remove/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart()
        })
    }

    $scope.decrementItem = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/' + $localStorage.cartId + '/decrease/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart()
        })
    }

    $scope.incrementItem = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/' + $localStorage.cartId + '/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart()
        })
    }

    $scope.checkOut = function () {
        $location.path("/order_confirmation");
    }

    $scope.disabledCheckOut = function () {
        alert("Need to log in for check out");
    }

    $scope.loadCart();
});