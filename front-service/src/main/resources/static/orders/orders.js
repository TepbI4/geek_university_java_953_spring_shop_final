angular.module('front-shop').controller('ordersController', function ($scope, $http) {
    const contextPath = 'http://localhost:5555';

    $scope.loadOrders = function () {
        $http({
            url: contextPath + '/core/api/v1/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.orders = response.data;
            console.log($scope.orders);
        });
    };

    $scope.loadOrders();
});