(function () {
    var app = angular.module('userInfo', []);

    app.controller('UserInfoCtrl', function ($scope, $http) {
            $.material.init();

            $http.get('/user/info').
                success(function (data) {
                    $scope.info = data;

                    console.log('update success:' + data);
                }).
                error(function () {
                    console.log('update Fail:');
                });

            $scope.save = function () {
                var dataObj = {
                    id: $scope.info.id,
                    userFullName: $scope.info.userFullName,
                    email: $scope.info.email,
                    userName: $scope.info.userName,
                    sex: $scope.info.sex,
                    birthday: $scope.info.birthday,
                    phone: $scope.info.phone,
                    city: $scope.info.city,
                    country: $scope.info.country
                };
                $http.post('/user/update',dataObj).
                    success(function (data) {
                        console.log('update success:' + data);
                    }).
                    error(function () {
                        console.log('update Fail:');
                    });

            };
        }
    )
})();