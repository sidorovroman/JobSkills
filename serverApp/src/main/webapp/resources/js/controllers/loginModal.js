(function () {
    var app = angular.module('loginModal', []);

    app.controller('ModalLoginCtrl', function ($scope, $http, $location) {
        $scope.login = function () {
            $.post(
                "/j_spring_security_check",
                {
                    mail:$scope.mail,
                    password: $scope.password
                },
                function(data){
                    console.log("post success");
                    if(data.error==null){
                        console.log("login success");
                    }else{
                        alert(data.error);
                    }
                }
            );
        };
        $scope.register = function () {
            $.post(
                "/register",
                {
                    mail:$scope.mail,
                    password: $scope.password
                },
                function(data){
                    console.log("post success");
                    if(data.error==null){
                        console.log("register success");
                    }else{
                        alert(data.error);
                    }
                }
            );
        };
    })

})();