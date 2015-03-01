(function () {
    angular.module('App.controllers', ['login','jobs','news','skills','skillWays','userInfo','loginModal'])

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
