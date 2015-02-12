(function () {
    var app = angular.module('userInfo', []);

    app.controller('UserInfoCtrl', function($scope,$http) {

            $('.datepicker').datepicker({
                autoclose: true,
                clearBtn:true,
                todayHighlight: true,
                language: "ru"
            }).datepicker("setDate", new Date());

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