angular.module('App.controllers', []).

    controller('UserInfo',
    function UserInfo($scope) {
        $scope.save = function (info, userInfoForm) {
            if(info.pass.equals(info.pass2)){
                alert("пароли не совпадают");
            }
            if (userInfoForm.$valid) {
                // действия по сохранению
                alert(info.login + ", всё ништяк");
            }
        };
    }
);