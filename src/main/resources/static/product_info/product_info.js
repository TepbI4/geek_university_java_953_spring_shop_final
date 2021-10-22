angular.module('front-shop').controller('productInfoController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://localhost:8189/shop';

    $scope.loadProduct = function () {
        $http({
            url: contextPath + '/api/v1/products/' + $routeParams.productId,
            method: 'GET'
        }).then(function (response) {
            $scope.product = response.data;
        });
    };

    $scope.createComment = function () {
        $http({
            url: contextPath + '/api/v1/comments',
            method: 'POST',
            data: {
                productId: $scope.product.id,
                comment: $scope.productComment
            }
        }).then(function (response) {
            alert('Your comment has been successfully created');
            $scope.productComment = null;
            $scope.loadProduct();
            $scope.loadComments();
            $location.path('/product_info/' + $scope.product.id);
        });
    };

    $scope.loadComments = function () {
        $http({
            url: contextPath + '/api/v1/comments/' + $routeParams.productId,
            method: 'GET'
        }).then(function (response) {
            $scope.comments = response.data;
        });
    };

    $scope.checkProductOrderedByUser = function () {
        $http({
            url: contextPath + '/api/v1/comments/' + $routeParams.productId + '/check',
            method: 'GET'
        }).then(function (response) {
            $scope.productOrderedByUser = response.data;
        });
    };

    $scope.loadProduct();
    $scope.loadComments();
    $scope.checkProductOrderedByUser();
});