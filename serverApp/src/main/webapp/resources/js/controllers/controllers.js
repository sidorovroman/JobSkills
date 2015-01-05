(function () {
    angular.module('App.controllers', ['jobs'])

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
        .controller("NewsListCtrl", function ($scope, $http) {
            $http.get('/news/list').
                success(function (data) {
                    $scope.news = data;
                }).
                error(function () {
                    alert("Fail");
                });
        })
        .controller("SkillsListCtrl", function ($scope, $http) {
            $http.get('/skills/list').
                success(function (data) {
                    $scope.skills = data;
                }).
                error(function () {
                    alert("Fail");
                });
        })
        .controller("WaysToImproveSkillsListCtrl", function ($scope, $http) {
            $http.get('/waysToImproveSkills/list').
                success(function (data) {
                    $scope.ways = data;
                }).
                error(function () {
                    alert("Fail");
                });
        })
})();
