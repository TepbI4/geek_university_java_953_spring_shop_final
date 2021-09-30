angular.module('front-shop').controller('registrationController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/shop';

    $scope.createUser = function () {
        if ($scope.new_user == null) {
            alert('Data not filled');
            return;
        }
        $http.post(contextPath + '/register', $scope.new_user)
            .then(function successCallback (response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webMarketUser = {username: $scope.new_user.username, token: response.data.token};
                }
                alert('User successfully created');
                $scope.new_user = null;
                $location.path('/catalog');
            }, function failureCallback (response) {
                console.log(response);
                alert(response.data.messages);
            });
    }
});