(function () {
    var app = angular.module('login', []);

    app.controller("LoginCtrl", function ($scope, $location, $http, $routeParams) {
        $scope.LoginForm = {};
        $scope.reg = function () {
            console.log("reg");

            var dataObject = {
                email: this.LoginForm.email,
                password: this.LoginForm.password
            };

            $http.post('/register',dataObject).
                success(function (data) {
                    console.log("успех");
                }).
                error(function (data) {
                    console.log("Fail");
                });
        }
    });
})();