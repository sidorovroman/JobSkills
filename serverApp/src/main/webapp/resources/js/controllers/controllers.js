(function () {
    angular.module('App.controllers', ['jobs','news','skills','skillWays'])

        .controller('UserInfo', function UserInfo($scope) {
            $scope.save = function (info, userInfoForm) {
                if (info.pass.equals(info.pass2)) {
                    alert("пароли не совпадают");
                }
                if (userInfoForm.$valid) {
                    // действия по сохранению
                    alert(info.login + ", всё ништяк");
                }
            };
        }
    )
        .controller("DashboardListCtrl", function ($scope, $http) {
            $http.get('/dashboard/list').
                success(function (data) {
                    $scope.dashboard = data;
                }).
                error(function () {
                    alert("Fail");
                });
        })


})();
