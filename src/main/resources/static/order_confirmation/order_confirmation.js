angular.module('front-shop').controller('orderConfirmationController', function($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/shop';

    $scope.loadCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/' + $localStorage.cartId,
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.createOrder = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'POST',
            data: $scope.orderDetails
        }).then(function (response) {
            alert('Your order has been successfully created');
            $location.path('/');
        });
    };

    $scope.loadCart();
});