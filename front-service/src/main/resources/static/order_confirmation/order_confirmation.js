angular.module('front-shop').controller('orderConfirmationController', function($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555';

    $scope.loadCart = function () {
        $http({
            url: contextPath + '/cart/api/v1/cart/' + $localStorage.cartId,
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.createOrder = function () {
        $http({
            url: contextPath + '/core/api/v1/orders',
            method: 'POST',
            data: $scope.orderDetails
        }).then(function (response) {
            alert('Your order has been successfully created');
            var orderId = response.data.value;
            $location.path('/order_pay/' + orderId);
        });
    };

    $scope.loadCart();
});