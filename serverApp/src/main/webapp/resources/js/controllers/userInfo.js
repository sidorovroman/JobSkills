(function () {
    var app = angular.module('userInfo', []);

    app.controller('UserInfoCtrl', function($scope,$http) {

            $scope.save = function (info, userInfoForm) {
                if (info.pass.equals(info.pass2)) {
                    alert("пароли не совпадают");
                }
                if (userInfoForm.$valid) {
                    // действия по сохранению
                    alert(info.login + ", всё ништяк");
                }
            };
            $scope.logout = function () {
                $http.get('/users/logout').
                    success(function (data) {
                        console.log('logout success:'+data);
                    }).
                    error(function () {
                        console.log('logout Fail:');
                    });

            };
        }
    )
})();