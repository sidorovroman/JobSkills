(function () {
    var app = angular.module('loginModal', []);

    app.controller('ModalLoginCtrl', function ($scope, $http, $location) {
        $scope.login = function () {

            var dataObject = {
                mail: $scope.mail,
                password: $scope.password
            };

            $http.post('/j_spring_security_check',dataObject)
                .success(function(response){
                    if(response.error==null){
                        console.log("login success");
                    }else{
                        alert(response.error);
                    }
                });
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