angular.module('front-index',[]).controller('indexController', function($scope, $http) {
    const contextPath = 'http://localhost:8189/shop/product'

    $scope.pageIndex = 1;
    $scope.totalPages;

    $scope.loadProducts = function () {
        $http({
            url: contextPath,
            method: 'GET',
            params : {
                page: $scope.pageIndex
            }
        }).then(function (response) {
            $scope.totalPages = response.data.totalPages;
            $scope.productsPage = response.data;
        })
    }

    $scope.deleteProduct = function (id) {
        $http({
            url: contextPath + /delete/ + id,
            method: 'GET'
        }).then(function (response) {
            $scope.loadProducts()
        })
    }

    $scope.prevPage = function () {
        if ($scope.pageIndex === 1) {
            $scope.pageIndex = 1
        } else {
            $scope.pageIndex -= 1
        }
        $scope.loadProducts()
    }

    $scope.nextPage = function () {
        if ($scope.pageIndex === $scope.totalPages) {
            $scope.pageIndex = $scope.totalPages
        } else {
            $scope.pageIndex += 1
        }
        $scope.loadProducts()
    }

    $scope.loadProducts();
});