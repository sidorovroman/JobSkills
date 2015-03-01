(function () {
    var app = angular.module('userInfo', []);

    app.controller('UserInfoCtrl', function ($scope, $http) {
            $scope.logout = function () {
                $http.get('/users/logout').
                    success(function (data) {
                        console.log('logout success:' + data);
                    }).
                    error(function () {
                        console.log('logout Fail:');
                    });

            };
        }
    )
})();