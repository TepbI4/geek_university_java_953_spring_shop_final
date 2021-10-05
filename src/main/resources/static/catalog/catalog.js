angular.module('front-shop').controller('catalogController', function($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/shop';

    $scope.pageIndex = 1;

    $scope.loadProducts = function () {
        $http({
            url: contextPath + '/api/v1/products',
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
            url: contextPath + '/' + id,
            method: 'DELETE'
        }).then(function (response) {
            $scope.loadProducts()
        })
    }

    $scope.decreasePrice = function (p) {
        if (p.price - 1 > 0) {
            $http({
                url: contextPath,
                method: 'PUT',
                data: {
                    id: p.id,
                    title: p.title,
                    price: p.price - 1
                }
            }).then(function (response) {
                $scope.loadProducts()
            })
        }
    }

    $scope.increasePrice = function (p) {
        $http({
            url: contextPath,
            method: 'PUT',
            data: {
                id: p.id,
                title: p.title,
                price: p.price + 1
            }
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

    $scope.navToEditProductPage = function (productId) {
        $location.path('/edit_product/' + productId);
    }

    $scope.addToCart = function (productId) {
        $http.get(contextPath + '/api/v1/cart/' + $localStorage.cartId + '/add/' + productId);
    }

    $scope.loadProducts();
});