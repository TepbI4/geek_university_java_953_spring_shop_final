angular.module('front-shop').controller('cartController', function($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/shop';

    $scope.loadCart = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.removeItem = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/remove/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart()
        })
    }

    $scope.decrementItem = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/decrease/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart()
        })
    }

    $scope.incrementItem = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart()
        })
    }

    $scope.checkOut = function () {
        $location.path("/order_confirmation");
    }

    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    }

    $scope.loadCart();
});